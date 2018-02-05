package com.space.wechat.common;

public class ConstantVar {

	public static String VERSION = "3.0";

	public static String RAP_URL = "http://10.10.10.190:8280/rap.plugin.js";

	public static String RAP_MODE = "3";

	/*************************** PUSH 参数 *********************************/
	/**
	 * 状态在用
	 */
	public static final int STATE_OPEN = 1;
	/**
	 * 状态1 在用，0停用
	 */
	public static final int STATE_STOP = 0;

	/**
	 * 性别-男
	 */
	public static final int SEX_BOY = 1;
	public static final String SEX_CH_BOY = "男";

	/**
	 * 性别-女
	 */
	public static final int SEX_GIRL = 2;
	public static final String SEX_CH_GIRL = "女";

	/**
	 * 默认头像-男
	 */
	public static final String PORTRAIT_BOY = "http://jeff-ye-1978.qiniudn.com/boyteacher.jpg";

	/**
	 * 默认头像-女
	 */
	public static final String PORTRAIT_GIRL = "http://jeff-ye-1978.qiniudn.com/girlteacher.jpg";

	public static final String KEY_ERROR_CODE = "errcode";
	public static final String KEY_ERROR_MSG = "errmsg";
	public static final String AES_KEY = "J90a*&j_~!ks^T4j";
	public static final String STR_SPLIT = "::::::::::";
	public static final String STR_SPLIT_SHORT = ":::";

	public static final String CHANNEL_TYPE_CRM = "crm";// crm
	public static final String CHANNEL_TYPE_TEMPLATE = "template";// 模版推送
	public static final String CHANNEL_TYPE_PHONESMS = "phoneSMS";// 短信通道
	public static final String CHANNEL_TYPE_YIXIN_CRM = "yixinCRM";// 易信通道
	public static final String CHANNEL_TYPE_WXPOSTFILE = "wxPostFile";// 易信通道
	public static final String CHANNEL_TYPE_GROUPS = "groups"; // 用户相关管理

	public static final String EVENT_SENDMGS = "TEMPLATESENDJOBFINISH";
	public static final String EVENT_CLICK = "CLICK";
	public static final String EVENT_VIEW = "VIEW";
	public static final String EVENT_SUBSCRIBE = "subscribe";
	public static final String EVENT_SCAN = "SCAN";
	public static final String EVENT_QRSCENE = "qrscene";

	public static final Integer CHANNEL_TYPE_YIXIN_CODE = 1;// 易信，当前只有安徽新华幼儿园采用易信
	public static final Integer CHANNEL_TYPE_WECHAT_CODE = 0;// 微信
	public static final Integer CHANNEL_TYPE_CROP_CODE = 2;// 微信企业号

	/** 开关配置参数 */
	public static final String CONFIG_VALUE_USED = "1"; // 开通状态
	public static final String CONFIG_VALUE_STOP = "0"; // 停用状态

	public static final String CONFIG_VALUE_TIME = "15:05:00";

	public static final Long CONFIG_CODE_YZXX = 107109l;
	public static final String CONFIG_NAME_YZXX = "园长信箱";
	public static final String CONFIG_REMARK_YZXX = "对外的园长信箱开启或关闭";

	public static final Long CONFIG_CODE_COMMENT_BJQ = 207101404l;
	public static final String CONFIG_NAME_COMMENT_BJQ = "班级圈评论开关";
	public static final String CONFIG_REMARK_COMMENT_BJQ = "班级圈评论的开启或关闭";

	public static final Long CONFIG_CODE_COMMENT_STUDY = 207103104l;
	public static final String CONFIG_NAME_COMMENT_STUDY = "课堂动态评论开关";
	public static final String CONFIG_REMARK_COMMENT_STUDY = "课堂动态的评论功能的开启或关闭";

	// 培训机构，没有食谱。不设置
	public static final Long CONFIG_CODE_COMMENT_MZSP = 10710704l;
	public static final String CONFIG_NAME_COMMENT_MZSP = "食谱评论开关";
	public static final String CONFIG_REMARK_COMMENT_MZSP = "食谱评论的开启或关闭";

	// 新闻动态评论的开关
	public static final Long CONFIG_CODE_COMMENT_NEWS = 10710301L;

	// 中小学：教育资讯 培训班：培训资讯
	public static final Long CONFIG_CODE_COMMENT_YEZS = 107104204l;
	public static final String CONFIG_NAME_COMMENT_YEZS = "育儿知识评论开关";
	public static final String CONFIG_REMARK_COMMENT_YEZS = "育儿知识评论的开启或关闭";
	public static final String CONFIG_NAME_COMMENT_JYZX = "教育资讯评论开关";
	public static final String CONFIG_REMARK_COMMENT_JYZX = "教育资讯评论的开启或关闭";
	public static final String CONFIG_NAME_COMMENT_PXZX = "培训资讯评论开关";
	public static final String CONFIG_REMARK_COMMENT_PXZX = "培训资讯评论的开启或关闭";

	public static final Long CONFIG_CODE_BJQ_TXL = 307102l;
	public static final String CONFIG_NAME_BJQ_TXL = "消息中心班级通讯录";
	public static final String CONFIG_REMARK_BJQ_TXL = "消息中心对班级通讯录发送消息的权限开启或关闭";
	public static final Long CONFIG_CODE_MZSP = 201l;
	public static final String CONFIG_NAME_MZSP = "每周食谱";

	public static final String CONFIG_CODE_APP_VERSION = "APP_VERSION";

	public static final String CONFIG_CODE_APP_DOWNLOAD_URL = "APP_DOWNLOAD_URL";

	public static final String CONFIG_CODE_APP_DESCRIPTION = "APP_DESCRIPTION";

	public static final String CONFIG_CODE_APP_LASTVERSION = "APP_LASTVERSION";

	public static final String CONFIG_CODE_APP_VERSION_FOR_PARENT = "APP_VERSION_FOR_PARENT";

	public static final String CONFIG_CODE_APP_DOWNLOAD_URL_FOR_PARENT = "APP_DOWNLOAD_URL_FOR_PARENT";

	public static final String CONFIG_CODE_APP_DESCRIPTION_FOR_PARENT = "APP_DESCRIPTION_FOR_PARENT";

	public static final String CONFIG_CODE_APP_LASTVERSION_FOR_PARENT = "APP_LASTVERSION_FOR_PARENT";
	/**
	 * 为园长/管理员 每日15:00推送应用统计消息，点击进入应用统计
	 */
	public static final Long CONFIG_CODE_APP_STATISTICAL = 401L;

