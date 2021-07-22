package com.lm.dorm.dao;

import com.lm.dorm.bean.DormBuild;
import com.lm.dorm.bean.User;
import com.lm.dorm.util.ConnectionFatory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormBuilDaoImpl implements  DormBuildDao {
    @Override
    public DormBuild findByName(String name) throws SQLException {
        // TODO Auto-generated method stub
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet rs =null;
        String sql = "select * from tb_dormbuild where name=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,	name);


            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                DormBuild dormBuild = new DormBuild();
                dormBuild.setId(rs.getInt("id"));
                dormBuild.setRemark(rs.getString("remark"));
                dormBuild.setName(rs.getString("name"));
                dormBuild.setDisabled(rs.getInt("disabled"));
                return dormBuild;
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
    public void save(DormBuild build) throws SQLException {
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement =null;
        String sql = "insert into tb_dormbuild (name,remark,disabled) values(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,	build.getName());
            preparedStatement.setString(2,	build.getRemark());
            preparedStatement.setInt(3,	build.getDisabled());
            int rs = preparedStatement.executeUpdate();//更新返回的是一个int，影响的函数



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ConnectionFatory.close(connection,preparedStatement,null);
        }


    }

    @Override
    public List<DormBuild> findAll() throws SQLException {
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement=null;
        ResultSet rs =null;
        try {
            String sql = "select * from tb_dormbuild";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            List<DormBuild> builds = new ArrayList<DormBuild>();
            while(rs.next()) {
                DormBuild dormBuild = new DormBuild();
                dormBuild.setId(rs.getInt("id"));
                dormBuild.setRemark(rs.getString("remark"));
                dormBuild.setName(rs.getString("name"));
                dormBuild.setDisabled(rs.getInt("disabled"));
                builds.add(dormBuild);
            }
            return builds;


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ConnectionFatory.close(connection,preparedStatement,rs);
        }
        return null;
    }

    @Override
    public DormBuild find(Integer id) throws SQLException {
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            String sql = "select * from tb_dormbuild where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                DormBuild dormBuild = new DormBuild();
                dormBuild.setId(rs.getInt("id"));
                dormBuild.setRemark(rs.getString("remark"));
                dormBuild.setName(rs.getString("name"));
                dormBuild.setDisabled(rs.getInt("disabled"));
                return dormBuild;
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
    public void update(DormBuild dormBuild) throws SQLException {
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update tb_dormbuild set name=? ,remark=? ,disabled=? where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,	dormBuild.getName());
            preparedStatement.setString(2,	dormBuild.getRemark());
            preparedStatement.setInt(3,	dormBuild.getDisabled());
            preparedStatement.setInt(4,dormBuild.getId());
            int rs = preparedStatement.executeUpdate();//更新返回的是一个int，影响的hang数



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ConnectionFatory.close(connection,preparedStatement,null);
        }
    }

    @Override
    public void saveManagerAndBuild(Integer userId, String[] dormBuildIds) throws SQLException {
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into tb_manage_dormbuild(user_id,dormBuild_id) value(?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);

            for (String dormBuildId : dormBuildIds){
                preparedStatement.setInt(1,userId);
                preparedStatement.setInt(2,Integer.parseInt(dormBuildId));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ConnectionFatory.close(connection,preparedStatement,null);
        }
    }

    @Override
    public void updateManagerAndBuilds(Integer userId, String[] dormBuildIds) throws SQLException {
        System.out.println("updateManagerAndBuilds");
        Connection connection = ConnectionFatory.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "delete from tb_manage_dormbuild where user_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,	userId);
            preparedStatement.executeUpdate();
            saveManagerAndBuild(userId,dormBuildIds);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ConnectionFatory.close(connection,preparedStatement,null);
        }
    }
}
