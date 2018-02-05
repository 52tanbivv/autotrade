package com.space.wechat.util;

public class UtilConstant {

	public static final String arrive_late_hhmmss = "07:50:00";
	public static final String leave_early_hhmmss = "16:30:00";

	/**
	 * 上传的各类文件存在的文件夹名称
	 */
	public static final String FOLDER_LOGO = "logo";// 每个学校的logo，文件夹

	public static final String FOLDER_XXJJ = "xxjj";// 每个学校简介

	public static final String FOLDER_TEACHERZP = "techaerzp";// 每个学校新闻动态

	public static final String FOLDER_NEWS = "news";// 公司网站新闻

	public static final String FOLDER_VIDEOFMS = "videofms";// 公司网站视频封面

	public static final String FOLDER_VIDEOS = "videos";// 公司网站视频

	public static final String FOLDER_BJSJ = "bjsj";// 每个班级图标

	public static final String FOLDER_BJDT = "bjdt";// 每个班级图标

	public static final String FOLDER_YQDT = "yqdt";// 每个班级图标

	public static final String FOLDER_CYTD = "cytd";// 创意空间上传的图片

	public static final String FOLDER_XSZP = "xszp";// 创意空间上传的图片

	public static final String GHHD_THEME_PIC = "ghhd";// 运营活动上传的图片

	public static final String WEIKE_FILE = "weike";// 微课上传文件目录
	
	public static final int XXJJ_TYPE = 98;// 学校简介的wlzyType

	public static final int SUPERADMIN = 1;// 初始化管理员的标识

	public static final String[] MRCP_TYPE = new String[] { "早餐", "早点", "午餐",
			"午点", "晚餐", "" };
	public static final String[] WEEK_DAY = new String[] { "周一", "周二", "周三",
			"周四", "周五", "周六", "周日" };

	public static final String[] YQDT_TYPE_CH = new String[] { "招生公告", "园所新闻",
			 "育儿知识", "家长感言" };

	public static final int[] YQDT_TYPE = new int[] { 2, 3, 7, 8 };

	/*** 系统参数 start ****/

	/**
	 * 域名
	 */
	public static final String DOMAIN_NAME = "401";

	/*** 系统参数 end ****/

	/*** 消息参数 start ****/

	public static final String USER_ROLE_SUPERADMIN = "superAdmin";
	public static final String USER_ROLE_USER = "user";
	public static final String USER_ROLE_ADMIN = "admin";
	public static final String USER_ROLE_LEADER = "leader";
	public static final String USER_ROLE_ASSISTANT = "assistant";

	public static final String ZKY_ORGCODE = "10010";

	/*** 消息参数 end ****/

	public static final String CRLF_RN = "\r\n";

	public static final int SHCOOL_NOTIC_TYPE = 1;// 全校通知

	public static final int CLASS_NOTIC_TYPE = 2;// 班级通知

	public static final int KINDLY_REMINDER_TYPE = 3;// 温馨提示

	public static final int TEACHER_NOTIC_TYPE = 4;// 教师通知

	public static final int HISTORY_NOTIC_TYPE = 5;// 教师通知

	public static final int Comment_HOMEWORK_TYPE = 1;// 作业评语

}