	public static final String CONFIG_NAME_APP_STATISTICAL = "校长/管理员汇报";
	public static final String CONFIG_REMARK_APP_STATISTICAL = "每天18:30微信推送当天的全园老师考勤、应用统计。";
	/**
	 * 为带班老师 每日9:00推送应用提醒消息（学生考勤），点击进入我的学生,每周一9:00推送应用提醒消息（个人信息），点击进入个人中心
	 */
	public static final Long CONFIG_CODE_APP_REMIND = 402L;
	public static final String CONFIG_NAME_MYSTUDENT = "教师汇报";
	public static final String CONFIG_REMARK_MYSTUDENT = "每天09:00微信推送当天的学生考勤；每周一09:00微信推送老师的上一周应用统计。";

	/**
	 * 打赏开关
	 */
	public static final String CONFIG_CODE_REWARD = "1030001";
	public static final String CONFIG_DEFAULT_VALUE_REWARD = "1";
	/**
	 * 打赏下限
	 */
	public static final String CONFIG_CODE_REWARD_MIN = "1030002";
	public static final String CONFIG_DEFAULT_VALUE_REWARD_MIN = "1";
	/**
	 * 打赏上限
	 */
	public static final String CONFIG_CODE_REWARD_MAX = "1030003";
	public static final String CONFIG_DEFAULT_VALUE_REWARD_MAX = "2";
	/**
	 * 悬赏下限
	 */
	public static final String CONFIG_CODE_CASH_MIN = "1030004";
	public static final String CONFIG_DEFAULT_VALUE_CASH_MIN = "10";
	/**
	 * 悬赏上限
	 */
	public static final String CONFIG_CODE_CASH_MAX = "1030005";
	public static final String CONFIG_DEFAULT_VALUE_CASH_MAX = "200";

	/**
	 * 考勤时间-早上-开始时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_AM_STARTTIME = 20710501l;
	public final static String CONFIG_NAME_ATTENDANCETIME_AM_STARTTIME = "考勤时间上午开始时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_AM_STARTTIME = "09:00:00";

	/**
	 * 考勤时间-早上-结束时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_AM_ENDTIME = 20710502l;
	public final static String CONFIG_NAME_ATTENDANCETIME_AM_ENDTIME = "考勤时间上午结束时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_AM_ENDTIME = "11:30:00";

	/**
	 * 考勤时间-下午-开始时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_PM_STARTTIME = 20710503l;
	public final static String CONFIG_NAME_ATTENDANCETIME_PM_STARTTIME = "考勤时间下午开始时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_PM_STARTTIME = "13:00:00";

	/**
	 * 考勤时间-下午-结束时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_PM_ENDTIME = 20710504l;
	public final static String CONFIG_NAME_ATTENDANCETIME_PM_ENDTIME = "考勤时间下午节数时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_PM_ENDTIME = "17:00:00";

	/**
	 * 考勤时间-早上-时段一-开始时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_MORNING_ONE_STARTTIME = 20710511l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_MORNING_ONE_STARTTIME = 20710611l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_MORNING_ONE_STARTTIME = "考勤时间老师早上时段一开始时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_MORNING_ONE_STARTTIME = "考勤时间学生早上时段一开始时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_MORNING_ONE_STARTTIME = "06:00";

	/**
	 * 考勤时间-早上-时段一-结束时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_MORNING_ONE_ENDTIME = 20710512l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_MORNING_ONE_ENDTIME = 20710612l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_MORNING_ONE_ENDTIME = "考勤时间老师早上时段一结束时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_MORNING_ONE_ENDTIME = "考勤时间学生早上时段一结束时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_MORNING_ONE_ENDTIME = "09:00";

	/**
	 * 考勤时间-早上-时段二-开始时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_MORNING_TWO_STARTTIME = 20710521l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_MORNING_TWO_STARTTIME = 20710621l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_MORNING_TWO_STARTTIME = "考勤时间老师早上时段二开始时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_MORNING_TWO_STARTTIME = "考勤时间学生早上时段二开始时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_MORNING_TWO_STARTTIME = "09:00";

	/**
	 * 考勤时间-早上-时段二-结束时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_MORNING_TWO_ENDTIME = 20710522l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_MORNING_TWO_ENDTIME = 20710622l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_MORNING_TWO_ENDTIME = "考勤时间老师早上时段二结束时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_MORNING_TWO_ENDTIME = "考勤时间学生早上时段二结束时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_MORNING_TWO_ENDTIME = "12:00";

	/**
	 * 考勤时间-下午-时段一-开始时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NOON_ONE_STARTTIME = 20710531l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NOON_ONE_STARTTIME = 20710631l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NOON_ONE_STARTTIME = "考勤时间老师下午时段一开始时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NOON_ONE_STARTTIME = "考勤时间学生下午时段一开始时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NOON_ONE_STARTTIME = "12:00";

	/**
	 * 考勤时间-下午-时段一-结束时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NOON_ONE_ENDTIME = 20710532l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NOON_ONE_ENDTIME = 20710632l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NOON_ONE_ENDTIME = "考勤时间老师下午时段一结束时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NOON_ONE_ENDTIME = "考勤时间学生下午时段一结束时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NOON_ONE_ENDTIME = "15:00";

	/**
	 * 考勤时间-下午-时段二-开始时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NOON_TWO_STARTTIME = 20710541l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NOON_TWO_STARTTIME = 20710641l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NOON_TWO_STARTTIME = "考勤时间老师下午时段二开始时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NOON_TWO_STARTTIME = "考勤时间学生下午时段二开始时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NOON_TWO_STARTTIME = "15:00";

	/**
	 * 考勤时间-下午-时段二-结束时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NOON_TWO_ENDTIME = 20710542l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NOON_TWO_ENDTIME = 20710642l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NOON_TWO_ENDTIME = "考勤时间老师下午时段二结束时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NOON_TWO_ENDTIME = "考勤时间学生下午时段二结束时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NOON_TWO_ENDTIME = "18:00";

	/**
	 * 考勤时间-晚上-时段一-开始时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NIGHT_ONE_STARTTIME = 20710551l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NIGHT_ONE_STARTTIME = 20710651l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NIGHT_ONE_STARTTIME = "考勤时间老师晚上时段一开始时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NIGHT_ONE_STARTTIME = "考勤时间学生晚上时段一开始时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NIGHT_ONE_STARTTIME = "18:00";

	/**
	 * 考勤时间-晚上-时段一-结束时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NIGHT_ONE_ENDTIME = 20710552l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NIGHT_ONE_ENDTIME = 20710652l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NIGHT_ONE_ENDTIME = "考勤时间老师晚上时段一结束时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NIGHT_ONE_ENDTIME = "考勤时间学生晚上时段一结束时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NIGHT_ONE_ENDTIME = "21:00";

	/**
	 * 考勤时间-晚上-时段二-开始时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NIGHT_TWO_STARTTIME = 20710561l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NIGHT_TWO_STARTTIME = 20710661l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NIGHT_TWO_STARTTIME = "考勤时间老师晚上时段二开始时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NIGHT_TWO_STARTTIME = "考勤时间学生晚上时段二开始时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NIGHT_TWO_STARTTIME = "21:00";

	/**
	 * 考勤时间-晚上-时段二-结束时间
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NIGHT_TWO_ENDTIME = 20710562l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NIGHT_TWO_ENDTIME = 20710662l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NIGHT_TWO_ENDTIME = "考勤时间老师晚上时段二结束时间";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NIGHT_TWO_ENDTIME = "考勤时间学生晚上时段二结束时间";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NIGHT_TWO_ENDTIME = "22:00";

	/**
	 * 考勤时间-早上-时段一-打卡类型
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_MORNING_ONE_PUNCH_TYPE = 2071051l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_MORNING_ONE_PUNCH_TYPE = 2071061l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_MORNING_ONE_PUNCH_TYPE = "考勤时间老师早上时段一打卡类型";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_MORNING_ONE_PUNCH_TYPE = "考勤时间学生早上时段一打卡类型";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_MORNING_ONE_PUNCH_TYPE = "0";

	/**
	 * 考勤时间-早上-时段二-打卡类型
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_MORNING_TWO_PUNCH_TYPE = 2071052l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_MORNING_TWO_PUNCH_TYPE = 2071062l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_MORNING_TWO_PUNCH_TYPE = "考勤时间老师早上时段二打卡类型";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_MORNING_TWO_PUNCH_TYPE = "考勤时间学生早上时段二打卡类型";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_MORNING_TWO_PUNCH_TYPE = "1";
	/**
	 * 考勤时间-下午-时段一-打卡类型
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NOON_ONE_PUNCH_TYPE = 2071053l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NOON_ONE_PUNCH_TYPE = 2071063l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NOON_ONE_PUNCH_TYPE = "考勤时间老师下午时段一打卡类型";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NOON_ONE_PUNCH_TYPE = "考勤时间学生下午时段一打卡类型";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NOON_ONE_PUNCH_TYPE = "0";
	/**
	 * 考勤时间-下午-时段二-打卡类型
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NOON_TWO_PUNCH_TYPE = 2071054l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NOON_TWO_PUNCH_TYPE = 2071064l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NOON_TWO_PUNCH_TYPE = "考勤时间老师下午时段二打卡类型";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NOON_TWO_PUNCH_TYPE = "考勤时间学生下午时段二打卡类型";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NOON_TWO_PUNCH_TYPE = "1";
	/**
	 * 考勤时间-晚上-时段一-打卡类型
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NIGHT_ONE_PUNCH_TYPE = 2071055l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NIGHT_ONE_PUNCH_TYPE = 2071065l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NIGHT_ONE_PUNCH_TYPE = "考勤时间老师晚上时段一打卡类型";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NIGHT_ONE_PUNCH_TYPE = "考勤时间学生晚上时段一打卡类型";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NIGHT_ONE_PUNCH_TYPE = "0";
	/**
	 * 考勤时间-晚上-时段二-打卡类型
	 */
	public final static Long CONFIG_CODE_ATTENDANCETIME_TEA_NIGHT_TWO_PUNCH_TYPE = 2071056l;
	public final static Long CONFIG_CODE_ATTENDANCETIME_STU_NIGHT_TWO_PUNCH_TYPE = 2071066l;
	public final static String CONFIG_NAME_ATTENDANCETIME_TEA_NIGHT_TWO_PUNCH_TYPE = "考勤时间老师晚上时段二打卡类型";
	public final static String CONFIG_NAME_ATTENDANCETIME_STU_NIGHT_TWO_PUNCH_TYPE = "考勤时间学生晚上时段二打卡类型";
	public final static String CONFIG_DEFAULT_VALUE_ATTENDANCETIME_NIGHT_TWO_PUNCH_TYPE = "1";

