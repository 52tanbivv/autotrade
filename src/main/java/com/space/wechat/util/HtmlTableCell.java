package com.space.wechat.util;

import java.util.ArrayList;
import java.util.List;

/**
 * HTML table 中的td对象
 * 
 * @author yejianfei
 * 
 */
public class HtmlTableCell {
	private String text = "";
	private String id = "";
	private String cssclass = "";
	private List<String> content = new ArrayList<String>();

	public HtmlTableCell(String text) {
		this.text = text;
	}

	public HtmlTableCell(String text, String id) {
		this.text = text;
		this.id = id;
	}

	public HtmlTableCell(String text, String id, List<String> content) {
		this.text = text;
		this.id = id;
		this.content = content;
	}

	public HtmlTableCell(String text, List<String> content) {
		this.text = text;
		this.content = content;
	}

	public HtmlTableCell() {
		// TODO Auto-generated constructor stub
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCssclass() {
		return cssclass;
	}

	public void setCssclass(String cssclass) {
		this.cssclass = cssclass;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

	public String toHtml() {
		StringBuffer html = new StringBuffer("<td ");
		html.append(HtmlUtil.getHtmlAttr("id", this.getId()));
		html.append(HtmlUtil.getHtmlAttr("class", this.getCssclass()));
		html.append(" style=\"text-align: center;vertical-align: middle;\">");
		html.append(this.getText());
		html.append(this.getTdContentHtml());
		html.append("</td>");
		return html.toString();
	}

	private String getTdContentHtml() {
		StringBuffer html = new StringBuffer("");
		for (String ht : content) {
			html.append(ht);
		}
		return html.toString();
	}
}
