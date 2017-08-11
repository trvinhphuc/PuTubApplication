package webservice.pojo;

public class VideoVO {
	private int id;
	private String videoname;
	private String ownername;
	private String date;
	private String channelname;
	private String descript;
	private String tag;
	private byte[] video;
	private byte[] img;
	private String realname;
	private int view_n;
	private int like_n;
	private int dislike_n;
	private String privacy;

	////////////////////////////////////////////
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/////////////////////////////////
	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	/////////////////////////////////
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	/////////////////////////////////

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
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
	public byte[] getVideo() {
		return video;
	}

	public void setVideo(byte[] video) {
		this.video = video;
	}

	///////////////////////////////////
	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	////////////////////////////////////
	public String getDescript() {
		return descript;
	}

	public void setDescript(String des) {
		this.descript = des;
	}

	//////////////////////////////////////
	public String getTag() {
		return tag;
	}

	public void setTag(String tg) {
		this.tag = tg;
	}

	//////////////////////////////////////
	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	////////////////////////////////////
	public int getView_n() {
		return view_n;
	}

	public void setView_n(int num) {
		this.view_n = num;
	}

	////////////////////////////////////
	public int getLike_n() {
		return like_n;
	}

	public void setLike_n(int num) {
		this.like_n = num;
	}

	////////////////////////////////////
	public int getDislike_n() {
		return dislike_n;
	}

	public void setDislike_n(int num) {
		this.dislike_n = num;
	}
}
