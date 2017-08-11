package webservice.pojo;

//import java.util.*;

public class UserVO {
	private int id;
	private String username;
	private String password;
	private String newpass;
	private String email;
	private String date;
	private String channelname;
	private byte[] img;
	private String ava;
	private String subname;
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/////////////////////////////////
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	///////////////////////////////
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//////////////////////////////////
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//////////////////////////////////
	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String pass) {
		this.newpass = pass;
	}


	/////////////////////////////////
	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	//////////////////////////////////
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	///////////////////////////////////
	public byte[] getAvatar() {
		return img;
	}

	public void setAvatar(byte[] img) {
		this.img = img;
	}

	/////////////////////////////////
	public String getAva() {
		return ava;
	}

	public void setAva(String ava) {
		this.ava = ava;
	}
	///////////////////////////////
	public String getSubname() {
		return subname;
	}

	public void setSubname(String username) {
		this.subname = username;
	}
}