	/**
	 * 是否启用短信是否启用消息中心短信通道
	 */
	public static final Long CONFIG_CODE_SMS = 304L;

	/**
	 * 是否启用刷卡短信通道
	 */
	public static final Long CONFIG_CODE_SMS_CARD = 305L;

	/**
	 * 是否启用食谱短信通道
	 */
	public static final Long CONFIG_CODE_SMS_MRCP = 306L;

	/**
	 * 是否启用作业短信通道
	 */
	public static final Long CONFIG_CODE_SMS_HOMEWORK = 307L;

	/**
	 * 是否启用成绩短信通道
	 */
	public static final Long CONFIG_CODE_SMS_SCORE = 308L;

	/**
	 * 是否启用短信是否启用短信通道
	 */
	public static final String CONFIG_NAME_SMS = "是否启用短信";
	public static final String CONFIG_REMARK_SMS = "开通短信通道后,将以实际的短信发送量进行月度结算,费用由代理商自行承担,已绑定优先发微信";

	/**
	 * 门户网站同步接口
	 */
	public static final Long CONFIG_CODE_WEBSTIE = 701L;
	public static final String CONFIG_NAME_WEBSTIE = "门户网站同步接口";
	public static final String CONFIG_REMARK_WEBSTIE = "微校通新闻、公告、资讯、课堂动态、教师风采数据自动同步至对应门户网站光标移出自动保存";

	/**
	 * 是否开启网站
	 */
	public static final Long CONFIG_CODE_WEBTEMPLAATE = 403L;
	public static final String CONFIG_NAME_WEBTEMPLAATE = "门户群权限开启";
	public static final String CONFIG_REMARK_WEBTEMPLAATE = "是否开启网站模版选择。";

	public static final Long CONFIG_CODE_CAMERA = 404L;
	public static final String CONFIG_NAME_CAMERA = "云看权限开启";
	public static final String CONFIG_REMARK_CAMERA = "是否具备接入云看权限。";

	public static final String textMsg = "<xml>"
			+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
			+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
			+ "<CreateTime>%3$s</CreateTime>"
			+ "<MsgType><![CDATA[text]]></MsgType>"
			+ "<Content><![CDATA[%4$s]]></Content>" + "<FuncFlag>0</FuncFlag>"
			+ "</xml>";

	public static final String newsMsg = "<xml>"
			+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
			+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
			+ "<CreateTime>%3$s</CreateTime>"
			+ "<MsgType><![CDATA[news]]></MsgType>"
			+ "<ArticleCount>%4$s</ArticleCount>" + "<Articles>%5$s</Articles>"
			+ "<FuncFlag>1</FuncFlag>" + "</xml>";

	public static final String newsMsgItem = "<item>"
			+ "<Title><![CDATA[%1$s]]></Title>"
			+ "<Description><![CDATA[%2$s]]></Description>"
			+ "<PicUrl><![CDATA[%3$s]]></PicUrl>"
			+ "<Url><![CDATA[%4$s]]></Url>" + "</item>";

