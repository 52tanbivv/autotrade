package com.space.wxpay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlCDATA;

@XmlRootElement(name = "xml")
public class NotifyUrlPost {

	/**
	 * 用户在成功完成支付后,微信后台通知(POST)商户服务器(notify_url)支付结果
	 */
	/*** 协议参数 ****/
	private String sign_type;// 签名类型,取值:MD5、RSA,默 讣:MD5

	private String service_version;// 接口版本号,默讣为 1.0

	private String input_charset;// 字符编码,取值:GBK、UTF-8,默 讣:GBK。

	private String sign;// 签名

	private int sign_key_index;// 密钥序号,多密钥支持的密钥序号,默讣 1

	/*** 业务参数 ****/
	private int trade_mode;// 1-即时到账 其他保留

	private int trade_state;// 支付结果:0—成功 其他保留

	private String pay_info;// 支付结果信息,支付成功时为空

	private String partner;// 商户号,也即之前步骤的 partnerid, 由微信统一分配的 10 位正整数 (120XXXXXXX)号

	private String bank_type;// 签名 银行类型,在微信中使用 WX
	
	private String bank_billno;//银行订单号
	
	private int total_fee;// 支付金额,单位为分,如果 discount 有值,通知的 total_fee + discount = 请求的 total_fee
	
	private int fee_type;// 现金支付币种,目前只支持人民币, 默讣值是 1-人民币
	
	private String notify_id;//支付结果通知 id,对于某些特定商 户,只返回通知 id,要求商户据此 查询交易结果
	
	private String transaction_id;//交易号,28位长的数值,其中前10 位为商户号,之后 8 位为订单产生 的日期,如 20090415,最后 10 位 是流水号。
	
	private String  out_trade_no;//商户系统的订单号,与请求一致
	
	private String attach;//商户数据包,原样返回
	
	private String time_end;//支付完成时间,格式为 yyyyMMddhhmmss,如 2009 年 12 月 27 日 9 点 10 分 10 秒表示为 20091227091010。时区为 GMT+8 beijing。
	
	private int transport_fee;//物流费用,单位分,默讣 0。如果 有值,必须保证 transport_fee + product_fee = total_fee
	
	private int product_fee;//物品费用,单位分。如果有值,必 须 保 证 transport_fee +product_fee=total_fee

	private int discount;//折扣价格,单位分,如果有值,通 知的 total_fee + discount = 请求的 total_fee
	
	private int buyer_alias;//对应买家账号的一个加密串
	
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
	
	public String getSign_type() {
		return sign_type;
	}
	
	@XmlCDATA
	@XmlElement(name = "sign_type")
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
	public String getService_version() {
		return service_version;
	}
	
	@XmlCDATA
	@XmlElement(name = "service_version")
	public void setService_version(String service_version) {
		this.service_version = service_version;
	}
	
	public String getInput_charset() {
		return input_charset;
	}
	
	@XmlCDATA
	@XmlElement(name = "input_charset")
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	
	public String getSign() {
		return sign;
	}
	
	@XmlCDATA
	@XmlElement(name = "sign")
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public int getSign_key_index() {
		return sign_key_index;
	}
	
	@XmlCDATA
	@XmlElement(name = "sign_key_index")
	public void setSign_key_index(int sign_key_index) {
		this.sign_key_index = sign_key_index;
	}
	
	public int getTrade_mode() {
		return trade_mode;
	}
	
	@XmlCDATA
	@XmlElement(name = "trade_mode")
	public void setTrade_mode(int trade_mode) {
		this.trade_mode = trade_mode;
	}
	
	public int getTrade_state() {
		return trade_state;
	}
	
	@XmlCDATA
	@XmlElement(name = "trade_state")
	public void setTrade_state(int trade_state) {
		this.trade_state = trade_state;
	}
	
	public String getPay_info() {
		return pay_info;
	}
	
	@XmlCDATA
	@XmlElement(name = "pay_info")
	public void setPay_info(String pay_info) {
		this.pay_info = pay_info;
	}
	
	public String getPartner() {
		return partner;
	}
	
	@XmlCDATA
	@XmlElement(name = "partner")
	public void setPartner(String partner) {
		this.partner = partner;
	}
	
	public String getBank_type() {
		return bank_type;
	}
	
	@XmlCDATA
	@XmlElement(name = "bank_type")
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	
	public String getBank_billno() {
		return bank_billno;
	}
	
	@XmlCDATA
	@XmlElement(name = "bank_billno")
	public void setBank_billno(String bank_billno) {
		this.bank_billno = bank_billno;
	}
	
	public int getTotal_fee() {
		return total_fee;
	}
	
	@XmlCDATA
	@XmlElement(name = "total_fee")
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	
	public int getFee_type() {
		return fee_type;
	}
	
	@XmlCDATA
	@XmlElement(name = "fee_type")
	public void setFee_type(int fee_type) {
		this.fee_type = fee_type;
	}
	
	public String getNotify_id() {
		return notify_id;
	}
	
	@XmlCDATA
	@XmlElement(name = "notify_id")
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	
	public String getTransaction_id() {
		return transaction_id;
	}
	
	@XmlCDATA
	@XmlElement(name = "transaction_id")
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	public String getOut_trade_no() {
		return out_trade_no;
	}
	
	@XmlCDATA
	@XmlElement(name = "out_trade_no")
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	public String getAttach() {
		return attach;
	}
	
	@XmlCDATA
	@XmlElement(name = "attach")
	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	public String getTime_end() {
		return time_end;
	}
	
	@XmlCDATA
	@XmlElement(name = "time_end")
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	
	public int getTransport_fee() {
		return transport_fee;
	}
	
	@XmlCDATA
	@XmlElement(name = "transport_fee")
	public void setTransport_fee(int transport_fee) {
		this.transport_fee = transport_fee;
	}
	
	public int getProduct_fee() {
		return product_fee;
	}
	
	@XmlCDATA
	@XmlElement(name = "product_fee")
	public void setProduct_fee(int product_fee) {
		this.product_fee = product_fee;
	}
	
	public int getDiscount() {
		return discount;
	}
	
	@XmlCDATA
	@XmlElement(name = "discount")
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public int getBuyer_alias() {
		return buyer_alias;
	}
	
	@XmlCDATA
	@XmlElement(name = "buyer_alias")
	public void setBuyer_alias(int buyer_alias) {
		this.buyer_alias = buyer_alias;
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
