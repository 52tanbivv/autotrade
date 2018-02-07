package com.space.wechat.util.email;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class MailQueue {

	public static BlockingQueue<JSONObject> mailQueue = new ArrayBlockingQueue<JSONObject>(10 * 1024);

	private static Logger logger = LoggerFactory.getLogger(MailQueue.class);

	public static void put(String to, String subject, String content) {
		JSONObject mail = new JSONObject();
		mail.put("to", to);
		mail.put("subject", subject);
		mail.put("content", content);
		try {
			logger.info("发送邮件：" + mail.toJSONString());
			MailQueue.put(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void put(JSONObject mail) throws InterruptedException {
		logger.info("发送邮件：" + mail.toJSONString());
		mailQueue.put(mail);
		logger.info("  更新后当前mailQueue队列数量" + mailQueue.size());
	}

	public static final String mailSubject = "【研发管理平台邮件提醒】";

	public static String makeMailContent(String msg) {
		return "<div style='display:flex;flex-direction:columns;padding:10px;align-items:center'>"
				+ "<div style='display: -webkit-box;   display: -ms-flexbox;  display: flex;  background: #f6f8fa; border: 1px solid #d1d5da; border-radius: 4px; margin: 10px 0px 10px 10px;  box-shadow: 0 2px 3px rgba(0,47,55,.09);padding:15px 20px;width:100%; '>"
				+ msg + "</div></div>";
	}

	/**
	 * 
	 * @return
	 */
	public static JSONObject take() {
		try {
			return mailQueue.take();
		} catch (InterruptedException e) {
			return null;
		}
	}

	public static BlockingQueue<JSONObject> getFeedbackQueue() {
		return mailQueue;
	}

	public static void setFeedbackQueue(BlockingQueue<JSONObject> feedbackQueue) {
		MailQueue.mailQueue = feedbackQueue;
	}

}
