package webservice.pojo;

public class CommentVO {
	private int id_user;
	private int id_video;
	private String date;
	private String content;
	private String username;
	///////////////////////////
	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id) {
		this.id_user = id;
	}

	/////////////////////////////
	public int getId_video() {
		return id_video;
	}

	public void setId_video(int id) {
		this.id_video = id;
	}

	////////////////////////////
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	////////////////////////////
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	//////////////////////////////
	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}
}
