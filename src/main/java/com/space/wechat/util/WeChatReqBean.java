package com.space.wechat.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlCDATA;

@XmlRootElement(name = "xml")
public class WeChatReqBean {
	private String toUserName;
	private String fromUserName;
	private Long createTime;
	private String msgType;
	private String content;
	private String event;
	private String eventKey;
	private String picUrl;
	private String mediaId;

	private Double location_X;
	private Double location_Y;
	private Integer scale;
	private String label;

	private String msgId;

	private String recognition;

	private String failtime;// 失败发生时间
	private String failreason;// 认证失败的原有
	private String expiredtime;// 过期时间

	public String getFailtime() {
		return failtime;
	}

	@XmlCDATA
	@XmlElement(name = "FailTime")
	public void setFailtime(String failtime) {
		this.failtime = failtime;
	}

	public String getFailreason() {
		return failreason;
	}

	@XmlCDATA
	@XmlElement(name = "FailReason")
	public void setFailreason(String failreason) {
		this.failreason = failreason;
	}

	public String getExpiredtime() {
		return expiredtime;
	}

	@XmlCDATA
	@XmlElement(name = "ExpiredTime")
	public void setExpiredtime(String expiredtime) {
		this.expiredtime = expiredtime;
	}

	public String getEvent() {
		return event;
	}

	@XmlCDATA
	@XmlElement(name = "Event")
	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	@XmlCDATA
	@XmlElement(name = "EventKey")
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getToUserName() {
		return toUserName;
	}

	@XmlCDATA
	@XmlElement(name = "ToUserName")
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	@XmlCDATA
	@XmlElement(name = "FromUserName")
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	@XmlElement(name = "CreateTime")
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	@XmlCDATA
	@XmlElement(name = "MsgType")
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	@XmlCDATA
	@XmlElement(name = "Content")
	public void setContent(String content) {
		this.content = content;
	}

	public Double getLocation_X() {
		return location_X;
	}

	@XmlElement(name = "Location_X")
	public void setLocation_X(Double location_X) {
		this.location_X = location_X;
	}

	public Double getLocation_Y() {
		return location_Y;
	}

	@XmlElement(name = "Location_Y")
	public void setLocation_Y(Double location_Y) {
		this.location_Y = location_Y;
	}

	public Integer getScale() {
		return scale;
	}

	@XmlElement(name = "Scale")
	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	@XmlCDATA
	@XmlElement(name = "Label")
	public void setLabel(String label) {
		this.label = label;
	}

	public String getMsgId() {
		return msgId;
	}

	@XmlElement(name = "MsgId")
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	@XmlElement(name = "PicUrl")
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	@XmlElement(name = "MediaId")
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getRecognition() {
		return recognition;
	}

	@XmlElement(name = "Recognition")
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

}
