package com.space.wxpay.card;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class WxCardSign {

	public WxCardSign() {
		m_param_to_sign = new ArrayList<String>();
	}

	public void AddData(String value) {
		m_param_to_sign.add(value);
	}

	public void AddData(Integer value) {
		m_param_to_sign.add(value.toString());
	}

	public static String GetSignature(String appsecret, String card_id,
			String timestamp) {
		ArrayList<String> param_to_sign = new ArrayList<String>();
		param_to_sign.add(appsecret);
		param_to_sign.add(timestamp);
		param_to_sign.add(card_id);// 设置奖项时定义的cardid
		Collections.sort(param_to_sign);
		StringBuilder string_to_sign = new StringBuilder();
		for (String str : param_to_sign) {
			System.out.println("string_to_sign_str:" + str);

			string_to_sign.append(str);
		}
		System.out.println("string_to_sign:" + string_to_sign);
		try {
			MessageDigest hasher = MessageDigest.getInstance("SHA-1");
			byte[] digest = hasher.digest(string_to_sign.toString().getBytes());
			return ByteToHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String ByteToHexString(byte[] data) {
		StringBuilder str = new StringBuilder();
		for (byte b : data) {
			str.append(Integer.toHexString(b & 0xFF));
		}
		return str.toString();
	}

	private ArrayList<String> m_param_to_sign;
}
