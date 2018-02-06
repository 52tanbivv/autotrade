package com.space.wechat.service.kindergarten.xtgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.alibaba.fastjson.JSONObject;
import com.space.wechat.entity.Project;
import com.space.wechat.entity.User;
import com.space.wechat.entity.UserProject;
import com.space.wechat.entity.role.Role;
import com.space.wechat.repository.ProjectDao;
import com.space.wechat.repository.RoleDao;
import com.space.wechat.repository.UserDao;
import com.space.wechat.repository.UserProjectDao;
import com.space.wechat.util.DateUtil;
import com.space.wechat.util.StringUtil;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional(readOnly = true)
public class AccountService {

	public static Map<String, User> tapdUserMap = new HashMap<String, User>();
	public static Map<String, User> userMap = new HashMap<String, User>();

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserProjectDao userProjectDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private RoleDao roleDao;

	public Map<String, User> initTapdUserMap() {
		Map<String, User> result = new HashMap<String, User>();
		List<User> allUser = userDao.findAll();
		for (User user : allUser) {
			userMap.put(user.getId(), user);
			if (user.getTapdUsername() != null && !user.getTapdUsername().isEmpty()) {
				String[] usernames = user.getTapdUsername().split(",");
				for (String username : usernames) {
					result.put(username, user);
				}
			}
		}
		return result;
	}

	public List<User> list(JSONObject params) {
		String nameLike = StringUtil.getNullStrLike(params.getString("userStr"));
		// User user = (User) params.get("currentUser");

		List<User> userList = userDao.findUserList(nameLike);

		if (userList != null && userList.size() > 0) {
			Map<String, String> userRoleMap = getRoleMap(nameLike);
			for (User user : userList) {
				if (userRoleMap.containsKey(user.getId())) {
					user.setRoleName(userRoleMap.get(user.getId()));
				}
			}
		}

		return userList;
	}

