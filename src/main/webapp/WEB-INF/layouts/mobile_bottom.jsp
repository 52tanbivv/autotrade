<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <script type="text/javascript" src="${ctx}/sea-modules/seajs/dist/sea.js?v=${version}"></script>
<script type="text/javascript" src="${ctx}/sea-modules/seajs/sea.config.js?v=${version}"></script> --%>
<script type="text/javascript">
/**
 * 公共参数
 */
var basicParameters = {campusid:${campusid},orgcode:${orgcode},userid:${userid},usertype:${usertype},stuid:'${stuid}',appid:'${appid}',fromusername:'${fromusername}',ctx:'${pageContext.request.contextPath}',loadingPage:'${ctx}${commonUrl_loadingPage}',loadingData:'${ctx}${commonUrl_loadingData}',xxType:'${xxType}'};
/**
 * 微信公共参数
 */
var wxBaseInfo = {wxappid : "${jsApiItem['wxappid']}",timestamp : "${jsApiItem['timestamp']}",nonceStr : "${jsApiItem['nonceStr']}",signature : "${jsApiItem['signature']}",wxslaveuser : "${jsApiItem['wxslaveuser']}",
		netType : (function(callback){
			wx.ready(function(){
				wx.getNetworkType({
				    success: function (res) {
				    	var networkType = res.networkType; // 返回网络类型2g，3g，4g，wifi
				        callback(networkType);
				    }
				});
			});
		})
};
</script>
<!-- api参数 -->
<%@ include file="/WEB-INF/layouts/commonParameters.jsp"%>
<!-- 微信接口 -->
<%@ include file="/WEB-INF/layouts/wechatJssdk.jsp"%>
<!-- 微、易信权限过滤屏蔽 -->
<script type="text/javascript" src="${ctx}/static/styles/mobile/wechat/verifyWechatVisit.js?v=${version}2"></script>
<!-- 菜单 -->
<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery.mmenu/js/jquery.mmenu.min.all.js?v=${version}"></script>
<script type="text/javascript" src="${ctx}/static/styles/mobile/js/mpage_header.js?v=${version}122401"></script>
<!-- 提示框 -->
<script type="text/javascript" src="${ctx}/static/plugins/tools/promptBox/PromptBoxUtil.js?v=${version}1022"></script>
<!-- 图片播放器 -->
<script type="text/javascript" src="${ctx}/static/plugins/jquery/swiper/js/swiper.jquery.min.js?v=${version}"></script>
<!-- 图片工具 -->
<script type="text/javascript" src="${ctx}/static/plugins/tools/image/imageUtil.js?v=${version}0111"></script>
<!-- 字符处理工具 -->
<script type="text/javascript" src="${ctx}/static/styles/mobile/js/stringUtil.js?v=${version}0219"></script>
<!-- 时间人性化工具 -->
<script type="text/javascript" src="${ctx}/static/plugins/tools/datetime/datetimeUtil.min.js?v=${version}"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/wlzy/jWeixinApi.js?v=${version}"></script> --%>
<!-- 移动端时间控件 -->
<script type="text/javascript" src="${ctx}/static/plugins/jquery/mobiscroll/js/mobiscroll.custom-2.17.0.min.js?v=${version}"></script>
<!-- Json处理工具 -->
<script type="text/javascript" src="${ctx}/static/plugins/tools/json/JsonUtil.js?v=${version}"></script>
<script type="text/javascript">
var PB = new PromptBox();
var dateTimesUtil = new dateTime();
var JsonsUtil = new JsonUtil();
</script>
<%-- <c:if test="${rapMode!=null && rapMode==3}">
	<script src="${rapUrl}?projectId=2&mode=${rapMode}"></script>
</c:if> --%>
