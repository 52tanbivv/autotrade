package com.space.wechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.space.wechat.entity.Project;

public interface ProjectDao extends
		PagingAndSortingRepository<Project, String>,
		JpaSpecificationExecutor<Project> {

	/**
	 * 查询所有项目列表.
	 */
	@Query(value = " SELECT pro.* FROM project pro ", nativeQuery = true)
	List<Project> findAll();

	/**
	 * 根据用户ID查询项目列表.
	 * 
	 * @param userid
	 * @return
	 */
	@Query(value = " SELECT DISTINCT pro.* FROM project pro "
			+ " INNER JOIN user_project up ON up.projectid = pro.id "
			+ " INNER JOIN `user` u ON u.id = up.userid WHERE u.id = ?1  ORDER BY up.default_state desc,pro.create_time ", nativeQuery = true)
	List<Project> findProjectByUserid(String userid);

}
