package webservice.bo;

import java.sql.Connection;
import java.util.ArrayList;

import webservice.dao.VideoDAOImpl;
import webservice.pojo.VideoVO;
import webservice.server.DataSource;
import webservice.utils.JsonUtil;

public class VideoBO {
	public VideoVO getVideo(int id) throws Exception {

		VideoVO video = new VideoVO();
		// Image img = null;
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			VideoDAOImpl dao = new VideoDAOImpl();
			video.setId(id);
			video = (VideoVO) dao.getVid(video, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return video;

	}
	public VideoVO getVideoInfo(int id) throws Exception {

		VideoVO video = new VideoVO();
		// Image img = null;
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			VideoDAOImpl dao = new VideoDAOImpl();
			video.setId(id);
			video = (VideoVO) dao.getVidInfo(video, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return video;

	}
	public ArrayList<VideoVO> getVideoList(int id) throws Exception{
		ArrayList<VideoVO> videoList = null;
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			VideoDAOImpl dao = new VideoDAOImpl();
			videoList = dao.getVideoList(id, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return videoList;
	}
	public ArrayList<VideoVO> getAllVideo() throws Exception{
		ArrayList<VideoVO> videoList = null;
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			VideoDAOImpl dao = new VideoDAOImpl();
			videoList = dao.getAllVideos(connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return videoList;
	}
	public boolean insertVideo(Object vid) throws Exception {
		boolean addSuccess = false ;
		
		// Image img = null;
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			VideoDAOImpl dao = new VideoDAOImpl();
		
			addSuccess = dao.insertVidToDB(vid, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return addSuccess;

	}
	public boolean deleteVideo(int id) throws Exception {
		boolean addSuccess = false ;
		
		// Image img = null;
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			VideoDAOImpl dao = new VideoDAOImpl();
			addSuccess = dao.deleteVideo(id, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return addSuccess;

	}
	public boolean updateVideoInfo(Object vid) throws Exception {
		boolean addSuccess = false ;
		
		
		try {
			DataSource database = new DataSource();
			Connection connection = database.getInstance().getConnection();
			VideoDAOImpl dao = new VideoDAOImpl();
			addSuccess = dao.updateVidInfo(vid, connection);
			connection.close();
		} catch (Exception e) {
			System.out.println("Unable connect DB");
			throw e;
		}
		return addSuccess;

	}
	
}
