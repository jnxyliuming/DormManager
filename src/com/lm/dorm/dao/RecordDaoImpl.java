package com.lm.dorm.dao;

import com.lm.dorm.bean.Record;
import com.lm.dorm.bean.User;
import com.lm.dorm.util.ConnectionFatory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements RecordDao{
    @Override
    public void saveRecord(Record record) throws SQLException {
        System.out.println(record.getDate()+record.getStuId()+"dao");
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        String sql = "insert into tb_record(stu_jd,date,remark,disabled) VALUES(?,?,?,?);";
        try {
            preparedStatement = connection.prepareStatement(sql);
            System.out.println("this is saveStudent");
            System.out.println(record.getRemark()+"record.getRemark()");
            preparedStatement.setInt(1,record.getStuId());
            preparedStatement.setString(2,record.getDate());
            preparedStatement.setString(3,record.getRemark());
            preparedStatement.setInt(4,record.getDisabled());
            int rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ConnectionFatory.close(connection,preparedStatement, null);
        }
    }

    @Override
    public List<Record> findAll() throws SQLException {
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet rs =null;
        try {
            String sql = "select * from tb_record";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            List<Record> records = new ArrayList<Record>();
            while(rs.next()) {
                Record record =new Record();
                record.setId(rs.getInt("id"));
                record.setStuId(rs.getInt("stu_jd"));
                record.setDate(rs.getString("date"));
                record.setRemark(rs.getString("remark"));
                record.setDisabled(rs.getInt("disabled"));
                record.toString();
                records.add(record);
            }
            return records;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ConnectionFatory.close(connection,preparedStatement,rs);
        }
        return null;
    }

    @Override
    public Record find(Integer id) throws SQLException {
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            String sql = "select * from tb_record where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Record record = new Record();
                record.setId(rs.getInt("id"));
                record.setStuId(rs.getInt("stu_jd"));
                record.setDate(rs.getString("date"));
                record.setRemark(rs.getString("remark"));
                record.setDisabled(rs.getInt("disabled"));
                return record;

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
    public void update(Record record) throws SQLException {
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update tb_record set stu_jd=?,date=?,remark=?,disabled=? where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,record.getStuId());
            preparedStatement.setString(2,record.getDate());
            preparedStatement.setString(3,record.getRemark());
            preparedStatement.setInt(4,record.getDisabled());
            preparedStatement.setInt(5,record.getId());
            int rs = preparedStatement.executeUpdate();//更新返回的是一个int，影响的hang数



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ConnectionFatory.close(connection,preparedStatement,null);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "delete from tb_record where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,	id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ConnectionFatory.close(connection,preparedStatement,null);
        }

    }

}
