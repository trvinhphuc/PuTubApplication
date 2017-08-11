package webservice.controller;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import webservice.bo.VideoBO;
import webservice.pojo.VideoVO;
import webservice.utils.JsonUtil;

@Path("/video3")
public class AddVideoService {
	
	/*creator: Truong Vinh Phuc 
	 *function: catch video info and insert to database 
	 *date: 11/08/2017
	 *note: the video source has already been save by UploadFileService
	 */
	@POST
	@Path("/upload")
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertVideo(String jsonString) {
		JsonUtil json = new JsonUtil();
		boolean success = false;
		
		
		// System.out.println(jsonString);
		VideoVO vid = json.buildObject(jsonString, VideoVO.class);

		// System.out.println(vid.getVideoname());
		try {
			VideoBO addVid = new VideoBO();

			success = addVid.insertVideo(vid);

			return json.buildJson(success);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return json.buildJson(success);
		
	}
}
