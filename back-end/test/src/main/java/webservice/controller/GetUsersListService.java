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


@Path("/user")
public class GetUsersListService {
	JsonUtil json = new JsonUtil();

	/*creator: Truong Vinh Phuc 
	 *function: get all users from database 
	 *date: 11/08/2017
	 *note:
	 */
	@GET
	@Path("/getUserList")
	public Response getUsersList() {
		ArrayList<UserVO> userList = null;
		UserBO user = new UserBO();
		try {
			userList = user.getAllUsersList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (UserVO userVO : userList) {
			userVO.setPassword(null);
			if(userVO.getUsername()=="admin") {
				userVO.setUsername(null);
				userVO.setId(0);}
		}
		String output = json.buildJson(userList);
		return Response.status(200).entity(output).build();
	}

}
