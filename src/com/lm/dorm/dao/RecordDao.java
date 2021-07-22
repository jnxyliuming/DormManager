package com.lm.dorm.dao;

import com.lm.dorm.bean.Record;

import java.sql.SQLException;
import java.util.List;

public interface RecordDao {
    void saveRecord(Record record) throws SQLException;

    List<Record> findAll() throws SQLException;

    Record find(Integer id) throws SQLException;

    void update(Record record) throws SQLException;

    void delete(Integer id) throws SQLException;
}
