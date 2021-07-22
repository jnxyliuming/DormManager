package com.lm.dorm.service;

import com.lm.dorm.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

	User findBystuCodeAndPass(String stuCode, String password) throws SQLException;

    void saveManager(User user, String[] dormBuildIds) throws SQLException;

    List<User> findAll() throws SQLException;

    User find(Integer id) throws SQLException;

    void update(User user) throws SQLException;

    void updateManagerAndBuilds(User user, String[] dormBuildIds) throws SQLException;

    List<User> findAllStu() throws SQLException;

    void updateDis(User user)throws SQLException;

    void add(User user) throws SQLException;

    User findByStuCode(String stuCode) throws SQLException;
}
