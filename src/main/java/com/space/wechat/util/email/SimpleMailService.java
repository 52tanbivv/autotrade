package com.space.wechat.util.email;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * 纯文本邮件服务类.
 * 
 * @author calvin
 */
@Component
public class SimpleMailService {
	private static Logger logger = LoggerFactory.getLogger(SimpleMailService.class);

	private JavaMailSenderImpl mailSender;

	private String textTemplate;

	// public final static String mailAddress_hbz = "wxt@spacetech.com.cn";
	public final static String mailAddress_yjf = "dev@weixiaotong.com";

	/**
	 * 发送纯文本的邮件.
	 * 
	 * @param mailAddress
	 *            用户邮箱地址
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 */
	public void sendMail(String mailAddress, String subject, String content) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, CharEncoding.UTF_8);
			message.setTo(mailAddress);
			message.setFrom(mailAddress_yjf);
			message.setSubject(subject);
			message.setText(content, true);
			mailSender.send(mimeMessage);
			//
			// // 将用户名与当期日期格式化到邮件内容的字符串模板
			// String content = String.format(textTemplate, userName, new
			// Date());

			// mailSender.send(msg);
			if (logger.isInfoEnabled()) {
				logger.info("邮件已发送至{}", StringUtils.join(mailAddress, ","));
			}
		} catch (Exception e) {
			logger.error("发送邮件失败", e);
		}
	}

	public void sendMail(Mail mail) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(mailAddress_yjf);
		msg.setTo(mail.getToUser());
		msg.setSubject(mail.getSubject());
		//
		// // 将用户名与当期日期格式化到邮件内容的字符串模板
		// String content = String.format(textTemplate, userName, new Date());
		msg.setText(mail.getContent());

		try {
			mailSender.send(msg);
			if (logger.isInfoEnabled()) {
				logger.info("纯文本邮件已发送至{}", StringUtils.join(msg.getTo(), ","));
			}
		} catch (Exception e) {
			logger.error("发送邮件失败", e);
		}
	}

	/**
	 * Spring的MailSender.
	 */
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 邮件内容的字符串模板.
	 */
	public void setTextTemplate(String textTemplate) {
		this.textTemplate = textTemplate;
	}
}
