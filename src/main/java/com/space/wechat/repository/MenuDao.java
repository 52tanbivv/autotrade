package com.space.wechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.space.wechat.entity.role.Menu;

public interface MenuDao extends PagingAndSortingRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
	@Query(value = "SELECT m.* FROM menu m" + " INNER JOIN role_menu rm ON m.id=rm.menuid "
			+ " INNER JOIN role_user ru ON rm.roleid=ru.roleid "
			+ "  WHERE ru.userid = ?1 ORDER BY m.sort ", nativeQuery = true)
	public List<Menu> findByUser(String userid);

}
