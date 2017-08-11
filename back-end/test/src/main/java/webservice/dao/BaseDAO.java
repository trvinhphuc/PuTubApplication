package webservice.dao;

import java.sql.Connection;

public class BaseDAO {
	Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}