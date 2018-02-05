package com.space.wechat.util.duiba;

import java.util.HashMap;
import java.util.Map;

public class TestDuiBa {
	public String test(){
		String redirect = "";
		CreditTool tool=new CreditTool("2VoLPG1Rdabd3swoCsJtdDaeTsXc", "34Fs4rwxBs961GzUXAMR12R5d9gK");
		Map<String,String> params=new HashMap<String,String>();
		params.put("uid","userId001");
		params.put("credits","100");
		if(redirect!=null){
		    //redirect是目标页面地址，默认积分商城首页是：http://www.duiba.com.cn/chome/index
		    //此处请设置成一个外部传进来的参数，方便运营灵活配置
		    params.put("redirect",redirect);
		}
		String url=tool.buildUrlWithSign("http://www.duiba.com.cn/autoLogin/autologin?",params);
		return url;
	}
	
	public static void main(String[] args) {
		TestDuiBa text = new TestDuiBa();
		System.out.println(text.test());
	}
}
