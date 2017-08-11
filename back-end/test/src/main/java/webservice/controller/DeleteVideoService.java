package webservice.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import webservice.bo.VideoBO;
import webservice.pojo.VideoVO;
import webservice.utils.JsonUtil;

@Path("/delete")
public class DeleteVideoService {
	
	/*creator: Truong Vinh Phuc 
	 *function: del video from database 
	 *date: 11/08/2017
	 *note: permission has already been checked from front-end
	 */
	@GET
	@Path("/{param}")
	public String deleteVideo(@PathParam("param") String id) {
		JsonUtil json = new JsonUtil();
		boolean success = false;	
		
		try {
			VideoBO delVid = new VideoBO();

			success = delVid.deleteVideo(Integer.parseInt(id));

			return json.buildJson(success);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return json.buildJson(success);
		
	}
}
