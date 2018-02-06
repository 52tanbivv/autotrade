package com.space.wxpay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

import javax.servlet.ServletInputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.space.wxpay.demo.WxPayHelper;

public class PayActivity {

	private static Logger logger = LoggerFactory.getLogger(PayActivity.class);

	// 从输入流读取post参数
	public static GenPackageReq readStreamParameter(ServletInputStream in) {
		Scanner scanner = new Scanner(in);
		BufferedReader reader = null;
		GenPackageReq genPackageReq = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			// 1、获取用户发送的信息
			StringBuffer sb = new StringBuffer();
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			// 2、解析用户的信息
			JAXBContext jc = JAXBContext.newInstance(GenPackageReq.class);
			Unmarshaller u = jc.createUnmarshaller();
			logger.error("***************");
			logger.error("readStreamParameter=" + sb.toString());
			logger.error("***************");
			genPackageReq = (GenPackageReq) u.unmarshal(new StringReader(sb
					.toString()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return genPackageReq;
	}

	// 从输入流读取post参数
	public static NotifyUrlPost readNotifyStreamParameter(ServletInputStream in) {
		Scanner scanner = new Scanner(in);
		BufferedReader reader = null;
		NotifyUrlPost notifyUrlPostData = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			// 1、获取用户发送的信息
			StringBuffer sb = new StringBuffer();
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			// 2、解析用户的信息
			JAXBContext jc = JAXBContext.newInstance(NotifyUrlPost.class);
			Unmarshaller u = jc.createUnmarshaller();
			logger.error("***************");
			logger.error("readStreamParameter=" + sb.toString());
			logger.error("***************");
			notifyUrlPostData = (NotifyUrlPost) u.unmarshal(new StringReader(sb
					.toString()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return notifyUrlPostData;
	}

	public static String CreateNativeUrl(Long stuid, Long userid) {
		WxPayHelper wxPayHelper = new WxPayHelper();
		try {

			// 先设置基本信息
			wxPayHelper.SetAppId(PayConstantVar.APP_ID);
			wxPayHelper.SetAppKey(PayConstantVar.APP_KEY);
			wxPayHelper.SetPartnerKey(PayConstantVar.PARTNER_KEY);
			wxPayHelper.SetSignType("sha1");

			return wxPayHelper.CreateNativeUrl(stuid + "_" + userid);
			// System.out.println("生成原生支付package:");
			// System.out.println(wxPayHelper.CreateNativePackage("0", "ok"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