	public static final String eventMsg = "<xml>"
			+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
			+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
			+ "<CreateTime>%3$s</CreateTime>"
			+ "<MsgType><![CDATA[event]]></MsgType>"
			+ "<Event><![CDATA[%4$s]]></Event>"
			+ "<EventKey><![CDATA[%5$s]]></EventKey>" + "</xml>";

	public static final String linkMsg = "<xml>"
			+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
			+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
			+ "<CreateTime>%3$s</CreateTime>"
			+ "<MsgType><![CDATA[link]]></MsgType>"
			+ "<Title><![CDATA[%4$s]]></Title>"
			+ "<Description><![CDATA[%5$s]]></Description>"
			+ "<Url><![CDATA[%6$s]]></Url>" + "<MsgId>%7$s</MsgId>" + "</xml>";

	public static final String musicMsg = "<xml>"
			+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
			+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
			+ "<CreateTime>%3$s</CreateTime>"
			+ "<MsgType><![CDATA[music]]></MsgType>" + "<Music>"
			+ "<Title><![CDATA[%4$s]]></Title>"
			+ "<Description><![CDATA[%5$s]]></Description>"
			+ "<MusicUrl><![CDATA[%6$s]]></MusicUrl>"
			+ "<HQMusicUrl><![CDATA[%7$s]]></HQMusicUrl>" + "</Music>"
			+ "<FuncFlag>0</FuncFlag>" + "</xml>";

	/****** config 配置 start *******/

	/******* config 配置 end ********/

	public static final int MSG_TYPE_MRCP_CODE = 20001;// 每日菜谱
	public static final String MSG_TYPE_MRCP_NAME = "每日菜谱";// 每日菜谱
	public static final String MSG_TYPE_MRCP_SENDER = "定时发送";// 每日菜谱

	/******* 中小学消息推送类型代码 start ********/
	public static final int MSG_TYPE_QXTZ_CODE = 20003;// 全校通知
	public static final String MSG_TYPE_QXTZ_NAME = "通知";// 全校通知

	public static final int MSG_TYPE_BJTZ_CODE = 20004;// 班级通知
	public static final String MSG_TYPE_BJTZ_NAME = "通知";// 班级通知

	public static final int MSG_TYPE_TZ_TEACHER_CODE = 20005;// 老师通知
	public static final String MSG_TYPE_TZ_TEACHER_NAME = "通知";// 老师通知

	public static final int MSG_TYPE_MAIL_CODE = 20006;// 邮件提醒
	public static final String MSG_TYPE_MAIL_NAME = "邮件提醒";// 邮件提醒

	public static final int MSG_TYPE_OA_CODE = 20007;// OA办公
	public static final String MSG_TYPE_OA_NAME = "OA办公";// OA办公

	public static final int MSG_TYPE_XXT_CODE = 20008;// 校讯通
	public static final String MSG_TYPE_XXT_NAME = "学校通知";// 校讯通

	public static final int MSG_TYPE_REPLY_CODE = 20009;// 消息回复
	public static final String MSG_TYPE_REPLY_NAME = "消息回复";// 消息回复

	public static final int MSG_TYPE_XWGG_CODE = 20010;// 新闻公告
	public static final String MSG_TYPE_XWGG_NAME = "新闻公告";// 新闻公告

	public static final int MSG_TYPE_ZSKS_CODE = 20011;// 招生考试
	public static final String MSG_TYPE_ZSKS_NAME = "招生考试";// 招生考试

	public static final int MSG_TYPE_BJQ_CODE = 20012;// 班级圈
	public static final String MSG_TYPE_BJQ_NAME = "班级圈";// 班级圈

	public static final int MSG_TYPE_HDTZ_CODE = 20013;// 新闻公告
	public static final String MSG_TYPE_HDTZ_NAME = "活动通知";// 新闻公告

	public static final int MSG_TYPE_SENDIMAGE_CODE = 30001;// 发送照片
	public static final String MSG_TYPE_SENDIMAGE_NAME = "精彩瞬间";// 发送照片

	public static final int MSG_TYPE_HOMEWORK_CODE = 30002;// 作业提醒
	public static final String MSG_TYPE_HOMEWORK_NAME = "作业提醒";// 学生作业通知

	public static final int MSG_TYPE_SCORE_CODE = 30003;// 考试成绩通知
	public static final String MSG_TYPE_SCORE_NAME = "成绩提醒";// 学生作业通知

	public static final int MSG_TYPE_PAXY_BBQJ_CODE = 10005;// 宝宝请假
	public static final String MSG_TYPE_PAXY_BBQJ_NAME = "请假申请";// 宝宝请假

	/******* 中小学消息推送类型代码 end ********/

	public static final int MSG_TYPE_PAXY_CODE = 10001;// 家长刷卡
	public static final int MSG_TYPE_PAXY_PATRIARCH_CODE = 10001;// 家长刷卡
	public static final int MSG_TYPE_PAXY_TEACHER_CODE = 10002;// 家长刷卡
	public static final String MSG_TYPE_PAXY_PATRIARCH_NAME = "家长刷卡";// 老师刷卡
	public static final String MSG_TYPE_PAXY_TEACHER_NAME = "老师刷卡";// 老师刷卡

	public static final int MSG_TYPE_PATRIARCH_REPLY_CODE = 10004;// 家长回复
	public static final String MSG_TYPE_PATRIARCH_REPLY_NAME = "家长回复";// 家长回复

	public static final int MSG_TYPE_TEACHER_REPLY_CODE = 10007;// 家长老师回复
	public static final String MSG_TYPE_TEACHER_REPLY_NAME = "老师回复";// 家长老师回复

	public static final int MSG_TYPE_JYHD_LXLS_CODE = 10006;// 联系老师
	public static final String MSG_TYPE_JYHD_LXLS_NAME = "家长来信";// 家长来信
	// public static final int MSG_TYPE_UPLSIMG = 10006;

	public static final int MSG_TYPE_CRM_NEWS_CODE = 40001;// 24小时图文消息
	public static final String MSG_TYPE_CRM_NEWS_NAME = "CRM-NEWS";// 宝宝请假

	public static final int MSG_TYPE_USER_GET_CODE = 50005; // 拉取关注用户数据
	public static final int MSG_TYPE_USERINFO_GET_CODE = 50006; // 通过用户的微信加密id获取用户微信基本信息

	/*** 系统参数 start ****/

	/**
	 * 到校离校模版在config表中的代码
	 */
	public static final String TM00188_TEMPLATE = "TM00188";

