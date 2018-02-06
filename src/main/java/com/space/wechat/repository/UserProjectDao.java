package com.space.wechat.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.space.wechat.entity.UserProject;

public interface UserProjectDao extends PagingAndSortingRepository<UserProject, String>,
		JpaSpecificationExecutor<UserProject> {
	
	@Modifying
	@Query(value = " DELETE FROM user_project WHERE userid = ?1 ", nativeQuery = true)
	void deleteUserProject(String userid);
	
	/**
	 * 重置user_project表中的default_state字段为0.
	 * 
	 * @param userid
	 */
	@Modifying
	@Query(value = " UPDATE user_project SET default_state = 0 WHERE userid = ?1 AND default_state = 1 ", nativeQuery = true)
	void resetDefaultState(String userid);
	
	/**
	 * 更新user_project表中的default_state字段.
	 * 
	 * @param userid
	 * @param projectid
	 * @param defaultState
	 */
	@Modifying
	@Query(value = " UPDATE user_project SET default_state = ?3 WHERE userid = ?1 AND projectid = ?2 ", nativeQuery = true)
	void updateDefaultState(String userid, String projectid,
			Integer defaultState);
}
