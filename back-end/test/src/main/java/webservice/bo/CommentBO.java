package webservice.bo;

import java.sql.Connection;
import java.util.ArrayList;

import webservice.dao.CommentDAOImpl;

import webservice.pojo.CommentVO;

import webservice.server.DataSource;

public class CommentBO {
	public ArrayList<CommentVO> getCommentList(int id) throws Exception{
		ArrayList<CommentVO> commentList = null;
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			CommentDAOImpl dao = new CommentDAOImpl();
			commentList = dao.getComments(id, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return commentList;
	}
	public boolean setComment(CommentVO comment) throws Exception {

		boolean addSuccess = false;

		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			CommentDAOImpl dao = new CommentDAOImpl();
			addSuccess = dao.setComment(comment, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return addSuccess;

	}

	public boolean delComment(CommentVO comment) throws Exception {

		boolean delSuccess = false;

		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			CommentDAOImpl dao = new CommentDAOImpl();
			delSuccess = dao.deleteComment(comment, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return delSuccess;

	}
}
