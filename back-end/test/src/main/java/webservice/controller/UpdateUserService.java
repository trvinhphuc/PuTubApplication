package webservice.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import webservice.bo.CommentBO;
import webservice.bo.UserBO;
import webservice.pojo.CommentVO;
import webservice.pojo.UserVO;
import webservice.utils.JsonUtil;

@Path("/user2")
public class UpdateUserService {
	
	/*creator: Truong Vinh Phuc 
	 *function: update user info 
	 *date: 11/08/2017
	 *note:
	 */
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateUserInfo(String jsonString) {
		JsonUtil json = new JsonUtil();
		boolean success = false;
		//System.out.println(jsonString);
		UserVO user = json.buildObject(jsonString, UserVO.class);

		// logup.setUserInfo(users);
		try {
			UserBO update = new UserBO();
			// System.out.println(json.buildJson(users));
			success = update.updateUser(user);

			System.out.println(json.buildJson(success));
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return json.buildJson(success);

	}
	
	/*creator: Truong Vinh Phuc 
	 *function: get user info 
	 *date: 11/08/2017
	 *note:
	 */
	@GET
	@Path("/getUserInfo/{param}")
	public Response getUserInfo(@PathParam("param") String id) {
		JsonUtil json = new JsonUtil();
		UserBO getInfo = new UserBO();
		UserVO user = new UserVO();
		// System.out.println(id);
		try {
			user = getInfo.getUserInfo(Integer.parseInt(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("cannot get info");
			e.printStackTrace();
		}
		user.setPassword(null);
		String output = json.buildJson(user);
		// System.out.println(output);
		return Response.status(200).entity(output).build();

	}
	
	/*creator: Truong Vinh Phuc 
	 *function: change password user info 
	 *date: 11/08/2017
	 *note:
	 */
	@POST
	@Path("/changePass")
	@Consumes(MediaType.APPLICATION_JSON)
	public String changePass(String jsonString) {
		JsonUtil json = new JsonUtil();
		boolean success = false;
		// System.out.println(jsonString);
		UserVO user = json.buildObject(jsonString, UserVO.class);
		UserBO setNewPass = new UserBO();
		// logup.setUserInfo(users);
		try {
			
			success = checkPass(user.getID(),user.getPassword());
			if(success) setNewPass.updatePass(user);
			System.out.println(json.buildJson(success));
			return json.buildJson(success);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return json.buildJson(success);

	}
	/*creator: Truong Vinh Phuc 
	 *function: check old password of user  
	 *date: 11/08/2017
	 *note:
	 */
	private boolean checkPass(int id,String password) {
		UserBO securityManager = new UserBO();
		boolean check = false;
		UserVO user = new UserVO();
			try {
					user = securityManager.getUserInfo(id);
					if(user.getPassword().equals(password)) check = true;
				}
			catch (Exception e) {
			
			e.printStackTrace();
		}
		return check;
		
	}

}
