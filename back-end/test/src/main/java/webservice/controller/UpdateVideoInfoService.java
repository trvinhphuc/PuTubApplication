package webservice.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import webservice.bo.VideoBO;
import webservice.pojo.VideoVO;
import webservice.utils.JsonUtil;

@Path("/video4")
public class UpdateVideoInfoService {
	
	/*creator: Truong Vinh Phuc 
	 *function: update video info  
	 *date: 11/08/2017
	 *note:
	 */
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateVidInfo(String jsonString) {
		JsonUtil json = new JsonUtil();
		boolean success = false;
		
		VideoVO vid = json.buildObject(jsonString, VideoVO.class);

		// System.out.println(vid.getVideoname());
		try {
			VideoBO up = new VideoBO();
			success = up.updateVideoInfo(vid);
			return json.buildJson(success);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return json.buildJson(success);
		
	}
}
