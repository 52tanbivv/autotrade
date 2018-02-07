package com.space.wechat.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;

public class ProcessSvgBootStrap {
	// <div><img src='./action/svg/ic_3d_rotation_24px.svg'><br>3d
	// rotation</div>

	public static void main(String[] args) {

		JSONObject json = new JSONObject();
		String path = "/Users/yejianfei/WORK/bak";
		File dir = new File(path);
		List<JSONObject> svgList = new ArrayList<JSONObject>();
		StringBuffer result = new StringBuffer("");

		File file = new File("/Users/yejianfei/WORK/svgccc.js");
		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			try {
				int i = 0;
				if (dir.isDirectory()) {
					File[] tempList = dir.listFiles();
					for (File svgfile : tempList) {

						String svgname = "g-"
								+ svgfile
										.getName()
										.substring(3,
												svgfile.getName().length() - 9)
										.replace("_", "-");

						writer.write(getIconName(svgname));
						writer.newLine();// 换行
					}

				}

				for (int j = 1; j < 361; j++) {

					String svgname = "b" + j;

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	private static String getIconName(String iconname) {
		// <div><img src='./action/svg/ic_3d_rotation_24px.svg'><br>3d
		// rotation</div>
		return "<div><icon name='" + iconname + "' scale='2'></icon><br>"
				+ iconname + "</div>";
	}

	public static void main2(String[] args) {
		JSONObject json = new JSONObject();

		try {
			Document d = XmlParser
					.getDocument("/Users/yejianfei/WORK/fontawesome-webfont.svg");
			JSONObject attr = new JSONObject();
			JSONObject svg = new JSONObject();
			listNodes(d.getRootElement(), attr);
			// String svgname = "g-"
			// + file.getName().substring(3, file.getName().length() - 9);
			//
			// svg.put("name", svgname);
			// svg.put("attr", attr);
			// svgList.add(svg);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		File file = new File("/Users/yejianfei/WORK/svg_b.js");

		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			// 当前节点下面子节点迭代器
			Iterator<Element> it = node.elementIterator();
			// 遍历
			int i = 0;
			while (it.hasNext()) {
				i++;
				// 获取某个子节点对象
				Element childNode = it.next();
				// 对子节点进行遍历
				System.out.println("unicode=b" + i + " d="
						+ childNode.attributeValue("d"));

				String name = "b" + i;
				String d = childNode.attributeValue("d");

				String str = "\"" + name
						+ "\": {\"width\": 24,\"height\": 24, \"d\": \"" + d
						+ "\"},";
				writer.write(str);
				writer.newLine();// 换行

				// "g-accessible": {
				// "width": 24,
				// "height": 24,
				// "d":
				// "M19 13v-2c-1.54.02-3.09-.75-4.07-1.83l-1.29-1.43c-.17-.19-.38-.34-.61-.45-.01 0-.01-.01-.02-.01H13c-.35-.2-.75-.3-1.19-.26C10.76 7.11 10 8.04 10 9.09V15c0 1.1.9 2 2 2h5v5h2v-5.5c0-1.1-.9-2-2-2h-3v-3.45c1.29 1.07 3.25 1.94 5 1.95zm-6.17 5c-.41 1.16-1.52 2-2.83 2-1.66 0-3-1.34-3-3 0-1.31.84-2.41 2-2.83V12.1c-2.28.46-4 2.48-4 4.9 0 2.76 2.24 5 5 5 2.42 0 4.44-1.72 4.9-4h-2.07z"
				// },

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

}
