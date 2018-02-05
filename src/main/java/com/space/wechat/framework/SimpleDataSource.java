package com.space.wechat.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 一个简单的DataSource实现
 * 
 * @author leizhimin 2010-1-14 0:03:17
 */
public class SimpleDataSource {
	private static Log log = LogFactory.getLog(SimpleDataSource.class);
	private static String dirverClassName = "com.mysql.jdbc.Driver";
	private static String url;
	private static String user;
	private static String pswd;

	public static void initConn(String _url, String _user, String _pwd) {
		url = _url;
		user = _user;
		pswd = _pwd;
	}

	// 连接池
	private static List<Connection> pool = (List<Connection>) Collections
			.synchronizedList(new LinkedList<Connection>());
	private static SimpleDataSource instance = new SimpleDataSource();

	static {
		try {
			Class.forName(dirverClassName);
		} catch (ClassNotFoundException e) {
			log.error("找不到驱动类！", e);
		}
	}

	private SimpleDataSource() {
	}

	/**
	 * 获取数据源单例
	 * 
	 * @return 数据源单例
	 */
	public static SimpleDataSource instance() {
		if (instance == null)
			instance = new SimpleDataSource();
		return instance;
	}

	/**
	 * 获取一个数据库连接
	 * 
	 * @return 一个数据库连接
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {

		return makeConnection();

	}

	private Connection makeConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pswd);
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}