	/**
	 * 微信请假模版在config表中的代码
	 */
	// public static final String TM00222_TEMPLATE = "TM00222";
	public static final String TM00190_TEMPLATE = "TM00190";
	/**
	 * 微信作业模版在config表中的代码
	 */
	public static final String TM00376_TEMPLATE = "TM00376";

	/**
	 * 微信考试成绩模版在config表中的代码
	 */
	public static final String TM00084_TEMPLATE = "TM00084";

	/**
	 * 微信放假模版在config表中的代码
	 */
	public static final String TM00221_TEMPLATE = "TM00221";

	/**
	 * 微信通用模版在config表中的代码——订阅成功模版
	 */
	public static final String TP_DYCG = "TM00157";

	/**
	 * 微信通用模版在config表中的代码——教育行业－院校订阅成功模版
	 */
	public static final String TP_YX_DYCG = "OPENTM201387310";

	/**
	 * 微信通用模版在config表中的代码——教育行业－院校学校通知模版
	 */
	public static final String OPENTM204845041_TEMPLATE = "OPENTM204845041";

	/**
	 * 微信新闻公告通知模版在config表中的代码——教育行业－新闻公告通知模版
	 */
	public static final String OPENTM206848486_TEMPLATE = "OPENTM206848486";

	/**
	 * 微信活动通知模版
	 */
	public static final String OPENTM207276768_TEMPLATE = "OPENTM207276768";

	/**
	 * <<<<<<< HEAD 微信支付通知模版
	 */
	public static final String OPENTM202841150_TEMPLATE = "OPENTM202841150";

	/*
	 * 微信留言提醒模版
	 */
	public static final String OPENTM202309749_TEMPLATE = "OPENTM202309749";

	/**
	 * 微信用户绑定成功模版
	 */
	public static final String OPENTM202551326_TEMPLATE = "OPENTM202551326";

	/**
	 * 微信通用模版在config表中的域名
	 */
	public static final String DOMAIN_URL = "401";

	/**
	 * 微信二维码服务地址
	 */
	public static final String WX_PROXY = "WX_PROXY";

	/*** 系统参数 end ****/
	/**
	 * 模拟发送文本
	 */
	public static final String MSG_PUSHTYPE_TEXT = "text";
	/**
	 * 模拟发送图文消息,需先创建图文
	 */
	public static final String MSG_PUSHTYPE = "news";
	/**
	 * 模拟发送图文消息
	 */
	public static final String MSG_PUSHTYPEL_MUSIC = "music";

	public static final String NOTICE_NEWS_COVER = "http://jeff-ye-1978.qiniudn.com/wxt_twtz.jpeg";
	public static final String MRCP_NEWS_COVER = "http://static.weixiaotong.com.cn/c3daa812e3a160cb485360a8ff1eda2e95b32dce.jpg";

	public static final String CHECK_SMS = "601";

	public static final String WEBSTIE_INTERFACE_URL = "701";

	/**
	 * 普通文本
	 */
	public static final int MSG_TYPE_TEXT_CODE = 90000;

	/*************************** 业务功能 参数 *********************************/
	/**
	 * 抽奖主题类型-用户绑定
	 */
	public static final String LOTTERY_TYPE_BINDING = "140001";
	/**
	 * 首次绑定成功增加的有效抽奖次数
	 */
	public static final Integer LOTTERY_BINDING_CHANCE = 1;
	/**
	 * 是否开启绑定后的抽奖功能
	 */
	public static final boolean IS_OPEN_BINDING_LOTTERY = false;
	/**
	 * 消息通道为易信
	 */
	public static final int ORGCODE_YIXIN_CHANNEL = 1;

	/**
	 * 关注人信息管理-绑定
	 */
	public static final int FAKEID_STATE_BINDING = 1;

	/**
	 * 关注人信息管理-未绑定
	 */
	public static final int FAKEID_STATE_UNBINDING = 2;

	/**
	 * 信息群发类型
	 */
	public static final int YQDT_TYPE_XXQF = 22;

	/**
	 * 信息群发类型
	 */
	public static final String TYPE_RECEIVER_ALL = "0";
	/**
	 * 信息群发类型
	 */
	public static final String TYPE_RECEIVER_BINDING = "1";
	/**
	 * 信息群发类型
	 */
	public static final String TYPE_RECEIVER_UNBINDING = "2";

	/**
	 * 班级圈类型
	 */
	public static final int WLZY_TYPE_BJQ = 5;

	/**
	 * 宝宝作品
	 */
	public static final int WLZY_TYPE_BBZP = 6;

	/**
	 * 成长记录 （微信 成长记录 图片保存）
	 */
	public static final int WLZY_TYPE_GROW = 7;

	/**
	 * 宝宝作品条数
	 */
	public static final int WLZY_TYPE_BBZP_SIZE = 20;

	/**
	 * 新闻公告1：推送
	 */
	public static final int WLZY_YQDT_PUSH = 1;

	/**
	 * 主题活动：推送
	 */
	public static final int THEME_PUSH = 1;

	/**
	 * 可见范围 1：公开
	 */
	public static final int WLZY_PHOTO_ISOPEN = 1;

	/**
	 * 课堂动态类型:包括以前的(wlzy表中的：儿歌故事：1 ,经典音乐：2 ,经典游戏：3 ,微课堂：4,xxjj表中的：班级动态：4)
	 */
	public static final int WLZY_TYPE_KTDT = 1;

	/**
	 * 未发布状态
	 */
	public static final int STATE_UN_PUBLISHED = 1;
	/**
	 * 已发布状态
	 */
	public static final int STATE_PUBLISHED = 2;

	/**
	 * 预约发布状态
	 */
	public static final int STATE_PRESET_PUBLISHED = 1;

	/**
	 * 预览状态
	 */
	public static final int STATE_PREVIEW = 3;

	/**
	 * 微信加密方式
	 */
	public static final String WECHAT_ENCRYPT_TYPE = "aes";

	/**
	 * 微信token
	 */
	public static final String TOKEN = "yeywechat";

	/**
	 * 微信端每页显示数据量
	 */
	public static final int PAGE_SIZE = 10;

	/**
	 * 微信端每页显示数据量
	 */
	public static final int BIG_PAGE_SIZE = 20;

	/**
	 * 信息已读状态
	 */
	public static final int INFO_IS_READ = 1;

	/**
	 * 信息未读状态
	 */
	public static final int INFO_UN_READ = 0;

	/**
	 * 信息已回复状态
	 */
	public static final int INFO_IS_REPLY = 1;

	/**
	 * 图片类型：园所环境中上传的图片的类型
	 */
	public static final int FILE_TYPE_YSHJ = 98;

	/**
	 * 图片类型：宝宝作品中上传的图片的类型
	 */
	public static final int FILE_TYPE_BBZP = 6;

