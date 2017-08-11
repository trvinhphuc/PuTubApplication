package webservice.controller;

import java.awt.Image;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import webservice.bo.UserBO;
import webservice.pojo.UserVO;
import webservice.utils.JsonUtil;

@Path("/ava")
public class AvatarService {
	
	/*creator: Truong Vinh Phuc 
	 *function: get avatar from database and send to front-end 
	 *date: 11/08/2017
	 *note: avatar is read as base64 code
	 */
	@GET
	@Path("/{param}")
	//@Produces(MediaType.)
	public Response getMsg(@PathParam("param") String id) {
		JsonUtil json = new JsonUtil();
		UserBO getAva = new UserBO();
		UserVO user = new UserVO();
		//Image img = null;
		try {
			user = getAva.getAvatar(Integer.parseInt(id));
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = json.buildJson(user);
		//System.out.println(output);
		return Response.status(200).entity(output).build();
		
	}
	
}
