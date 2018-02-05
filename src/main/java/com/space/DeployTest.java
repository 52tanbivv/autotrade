package com.space;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//Runtime.getRuntime().exec("你的命令",null,new File("d:/test"));
public class DeployTest {
	public static void main(String[] args) {
		// 工程根目录
		String projectDir = "f:/workspace/integratetest";
		// 执行clean
		String cmd = "cmd /c mvn clean";
		BufferedReader br = null;
		try {
			Process proc = Runtime.getRuntime().exec(cmd, null,
					new File(projectDir));
			InputStream os = proc.getInputStream();
			br = new BufferedReader(new InputStreamReader(os));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			// 执行package 打包
			cmd = "cmd /c mvn package"; // 打包时存在 版本号 不在文件名上的问题
			proc = Runtime.getRuntime().exec(cmd, null, new File(projectDir));
			os = proc.getInputStream();
			br = new BufferedReader(new InputStreamReader(os));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			// 执行clean
			cmd = "cmd /c mvn tomcat7:deploy"; // 部署所需war包 需要带版本号
			proc = Runtime.getRuntime().exec(cmd, null, new File(projectDir));
			os = proc.getInputStream();
			br = new BufferedReader(new InputStreamReader(os));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}