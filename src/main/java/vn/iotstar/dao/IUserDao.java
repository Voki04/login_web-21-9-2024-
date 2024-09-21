package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();

	UserModel findByUserName(String username);

	UserModel register(String username, String password,String email);

	void insert(UserModel user);

	void delete(int id);

	boolean checkExistUsername(String username);

	boolean checkExistEmail(String email);

	String getCurrentPassword(String username, String email);
}
