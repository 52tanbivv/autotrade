/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.space.wechat.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author hbz
 */
public abstract class AbstractService<T> {

	private static Logger logger = LoggerFactory
			.getLogger(AbstractService.class);

	private Class<T> entityClass;

	public AbstractService(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	@Transactional("transactionManagerJpa")
	public void create(T entity) {

		try {
			getEntityManager().persist(entity);
		} catch (ConstraintViolationException e) {
			logger.error("发生异常！");
			logger.error(e.getMessage());
		}

	}

	@Transactional("transactionManagerJpa")
	public void edit(T entity) {
		getEntityManager().merge(entity);
	}

	@Transactional("transactionManagerJpa")
	public void remove(T entity) {
		try {
			entity = getEntityManager().merge(entity);
			getEntityManager().remove(entity);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	public T find(Object id) {

		try {
			return getEntityManager().find(entityClass, id);
		} catch (ConstraintViolationException e) {
			logger.error("发生异常！");
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 传入Po，通过Po的属性进行匹配查询，数字型字段精确查询，字符型字段模糊查询
	 * 
	 * @param entityClass
	 * @return
	 */

	public List<T> findByPo(T entityClass, String orderByCondition) {
		try {

			StringBuilder jql = new StringBuilder();
			jql.append("select p from ")
					.append(entityClass.getClass().getSimpleName())
					.append(" p ");
			String andStr = " ";
			Field[] fields = entityClass.getClass().getDeclaredFields();

			List<Object> parameters = new ArrayList<Object>();
			int count = 0;
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				Type type = fields[i].getType();
				String typeString = type.toString();
				if (type.equals(java.lang.Integer.class)
						|| type.equals(java.lang.Long.class)
						|| type.equals(java.lang.String.class)) {
					Object value = this.getMethod(entityClass.getClass(), name)
							.invoke(entityClass);

					if (notBlank(value)) {
						String condition = "p." + name + "=?";

						// 字符串采用 like '%a%'的形式进行模糊查询
						if (type.equals(java.lang.String.class)) {
							condition = "p." + name + " like ?";
							value = "%" + value.toString().trim() + "%";
						}

						logger.info(" condition = " + condition);
						count = this.checkIsNullOrConvertJpql(jql, value,
								count, condition + (count + 1), parameters);

					}
				} else if (typeString.indexOf("com.bsd.edump.entity") > -1) {
					Object js = this.getMethod(entityClass.getClass(), name)
							.invoke(entityClass);
					if (js == null) {
						continue;
					}
					Field[] jsfields = js.getClass().getDeclaredFields();

					for (int j = 0; j < jsfields.length; j++) {
						String jsname = jsfields[j].getName();
						Type jstype = jsfields[j].getType();

						if (jstype.equals(java.lang.Integer.class)
								|| jstype.equals(java.lang.Long.class)
								|| jstype.equals(java.lang.String.class)) {
							Object jsvalue = this.getMethod(js.getClass(),
									jsname).invoke(js);

							if (notBlank(jsvalue)) {
								String condition = "p." + name + "." + jsname
										+ "=?";

								// 字符串采用 like '%a%'的形式进行模糊查询
								if (jstype.equals(java.lang.String.class)) {
									condition = "p." + name + "." + jsname
											+ " like ?";
									jsvalue = "%" + jsvalue.toString().trim()
											+ "%";
								}

								count = this.checkIsNullOrConvertJpql(jql,
										jsvalue, count,
										condition + (count + 1), parameters);

							}
						}
					}
				}
			}
			logger.info(jql.toString());
			return this.find(((Class<T>) entityClass.getClass()
					.getGenericSuperclass()), jql.toString() + " "
					+ orderByCondition, parameters);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查询发生错误，错误原因：" + e.getMessage());
		}
	}

	private boolean notBlank(Object value) {
		return value != null && !"all".equals(value)
				&& (!value.toString().equals(""))
				&& (!value.toString().equals("-1"));
	}

	private Method getMethod(Class _class, String fieldName)
			throws SecurityException, NoSuchMethodException {

		StringBuffer getCodeFldMethod = new StringBuffer();
		getCodeFldMethod.append("get")
				.append(fieldName.substring(0, 1).toUpperCase())
				.append(fieldName.substring(1, fieldName.length()));

		return _class.getDeclaredMethod(getCodeFldMethod.toString());
	}

	public List<T> findAll() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	public List<T> findRange(int[] range) {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
				.getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	public int checkIsNullOrConvertJpql(StringBuilder jpql, Object param,
			int count, String judge, List<Object> params) {
		if (param != null && (!param.toString().equals(""))) {
			if (count == 0) {
				jpql.append(" where ").append(judge);
			} else {
				jpql.append(" and ").append(judge);
			}
			params.add(param);
			return ++count;
		}
		return count;
	}

	public List<T> find(Class<T> type, String query, List<Object> parameters) {
		return createQuery(type, query, parameters).getResultList();
	}

	public Query createQuery(Class<T> type, String query,
			List<Object> parameters) {
		Query typedQuery = getEntityManager().createQuery(query, type);
		for (int i = 0; i < parameters.size(); i++) {
			if (parameters.get(i) instanceof Date) {
				typedQuery.setParameter(i + 1, (Date) parameters.get(i),
						TemporalType.DATE);
			} else {
				typedQuery.setParameter(i + 1, parameters.get(i));
			}
		}
		return typedQuery;
	}

	/**
	 * 创建分页请求.
	 */
	// public PageRequest buildPageRequest(int pageNumber, int pagzSize,
	// String sortType) {
	// Sort sort = new Sort(Direction.DESC, "id");
	// // if ("auto".equals(sortType)) {
	// // sort = new Sort(Direction.DESC, "id");
	// // } else if ("xh".equals(sortType)) {
	// // sort = new Sort(Direction.ASC, "xh");
	// // }
	//
	// return new PageRequest(pageNumber - 1, pagzSize, sort);
	// }
	//
	// /**
	// * 创建动态查询条件组合.
	// */
	// public Specification<T> buildSpecification(
	// Map<String, Object> searchParams) {
	// Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
	// Specification<T> spec = DynamicSpecifications.bySearchFilter(
	// filters.values(),entityClass);
	// return spec;
	// }
}
