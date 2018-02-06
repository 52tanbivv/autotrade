package com.space.wechat.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

public class AliyunStsUtil {

	public static JSONObject stsTokenJson = new JSONObject();
	// 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
	public static final String REGION_CN_HANGZHOU = "cn-qingdao";
	// 当前 STS API 版本
	public static final String STS_API_VERSION = "2015-04-01";

	private static final Long DURATION_SECONDS = 3600l;

	public static AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
			String roleSessionName, String policy, ProtocolType protocolType, Long durationSeconds)
			throws ClientException {
		try {
			// 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
			IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
			DefaultAcsClient client = new DefaultAcsClient(profile);

			// 创建一个 AssumeRoleRequest 并设置请求参数
			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setVersion(STS_API_VERSION);
			request.setMethod(MethodType.POST);
			request.setProtocol(protocolType);
			request.setDurationSeconds(durationSeconds);
			request.setRoleArn(roleArn);
			request.setRoleSessionName(roleSessionName);
			request.setPolicy(policy);

			// 发起请求，并得到response
			final AssumeRoleResponse response = client.getAcsResponse(request);

			return response;
		} catch (ClientException e) {
			throw e;
		}
	}

	public static void main(String[] args) {
		// System.out.println((new AliyunStsUtil()).getStsToken());
	}

	public static void refreshStsToken() {
		String accessKeyId = "LTAIsHRnpgOHbgn9";
		String accessKeySecret = "KLEA4Ou34thb4XOOpDzOpVclZeslgb";// 需要在RAM控制台获取，此时要给子账号权限，并建立一个角色，把这个角色赋给子账户，这个角色会有一串值，就是rolearn要填的//记得角色的权限，子账户的权限要分配好，不然会报错
		String roleArn = "acs:ram::1851162222232524:role/pageuploaderrole";// 临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
		String roleSessionName = "alice-001";// 这个可以为空，不好写，格式要对，无要求建议为空
		String policy = null;

		ProtocolType protocolType = ProtocolType.HTTPS;
		try {
			AssumeRoleResponse response = AliyunStsUtil.assumeRole(accessKeyId, accessKeySecret, roleArn,
					roleSessionName, policy, protocolType, DURATION_SECONDS);
			String accesskeyid = response.getCredentials().getAccessKeyId();
			String accesskeysecret = response.getCredentials().getAccessKeySecret();
			String securitytoken = response.getCredentials().getSecurityToken();
			stsTokenJson.put("accessKeyId", accesskeyid);
			stsTokenJson.put("accessKeySecret", accesskeysecret);
			stsTokenJson.put("stsToken", securitytoken);
			System.out.println("刷新ALIYUN OSS TOKEN 成功，TOKEN IS " + stsTokenJson.toJSONString());

		} catch (ClientException e) {
			e.printStackTrace();
		}

	}
}