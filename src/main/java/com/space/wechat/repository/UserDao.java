package com.space.wechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.space.wechat.entity.User;

public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

	@Modifying
	@Query(value = " DELETE FROM user WHERE id=?1 ", nativeQuery = true)
	void delete(String id);

	@Modifying
	@Query(value = " DELETE FROM role_user WHERE userid=?1 ", nativeQuery = true)
	void deleteRoleUser(String userid);

	/**
	 * 查询所有用户列表.
	 */
	@Query(value = " SELECT * FROM user ORDER BY name", nativeQuery = true)
	List<User> findAll();

	User findById(String id);

	User findByLoginName(String loginName);

	@Query(value = " SELECT u.id AS userid, r.`name`, r.id AS roleid FROM role r "
			+ " INNER JOIN role_user ru ON ru.roleid = r.id " + " INNER JOIN `user` u ON u.id = ru.userid "
			+ " WHERE u.login_name LIKE ?1 OR u.`name` LIKE ?1 ", nativeQuery = true)
	List<Object[]> findRoleUserList(String nameLike);

	/**
	 * 查询用户列表.
	 */
	@Query(value = " SELECT * FROM `user` WHERE login_name LIKE ?1 OR `name` LIKE ?1 ", nativeQuery = true)
	List<User> findUserList(String nameLike);

	@Modifying
	@Query(value = " INSERT INTO role_user(id,roleid,userid) VALUES (?1,?2,?3) ", nativeQuery = true)
	void insertRoleUser(String id, String roleid, String userid);

	/**
	 * 更新用户信息.
	 * 
	 * @param id
	 * @param name
	 *            姓名
	 * @param password
	 *            密码
	 * @param salt
	 * @param avatar
	 *            头像
	 * @param debug_url
	 *            调试地址
	 */
	@Modifying
	@Query(value = " UPDATE `user` SET `name` = ?2 ,`password` = ?3 ,salt = ?4 ,avatar = ?5 ,debug_url = ?6 WHERE id = ?1 ", nativeQuery = true)
	void updateById(String id, String name, String password, String salt, String avatar, String debug_url);

	@Modifying
	@Query(value = " DELETE FROM user WHERE id=?1 ", nativeQuery = true)
	void updateUserProject();

	@Modifying
	@Query(value = " INSERT INTO role_user(id,roleid,userid) VALUES (?1,?2,?3) ", nativeQuery = true)
	public void updateUrl(String userid, String debugUrl, String loginName, String password);

	@Query(value = " SELECT * FROM `user` WHERE developer_type LIKE ?1 ORDER BY name", nativeQuery = true)
	public List<User> findByDeveloperType(String type);

	@Modifying
	@Query(value = " UPDATE `user` SET  debug_url = ?2 WHERE id = ?1 ", nativeQuery = true)
	void updateDebugUrl(String id, String debug_url);

	@Query(value = " SELECT id,name FROM wx_xx_dls WHERE name LIKE ?1", nativeQuery = true)
	public List<Object[]> findDlsByName(String name);

	@Query(value = " SELECT id,name FROM wx_xx_dls WHERE NOT name LIKE '%退出' ORDER BY id", nativeQuery = true)
	public List<Object[]> findDls();

	@Query(value = " SELECT id FROM wx_xx_dls WHERE loginname = ?1", nativeQuery = true)
	public List<Long> findDlsByLoginname(String loginname);

	@Query(value = " SELECT name FROM wx_xx_dls WHERE id = ?1", nativeQuery = true)
	public String findDlsById(Long id);

	@Query(value = "select * from user where developer_type is not null order by developer_type desc", nativeQuery = true)
	public List<User> findAllDevelopers();

}
