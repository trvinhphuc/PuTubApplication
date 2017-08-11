package webservice.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import webservice.bo.CommentBO;
import webservice.bo.UserBO;
import webservice.pojo.CommentVO;
import webservice.pojo.UserVO;
import webservice.utils.JsonUtil;

@Path("/comment")
public class CommentService {
	JsonUtil json = new JsonUtil();
	
	/*creator: Truong Vinh Phuc 
	 *function: get comments from database 
	 *date: 11/08/2017
	 *note:
	 */
	@GET
	@Path("/getComments/{param}")
	public Response getComments(@PathParam("param") String id) {
		
		CommentBO cmt = new CommentBO();
		ArrayList<CommentVO> commentList = new ArrayList<CommentVO>();
		//System.out.println(id);
		try {
			commentList = cmt.getCommentList(Integer.parseInt(id));
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("cannot get comments");
			e.printStackTrace();
		}
		String output = json.buildJson(commentList);
		//System.out.println(output);
		return Response.status(200).entity(output).build();
		
	}
	
	/*creator: Truong Vinh Phuc 
	 *function: set comments to database 
	 *date: 11/08/2017
	 *note:
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public String setComment(String jsonString) {
		boolean success = false;
		//System.out.println(jsonString);
		CommentVO comment = json.buildObject(jsonString, CommentVO.class);
		
		//logup.setUserInfo(users);
		try {
			CommentBO cmt = new CommentBO();
			//System.out.println(json.buildJson(users));
			comment.getContent();
			success = cmt.setComment(comment);
			
			System.out.println(json.buildJson(success));
			//return json.buildJson(success);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return json.buildJson(success);
		 
	}
	
	/*creator: Truong Vinh Phuc 
	 *function: delete comments from database 
	 *date: 11/08/2017
	 *note:
	 */
	@POST
	@Path("/del")
	@Consumes(MediaType.APPLICATION_JSON)
	public String delComment(String jsonString) {
		boolean success = false;
		//System.out.println(jsonString);
		CommentVO comment = json.buildObject(jsonString, CommentVO.class);
		
		//logup.setUserInfo(users);
		try {
			CommentBO cmt = new CommentBO();
			//System.out.println(json.buildJson(users));
			success = cmt.delComment(comment);
			
			System.out.println(json.buildJson(success));
			return json.buildJson(success);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return json.buildJson(success);
	}
}
