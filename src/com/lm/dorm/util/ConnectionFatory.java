package com.lm.dorm.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class ConnectionFatory {
	//获取数据源，自动加载配置文件
	private static DataSource dataSource = new ComboPooledDataSource();
	//获取连接
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static  void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
			if (resultSet!=null){
				resultSet.close();
			}if (preparedStatement!=null){
				preparedStatement.close();
		}if (connection!=null){
				connection.close();
		}
	}
}
