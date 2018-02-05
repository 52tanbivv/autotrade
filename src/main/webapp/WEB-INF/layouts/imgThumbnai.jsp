<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="imgThumbnailParam" value="@120w_120h_1e_0c_0i_60Q_1x_1o.jpg" />
<c:set var="oscdn_domain" value="static.weixiaotong.com.cn" />
<c:set var="imgThumbnailParam200" value="@200w_200h_1e_0c_0i_60Q_1x_1o.jpg" />
<c:set var="imgThumbnailParam120" value="@120w_120h_1e_0c_0i_60Q_1x_1o" />
<c:set var="imgThumbnailParam320" value="@320w_320h_1e_0c_0i_70Q_1x_1o" />
<c:set var="imgThumbnailParam700" value="@700w_700h_1e_0c_0i_70Q_1x_1o" />
<script type="text/javascript">
/**
 * 判断图片路径是否属于阿里的cdn
 * @param qiniuUrl
 * @returns
 */
function checkQiniuUrl(qiniuUrl) {
	var oscdn_domain = '${oscdn_domain}';
	if (qiniuUrl.indexOf(oscdn_domain) > 0 && qiniuUrl.indexOf(".") > 0 && qiniuUrl.indexOf("@")<=0) {

		return qiniuUrl + '${imgThumbnailParam320}'+ "." + qiniuUrl.split(".")[qiniuUrl.split(".").length - 1];;
	} else {
		return qiniuUrl;
	}
}

/**
 * 判断图片路径是否属于阿里的cdn
 * @param qiniuUrl
 * @returns
 */
function checkQiniuUrl120(qiniuUrl) {
	var oscdn_domain = '${oscdn_domain}';
	if (qiniuUrl.indexOf(oscdn_domain) > 0 && qiniuUrl.indexOf(".") > 0) {
		if(qiniuUrl.indexOf("@")>0){
			return qiniuUrl.split("@")[0] + '${imgThumbnailParam120}'+ "." + qiniuUrl.split(".")[qiniuUrl.split(".").length - 1];
		}else{
			return qiniuUrl + '${imgThumbnailParam120}'+ "." + qiniuUrl.split(".")[qiniuUrl.split(".").length - 1];;
		}
	} else {
		return qiniuUrl;
	}
}

function checkQiniuUrl700(qiniuUrl) {
	var oscdn_domain = '${oscdn_domain}';
	if (qiniuUrl.indexOf(oscdn_domain) > 0 && qiniuUrl.indexOf(".") > 0 && qiniuUrl.indexOf("@")<=0) {

		return qiniuUrl + '${imgThumbnailParam700}'+ "." + qiniuUrl.split(".")[qiniuUrl.split(".").length - 1];;
	} else {
		return qiniuUrl;
	}
}

/**
 * 阿里云 OSS 图片处理服务
 * @param qiniuUrl
 * @returns
 */
function ossImg(qiniuUrl, imgThumbnailParam) {
	var oscdn_domain = '${oscdn_domain}';
	if (qiniuUrl.indexOf(oscdn_domain) > 0 && qiniuUrl.indexOf(".") > 0 && qiniuUrl.indexOf("@")<=0) {
		return qiniuUrl + imgThumbnailParam + "." + qiniuUrl.split(".")[qiniuUrl.split(".").length - 1];
	} else {
		return qiniuUrl;
	}
}
</script>
