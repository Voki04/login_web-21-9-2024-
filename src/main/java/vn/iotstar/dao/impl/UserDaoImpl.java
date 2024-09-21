package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.config.DBConnectSQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl implements IUserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	// tìm theo tên user
	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM users_inf_test WHERE username = ?";
		UserModel user = null;
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserModel(rs.getString("username"), rs.getString("password"),rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	// show thông tin user
	@Override
	public List<UserModel> findAll() {

		String sql = "select * from users_inf_test";
		List<UserModel> list = new ArrayList<>();
		try {
			Connection conn = new DBConnectSQL().getConnection();
			PreparedStatement ps = conn.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserModel(rs.getString("username"), rs.getString("password"),rs.getString("email")));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	// Thêm user
	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users_inf_test (username, password,email) VALUES (?, ?, ?)";
		try (Connection conn = new DBConnectSQL().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());// Ensure passwords are hashed for security

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		String sql = "SELECT 1 FROM users_inf_test WHERE username = ?";
		try (Connection conn = new DBConnectSQL().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Xoá user theo ID
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM users_inf_test WHERE id = ?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();

		String password = userDao.getCurrentPassword("d", "123@gmail.com");
	    
	    if (password != null) {
	        System.out.println("Mật khẩu của user 'd' là: " + password);
	    } else {
	        System.out.println("Không tìm thấy user với tên 'd' và email '123@gmail.com'.");
	    }
	}
	@Override
    public String getCurrentPassword(String username, String email) {
        String password = null;
        String sql = "SELECT password FROM users_inf_test WHERE username = ? AND email = ?";

        try {
        	conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return password;
    }

	@Override
	public UserModel register(String username, String password,String email) {
		// Check if username already exists
		if (checkExistUsername(username)) {
			System.out.println("Username already exists: " + username);
			return null;
		}

		// Create a new user object
		UserModel user = new UserModel();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);// Ensure passwords are hashed for security

		// Insert user into the database
		try {
			insert(user);
			System.out.println("User inserted successfully: " + username);
		} catch (Exception e) {
			System.out.println("Failed to insert user: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		// Return the registered user
		return user;
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from [user] where email = ?";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}
	

}
