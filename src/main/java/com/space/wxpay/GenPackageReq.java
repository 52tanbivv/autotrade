package com.space.wxpay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlCDATA;

@XmlRootElement(name = "xml")
public class GenPackageReq {

	/**
	 * 在公众平台接到用户点击上述特殊 Native(原生)支付的 URL 之后,会调用注册时填 写的商家获取订单 Package 的回调 URL。
	 * 假设回调 URL 为 https://www.outdomain.com/cgi-bin/bizpaygetpackage
	 * 微信公众平台调用时会使用 Post 方式,推送 xml 格式的 PostData
	 */

	/**
	 * 公众帐号的 appid
	 */
	private String AppId;
	/**
	 * 点击链接准备购买商品的用户 openid
	 */
	private String OpenId;
	/**
	 * IsSubscribe,标记用户是否订阅该公众帐号, 1 为关注,0 为未关注
	 */
	private String IsSubscribe;
	/**
	 * 第三方的商品 ID 号
	 */
	private String ProductId;
	/**
	 * 时间戳
	 */
	private String TimeStamp;
	/**
	 * 随机串
	 */
	private String NonceStr;
	/**
	 * 参数的加密签名,是根据 2.7 支付签名( paySign)生成方法中所讲的签名方式生成的签名
	 */
	private String AppSignature;
	/**
	 * 签名方式,目前只支持 “SHA1”。该字段不参与签名。
	 */
	private String SignMethod;

	public String getAppId() {
		return AppId;
	}

	@XmlCDATA
	@XmlElement(name = "AppId")
	public void setAppId(String appId) {
		AppId = appId;
	}

	public String getOpenId() {
		return OpenId;
	}

	@XmlCDATA
	@XmlElement(name = "OpenId")
	public void setOpenId(String openId) {
		OpenId = openId;
	}

	public String getIsSubscribe() {
		return IsSubscribe;
	}

	@XmlCDATA
	@XmlElement(name = "IsSubscribe")
	public void setIsSubscribe(String isSubscribe) {
		IsSubscribe = isSubscribe;
	}

	public String getProductId() {
		return ProductId;
	}

	@XmlCDATA
	@XmlElement(name = "ProductId")
	public void setProductId(String productId) {
		ProductId = productId;
	}

	public String getTimeStamp() {
		return TimeStamp;
	}

	@XmlCDATA
	@XmlElement(name = "TimeStamp")
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}

	public String getNonceStr() {
		return NonceStr;
	}

	@XmlCDATA
	@XmlElement(name = "NonceStr")
	public void setNonceStr(String nonceStr) {
		NonceStr = nonceStr;
	}

	public String getAppSignature() {
		return AppSignature;
	}

	@XmlCDATA
	@XmlElement(name = "AppSignature")
	public void setAppSignature(String appSignature) {
		AppSignature = appSignature;
	}

	public String getSignMethod() {
		return SignMethod;
	}

	@XmlCDATA
	@XmlElement(name = "SignMethod")
	public void setSignMethod(String signMethod) {
		SignMethod = signMethod;
	}

}
