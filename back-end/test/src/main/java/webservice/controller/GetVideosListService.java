package webservice.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import webservice.bo.UserBO;
import webservice.bo.VideoBO;
import webservice.pojo.UserVO;
import webservice.pojo.VideoVO;
import webservice.utils.JsonUtil;

@Path("/video")
public class GetVideosListService {
	
	/*creator: Truong Vinh Phuc 
	 *function: get videos from database 
	 *date: 11/08/2017
	 *note:
	 */
	@GET
	@Path("/getVideoList/{param}")
	public Response getVid(@PathParam("param") String id) {
		JsonUtil json = new JsonUtil();
		VideoBO getVidList = new VideoBO();
		VideoVO vid = new VideoVO();
		ArrayList<VideoVO> vidList = null;
		try {
			vidList = getVidList.getVideoList(Integer.parseInt(id));
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = json.buildJson(vidList);
		//System.out.println(output);
		return Response.status(200).entity(output).build();
	}
	
	
}
