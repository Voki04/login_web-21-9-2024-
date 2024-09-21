package vn.iotstar.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectSQL {


	private final String serverName = "SANDRA\\QUYEN";

	private final String dbName = "DataTest";

	private final String portNumber = "1433";

	private final String userID = "sa";

	private final String password = "123";

	public Connection getConnection() throws Exception {

		String url = "jdbc:sqlserver://" + serverName  + ";databaseName=" + dbName;

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		return DriverManager.getConnection(url, userID, password);

	}

// Test chương trình. Kích phải chuột chọn run as->java application

	public static void main(String[] args) {

		try {

			System.out.println(new DBConnectSQL().getConnection());

		} catch (Exception e) {

			e.printStackTrace();

		}

}
	}