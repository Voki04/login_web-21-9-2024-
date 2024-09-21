package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface IUserSevice {
	UserModel login(String username, String password);

	UserModel FindByUserName(String username);

	void insert(UserModel user);

	boolean register(String username, String password, String email);

	boolean checkExistUsername(String username);

	boolean checkExistEmail(String email);
	
	String getCurrentPassword(String username, String email);

}
