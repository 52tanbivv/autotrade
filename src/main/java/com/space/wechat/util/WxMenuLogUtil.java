package com.space.wechat.util;

import java.util.HashMap;
import java.util.Map;

public class WxMenuLogUtil {

	/**
	 * 园所介绍
	 */
	public final static String MENU_YSJS = "10701";
	/**
	 * 师资力量
	 */
	public final static String MENU_SZLL = "10702";
	/**
	 * 园所公告
	 */
	public final static String MENU_YSGG = "10703";
	/**
	 * 新闻动态
	 */
	public final static String MENU_XWDT = "10704";
	/**
	 * 每周食谱
	 */
	public final static String MENU_MZSP = "10705";
	/**
	 * 班级动态
	 */
	public final static String MENU_BJDT = "10706";
	/**
	 * 微课堂
	 */
	public final static String MENU_WKT = "10707";
	/**
	 * 快乐学习
	 */
	public final static String MENU_KLXX = "10708";
	/**
	 * 在线点播
	 */
	public final static String MENU_ZXDB = "10709";
	/**
	 * 班级圈
	 */
	public final static String MENU_BJQ = "10710";

	private static Map<String, String> menuMap;

	public static String getMenuName(String key) {
		if (menuMap == null || menuMap.size() == 0) {
			menuMap = initMenuMap();
		}
		if (menuMap.containsKey(key)) {
			return menuMap.get(key);
		}
		return "";
	}

	private static Map<String, String> initMenuMap() {
		Map<String, String> menuMap = new HashMap<String, String>();
		menuMap.put(MENU_YSJS, "园所介绍");
		menuMap.put(MENU_SZLL, "师资力量");
		menuMap.put(MENU_YSGG, "园所公告");
		menuMap.put(MENU_XWDT, "新闻公告");
		menuMap.put(MENU_MZSP, "每周食谱");
		menuMap.put(MENU_BJDT, "班级动态");
		menuMap.put(MENU_WKT, "微课堂");
		menuMap.put(MENU_KLXX, "快乐学习");
		menuMap.put(MENU_ZXDB, "在线点播");
		menuMap.put(MENU_BJQ, "班级圈");
		return menuMap;
	}
}
