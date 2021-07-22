package com.lm.dorm.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class ConnectionFatory {
	//��ȡ����Դ���Զ����������ļ�
	private static DataSource dataSource = new ComboPooledDataSource();
	//��ȡ����
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
