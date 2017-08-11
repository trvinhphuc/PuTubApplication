package webservice.bo;

import java.sql.Connection;
import java.util.ArrayList;

import webservice.dao.UserDAOImpl;
import webservice.dao.VideoDAOImpl;
import webservice.pojo.UserVO;
import webservice.server.DataSource;

public class SearchBO {
	
	
	public ArrayList<Object> searchList() throws Exception{
		ArrayList<Object> searchList = null;
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			UserDAOImpl u_dao = new UserDAOImpl();
			VideoDAOImpl v_dao = new VideoDAOImpl();
			System.out.println("debug");
			
			System.out.println(searchList);
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return searchList;
	}
}
