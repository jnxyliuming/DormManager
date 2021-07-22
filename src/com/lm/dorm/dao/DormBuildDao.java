package com.lm.dorm.dao;

import com.lm.dorm.bean.DormBuild;

import java.sql.SQLException;
import java.util.List;

public interface DormBuildDao {
    DormBuild findByName(String inputName) throws SQLException;

    void save(DormBuild build) throws SQLException;

    List<DormBuild> findAll() throws SQLException;


    DormBuild find(Integer id) throws SQLException;

    void update(DormBuild dormBuild) throws SQLException;

    void saveManagerAndBuild(Integer userId, String[] dormBuildIds) throws SQLException;


    void updateManagerAndBuilds(Integer userId, String[] dormBuildIds) throws SQLException;
}
