<%@ page contentType="text/html;charset=UTF-8"%>
<%-- <%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>微信图片轮播脚本</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

</head>

<body>
	<script type="text/javascript">
		var uin = "MTU1NjU1NTYwMA==";
		var key = "19447e47ed64605c42f9a6e5505e40a5203421f6708688873d2a973b5506d62acea068d54635fb401f60549b534353ac";
		var pass_ticket = "36PJR70lhNG22I1UMIUpQGsbvYtOQ0nwQNX2GrPRPRwCA5D8UcR0aTB67rjwYzF6";
	</script>

	<script>
		window.moon_map = {
			"a/ios.js" : "/mmbizwap/zh_CN/htmledition/js/a/ios21e2a6.js",
			"a/android.js" : "/mmbizwap/zh_CN/htmledition/js/a/android21dd09.js",
			"a/profile.js" : "/mmbizwap/zh_CN/htmledition/js/a/profile2183d0.js",
			"biz_common/utils/url/parse.js" : "/mmbizwap/zh_CN/htmledition/js/biz_common/utils/url/parse20910f.js",
			"biz_common/utils/report.js" : "/mmbizwap/zh_CN/htmledition/js/biz_common/utils/report20910f.js",
			"biz_common/utils/cookie.js" : "/mmbizwap/zh_CN/htmledition/js/biz_common/utils/cookie20910f.js",
			"appmsg/like.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/like2107db.js",
			"appmsg/a.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/a21dd09.js",
			"biz_common/tmpl.js" : "/mmbizwap/zh_CN/htmledition/js/biz_common/tmpl2107db.js",
			"biz_common/dom/class.js" : "/mmbizwap/zh_CN/htmledition/js/biz_common/dom/class20910f.js",
			"biz_wap/utils/ajax.js" : "/mmbizwap/zh_CN/htmledition/js/biz_wap/utils/ajax215c4d.js",
			"biz_common/utils/string/html.js" : "/mmbizwap/zh_CN/htmledition/js/biz_common/utils/string/html20910f.js",
			"biz_common/ui/imgonepx.js" : "/mmbizwap/zh_CN/htmledition/js/biz_common/ui/imgonepx21b6dc.js",
			"biz_common/dom/attr.js" : "/mmbizwap/zh_CN/htmledition/js/biz_common/dom/attr20910f.js",
			"appmsg/report.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/report215c4d.js",
			"appmsg/report_and_source.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/report_and_source20f7b6.js",
			"appmsg/share.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/share212f4d.js",
			"appmsg/page_pos.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/page_pos21e3f7.js",
			"appmsg/cdn_speed_report.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/cdn_speed_report211355.js",
			"appmsg/iframe.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/iframe21b020.js",
			"appmsg/review_image.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/review_image21abce.js",
			"appmsg/outer_link.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/outer_link20910f.js",
			"biz_wap/jsapi/core.js" : "/mmbizwap/zh_CN/htmledition/js/biz_wap/jsapi/core20910f.js",
			"biz_wap/utils/mmversion.js" : "/mmbizwap/zh_CN/htmledition/js/biz_wap/utils/mmversion20910f.js",
			"biz_common/dom/event.js" : "/mmbizwap/zh_CN/htmledition/js/biz_common/dom/event20910f.js",
			"appmsg/async.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/async21e3f7.js",
			"biz_wap/ui/lazyload_img.js" : "/mmbizwap/zh_CN/htmledition/js/biz_wap/ui/lazyload_img21b6dc.js",
			"biz_common/log/jserr.js" : "/mmbizwap/zh_CN/htmledition/js/biz_common/log/jserr211fa1.js",
			"appmsg/index.js" : "/mmbizwap/zh_CN/htmledition/js/appmsg/index21e2a6.js"
		};
	</script>
	<script type="text/javascript"
		src="http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/js/biz_wap/moon2107db.js"></script>

	<script type="text/javascript">
		var networkType;
		window.appmsgstat = {
			"show" : ("false" == "true"),
			"liked" : ("" == "true"),
			"read_num" : "" * 1,
			"like_num" : "" * 1
		};
		window.appmsgticket = "50IIWokbKcbx9UyR7LdEFLPAjgl1rPC8q369u3z+cTLhqhGYrzW/lFhPShBeUM83";
		seajs.use("appmsg/index.js");
	</script>
</body>
</html>