	/**
	 * 图片类型：主题活动中上传的图片的类型
	 */
	public static final int FILE_TYPE_ZTHD = 120;

	/**
	 * 公用模板的orgcode
	 */
	public static final Long COMMON_TEMPLATE_ORGCODE = 1000L;

	/**
	 * 公用模板的campusid
	 */
	public static final Long COMMON_TEMPLATE_CAMPUSID = 0L;

	/**
	 * 中科院学校编码10010
	 */
	public static final Long ORG_ORGCODE_ZKY = 10010L;

	/**
	 * 小博士幼儿园学校编码10018
	 */
	public static final Long ORG_ORGCODE_XBS = 10018L;

	/**
	 * 总部公众帐号编码10008 幼儿园
	 */
	public static final Long ORG_ORGCODE_MAIN = 10008L;

	/**
	 * 总部公众帐号编码10136 中小学
	 */
	public static final Long ORG_ORGCODE_SCHOOL_MAIN = 10136L;

	/**
	 * 总部公众帐号编码10172 培训机构 课堂外
	 */
	public static final Long ORG_ORGCODE_TRAIN_MAIN = 10172L;

	/**
	 * 取消点赞-返回页面的参数
	 */
	public static final String PRAISE_RETURN_CANCEL = "cancel";

	/**
	 * 点赞-返回页面的参数
	 */
	public static final String PRAISE_RETURN_SUCCESS = "success";

	/**
	 * 类型：通知类型 全校通知
	 */
	public static final int TZ_TYPE_1 = 1;

	/**
	 * 类型：通知类型 班级通知
	 */
	public static final int TZ_TYPE_2 = 2;

	/**
	 * 类型：通知类型 老师通知
	 */
	public static final int TZ_TYPE_4 = 4;

	/**
	 * 类型：通知类型 联系老师
	 */
	public static final int TZ_TYPE_5 = 5;

	/**
	 * 类型：通知类型 请假
	 */
	public static final int TZ_TYPE_6 = 6;

	/**
	 * 类型：通知类型 家长和家长联系
	 */
	public static final int TZ_TYPE_7 = 7;

	/**
	 * 类型：园长信箱
	 */
	public static final int TZ_TYPE_8 = 8;

	/**
	 * 主题活动-主要信息的分类参数
	 */
	public static final String ACTIVITY_TYPE_INFO = "mainInfo";

	/**
	 * 主题活动-照片墙的分类参数
	 */
	public static final String ACTIVITY_TYPE_PHOTO = "photoWall";

	/**
	 * 在线资源-类型-儿歌
	 */
	public static final int RESOURCE_TYPE_EG = 1;

	/**
	 * 在线资源-类型-儿童小说
	 */
	public static final int RESOURCE_TYPE_ETXS = 2;

	/**
	 * 在线资源-类型-启蒙
	 */
	public static final int RESOURCE_TYPE_QM = 3;

	/**
	 * 在线资源-类型-国学
	 */
	public static final int RESOURCE_TYPE_GX = 4;

	/**
	 * 在线资源-类型-故事
	 */
	public static final int RESOURCE_TYPE_GS = 5;

	/**
	 * 在线资源-类型-英语
	 */
	public static final int RESOURCE_TYPE_YY = 6;

	/**
	 * 在线资源-类型-评书
	 */
	public static final int RESOURCE_TYPE_PS = 7;

	/**
	 * 在线资源-资格格式
	 */
	public static final String RESOURCE_FORMAT_AUDIO = "mp3";

	/**
	 * 班级主题活动-保存图片时的类型
	 */
	public static final int FILE_TYPE_THEME = 120;

	/**
	 * 班级是否毕业-未毕业：0
	 */
	public static final int BJSJ_ISNOT_BYBJ = 0;

	/**
	 * 班级是否毕业-未毕业：0
	 */
	public static final int NJSJ_ISNOT_BYNJ = 0;

	/**
	 * 发送消息-学生通知
	 */
	public static final int SENDMSG_TYPE_XSTZ = 1;

	/**
	 * 发送消息-老师通知
	 */
	public static final int SENDMSG_TYPE_LSTZ = 2;

	/**
	 * 发送消息-请假
	 */
	public static final int SENDMSG_TYPE_QJ = 3;

	/**
	 * 发送消息-联系老师
	 */
	public static final int SENDMSG_TYPE_LXLS = 4;
	/**
	 * 发送消息-联系学生（家长间联系）--add by yjf
	 */
	public static final int SENDMSG_TYPE_LXXS = 5;

	/**
	 * 系统-安卓
	 */
	public static final String SYSTEM_TYPE_ANDROID = "android";

	/**
	 * 系统-苹果
	 */
	public static final String SYSTEM_TYPE_IOS = "ios";

	/**
	 * 系统-移动端
	 */
	public static final String SYSTEM_TYPE_MOBILE = "mobile";

	/**
	 * 系统-PC端
	 */
	public static final String SYSTEM_TYPE_PC = "pc";

	// 数据来源-幼儿园
	public final static String SOURCE_YEY = "YEY";

	/**
	 * 活动关键字
	 */
	public final static String ORG_HD_KEYWORD = "光海萌宝宝";

	/**
	 * 默认开始页
	 */
	public final static int WLZY_DEFAULT_PAGE = 1;

	/**
	 * 老师配卡数据模版SHEET名称
	 */
	public final static String CARD_TEMPLATE_SHEETNAME_TEACHER = "老师配卡数据模版";

	/**
	 * 老师配卡数据模版SHEET名称
	 */
	public final static int CARD_TEMPLATE_TYPE_TEACHER = 1;

	/**
	 * 学生配卡数据模版SHEET名称
	 */
	public final static String CARD_TEMPLATE_SHEETNAME_STU = "学生配卡数据模版";

	/**
	 * 学生配卡数据模版SHEET名称
	 */
	public final static int CARD_TEMPLATE_TYPE_STU = 2;

	public static final String USERINFO_URL_WECHAT = "https://api.weixin.qq.com/cgi-bin/user/info?lang=zh_CN&";

	public static final String USERINFO_URL_YIXIN = "https://api.yixin.im/cgi-bin/user/info?";

	public static final String USERLIST_URL_WECHAT = "https://api.weixin.qq.com/cgi-bin/user/get?";

	public static final String USERLIST_URL_YIXIN = " https://api.yixin.im/sns/oauth2/access_token?grant_type=authorization_code&";

	/**
	 * 增加模版，获取template_id
	 */
	public static final String WECHAT_ADDTEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?";
	/**
	 * 食谱发送时间configcode
	 */
	public static final String MRCP_SENDTIME_CONFIGCODE = "201";

	/**
	 * sha1加密次数
	 */
	public static final int HASH_INTERATIONS = 1024;

	/**
	 * 初始密码
	 */
	public static final String DEFAULT_PASSWORD = "123456";

