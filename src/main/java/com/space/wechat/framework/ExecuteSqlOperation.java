package com.space.wechat.framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.statement.IPreparedBatchStatement;
import org.dbunit.database.statement.IStatementFactory;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.space.wechat.util.StringUtil;

public class ExecuteSqlOperation extends DatabaseOperation {
	private List<String> sqlList;
	private static final Logger logger = LoggerFactory
			.getLogger(ExecuteSqlOperation.class);

	public ExecuteSqlOperation(List<String> _sqlList) {
		this.sqlList = _sqlList;
	}

	public ExecuteSqlOperation() {
		// TODO Auto-generated constructor stub
	}

	public void executeSql(List<String> _sqlList) {

		try {
			this.setSqlList(_sqlList);
			this.execute(DataFixtures.getConnection(), null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List executeSqlQuery(String querySql) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			SimpleDataSource dataSource = SimpleDataSource.instance();

			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			System.out.println("executeSqlQuery querySql=" + querySql);
			rs = stmt.executeQuery(querySql);
			List result = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData(); // 取得数据表中的字段数目，类型等返回结果
			// 是以ResultSetMetaData对象保存
			int columnCount = rsmd.getColumnCount(); // 列的总数

			while (rs.next()) {
				Object[] objs = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					String field = rsmd.getColumnName(i);
					objs[i - 1] = rs.getObject(field);
				}
				result.add(objs);
			}
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("数据库异常:" + e.getMessage());
		}

		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}

	public void executeSql(String... sqls) {

		try {
			List<String> sqllist = new ArrayList<String>();
			for (String sql : sqls) {
				if (StringUtil.isNotEmpty(sql)) {
					sqllist.add(sql);
				}
			}
			this.setSqlList(sqllist);
			this.execute(DataFixtures.getConnection(), null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void execute(IDatabaseConnection connection, IDataSet dataSet)
			throws DatabaseUnitException, SQLException {
		logger.debug("execute(connection={}, dataSet={}) - start", connection,
				dataSet);

		DatabaseConfig databaseConfig = connection.getConfig();
		IStatementFactory factory = (IStatementFactory) databaseConfig
				.getProperty(DatabaseConfig.PROPERTY_STATEMENT_FACTORY);

		IPreparedBatchStatement statement = null;

		try {
			for (String sql : sqlList) {
				System.out.println("sql=" + sql);
				statement = factory.createPreparedBatchStatement(sql,
						connection);
				statement.addBatch();
			}

			statement.executeBatch();
			statement.clearBatch();

			// clear schema and sql
		}

		finally {
			if (statement != null) {
				statement.close();
			}
		}

	}

	public void setSqlList(List<String> _sqlList) {
		this.sqlList = _sqlList;
	}
}
