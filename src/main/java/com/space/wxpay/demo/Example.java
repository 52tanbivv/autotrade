package com.space.wxpay.demo;


public class Example {
	public static void main(String args[]) {
		try {
			WxPayHelper wxPayHelper = new WxPayHelper();
			// 先设置基本信息
			wxPayHelper.SetAppId("wx86c08c26de35ce2e");
			wxPayHelper
					.SetAppKey("WHk3k3Gqv1EXcLBVxo2mcZ0Y1CfQbyWLlUoNxdPDO7G0spHDWOCJVRk5BHvSl4eOyLdI5FWvmehsgzINvVCznygzSRzPxpeIL52sm5ykqrUj9IIfLsrgDis8M83VEog3");
			wxPayHelper.SetPartnerKey("06b3fc450dd4af634dca403d8b07ee07");
			wxPayHelper.SetSignType("sha1");
			// 设置请求package信息
			wxPayHelper.SetParameter("bank_type", "WX");
			wxPayHelper.SetParameter("body", "微校通信息服务费");
			wxPayHelper.SetParameter("attach", "81_100");
			wxPayHelper.SetParameter("partner", "1218116801");
			wxPayHelper.SetParameter("out_trade_no",
					CommonUtil.CreateNoncestr());
			wxPayHelper.SetParameter("total_fee", "100");
			wxPayHelper.SetParameter("fee_type", "1");
			wxPayHelper.SetParameter("notify_url",
					"http://www.weixiaotong.com.cn/wxpay/notifyurl");
			wxPayHelper.SetParameter("spbill_create_ip", "127.0.0.1");
			wxPayHelper.SetParameter("input_charset", "GBK");

			System.out.println("生成app支付package:");
			System.out.println(wxPayHelper.CreateAppPackage("test"));
			System.out.println("生成jsapi支付package:");
			System.out.println(wxPayHelper.CreateBizPackage());
			System.out.println("生成原生支付url:");
			System.out.println(wxPayHelper.CreateNativeUrl("81_100"));
			System.out.println("生成原生支付package:");
			System.out.println(wxPayHelper.CreateNativePackage("0", "ok"));

			String s = "aa!";
			System.out.println(new String(s.getBytes(), "GBK"));
			System.out.println(new String(s.getBytes(), "UTF-8"));
			// System.out.println("生成原生支付url1:="+PayActivity.CreateNativeUrl());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
