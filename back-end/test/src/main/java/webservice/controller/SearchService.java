package webservice.controller;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import webservice.bo.UserBO;
import webservice.bo.VideoBO;
import webservice.pojo.UserVO;
import webservice.pojo.VideoVO;
import webservice.utils.JsonUtil;

@Path("/search")
public class SearchService {
	////// these function needs to be improved, it just can search one type at a time
	
	/*creator: Truong Vinh Phuc 
	 *function: search users
	 *date: 11/08/2017
	 *note: search engine still not optimal, just a Sequential search
	 */
	@GET
	@Path("/user/{param}")
	public Response searchUsers(@PathParam("param") String msg) {
		JsonUtil json = new JsonUtil();
		UserBO s = new UserBO();
		// System.out.println(msg);
		String output = null;
		ArrayList<UserVO> userList = null;
		ArrayList<UserVO> returnList = new ArrayList<UserVO>();
		try {

			userList = s.getAllUsersList();
			for (int i = 0; i < userList.size(); i++) {
				UserVO us = new UserVO();
				us = userList.get(i);
				if (us.getUsername().contains(msg)) {
					us.setPassword(null);
					returnList.add(us);
				}
			}
			if (!returnList.isEmpty()) {
				output = json.buildJson(returnList);
				return Response.status(200).entity(output).build();
			}
			// System.out.println(output);

		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		output = json.buildJson("No result");
	
		return Response.status(200).entity(output).build();

	}
	
	/*creator: Truong Vinh Phuc 
	 *function: search videos with videoname and tag
	 *date: 11/08/2017
	 *note: search engine still not optimal, just a Sequential search
	 */
	@GET
	@Path("/video/{param}")
	public Response searchVideos(@PathParam("param") String msg) {
		JsonUtil json = new JsonUtil();
		VideoBO s = new VideoBO();
		// System.out.println(msg);
		String output = null;
		ArrayList<VideoVO> videoList = null;
		ArrayList<VideoVO> returnList = new ArrayList<VideoVO>();
		try {

			videoList = s.getAllVideo();
			for (int i = 0; i < videoList.size(); i++) {
				VideoVO us = new VideoVO();
				us = videoList.get(i);
				if (us.getVideoname().contains(msg)) {
					returnList.add(us);
				}
				else if(us.getTag().contains(msg)) {
					returnList.add(us);
				}
			}
			if (!returnList.isEmpty()) {
				output = json.buildJson(returnList);
				return Response.status(200).entity(output).build();
			}

		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		output = json.buildJson("No result");
		return Response.status(200).entity(output).build();

	}

}
