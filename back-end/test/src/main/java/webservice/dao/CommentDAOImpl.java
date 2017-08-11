package webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import webservice.pojo.CommentVO;

public class CommentDAOImpl {
	public ArrayList<CommentVO> getComments(int id, Connection connection) throws Exception {
		ArrayList<CommentVO> commentList = new ArrayList<CommentVO>();
		// System.out.println(id);
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT id_user,comment_content,username,comment_time "
					+ "FROM comments " + "INNER JOIN users ON id_user = users.id " + "where id_video = " + id
					+ "ORDER BY comment_time");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CommentVO cmt = new CommentVO();
				cmt.setContent(rs.getString("comment_content"));
				cmt.setDate(rs.getString("comment_time"));
				cmt.setUsername(rs.getString("username"));

				cmt.setId_user(rs.getInt("id_user"));
				commentList.add(cmt);
			}
			ps.close();
		} catch (Exception e) {
			System.out.println("cannot select comments");
		}
		return commentList;

	}

	public boolean setComment(CommentVO comment, Connection connection) throws Exception {
		boolean cmtSuccess = false;
		try {
			if (comment.getContent() != null) {
				String content = comment.getContent();
				content = content.replaceAll("'", "''");
				// content = content.replaceAll("^","\\^");
				System.out.println(content);
				PreparedStatement ps = connection.prepareStatement("INSERT INTO comments VALUES(" + comment.getId_user()
						+ "," + comment.getId_video() + ",getdate(),N'" + content + "')");
				int rs = ps.executeUpdate();			
			if (rs != 0)
				cmtSuccess = true;
			else
				System.out.println("Cannot insert *");
			ps.close();
			}

		} catch (Exception e) {
			System.out.println("Cannot insert");
			throw e;
		}
		return cmtSuccess;
	}

	public boolean deleteComment(CommentVO comment, Connection connection) throws Exception {
		boolean cmtSuccess = false;
		try {

			PreparedStatement ps = connection.prepareStatement(
					"delete from comments where id_user = " + comment.getId_user() + " and id_video = "
							+ comment.getId_video() + " and comment_time = '" + comment.getDate() + "'");
			int rs = ps.executeUpdate();
			if (rs != 0)
				cmtSuccess = true;
			else
				System.out.println("Cannot delete *");
			ps.close();

		} catch (Exception e) {
			System.out.println("Cannot delete");
			throw e;
		}
		return cmtSuccess;
	}
}
