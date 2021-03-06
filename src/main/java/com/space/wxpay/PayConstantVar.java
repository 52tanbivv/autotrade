package com.space.wxpay;

public class PayConstantVar {
	// APP_ID 替换为你的应用从官方网站申请到的合法appId
	// 测试
	// public static final String APP_ID = "wxf8b4f85f3a794e77";
	//
	// public static final String APP_KEY =
	// "2Wozy2aksie1puXUBpWD8oZxiD1DfQuEaiC7KcRATv1Ino3mdopKaPGQQ7TtkNySuAmCaDCrw4xhPY5qKTBl7Fzm0RgR3c0WaVYIXZARsxzHV2x7iwPPzOz94dnwPWSn";

	/*** 正式 ****/
	public static final String APP_ID = "wx86c08c26de35ce2e";
	public static final String APP_KEY = "WHk3k3Gqv1EXcLBVxo2mcZ0Y1CfQbyWLlUoNxdPDO7G0spHDWOCJVRk5BHvSl4eOyLdI5FWvmehsgzINvVCznygzSRzPxpeIL52sm5ykqrUj9IIfLsrgDis8M83VEog3";

	public static final String PARTNER_KEY = "06b3fc450dd4af634dca403d8b07ee07";

	public static final String AppSecret = "39ac66e43a1d886dda2bf40ec15b1b55";

	/** 商家向财付通申请的商家id */
	public static final String PARTNER_ID = "1218116801";

	public static final String NATIVE_URL = "weixin://wxpay/bizpayurl?";

	public static class ShowMsgActivity {
		public static final String STitle = "showmsg_title";
		public static final String SMessage = "showmsg_message";
		public static final String BAThumbData = "showmsg_thumb_data";
	}

	// Native（原生）支付回调商户后台获取 package
	/**
	 * 参数说明：（1）AppId，公众帐号的 appid （2）OpenId，点击链接准备购买商品的用户 openid
	 * （3）IsSubscribe，标记用户是否订阅该公众帐号，1 为关注，0 为未关注 （4）ProductId，第三方的商品 ID 号
	 * （5）TimeStamp，时间戳 （6）NonceStr，随机串 （7）AppSignature，参数的加密签名，是根据 2.7
	 * 支付签名（paySign）生成方法中所 讲的签名方式生成的签名 （8）SignMethod，签名方式，目前只支持“SHA1”。该字段不参与签名。
	 **/
	public static final String bizpayRetupackage = "<xml>"
			+ "<AppId><![CDATA[%1$s]]></AppId>"
			+ "<Package><![CDATA[%2$s]]></Package>"
			+ "<TimeStamp>%3$s</TimeStamp>"
			+ "<NonceStr><![CDATA[%4$s]]></NonceStr>"
			+ "<RetCode>%5$s</RetCode> "
			+ "<RetErrMsg><![CDATA[%6$s]]></RetErrMsg>"
			+ "<AppSignature><![CDATA[%7$s]]></AppSignature>"
			+ "<SignMethod><![CDATA[sha1]]></SignMethod>" + "</xml>";

	public static final String payfeedback = "";
}