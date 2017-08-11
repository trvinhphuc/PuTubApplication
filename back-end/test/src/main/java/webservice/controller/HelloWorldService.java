package webservice.controller;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import webservice.bo.SearchBO;
import webservice.bo.UserBO;
import webservice.pojo.UserVO;
import webservice.utils.JsonUtil;

@Path("/hello")
public class HelloWorldService {
	
	@GET
	@Path("/user/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		JsonUtil json = new JsonUtil();
		UserBO s = new UserBO();
		//System.out.println(msg);
		String output = null;
		ArrayList<UserVO> userList = null;
		ArrayList<UserVO> returnList = new ArrayList<UserVO>();
		try {
			
			userList =	s.getAllUsersList();
			for(int i=0 ; i<userList.size() ; i++) {
				UserVO us = new UserVO();
				us = userList.get(i);
				if(us.getUsername().equals(msg)) {
					us.setPassword(null);
					returnList.add(us);
					}
			}
			output = json.buildJson(returnList);
			//System.out.println(output);
			return Response.status(200).entity(output).build();
			
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		output = json.buildJson("No result");
		return Response.status(200).entity(output).build();
		
	}
	
}