	/**
	 * 在线报名录取后，学生的默认班级id
	 */
	public static final long ZXBM_DEFAULT_BJID = -1;

	/**
	 * 操作状态：0:新增，1:修改，-1需要删除
	 */
	public static final Integer SET_CARD_TYPE_ADD = 0;
	public static final Integer SET_CARD_TYPE_UPDATE = 1;
	public static final Integer SET_CARD_TYPE_DELETE = -1;
	/**
	 * 删除用户操作的APPID标识，记录日志用
	 */
	public static final String LOG_APPID_DELETE_USER = "1703";

	/**
	 * 学校服务默认免费结束日期
	 */
	public static final String XX_SERVICE_FREE_END_RQ = "2030-01-01";

	/**
	 * 学校服务默认开始日期
	 */
	public static final String XX_SERVICE_START_RQ = "2030-01-02";

	/**
	 * 学校服务默认结束日期
	 */
	public static final String XX_SERVICE_END_RQ = "2030-01-02";
	/**
	 * 学校管理审核状态-审核中
	 */
	public static final int XX_AUDIT_STATE_ING = 1;
	/**
	 * 学校管理审核状态-审核通过
	 */
	public static final int XX_AUDIT_STATE_PASS = 2;
	/**
	 * 学校管理审核状态-审核未通过
	 */
	public static final int XX_AUDIT_STATE_NOPASS = -1;
	/**
	 * 服务开通状态
	 */
	public static final int PAY_STATE_OPENSERVICE = 1;

	/**
	 * 服务开通状态-未出账
	 */
	public static final int PAY_SERVICEPOINT_NOACCOUNT = 1;

	/**
	 * 服务开通-线下支付
	 */
	public static final int PAY_TYPE_FORMAM = 1;

	/**
	 * 服务开通-线上支付
	 */
	public static final int PAY_TYPE_FORWEIXIN = 2;
	/**
	 * 微信端-没有校区时设置默认校区ID为0
	 */
	public static final String CAMPUSID_DEFAULT_VALUE = "0";

	/**
	 * 微信端-班级圈类型：1 （公开）班级圈；2：（私密）个人相册
	 */
	public static final int BJQ_TYPE_ISOPEN = 1;

	/**
	 * 班级管理-是否为毕业班级 0:否；1：是
	 */
	public static final int BJSJ_NOT_BYBJ = 0;

	/**
	 * 异步请求返回值 标识请求操作成功
	 */
	public static final String AJAX_REQUEST_SIGN_SUCCESS = "200";

	/**
	 * 异步请求返回值 标识请求操作失败
	 */
	public static final String AJAX_REQUEST_SIGN_FAIL = "400";

	/**
	 * 异步请求返回值 -打赏-余额不足
	 */
	public static final String AJAX_REQUEST_SIGN_REWARD_NOMONEY = "500";

	/**
	 * 周一
	 */
	public static final Integer WEEKDAY_NUM_MONDAY = 1;
	/**
	 * 周日
	 */
	public static final Integer WEEKDAY_NUM_SUNDAY = 7;

	/**
	 * 数据字典-OA控件类型
	 */
	public static final String DICT_TYPE_OA_CONTROLS_TYPE = "OACONTROLS";

	/**
	 * 数据字典-奖项字典类型
	 */
	public static final String DICT_TYPE_GHHD_AWARD_TYPE = "AWARDTYPE";

	/**
	 * 数据字典-投票规则字典类型
	 */
	public static final String DICT_TYPE_GHHD_VOTE_RULE = "VOTERULE";
	/**
	 * 数据字典-活动类型
	 */
	public static final String DICT_TYPE_GHHD_ACTIVITY_TYPE = "ACTIVITYTYPE";

	/**
	 * 数据字典-家长关系对应
	 */
	public static final String DICT_TYPE_PARENT_RELATIONS = "XSJZ";

	/**
	 * 数据字典-家长学历对应
	 */
	public static final String DICT_TYPE_EDUCATIONAL_BACKGROUND = "XLTYPE";

	/**
	 * 数据字典-微信端各模块在首页显示的记录数
	 */
	public static final String DICT_TYPE_ARTICLE_DATALIMIT = "DATALIMIT";

	/**
	 * 数据字典-反馈类型
	 */
	public static final String DICT_TYPE_ADVISE_TYPE = "FKLX";// 反馈类型

	/**
	 * 数据字典-血型
	 */
	public static final String DICT_TYPE_BLOODTYPE = "BLOODTYPE";

	/**
	 * 数据字典-性别
	 */
	public static final String DICT_TYPE_SEX = "SEX";

	/**
	 * 数据字典-绑定状态
	 */
	public static final String DICT_TYPE_BIND = "BIND";

	/**
	 * 数据字典-是否
	 */
	public static final String DICT_TYPE_WHETHER = "WHETHER";

	/**
	 * 数据字典-请假类型
	 */
	public static final String DICT_TYPE_QJLX = "QJLX";

	/**
	 * 数据字典-评语
	 */
	public static final String DICT_TYPE_COMMENT = "COMMENT";

	/**
	 * 打卡查询-全部
	 */
	public static final String PUNCHCARD_TYPE_ALL = "ALL";

	/**
	 * 打卡查询-未打卡
	 */
	public static final String PUNCHCARD_TYPE_NOTPUNCH = "NOTPUNCH";

	/**
	 * 数据字典-民族对应
	 */
	public static final String DICT_TYPE_MZLX = "MZLX";

	/**
	 * 数据字典-婚姻状况对应
	 */
	public static final String DICT_TYPE_MARITAL = "MARITAL";

	/**
	 * 数据字典-政治面貌对应
	 */
	public static final String DICT_TYPE_POLITICAL = "POLITICAL";

	/**
	 * 数据字典-考勤机类型
	 */
	public static final String DICT_TYPE_MECHINE_TYPE = "MECHINE_TYPE";

	/**
	 * 数据字典-考勤机状态
	 */
	public static final String DICT_TYPE_MECHINE_STATE = "MECHINE_STATE";

	/**
	 * 数据字典-考勤机是否启用
	 */
	public static final String DICT_TYPE_MECHINE_ISOPEN = "MECHINE_ISOPEN";

	/**
	 * 数据字典-考勤机是否启用
	 */
	public static final String DICT_TYPE_PUNCH_TYPE = "PUNCH_TYPE";

	/**
	 * 数据字典-学校状态
	 */
	public static final String DICT_TYPE_SCHOOL_TYPE = "XXTYPE";

	/**
	 * 数据字典-微信支付错误提示
	 */
	public static final String DICT_TYPE_PAY_ERROR_MSG = "PAYERRMSG";

	/**
	 * 数据字典-城市.
	 */
	public static final String DICT_TYPE_CITY_TYPE = "CITY";

