package com.lm.dorm.dao;

import com.lm.dorm.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

	User findBystuCodeAndPass(String stuCode, String password) throws SQLException;


	String findManagerStuCodeMax() throws SQLException;

	Integer saveManager(User user) throws SQLException;

	List<User> findAll() throws SQLException;

    User find(Integer id) throws SQLException;

    void update(User user) throws SQLException;

	List<User> findAllStu() throws SQLException;

    void updateDis(User user) throws SQLException;;

	void saveStudent(User user) throws SQLException;

	User findByStuCode(String stuCode) throws SQLException;
}
