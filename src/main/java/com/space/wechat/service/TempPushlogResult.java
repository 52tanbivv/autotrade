package com.space.wechat.service;

/**
 * PUSHLOG的临时存储对象
 * 
 * @author yejianfei
 *
 */
public class TempPushlogResult implements java.io.Serializable {

	public TempPushlogResult(int state, String returnmsg, String sendtime,
			Long id, String hashkey, int isdeleted, String puttime) {
		this.state = state;
		this.returnmsg = returnmsg;
		this.sendtime = sendtime;
		this.id = id;
		this.hashkey = hashkey;
		this.isdeleted = isdeleted;
		this.puttime = puttime;
	}

	public TempPushlogResult() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 989342892891212131L;

	private int state;
	private String returnmsg;
	private String sendtime;
	private Long id;
	private String hashkey;
	private int isdeleted;
	private String puttime;

	public String getPuttime() {
		return puttime;
	}

	public void setPuttime(String puttime) {
		this.puttime = puttime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getReturnmsg() {
		return returnmsg;
	}

	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHashkey() {
		return hashkey;
	}

	public void setHashkey(String hashkey) {
		this.hashkey = hashkey;
	}

	public int getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
