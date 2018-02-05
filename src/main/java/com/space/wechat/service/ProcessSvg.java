package com.space.wechat.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;

public class ProcessSvg {

	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		String path = "/Users/yejianfei/WORK/bak";
		File dir = new File(path);
		List<JSONObject> svgList = new ArrayList<JSONObject>();
		StringBuffer result = new StringBuffer("");
		try {
			int i = 0;
			if (dir.isDirectory()) {
				File[] tempList = dir.listFiles();
				for (File file : tempList) {
					String uri = path + "/" + file.getName();
					System.out.println("file=" + file.getName());
					Document d = XmlParser.getDocument(uri);
					JSONObject attr = new JSONObject();
					JSONObject svg = new JSONObject();
					listNodes(d.getRootElement(), attr);
					String svgname = "g-"
							+ file.getName().substring(3,
									file.getName().length() - 9);

					svg.put("name", svgname);
					svg.put("attr", attr);
					svgList.add(svg);
				}

				/**
				 * "alert": { "width": 16, "height": 16, "d":
				 * "M15.72 12.5l-6.85-11.98C8.69 0.21 8.36 0.02 8 0.02s-0.69 0.19-0.87 0.5l-6.85 11.98c-0.18 0.31-0.18 0.69 0 1C0.47 13.81 0.8 14 1.15 14h13.7c0.36 0 0.69-0.19 0.86-0.5S15.89 12.81 15.72 12.5zM9 12H7V10h2V12zM9 9H7V5h2V9z"
				 * },
				 */
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file = new File("/Users/yejianfei/WORK/svg.js");
		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			for (JSONObject svg : svgList) {
				//
				List<String> strList = convertStr(svg);
				for (String str : strList) {
					writer.write(str);
					writer.newLine();// 换行
				}

			}
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<String> convertStr(JSONObject svg) {
		List<String> result = new ArrayList<String>();
		JSONObject attr = svg.getJSONObject("attr");
		result.add("  \"" + svg.getString("name").replace("_", "-") + "\": {");
		result.add("    \"width\": " + attr.getString("width") + ",");
		result.add("    \"height\": " + attr.getString("height") + ",");
		result.add("    \"d\": \"" + attr.getString("d") + "\"");
		result.add("  },");
		return result;
	}

	/**
	 * 遍历当前节点元素下面的所有(元素的)子节点
	 * 
	 * @param node
	 */
	public static void listNodes(Element node, JSONObject svg) {

		// System.out.println("当前节点的名称：：" + node.getName());
		// 获取当前节点的所有属性节点
		List<Attribute> list = node.attributes();
		String path = "";
		// 遍历属性节点
		if (node.getName().equals("path") && svg.getString("d") != null
				&& !svg.getString("d").equals("")) {
			return;
		}
		for (Attribute attr : list) {
			if (node.getName().equals("svg")) {
				if (attr.getName().equals("height")) {
					svg.put("height", attr.getValue());
				} else if (attr.getName().equals("width")) {
					svg.put("width", attr.getValue());
				}
			} else if (node.getName().equals("path")) {
				if (node.attributeValue("fill") != null) {
					continue;
				}
				if (attr.getName().equals("d")) {
					svg.put("d", attr.getValue());
				} else if (attr.getName().equals("fill")) {
					continue;
				}
			}
			// System.out.println(attr.getName() + "---" + attr.getValue());
		}

		if (!(node.getTextTrim().equals(""))) {
			System.out.println("文本内容：：：：" + node.getText());
		}

		// 当前节点下面子节点迭代器
		Iterator<Element> it = node.elementIterator();
		// 遍历
		while (it.hasNext()) {
			// 获取某个子节点对象
			Element e = it.next();
			// 对子节点进行遍历
			listNodes(e, svg);
		}
	}
}