	public static String translateTapdUser(String userName) {
		if (userName != null) {
			if (userName.startsWith(";")) {
				userName = userName.substring(1, userName.length());
			}
			String[] owners = userName.split(";");
			User user = AccountService.tapdUserMap.get(owners[0]);
			if (user != null) {
				return user.getId();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public static User getTapdUser(String userName) {
		if (userName != null) {
			if (userName.startsWith(";")) {
				userName = userName.substring(1, userName.length());
			}
			String[] owners = userName.split(";");
			return AccountService.tapdUserMap.get(owners[0]);
		} else {
			return null;
		}
	}

	private Map<String, String> getRoleMap(String nameLike) {
		List<Object[]> userRoleList = userDao.findRoleUserList(nameLike);

		Map<String, String> userRoleMap = new HashMap<String, String>();
		if (userRoleList != null && userRoleList.size() > 0) {
			for (Object[] userRole : userRoleList) {
				userRoleMap.put(StringUtil.getNullStr(userRole[0]), StringUtil.getNullStr(userRole[1]));
			}
		}

		return userRoleMap;
	}

	/**
	 * 根据ID查询User信息：user_find
	 * 
	 * @param params
	 * @return
	 */
	public User find(JSONObject params) {
		String userid = StringUtil.getNullStr(params.getString("userid"));
		User user = findOne(userid);

		Role role = roleDao.findByUserid(user.getId());
		user.setAdmin(role.getName().equals("admin"));
		user.setDesigner(role.getName().equals("designer"));
		user.setBusiness(role.getName().equals("business"));
		user.setTester(role.getName().equals("tester"));
		user.setDeveloper(role.getName().equals("developer"));
		return user;
	}

	public Role findRole(String userid) {
		return roleDao.findByUserid(userid);
	}

	public User findOne(String userid) {
		User userItem = userDao.findOne(userid);

		if (userItem != null) {
			// 查询项目列表
			List<Project> projectList = projectDao.findProjectByUserid(userItem.getId());
			userItem.setProjectList(projectList);

			// 查询用户角色
			Role role = roleDao.findByUserid(userItem.getId());
			userItem.setRole(role);
		}

		return userItem;
	}

	public User findcurrentuser(JSONObject params) {
		User user = (User) params.get("currentUser");
		Role role = roleDao.findByUserid(user.getId());
		user.setAdmin(role.getName().equals("admin"));
		user.setDesigner(role.getName().equals("designer"));
		user.setBusiness(role.getName().equals("business"));
		user.setTester(role.getName().equals("tester"));
		user.setDeveloper(role.getName().equals("developer"));
		return user;

	}

	public User getUser(String id) {
		return userDao.findOne(id);
	}

	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	/**
	 * 接口：user_delete（删除用户信息）
	 * 
	 * @param params
	 * @return
	 */
	public String delete(JSONObject params) {
		String id = StringUtil.getNullStr(params.getString("id"));
		User user = (User) params.get("currentUser");

		userDao.delete(id);

		// 删除user_project关联表
		userProjectDao.deleteUserProject(user.getId());
		// 删除role_user关联表
		userDao.deleteRoleUser(user.getId());

		return "删除成功！";
	}

	/**
	 * 接口：user_save（用户信息保存）
	 * 
	 * @param params
	 * @return
	 */
	public String save(JSONObject params) {
		logger.info(
				"!!!!!!!!!!!!!!!! WARN SAVE USER " + params.getString("name") + " params is " + params.toJSONString());
		String id = StringUtil.getNullStr(params.getString("id"));

		String result = "";
		if (StringUtil.isNullOrEmpty(id)) {
			result = insertUser(params);
		} else {
			result = updateUser(params);
		}

		return result;
	}

	@Transactional(readOnly = false)
	private String insertUser(JSONObject params) {
		String name = StringUtil.getNullStr(params.getString("name"));
		String loginName = StringUtil.getNullStr(params.get("loginName"));
		String plainPassword = StringUtil.getNullStr(params.get("plainpassword"));
		JSONObject role = (JSONObject) params.get("role");
		String avatar = StringUtil.getNullStr(params.get("avatar"));
		String debugUrl = StringUtil.getNullStr(params.get("debugUrl"));
		String remark = StringUtil.getNullStr(params.get("remark"));
		String projectid = StringUtil.getNullStr(params.get("projectid"));

		User user = userDao.findByLoginName(loginName);
		if (user != null) {
			throw new RuntimeException("该邮箱已注册！");
		}

		user = new User();
		user.setId(StringUtil.guid());
		user.setName(name);
		user.setLoginName(loginName);
		user.setAvatar(avatar);
		user.setDebugUrl(debugUrl);
		user.setRemark(remark);
		user.setBlock(false);
		user.setCurrentProject(projectid);
		entryptPassword(user, plainPassword);
		user.setTapdUsername(name);

		user = userDao.save(user);

		if (projectid != null && !projectid.isEmpty()) {
			// 设置用户和项目的关联
			saveUserProject(user.getId(), projectid);
		}
		// 设置用户和角色关联
		saveUserRole(user.getId(), role.getString("id"));

		return "保存成功！";

	}

	@Transactional(readOnly = false)
	private void saveUserProject(String userid, String projectid) {
		// List<String> projectIds =
		// StringUtil.getNullStrToStringList(projects);

		// 清除历史关联
		userProjectDao.deleteUserProject(userid);
		// [["1","2"]]
		UserProject userProject = new UserProject();
		userProject.setId(StringUtil.guid());
		userProject.setUserid(userid);
		userProject.setProjectid(projectid);
		userProject.setDefaultState(UserProject.DEFAULT_STATE_FALSE);
		userProject.setCreateTime(DateUtil.getCurrentTimeDate());
		userProjectDao.save(userProject);

	}

	private void saveUserRole(String userid, String roleid) {
		if (StringUtil.isNotEmpty(roleid)) {
			// 清除历史关联
			userDao.deleteRoleUser(userid);

			String id = StringUtil.guid();
			userDao.insertRoleUser(id, roleid, userid);
		}
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 * 
	 * @param user
	 * @param plainPassword
	 */
	public void entryptPassword(User user, String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	private String updateUser(JSONObject params) {
		String id = StringUtil.getNullStr(params.get("id"));
		String name = StringUtil.getNullStr(params.getString("name"));
		String avatar = StringUtil.getNullStr(params.get("avatar"));
		// String email = StringUtil.getNullStr(params.get("email"));
		String debugUrl = StringUtil.getNullStr(params.get("debugUrl"));
		String plainPassword = StringUtil.getNullStr(params.get("plainpassword"));
		String projectid = StringUtil.getNullStr(params.getString("projectid"));
		JSONObject role = (JSONObject) params.get("role");

		String password = "";
		String salt = "";
		if (StringUtil.isNullOrEmpty(plainPassword)) {
			User user = userDao.findById(id);
			password = user.getPassword();
			salt = user.getSalt();
		} else {
			byte[] saltValue = Digests.generateSalt(SALT_SIZE);
			byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), saltValue, HASH_INTERATIONS);
			password = Encodes.encodeHex(hashPassword);
			salt = Encodes.encodeHex(saltValue);
		}

		userDao.updateById(id, name, password, salt, avatar, debugUrl);

		if (projectid != null && !projectid.isEmpty()) {
			// 设置用户和项目的关联
			saveUserProject(id, projectid);
		}
		// 设置用户和角色关联
		saveUserRole(id, role.getString("id"));

		return "保存成功！";
	}

	/**
	 * 接口：user_setCurrentProject（设置用户当前项目）
	 * 
	 * @param params
	 * @return
	 */
	public String setCurrentProject(JSONObject params) {
		String projectid = StringUtil.getNullStr(params.getString("projectid"));
		User user = (User) params.get("currentUser");

		// 清除之前设置的当前项目信息
		userProjectDao.resetDefaultState(user.getId());

		// 设置当前项目
		userProjectDao.updateDefaultState(user.getId(), projectid, UserProject.DEFAULT_STATE_TRUE);
		user.setCurrentProject(projectid);
		userDao.save(user);
		return "设置成功！";
	}

	public List<User> findDevelopers(JSONObject params) {
		return userDao.findByDeveloperType("%" + params.getString("developerType") + "%");
	}

}
