package webservice.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;

import webservice.pojo.UserVO;
import webservice.pojo.VideoVO;

public class VideoDAOImpl {
	public Object getVid(Object ob, Connection connection) throws Exception {

		VideoVO video = new VideoVO();
		video = (VideoVO) ob;
		try {
			// File file=new File("C:\\Users\\phucloc\\Desktop\\aaaaa.mp4");
			// FileOutputStream fos=new FileOutputStream(file);
			byte b[];
			Blob blob;
			PreparedStatement ps = connection
					.prepareStatement("SELECT video_name, video, owner_,description_ FROM videos WHERE id = " + video.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("video_name");
				video.setVideoname(name);
				blob = rs.getBlob("video");
				// System.out.println(blob)
				b = blob.getBytes(1, (int) blob.length());
				video.setVideo(b);
				video.setDescript(rs.getString("description_"));
				video.setOwnername(rs.getString("owner_"));
				
			}
			// fos.close();
			ps.close();

		} catch (Exception e) {
			System.out.println("Cannot select");
			throw e;
		}

		return video;

	}
	public Object getVidInfo(Object ob, Connection connection) throws Exception {

		VideoVO video = new VideoVO();
		video = (VideoVO) ob;
		try {
			// File file=new File("C:\\Users\\phucloc\\Desktop\\aaaaa.mp4");
			// FileOutputStream fos=new FileOutputStream(file);
			byte b[];
			Blob blob;
			PreparedStatement ps = connection
					.prepareStatement("SELECT video_name,owner_,description_,view_n,like_n,dislike_n,tag,privacy FROM videos WHERE id = " + video.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("video_name");
				video.setVideoname(name);
				video.setOwnername(rs.getString("owner_"));
				video.setDescript(rs.getString("description_"));
				video.setPrivacy(rs.getString("privacy"));
				video.setView_n(rs.getInt("view_n"));
				video.setLike_n(rs.getInt("like_n"));
				video.setDislike_n(rs.getInt("dislike_n"));
				video.setTag(rs.getString("tag"));
			}
			// fos.close();
			ps.close();

		} catch (Exception e) {
			System.out.println("Cannot select");
			throw e;
		}

		return video;

	}

	public ArrayList<VideoVO> getVideoList(int id, Connection connection) throws Exception {
		ArrayList<VideoVO> videoList = new ArrayList<VideoVO>();
		try {
			byte b[];
			Blob blob;
			PreparedStatement ps = connection
					.prepareStatement("select videos.id, video_name, review_image from videos,users "
							+ "where videos.owner_ = users.username AND users.id = " + id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VideoVO vid = new VideoVO();
				vid.setVideoname(rs.getString("video_name"));
				vid.setId(rs.getInt("id"));
				blob = rs.getBlob("review_image");
				b = blob.getBytes(1, (int) blob.length());
				vid.setImg(b);
				videoList.add(vid);
			}
			ps.close();
		} catch (Exception e) {
			System.out.println("Cannot select");
			throw e;
		}
		return videoList;

	}
	public ArrayList<VideoVO> getAllVideos(Connection connection) throws Exception {
		ArrayList<VideoVO> videoList = new ArrayList<VideoVO>();
		try {
			byte b[];
			Blob blob;
			PreparedStatement ps = connection
					.prepareStatement("SELECT videos.id, video_name, review_image,owner_,tag FROM videos ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VideoVO vid = new VideoVO();
				vid.setVideoname(rs.getString("video_name"));
				vid.setOwnername(rs.getString("owner_"));
				vid.setId(rs.getInt("id"));
				vid.setTag(rs.getString("tag"));
				blob = rs.getBlob("review_image");
				b = blob.getBytes(1, (int) blob.length());
				vid.setImg(b);
				videoList.add(vid);
			}
			ps.close();
		} catch (Exception e) {
			System.out.println("Cannot select");
			throw e;
		}
		return videoList;

	}

	public boolean insertVidToDB(Object ob, Connection connection) throws Exception {

		try {
			VideoVO video = new VideoVO();
			video = (VideoVO) ob;
			String videoname = video.getVideoname();
			String realname = video.getRealname();
			String owner = video.getOwnername();
			String descript = video.getDescript();
			String tag = video.getTag();
			String privacy = video.getPrivacy();
			descript = descript.replaceAll("'", "''");
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO videos(video_name,owner_,upload_time,description_,video,review_image,sub,tag,view_n,like_n,dislike_n,privacy) "
							+ "SELECT N'" + videoname + "' as video_name, " + "'"+owner+"' as owner_, "
							+ "GETDATE() as upload_time, " + "N'"+descript+"' as description_, " + "video.*, "
							+ "review_image.*, " + "null as sub, " + "'"+tag+"' as tag, " + "0 as view_n, "
							+ "0 as like_n, " + "0 as dislike_n, " + "'"+privacy+"' as privacy "
							+ "FROM Openrowset( Bulk N'C:\\uploaded/Video/" + realname + "', Single_blob) as video, "
							+ "Openrowset( Bulk 'C:\\videoplay.jpg', Single_blob) as review_image ");

			int rs = ps.executeUpdate();

			ps.close();
			File file = new File("C:\\uploaded/Video/"+realname);
			if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
			if (rs > 0)
				return true;
			else
				return false;

		} catch (Exception e) {

			throw e;
		}

	}
	public boolean deleteVideo(int id, Connection connection) throws Exception {

		try {
			PreparedStatement ps = connection.prepareStatement(
					"DELETE from comments " +
					"WHERE id_video = "+ id +
					" DELETE from videos " + 
					"WHERE id = "+ id );
			
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
	public boolean updateVidInfo(Object ob, Connection connection) throws Exception {

		try {
			VideoVO video = new VideoVO();
			video = (VideoVO) ob;
			int id = video.getId();
			String videoname = video.getVideoname();
			String descript = video.getDescript();
			String tag = video.getTag();
			String privacy = video.getPrivacy();
			descript = descript.replaceAll("'", "''");
			PreparedStatement ps = connection.prepareStatement(
					"update videos " + 
					"set video_name = '"+videoname+"', description_ = N'"+ descript +"', tag = '"+tag+"' , privacy = '"+privacy+"' " + 
					"where id = " + id);

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


}
