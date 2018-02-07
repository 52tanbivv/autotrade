<%@ page language="java" pageEncoding="UTF-8" %>
<c:set var="path" value="develop" />
<c:choose>
	<c:when test="${campusItem.templatestyle==1}">
		<link type="text/css" rel="stylesheet" href="${ctx}/static/styles/mobile/css/greenStyle.css?v=${version}0120" />
	</c:when>
	<c:when test="${campusItem.templatestyle==2}">
		<link type="text/css" rel="stylesheet" href="${ctx}/static/styles/mobile/css/blueStyle.css" />
	</c:when>
	<c:when test="${campusItem.templatestyle==3}">
		<link type="text/css" rel="stylesheet" href="${ctx}/static/styles/mobile/css/orangeStyle.css" />
	</c:when>
	<c:otherwise>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/styles/mobile/css/greenStyle.css?v=${version}0120" />
	</c:otherwise>
</c:choose>
<link type="text/css" rel="stylesheet" href="${ctx}/static/plugins/weui/style/weui.min.css?v=${version}"/>
<link type="text/css" rel="stylesheet" href="${ctx}/static/plugins/weui/style/weui.custom.css?v=${version}"/>
<link type="text/css" rel="stylesheet" href="${ctx}/static/plugins/jquery/jquery.mmenu/css/jquery.mmenu.all.css?v=${version}"/>
<link type="text/css" rel="stylesheet" href="${ctx}/views/mobile/css/mpage_header.css?v=${version}"/>
<link type="text/css" rel="stylesheet" href="${ctx}/static/plugins/jquery/swiper/css/swiper.min.css?v=${version}"/>
<link type="text/css" rel="stylesheet" href="${ctx}/static/plugins/jquery/mobiscroll/css/mobiscroll.custom-2.17.0.min.css" />
<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery/1.11.3/jquery-1.11.3.min.js?v=${version}"></script>
