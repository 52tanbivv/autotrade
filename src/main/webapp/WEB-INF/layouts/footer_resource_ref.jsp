<%@ page language="java" pageEncoding="UTF-8" %>

  <!-- Remember to include excanvas for IE8 chart support -->
        <!--[if IE 8]><script src="js/helpers/excanvas.min.js"></script><![endif]-->

        <!-- Include Jquery library from Google's CDN but if something goes wrong get Jquery from local file (Remove 'http:' if you have SSL) -->
        <script src="${ctx}/static/ajax/libs/jquery/1.11.0/jquery.min.js?ver=${version}"></script>
        <script>!window.jQuery && document.write(decodeURI('%3Cscript src="${ctx}/static/pixelcave/backend/js/vendor/jquery-1.11.0.min.js"%3E%3C/script%3E'));</script>

		<input type="hidden" id="login" value="${ctx}">
        <!-- Bootstrap.js, Jquery plugins and Custom JS code -->
        <script src="${ctx}/static/pixelcave/backend/js/vendor/bootstrap.min.js?ver=${version}"></script>
        <script src="${ctx}/static/pixelcave/backend/js/plugins.js?ver=${version}2"></script>
        <script src="${ctx}/static/pixelcave/backend/js/app.js?ver=${version}"></script>
		
		
		<!-- select check start-->
		<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery/jquery-ui.css?ver=${version}" />
		<link rel="stylesheet" type="text/css" href="${ctx}/static/multiselect/jquery.multiselect.css?ver=${version}" />
		<script type="text/javascript" src="${ctx}/static/multiselect/jquery.multiselect.js?ver=${version}"></script>
		<!-- select check end-->
		
		<!-- 字符处理工具 -->
		<script type="text/javascript" src="${ctx}/static/styles/mobile/js/stringUtil.js?v=${version}"></script>
		<!-- Json处理工具 -->
		<script type="text/javascript" src="${ctx}/static/plugins/tools/json/JsonUtil.js?v=${version}"></script>
		
        <!-- Google Maps API + Gmaps Plugin, must be loaded in the page you would like to use maps (Remove 'http:' if you have SSL) -->
        <!-- <script src="http://maps.google.com/maps/api/js?sensor=true"></script> -->
       <%--  <script src="${ctx}/static/pixelcave/backend/js/helpers/gmaps.min.js"></script> --%>
