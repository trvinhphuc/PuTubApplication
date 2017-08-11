package webservice.dao;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import org.bouncycastle.util.encoders.Base64;

import webservice.pojo.UserVO;

public class UserDAOImpl {

	public ArrayList<UserVO> getAllUsers(Connection connection) throws Exception {

		ArrayList<UserVO> userList = new ArrayList<UserVO>();
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT id,username,email,pwd FROM users");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserVO uservo = new UserVO();
				uservo.setId(rs.getInt("id"));
				// System.out.println(uservo.getID());
				uservo.setUsername(rs.getString("username"));
				uservo.setEmail(rs.getString("email"));
				uservo.setPassword(rs.getString("pwd"));

				userList.add(uservo);
			}
			ps.close();
			return userList;
		} catch (Exception e) {

			throw e;
		}
	}

	public boolean setUserInfo(Object ob, Connection connection) throws Exception {

		try {

			UserVO user = new UserVO();
			user = (UserVO) ob;
			String username = user.getUsername();
			String channelname = user.getChannelname();
			String email = user.getEmail();
			String password = user.getPassword();
			String date = user.getDate();
			// byte[] img = user.getAvatar();
			String ava = user.getAva();
			if (ava == "")
				ava = "1.jpg";
			// Blob blob = null ;
			// blob.setBytes(img.length, img);

			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO users(email, username, pwd, link_avt, chanel_name, birthday) "
							+ "SELECT '" + email + "' , '" + username + "' , '" + password + "' , BulkColumn , '"
							+ channelname + "' , '" + date + "' FROM OPENROWSET(Bulk N'C:\\uploaded\\Avatar\\" + ava
							+ "', SINGLE_BLOB) as users");

			// System.out.println("Debug2");
			int rs = ps.executeUpdate();
			if(ava != "1.jpg") {
				File file = new File("C:\\uploaded\\Avatar\\" + ava);
				if (file.delete()) {
					System.out.println(file.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
			}
			
			ps.close();
			if (rs > 0) {
				return true;
			} else
				return false;

		} catch (Exception e) {

			throw e;
		}

	}

	public boolean updateUserInfo(Object ob, Connection connection) throws Exception {

		try {

			UserVO user = new UserVO();
			user = (UserVO) ob;
			int id = user.getID();
			String username = user.getUsername();
			String channelname = user.getChannelname();
			String email = user.getEmail();
			String date = user.getDate();
			String ava = user.getAva();
			String query = null;
			if (ava != null) {
				System.out.println("debug");
				System.out.println(ava);
				query = "UPDATE users " + "SET username = '" + username + "', " + "email = '" + email + "', "
						+ "chanel_name = '" + channelname + "', " + "birthday = '" + date + "',  "
						+ "[link_avt] = (SELECT MyImage.* from Openrowset(Bulk 'C:\\uploaded\\Avatar\\" + ava
						+ "', Single_Blob) MyImage) " + "WHERE id = " + id;
			} else {
				query = "UPDATE users " + "SET username = '" + username + "', " + "email = '" + email + "', "
						+ "chanel_name = '" + channelname + "', " + "birthday = '" + date + "'  " + "WHERE id = " + id;

			}
			PreparedStatement ps = connection.prepareStatement(query);
			// System.out.println("Debug2");
			int rs = ps.executeUpdate();
			File file = new File("C:\\uploaded\\Avatar\\" + ava);
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}
			ps.close();
			if (rs > 0)
				return true;
			else
				return false;

		} catch (Exception e) {

			throw e;
		}

	}

	public UserVO getUserInfo(int id, Connection connection) throws Exception {
		UserVO user = new UserVO();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT id,username,pwd,email,birthday,chanel_name FROM users WHERE id = " + id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("pwd"));
				user.setEmail(rs.getString("email"));
				user.setDate(rs.getString("birthday"));
				user.setChannelname(rs.getString("chanel_name"));

			}

			ps.close();
		} catch (Exception e) {

			throw e;
		}
		return user;
	}
	public UserVO getUserID(String username, Connection connection) throws Exception {
		UserVO user = new UserVO();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT id FROM users WHERE username = '" + username +"'");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				user.setId(rs.getInt("id"));
				
			}

			ps.close();
		} catch (Exception e) {

			throw e;
		}
		return user;
		
	}

	public boolean changePassword(Object ob, Connection connection) throws Exception {

		try {

			UserVO user = new UserVO();
			user = (UserVO) ob;
			int id = user.getID();

			String password = user.getNewpass();

			PreparedStatement ps = connection
					.prepareStatement("UPDATE users " + "SET pwd = '" + password + "' " + "WHERE id = " + id);

			// System.out.println("Debug2");
			int rs = ps.executeUpdate();

			ps.close();
			if (rs > 0)
				return true;
			else
				return false;

		} catch (Exception e) {

			throw e;
		}

	}

	public Object getImage(Object ob, Connection connection) throws Exception {
		// BufferedImage image = null;
		UserVO user = new UserVO();
		user = (UserVO) ob;
		try {
			// File file=new File("C:\\Users\\phucloc\\Desktop\\PaiSho board3.jpg");
			// FileOutputStream fos=new FileOutputStream(file);
			byte b[];
			Blob blob;
			PreparedStatement ps = connection
					.prepareStatement("SELECT username, link_avt FROM users WHERE id = " + user.getID());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				blob = rs.getBlob("link_avt");
				// System.out.println(blob);
				b = blob.getBytes(1, (int) blob.length());
				user.setAvatar(b);
				// image = ImageIO.read(new ByteArrayInputStream(b));
				// fos.write(b);
			}
			ps.close();

		} catch (Exception e) {
			System.out.println("Cannot select");
			throw e;
		}
		return user;
	}

	public boolean setSubcriber(int id, String username, Connection connection) throws Exception {
		boolean subSuccess = false;
		try {

			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO subcribe(id_user,id_user_subcribed,subcribe_time) " + "SELECT " + id
							+ ",id,getdate()" + "FROM users WHERE username = '" + username + "' ");
			int rs = ps.executeUpdate();
			if (rs != 0)
				subSuccess = true;
			else
				System.out.println("Cannot insert *");
			ps.close();

		} catch (Exception e) {
			System.out.println("Cannot insert");
			throw e;
		}
		return subSuccess;
	}

	public boolean deleteSubcriber(int id, String username, Connection connection) throws Exception {
		boolean subSuccess = false;
		try {

			PreparedStatement ps = connection.prepareStatement("delete from subcribe where id_user = " + id
					+ "and id_user_subcribed IN(select id from users where username = '" + username + "') ");
			int rs = ps.executeUpdate();
			if (rs != 0)
				subSuccess = true;
			else
				System.out.println("Cannot delete *");
			ps.close();

		} catch (Exception e) {
			System.out.println("Cannot delete");
			throw e;
		}
		return subSuccess;
	}

	public ArrayList<UserVO> getSubcribers(String id, Connection connection) throws Exception {

		ArrayList<UserVO> userList = new ArrayList<UserVO>();
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT username " + "FROM subcribe "
					+ "INNER JOIN users ON id_user_subcribed = id " + "WHERE id_user = " + id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserVO uservo = new UserVO();

				uservo.setUsername(rs.getString("username"));

				userList.add(uservo);
			}
			ps.close();
			return userList;
		} catch (Exception e) {

			throw e;
		}
	}

}
