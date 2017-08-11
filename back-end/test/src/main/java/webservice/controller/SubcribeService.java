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
import webservice.pojo.UserVO;
import webservice.utils.JsonUtil;

@Path("/sub")
public class SubcribeService {
	JsonUtil json = new JsonUtil();
	
	/*creator: Truong Vinh Phuc 
	 *function: add subcriber to database 
	 *date: 11/08/2017
	 *note:
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public String setSubcriber(String jsonString) {
		boolean success = false;
		//System.out.println(jsonString);
		UserVO user = json.buildObject(jsonString, UserVO.class);
		
		//logup.setUserInfo(users);
		try {
			UserBO sub = new UserBO();
			//System.out.println(json.buildJson(users));
			success = sub.setSubcriber(user);
			
			System.out.println(json.buildJson(success));
			return json.buildJson(success);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return json.buildJson(success);
		 
	}
	
	/*creator: Truong Vinh Phuc 
	 *function: delete a subcriber from database 
	 *date: 11/08/2017
	 *note:
	 */
	@POST
	@Path("/del")
	@Consumes(MediaType.APPLICATION_JSON)
	public String delSubcriber(String jsonString) {
		boolean success = false;
		//System.out.println(jsonString);
		UserVO user = json.buildObject(jsonString, UserVO.class);
		
		//logup.setUserInfo(users);
		try {
			UserBO sub = new UserBO();
			//System.out.println(json.buildJson(users));
			success = sub.delSubcriber(user);
			
			System.out.println(json.buildJson(success));
			return json.buildJson(success);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return json.buildJson(success);
		 
	}
	
	/*creator: Truong Vinh Phuc 
	 *function: get subcribers of a user from database 
	 *date: 11/08/2017
	 *note:
	 */
	@GET
	@Path("/getSubList/{param}")
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response getSubcriberList(@PathParam("param") String id) {
		ArrayList<UserVO> userList = null;
		UserBO user = new UserBO();
		try {
			userList = user.getSubcriberList(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String output = json.buildJson(userList);
		return Response.status(200).entity(output).build();
	}
}
