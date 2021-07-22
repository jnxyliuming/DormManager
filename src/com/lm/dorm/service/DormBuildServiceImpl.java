package com.lm.dorm.service;

import com.lm.dorm.bean.DormBuild;
import com.lm.dorm.dao.DormBuilDaoImpl;
import com.lm.dorm.dao.DormBuildDao;

import java.sql.SQLException;
import java.util.List;

public class DormBuildServiceImpl implements DormBuildService{
    //服务层调取dao层
    private DormBuildDao dormBuildDao =new DormBuilDaoImpl();

    @Override
    public DormBuild find(Integer id) throws SQLException {
        return dormBuildDao.find(id);
    }

    @Override
    public DormBuild findByName(String inputName) throws SQLException {

        return dormBuildDao.findByName(inputName);
    }

    @Override
    public void save(DormBuild build) throws SQLException {
        dormBuildDao.save(build);
    }

    @Override
    public List<DormBuild> findAll() throws SQLException {

        return dormBuildDao.findAll();
    }

    @Override
    public void update(DormBuild dormBuild) throws SQLException {
        dormBuildDao.update(dormBuild);
    }


}
