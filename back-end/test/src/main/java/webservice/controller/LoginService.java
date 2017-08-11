package webservice.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import webservice.bo.UserBO;

import webservice.pojo.UserVO;
import webservice.utils.JsonUtil;

@Path("/test")
public class LoginService {
	JsonUtil json = new JsonUtil();

	/*creator: Truong Vinh Phuc 
	 *function: catch username and password 
	 *date: 11/08/2017
	 *note:
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)

	public String login(String jsonString) {
		UserVO user = json.buildObject(jsonString, UserVO.class);

		String username = user.getUsername();
		String password = user.getPassword();
		return isLogin(username, password);

	}
	
	/*creator: Truong Vinh Phuc 
	 *function: check username or email and password 
	 *date: 11/08/2017
	 *note: prevent SQL injection hacking
	 */
	public String isLogin(String username, String password) {

		// String userListData = null;
		try {
			ArrayList<UserVO> userList = null;
			UserBO securityManager = new UserBO();
			userList = securityManager.getAllUsersList();
			for (UserVO userVO : userList) {
				if (userVO.getPassword().equals(password)) {
					if (userVO.getUsername().equals(username)) {

						userVO.setPassword(null);
						String output = json.buildJson(userVO);
						return output;
					}
					else if(userVO.getEmail().equals(username)) {
						userVO.setPassword(null);
						String output = json.buildJson(userVO);
						return output;
					}
				}
			}

		} catch (Exception e) {
			System.out.println("error");
		}
		// return json.buildJson("You are not a Valid User");
		return json.buildJson("false");
		// return false;
	}
}
