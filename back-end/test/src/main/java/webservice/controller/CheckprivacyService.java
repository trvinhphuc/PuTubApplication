package webservice.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import webservice.bo.UserBO;
import webservice.bo.VideoBO;
import webservice.pojo.UserVO;
import webservice.pojo.VideoVO;
import webservice.utils.JsonUtil;

@Path("/checkPriv")
public class CheckprivacyService {
	
	/*creator: Truong Vinh Phuc 
	 *function: catch video_id and user_id then check that user can watch that video or not
	 *date: 11/08/2017
	 *note: using "contains" to check the array, so still have bugs, needs to improved (the privacy is a String that contains
	 *all user name and they are separated by ",")
	 */
	
	@GET
	//@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkPrivacy(@QueryParam("video_id") String video_id, @QueryParam("user_id") String user_id) {
		JsonUtil json = new JsonUtil();
		boolean success = false;
		//System.out.println(video_id + "and" + user_id);
		try {
			VideoBO vbo = new VideoBO();
			UserBO ubo = new UserBO();
			VideoVO vid = new VideoVO();
			UserVO user = new UserVO();
			vid = vbo.getVideoInfo(Integer.parseInt(video_id));
			if(Integer.parseInt(user_id)!=0)user = ubo.getUserInfo(Integer.parseInt(user_id));
			//System.out.println(vid.getPrivacy());
			if(vid.getPrivacy().equals("0")) {
				
				success = true;
				return json.buildJson(success);
			}
			else if(vid.getPrivacy().contains(user.getUsername())) {
				success = true;
				return json.buildJson(success);
			}
			// System.out.println(json.buildJson(success));
			 
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return json.buildJson(success);

	}
}
