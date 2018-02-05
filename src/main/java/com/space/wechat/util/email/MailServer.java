package com.space.wechat.util.email;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.space.wechat.framework.exception.BusinessException;

public class MailServer implements Runnable {

	private static SimpleMailService mailService;

	public static SimpleMailService getMailService() {
		return mailService;
	}

	@Autowired
	public static void setMailService(SimpleMailService _mailService) {
		mailService = _mailService;
	}

	/**
	 * 从总PushMsgQueue消息队列里取数据,并进行处理
	 */
	@Override
	public void run() {

		while (true) {
			try {

				// 、、mailService.sendMail(pageDev.getLoginName(),
				// "【API平台提醒邮件】api参数被修改", resultHtml);

				JSONObject mail = MailQueue.take();
				System.out.print("收到邮件！");
				mailService.sendMail(mail.getString("to"),
						mail.getString("subject"), mail.getString("content"));
			} catch (BusinessException be) {
				// logger.error(be.toString());
				be.printStackTrace();
			} catch (Exception exception) {
				// logger.error(exception.toString());
				exception.printStackTrace();
			}
		}
	}

}
