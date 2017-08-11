package webservice.controller;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import webservice.bo.UserBO;
import webservice.pojo.UserVO;
import webservice.utils.JsonUtil;

@Path("/test2")
public class LogupService {
	JsonUtil json = new JsonUtil();
	/*creator: Truong Vinh Phuc 
	 *function: insert user to database
	 *date: 11/08/2017
	 *note: still not check which information is invalid and return that error to front-end
	 */
	@POST
	@Path("/logup")
	@Consumes(MediaType.APPLICATION_JSON)
	public String logup(String jsonString) {
		boolean success = false;
		//System.out.println(jsonString);
		UserVO users = json.buildObject(jsonString, UserVO.class);
		UserVO user = new UserVO();
		UserBO bo = new UserBO();
		//logup.setUserInfo(users);
		try {
			UserBO logup = new UserBO();
			//System.out.println(json.buildJson(users));
			success = logup.addUser(users);
			if(success) {
				user = bo.getUserID(users.getUsername());
				return json.buildJson(user);
			}
			//System.out.println(json.buildJson(success));
			else return json.buildJson(false);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return json.buildJson(success);
		 
	}
}
