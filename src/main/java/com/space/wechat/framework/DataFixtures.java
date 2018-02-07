package com.space.wechat.framework;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class DataFixtures {

	private static Logger logger = LoggerFactory.getLogger(DataFixtures.class);
	public static String DBPropertiesFileName = "application.test.properties";
	private static IDatabaseConnection connection;

	public static String getUser() {

		return getDbProperties().getProperty("jdbc.username");
	}

	public static String getSchema() {

		return getDbProperties().getProperty("jdbc.schema").toUpperCase();
	}

	public static String getPassword() {

		return getDbProperties().getProperty("jdbc.password");
	}

	public static String getUrl() {

		return getDbProperties().getProperty("jdbc.url");
	}

	public static void main(String[] args) throws Exception {

		try {
			String sql = "select * FROM test_case_verify WHERE id='2'";
			System.out.println(JSON.toJSONString(new ExecuteSqlOperation()
					.executeSqlQuery(sql)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("数据发生错误，错误原因：" + e.getMessage());
		}

	}

	private static Properties getDbProperties() {
		try {
			InputStream in = new BufferedInputStream(DataFixtures.class
					.getClassLoader().getResourceAsStream(DBPropertiesFileName));
			Properties p = new Properties();

			p.load(in);
			return p;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("读取数据库文件发生错误");
		}

	}

	public static void setConnection(DatabaseConnection dbConn) {
		connection = dbConn;
	}

	public static IDatabaseConnection getConnection() {
		try {

			if (connection == null) {

				Class.forName(getDriver());

				Connection conn = DriverManager.getConnection(getUrl(),
						getUser(), getPassword());

				connection = new DatabaseConnection(conn);
			}

			return connection;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接异常，异常信息：" + e.getMessage());
		}
	}

	public static void colseConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {

			throw new RuntimeException("关闭数据库连接异常，异常信息：", e);
		}
	}

	public static void refreshConnection(String url, String user,
			String password) {
		try {
			Class.forName(getDriver());
			Connection conn = DriverManager.getConnection(url, user, password);

			logger.info(" in refreshConnection getUrl()=" + url + " getUser()="
					+ user);
			connection = new DatabaseConnection(conn);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接异常，异常信息：" + e.getMessage());
		}
	}

	public static void refreshConnection() {
		try {

			Class.forName(getDriver());

			Connection conn = DriverManager.getConnection(getUrl(), getUser(),
					getPassword());

			connection = new DatabaseConnection(conn);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接异常，异常信息：" + e.getMessage());
		}
	}

	public static String getDriver() {
		return getDbProperties().getProperty("jdbc.driver");
	}

	public static IDataSet getDataFromExcel(String excelResourcePath) {
		try {
			return new XlsDataSet(new FileInputStream(excelResourcePath));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("插入excel数据发生错误，错误原因：" + e.getMessage());
		}
	}
}
