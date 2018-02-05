package com.space.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public final static String PARTNER = "2088811092637030";

	// 交易安全检验码，由数字和字母组成的32位字符串
	// 如果签名方式设置为“MD5”时，请设置该参数
	public final static String KEY = "";

	// 商户的私钥
	// 如果签名方式设置为“0001”时，请设置该参数
	public final static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMRV33C8wmQp9jZZIVK3f10dsQPIFSeuuGqmjmQuNm/0FJeZV4Vcqe5nzxwxgD6FSj9eVd9/lgSdwag4CGioKCwvxtFvmLvv7Zrmw2UbMZHl8ow33O7SF8F/6isBHXxMyqaQMj5fFJFfmd7IW1qUPrCOFwGh2wQuQYF5pcGbZLrbAgMBAAECgYEAjFUAtzkEDMn9ZQ1CBziHPYwqaRSLImj9AaX44twxJsYkLdN5viXkhDYI0BMFoiptTmvkHzK3jKchs1YRlJSU2PfYdxzVQy8BAvdAlMviNgVx2OrkFlVzGwiPIOxXPoWDMDLwl8efBUFpJ+R5Ur3uXWI9tyRNvzZWLqODl/V4O3kCQQDmD1V4Ndxfu7weOJ0boojxxEOud+wFl4wbrU66G/M+qOJgSx01mt99vcijNyA02orvcX87KfFisTWiyVfzIWndAkEA2nkVmT4z3xDHifxqgqYw45FV7XLt4Y+OqksjVkep8JhFuvUDKFsq7PT+nri8B6YllWeQ9cBUPSW7rC11AJ2YFwJBAKvWMjnaySL8f7N3B+pXScCDnyYWzsUSgAVAOriKHfLkhEn2cPfUGTC7a7x1dWnk/reAZ88SMglMYcqYZ0fj4kUCQDkVklMsMZmDZN/lGF9y1Stg+jbE5Ose+4P7hgwxRXIcF3w2MNEGoTyw9ya51keZXhCfsvzDeD9R8KuYZQ34H5kCQEQNtn4DChAb92uHuygzxDnDh3CtDdIEfW+h9zZxQJLgf/ZjUYnLWos32MQ6UTgk67y5R77jSQvrfxeeqcp46fU=";

	// 支付宝的公钥
	// 如果签名方式设置为“0001”时，请设置该参数
	public final static String ALI_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYK9nN2U1zCTU2GugNNb9TDJQO3IynaAXjX8ZF IVxZcNrgoJQmIS42nyBFCAdAwcn7N6nzSAfj74kKOFRvHb+9o2RkJ5uofclPf9qehZmOfter+rL2 7OJu8snDfUpyDa0ShwF8Suvs50UXjiewc2e32XJuaCqIlstBQNPJF9NZ/QIDAQAB";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public final static String LOG_PATH = "D:\\";

	// 字符编码格式 目前支持 utf-8
	public final static String INPUT_CHARSET = "utf-8";

	// 签名方式，选择项：0001(RSA)、MD5
	public final static String SIGN_TYPE = "0001";
	// 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA

}
