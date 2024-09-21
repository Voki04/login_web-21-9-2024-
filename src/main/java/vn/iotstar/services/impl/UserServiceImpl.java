package vn.iotstar.services.impl;

import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserSevice;

public class UserServiceImpl  implements IUserSevice{

	IUserDao userDao =new UserDaoImpl();
	public boolean register(String username, String password,String email ) {
			 long millis=System.currentTimeMillis();   
			java.sql.Date date=new java.sql.Date(millis);
			 userDao.insert(new UserModel(username,password,email));
			 return true;
			 }
	
	
	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if(user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		return userDao.findByUserName(username);
	}
	
	@Override
	public void insert(UserModel user) {
		// TODO Auto-generated method stub	
	}
	@Override
	public boolean checkExistUsername(String username) {
	    return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.checkExistEmail(email);
	}


	@Override
	public String getCurrentPassword(String username, String email) {
		// TODO Auto-generated method stub
		return userDao.getCurrentPassword(username, email);
	}

	

}
