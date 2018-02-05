package com.space.wechat.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RmtShellExecutor {

	public static void main(String[] args) {
		try {
			String commands = "git branch";

			Process process = Runtime.getRuntime().exec(commands);

			// for showing the info on screen

			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());

			BufferedReader input = new BufferedReader(ir);

			String line;

			while ((line = input.readLine()) != null) {

				System.out.println(line);

			}// end try
		} catch (java.io.IOException e) {

			System.err.println("IOException " + e.getMessage());

		}
	}
}