	/**
	 * 数据字典-省份.
	 */
	public static final String DICT_TYPE_PROVINCE = "PROVINCE";
	/**
	 * 数据字典-县区
	 */
	public static final String DICT_TYPE_COUNTYTOWN = "COUNTY_TOWN";
	/**
	 * 数据字典-微信支付错误提示
	 */
	public static final String DICT_CODE_PAY_ERROR_MSG_01 = "PAYERRMSG_01";

	/**
	 * 数据字典-考勤机状态
	 */
	public static final String DICT_TYPE_USE_TYPE = "USE_TYPE";

	/**
	 * 数据字典-代理商广告：状态
	 */
	public static final String DICT_TYPE_DLS_AD_STATE = "DLS_AD_STATE";

	/**
	 * 数据字典-聊天模式用户输入－游客反馈
	 */
	public static final String DICT_TYPE_WECHAT_PROMPT_VISITOR = "WECHAT_PROMPT_VISITOR";

	/**
	 * 数据字典-聊天模式用户输入－园长反馈
	 */
	public static final String DICT_TYPE_WECHAT_PROMPT_LEADER = "WECHAT_PROMPT_LEADER";

	/**
	 * 数据字典-聊天模式用户输入－管理员反馈
	 */
	public static final String DICT_TYPE_WECHAT_PROMPT_ADMIN = "WECHAT_PROMPT_ADMIN";

	/**
	 * 数据字典-聊天模式用户输入－老师反馈
	 */
	public static final String DICT_TYPE_WECHAT_PROMPT_TEACHER = "WECHAT_PROMPT_TEACHER";

	/**
	 * 数据字典-聊天模式用户输入－家长反馈
	 */
	public static final String DICT_TYPE_WECHAT_PROMPT_PATRIARCH = "WECHAT_PROMPT_PATRIARCH";

	/**
	 * 数据字典-微信认证状态
	 */
	public static final String DICT_WECHAT_VERIFYSTATE = "WECHAT_VERIFYSTATE";

	/**
	 * 考勤
	 */
	public static final int MESSAGE_TYPE_ATTENDANCE_FOR_APP = 8;
	/**
	 * 请假
	 */
	public static final int MESSAGE_TYPE_LEAVE_FOR_APP = 6;
	/**
	 * 食谱
	 */
	public static final int MESSAGE_TYPE_RECIPE_FOR_APP = 5;
	/**
	 * 消息
	 */
	public static final int MESSAGE_TYPE_COMMON_FOR_APP = 4;
	/**
	 * 新闻
	 */
	public static final int MESSAGE_TYPE_NEWS_FOR_APP = 3;
	/**
	 * 公告
	 */
	public static final int MESSAGE_TYPE_ANNOUNCEMENT_FOR_APP = 2;
	/**
	 * 资讯
	 */
	public static final int MESSAGE_TYPE_INFORMATION_FOR_APP = 7;
	/**
	 * 系统消息
	 */
	public static final int MESSAGE_TYPE_SYSTEM_FOR_APP = 33;
	/**
	 * 通讯录-老师TAB
	 */
	public static final String ADDRESSBOOK_TYPE_TEACHER_FOR_APP = "0";

	/**
	 * 通讯录-学生分组TAB
	 */
	public static final String ADDRESSBOOK_TYPE_STUGROUP_FOR_APP = "1";

	/**
	 * 通讯录-班级TAB
	 */
	public static final String ADDRESSBOOK_TYPE_CLASS_FOR_APP = "2";

	// 用户请求 微校通app请求
	public static final String USER_AGENT_WXT = "WeiXiaoTong";

	public static Boolean checkUserAgent(String userAgent) {
		if (USER_AGENT_WXT.equals(userAgent)) {
			return true;
		} else {
			return false;
		}
	}

	public static String PAY_UNIFIEDORDER_URI = "/pay/unifiedorder";

	public static String getPayUnifiedorderUri(String source) {
		return "/" + source + PAY_UNIFIEDORDER_URI;
	}

	/**
	 * app消息推送类型-通知
	 */
	public static final String MESSAGE = "message";
	/**
	 * app消息推送类型-通知回复
	 */
	public static final String MESSAGE_REPLY = "msgreply";
	/**
	 * app消息推送类型-班级圈
	 */
	public static final String CLASSCIRCLE = "classcircle";

	public static final String CLASSCIRCLE_COMMENT = "newcomment";
	/**
	 * app消息推送类型-成长
	 */
	public static final String GROW = "grow";

	/**
	 * app消息推送类型-透传数据
	 */
	public static final String SYSTEM_DATA = "msgdata";

	/**
	 * app消息透传类型-确认时推送消息给发送人
	 */
	public static final String DT_UPDATE_CONFIRM_FOR_SENDER = "1";

	/**
	 * app消息透传类型-回复时推送消息给发送人
	 */
	public static final String DT_UPDATE_REPLY_FOR_SENDER = "2";

	/**
	 * app消息透传类型-同步消息确认数量到APP
	 */
	public static final String DT_SYNCHRONOUS_CONFIRM_FOR_RECEIVER = "3";

	/**
	 * app消息透传类型-同步消息回复数量到APP
	 */
	public static final String DT_SYNCHRONOUS_REPLY_FOR_RECEIVER = "4";

	/**
	 * 在MemCacheForOneCardUtil里的key里可以批量删除
	 */
	public static final String MEMCACHE_FOR_ONE_CARD_UTIL_SINGE_AND_BATCH = "MEMCACHEFORONECARDUTIL_SINGE_AND_BATCH";
	/**
	 * 在MemCacheForOneCardUtil里的不能批量删除
	 */
	public static final String MEMCACHE_FOR_ONE_CARD_UTIL_SINGE = "MEMCACHEFORONECARDUTIL_SINGE";
	/**
	 * 在MemCacheUtil里的key里可以批量删除
	 */
	public static final String MEMCACHE_UTIL_SINGE_AND_BATCH = "MEMCACHEUTIL_SINGE_AND_BATCH";
	/**
	 * 在MemCacheUtil里的不能批量删除
	 */
	public static final String MEMCACHE_UTIL_SINGE = "MEMCACHEUTIL_SINGE";

	/**
	 * 属相.
	 */
	public static final String DICT_TYPE_ZODIAC = "ZODIAC";

	/**
	 * 星座.
	 */
	public static final String DICT_TYPE_CONSTELLATION = "CONSTELLATION";

	public static final int IDISPLAY_START = 0;
	public static final int IDISPLAY_LENGTH = 9999999;

	/** 集成测试平台 */
	/** 初始化sql，转换当日的特殊字符串 */
	public static final String SPECIAL_STR_TODAY = "$TODAY$";
}
