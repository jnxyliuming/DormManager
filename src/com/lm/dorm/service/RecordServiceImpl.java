package com.lm.dorm.service;

import com.lm.dorm.bean.Record;
import com.lm.dorm.dao.RecordDao;
import com.lm.dorm.dao.RecordDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class RecordServiceImpl implements  RecordService{
    RecordDao recordDao = new RecordDaoImpl();
    @Override
    public void saveRecord(Record record) throws SQLException {

        System.out.println(record.getDate()+record.getStuId()+"service");
        recordDao.saveRecord(record);
    }

    @Override
    public List<Record> findAll() throws SQLException {

        return recordDao.findAll();
    }

    @Override
    public Record find(Integer id) throws SQLException {
        return recordDao.find(id);
    }

    @Override
    public void update(Record record) throws SQLException {
        recordDao.update(record);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        recordDao.delete(id);
    }
}
