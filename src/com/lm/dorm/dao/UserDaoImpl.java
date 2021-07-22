package com.lm.dorm.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.lm.dorm.bean.DormBuild;
import com.lm.dorm.bean.User;
import com.lm.dorm.util.ConnectionFatory;

public class UserDaoImpl implements UserDao {

	@Override
	public User findBystuCodeAndPass(String stuCode, String password) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = ConnectionFatory.getConnection();
		PreparedStatement preparedStatement =null;
		ResultSet rs = null;
				String sql = "select * from tb_user where stu_code=? and password=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,	stuCode);
			preparedStatement.setString(2,	password);
			
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setDisabled(rs.getInt("disabled"));
				user.setCeateUserId(rs.getInt("create_user_id"));
				user.setRoleId(rs.getInt("role_id"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setTel(rs.getString("tel"));
				user.setSex(rs.getString("sex"));
				user.setDormCode(rs.getString("dorm_code"));
				user.setStuCode(rs.getString("stu_code"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setId(rs.getInt("id"));
				return user;
				
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFatory.close(connection,preparedStatement,rs);
		}
		
		return null;
	}

	@Override
	public String findManagerStuCodeMax() throws SQLException {
		Connection connection = ConnectionFatory.getConnection();
		PreparedStatement preparedStatement =null;
		ResultSet rs = null;
		String sql = "select ifnull(Max(stu_code),2019018)+1 from tb_user where role_id=1";
		try {
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFatory.close(connection,preparedStatement,rs);
		}

		return null;
	}

	@Override
	public Integer saveManager(User user) throws SQLException {
		Connection connection = ConnectionFatory.getConnection();
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
				String sql = "insert into tb_user(name,password,stu_code,sex,tel,role_id,create_user_id,disabled) value(?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			System.out.println(user.getDisabled()+user.getName()+user.getPassword()+user.getStuCode()+user.getSex()+user.getTel()+user.getRoleId()+user.getCeateUserId());
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3,user.getStuCode());
			preparedStatement.setString(4,user.getSex());
			preparedStatement.setString(5,user.getTel());
			preparedStatement.setInt(6,user.getRoleId());
			preparedStatement.setInt(7,1);
			preparedStatement.setInt(8,user.getDisabled());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet==null){
				System.out.println("null?????");
			}
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFatory.close(connection,preparedStatement,resultSet);
		}

		return null;
	}

	@Override
	public List<User> findAll() throws SQLException {
		Connection connection = ConnectionFatory.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet rs =null;
		try {
			String sql = "select * from tb_user";

			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			List<User> users = new ArrayList<User>();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setStuCode(rs.getString("stu_code"));
				user.setDormCode(rs.getString("dorm_code"));
				user.setSex(rs.getString("sex"));
				user.setTel(rs.getString("tel"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setRoleId(rs.getInt("role_id"));
				user.setCeateUserId(rs.getInt("create_user_id"));
				user.setDisabled(rs.getInt("disabled"));
				users.add(user);
			}
			return users;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFatory.close(connection,preparedStatement,rs);
		}
		return null;
	}

	@Override
	public User find(Integer id) throws SQLException {
		Connection connection = ConnectionFatory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from tb_user where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setStuCode(rs.getString("stu_code"));
				user.setDormCode(rs.getString("dorm_code"));
				user.setSex(rs.getString("sex"));
				user.setTel(rs.getString("tel"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setRoleId(rs.getInt("role_id"));
				user.setCeateUserId(rs.getInt("create_user_id"));
				user.setDisabled(rs.getInt("disabled"));
				return user;

				//				dormBuild.setId(rs.getInt("id"));
//				dormBuild.setRemark(rs.getString("remark"));
//				dormBuild.setName(rs.getString("name"));
//				dormBuild.setDisabled(rs.getInt("disabled"));
//				return dormBuild;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFatory.close(connection,preparedStatement,rs);
		}

		return null;
	}

	@Override
	public void update(User user) throws SQLException {
		Connection connection = ConnectionFatory.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "update tb_user set name=?,sex=?,tel=? where id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2,user.getSex());
			preparedStatement.setString(3,user.getTel());
			preparedStatement.setInt(4,user.getId());
			int rs = preparedStatement.executeUpdate();//更新返回的是一个int，影响的hang数



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFatory.close(connection,preparedStatement,null);
		}
	}

	@Override
	public List<User> findAllStu() throws SQLException {
		Connection connection = ConnectionFatory.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet rs =null;
		try {
			String sql = "select * from tb_user where role_id=2";

			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			List<User> users = new ArrayList<User>();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setStuCode(rs.getString("stu_code"));
				user.setDormCode(rs.getString("dorm_code"));
				user.setSex(rs.getString("sex"));
				user.setTel(rs.getString("tel"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setRoleId(2);
				user.setCeateUserId(rs.getInt("create_user_id"));
				user.setDisabled(rs.getInt("disabled"));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFatory.close(connection,preparedStatement,rs);
		}
		return null;
	}

	@Override
	public void updateDis(User user) throws SQLException {
		Connection connection = ConnectionFatory.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "update tb_user set disabled=? where id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,user.getDisabled());
			preparedStatement.setInt(2,user.getId());
			int rs = preparedStatement.executeUpdate();//更新返回的是一个int，影响的hang数
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFatory.close(connection,preparedStatement,null);
		}
	}

	@Override
	public void saveStudent(User user) throws SQLException {
		Connection connection = ConnectionFatory.getConnection();
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		String sql = "insert into tb_user(name,password,stu_code,sex,tel,dormBuildId,role_id,create_user_id,disabled) value(?,?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			System.out.println("this is saveStudent");
			System.out.println(user.getDisabled()+user.getName()+user.getPassword()+user.getStuCode()+user.getSex()+user.getTel()+user.getRoleId()+user.getCeateUserId());
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3,user.getStuCode());
			preparedStatement.setString(4,user.getSex());
			preparedStatement.setString(5,user.getTel());
			preparedStatement.setInt(6,user.getDormBuildId());
			preparedStatement.setInt(7,user.getRoleId());
			preparedStatement.setInt(8,user.getCeateUserId());
			preparedStatement.setInt(9,user.getDisabled());
			int rs = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFatory.close(connection,preparedStatement, null);
		}
	}

	@Override
	public User findByStuCode(String stuCode) throws SQLException {
		Connection connection = ConnectionFatory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from tb_user where stu_code=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, stuCode);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setStuCode(rs.getString("stu_code"));
				user.setDormCode(rs.getString("dorm_code"));
				user.setSex(rs.getString("sex"));
				user.setTel(rs.getString("tel"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setRoleId(rs.getInt("role_id"));
				user.setCeateUserId(rs.getInt("create_user_id"));
				user.setDisabled(rs.getInt("disabled"));
				return user;

				//				dormBuild.setId(rs.getInt("id"));
//				dormBuild.setRemark(rs.getString("remark"));
//				dormBuild.setName(rs.getString("name"));
//				dormBuild.setDisabled(rs.getInt("disabled"));
//				return dormBuild;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFatory.close(connection, preparedStatement, rs);
		}

		return null;
	}
}
