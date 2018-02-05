package com.space.wechat.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class FenCiUtil {

	public static List<String> fenCi(String text) {

		try {

			// 创建分词对象
			Analyzer anal = new IKAnalyzer(true);
			StringReader reader = new StringReader(text);
			// 分词
			TokenStream ts = anal.tokenStream("", reader);
			CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
			// 遍历分词数据
			List<String> l = new ArrayList<String>();

			while (ts.incrementToken()) {
				l.add(term.toString());
				System.out.print(term.toString() + "|");

			}
			reader.close();
			System.out.println();

			return l;

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public void processFenCi(List<String> cizu) {

	}

}
