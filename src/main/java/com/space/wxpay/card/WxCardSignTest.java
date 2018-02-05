package com.space.wxpay.card;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class WxCardSignTest {
	public static void main(String[] args) throws Exception {
		String appsecret_ghwxt = "39ac66e43a1d886dda2bf40ec15b1b55";// 第三方用户唯一凭证密钥appsecret目前只有光海微校通申请卡券，统一使用这个参数
		String timestamp = Long.toString(new Date().getTime() / 1000);// 时间戳
		String card_id = "p-TXljg5ZcHI0-XqEVjFUmldowlo";// 设置奖项时定义的cardid

		String signature = WxCardSign.GetSignature(appsecret_ghwxt, card_id,
				timestamp);

		System.out.println(signature);
	}

}