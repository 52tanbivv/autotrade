package com.space.wechat.util;

import java.util.List;

/**
 * 转换HTML代码
 * 
 * @author yejianfei
 * 
 */
public class HtmlUtil {

	public static String getHtmlTable(String tableId, String tableClass,
			List<String> header, List<HtmlTableCell[]> td) {

		return getTableBegin(tableId, tableClass) + getTableHeader(header)

		+ getTableBody(td) + getTableEnd();
	}

	private static String getTableBody(List<HtmlTableCell[]> td) {
		return getTbodyBegin() + getTableBodyDetail(td) + getTbodyEnd();
	}

	private static String getTableBodyDetail(List<HtmlTableCell[]> tdlist) {
		StringBuffer html = new StringBuffer("");
		for (HtmlTableCell[] tds : tdlist) {
			html.append("<tr>");
			for (HtmlTableCell td : tds) {
				if (td != null) {
					html.append(td.toHtml());
				}
			}
			html.append("</tr>");
		}

		return html.toString();
	}

	/**
	 * TABLE启动
	 * 
	 * @param tableId
	 * @param tableClass
	 * @return
	 */
	private static String getTableBegin(String tableId, String tableClass) {
		return "<table " + getHtmlAttr("id", tableId)
				+ getHtmlAttr("class", tableClass) + ">";
	}

	public static String getHtmlAttr(String attrName, String attrValue) {
		return ("".equals(attrValue) || attrValue == null) ? "" : attrName
				+ "='" + attrValue + "' ";
	}

	private static String getTableEnd() {
		return "</table>";
	}

	private static String getTbodyEnd() {
		return "</tbody>";
	}

	private static String getTbodyBegin() {
		return "<tbody>";
	}

	private static String getTableHeader(List<String> header) {
		StringBuffer html = new StringBuffer("<thead><tr>");
		for (String h : header) {
			html.append("<th style=\"text-align: center;vertical-align: middle;\">").append(h).append("</th>");
		}
		html.append("</tr></thead>");
		return html.toString();
	}

	/**
	 * <table class="table table-striped table-bordered table-condensed">
	 * <thead>
	 * <tr>
	 * <th>时段</th>
	 * <th>周一（11.1）</th>
	 * <th>周二（11.2）</th>
	 * <th>周三（11.3）</th>
	 * <th>周四（11.4）</th>
	 * <th>周五（11.5）</th>
	 * </tr>
	 * </thead> <tbody>
	 * <tr>
	 * <td>早点</td>
	 * <td id="td_1_1">
	 * <div class="inner-td-div">纯牛奶</div> <div class="inner-td-div">饼干</div> <a
	 * style="" class="btn cell_btn" onclick="openMrcpDialog('周一','早点')"
	 * id="btn_1_1">修改</a></td>
	 * <td id="td_2_1">
	 * <div class="inner-td-div">纯牛奶</div> <div class="inner-td-div">饼干</div> <a
	 * style="" class="btn cell_btn" onclick="openMrcpDialog('周二','早点')"
	 * id="btn_2_1">修改</a></td>
	 * <td id="td_3_1">
	 * <div class="inner-td-div">纯牛奶</div> <div class="inner-td-div">饼干</div> <a
	 * style="" class="btn cell_btn" onclick="openMrcpDialog('周三','早点')"
	 * id="btn_3_1">修改</a></td>
	 * <td id="td_4_1">
	 * <div class="inner-td-div">纯牛奶</div> <div class="inner-td-div">饼干</div> <a
	 * style="" class="btn cell_btn" onclick="openMrcpDialog('周四','早点')"
	 * id="btn_4_1">修改</a></td>
	 * <td id="td_5_1">
	 * <div class="inner-td-div">纯牛奶</div> <div class="inner-td-div">饼干</div> <a
	 * style="" class="btn cell_btn" onclick="openMrcpDialog('周五','早点')"
	 * id="btn_5_1">修改</a></td>
	 * 
	 * </tr>
	 * <tr>
	 * <td>午餐</td>
	 * <td id="td_1_2">
	 * <div class="inner-td-div">糖醋里脊</div> <div
	 * class="inner-td-div">菜心豆腐汤</div> <a style="" class="btn cell_btn"
	 * onclick="openMrcpDialog('周一','午餐')" id="btn_1_2">修改</a></td>
	 * <td id="td_2_2">
	 * <div class="inner-td-div">红烧鸡翅中</div> <div
	 * class="inner-td-div">木耳香干炒黄瓜</div> <a style="" class="btn cell_btn"
	 * onclick="openMrcpDialog('周二','午餐')" id="btn_2_2">修改</a></td>
	 * <td id="td_3_2">
	 * <div class="inner-td-div">豉汁蒸鲳鳊鱼</div> <div
	 * class="inner-td-div">番茄蛋花菜心汤</div> <a style="" class="btn cell_btn"
	 * onclick="openMrcpDialog('周三','午餐')" id="btn_3_2">修改</a></td>
	 * <td id="td_4_2">
	 * <div class="inner-td-div">肉末蒸蛋</div> <div
	 * class="inner-td-div">胡萝卜千张炒白菜</div> <a style="" class="btn cell_btn"
	 * onclick="openMrcpDialog('周四','午餐')" id="btn_4_2">修改</a></td>
	 * <td id="td_5_2">
	 * <div class="inner-td-div">蘑菇肉丝</div> <div
	 * class="inner-td-div">菜心米粉干</div> <a style="" class="btn cell_btn"
	 * onclick="openMrcpDialog('周五','午餐')" id="btn_5_2">修改</a></td>
	 * </tr>
	 * <tr>
	 * <td>早点</td>
	 * <td id="td_1_3">
	 * <div class="inner-td-div">干点</div> <div class="inner-td-div">水果拼盘</div>
	 * <a style="" class="btn cell_btn" onclick="openMrcpDialog('周一','午点')"
	 * id="btn_1_3">修改</a></td>
	 * <td id="td_2_3">
	 * <div class="inner-td-div">干点</div> <div class="inner-td-div">水果拼盘</div>
	 * <a style="" class="btn cell_btn" onclick="openMrcpDialog('周二','午点')"
	 * id="btn_2_3">修改</a></td>
	 * <td id="td_3_3">
	 * <div class="inner-td-div">干点</div> <div class="inner-td-div">水果拼盘</div>
	 * <a style="" class="btn cell_btn" onclick="openMrcpDialog('周三','午点')"
	 * id="btn_3_3">修改</a></td>
	 * <td id="td_4_3">
	 * <div class="inner-td-div">干点</div> <div class="inner-td-div">水果拼盘</div>
	 * <a style="" class="btn cell_btn" onclick="openMrcpDialog('周四','午点')"
	 * id="btn_4_3">修改</a></td>
	 * <td id="td_5_3">
	 * <div class="inner-td-div">干点</div> <div class="inner-td-div">水果拼盘</div>
	 * <a style="" class="btn cell_btn" onclick="openMrcpDialog('周五','午点')"
	 * id="btn_5_3">修改</a></td>
	 * 
	 * </tr>
	 * </tbody>
	 * </table>
	 */
}
