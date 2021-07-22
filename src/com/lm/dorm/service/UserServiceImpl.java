package com.lm.dorm.service;

import com.lm.dorm.bean.User;
import com.lm.dorm.dao.DormBuilDaoImpl;
import com.lm.dorm.dao.DormBuildDao;
import com.lm.dorm.dao.UserDao;
import com.lm.dorm.dao.UserDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
	private UserDao userdao = new UserDaoImpl();
	private DormBuildDao dormBuilDao = new DormBuilDaoImpl();
	@Override
	public User findBystuCodeAndPass(String stuCode, String password) throws SQLException {
		
		return userdao.findBystuCodeAndPass(stuCode,password);
		
	}

	@Override
	public void saveManager(User user, String[] dormBuildIds) throws SQLException {
		String StuCodeMax = userdao.findManagerStuCodeMax();
		System.out.println(StuCodeMax+"maxcode");
		user.setStuCode(StuCodeMax);//保存宿舍管理员
		Integer userId = userdao.saveManager(user);
		System.out.println("userID:"+userId);
		dormBuilDao.saveManagerAndBuild(userId,dormBuildIds);

	}

	@Override
	public List<User> findAll() throws SQLException {
		return  userdao.findAll();
	}

	@Override
	public User find(Integer id) throws SQLException {
		return userdao.find(id);
	}

	@Override
	public void update(User user) throws SQLException {
		userdao.update(user);
	}

	@Override
	public void updateManagerAndBuilds(User user, String[] dormBuildIds) throws SQLException {
		Integer userId = user.getId();
		dormBuilDao.updateManagerAndBuilds(userId,dormBuildIds);
	}

	@Override
	public List<User> findAllStu() throws SQLException {
		return userdao.findAllStu();
	}

	@Override
	public void updateDis(User user) throws SQLException {
		userdao.updateDis(user);
	}

	@Override
	public void add(User user) throws SQLException {
		userdao.saveStudent(user);
	}
	@Override
	public User findByStuCode(String stuCode) throws SQLException {
		return userdao.findByStuCode(stuCode);
	}
}
