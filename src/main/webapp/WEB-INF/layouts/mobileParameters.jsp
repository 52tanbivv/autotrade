<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${ctx}/static/styles/mobile/js/PromptBoxUtil.js?v=${version}"></script>
<script type="text/javascript">
var PB = new PromptBox();
var cloudLibrary = {QINIU:"static.weixiaotong.com.cn",defaultImageConfig:["@",100,"w_",100,"h_",1,"e_",0,"c_",0,"i_",60,"Q_",1,"x_",1,"o","png"]};
/**
 * 设置七牛图片参数
 * 参数：1、图片地址2、图片类型3、宽4、高
 */
function setQiNiuImageSize(){
	var imageSrc = arguments[0];
	var imageType =/\.[^\.]+$/.exec(imageSrc);
	cloudLibrary.defaultImageConfig[17]=imageType;
	if(arguments[1])
		cloudLibrary.defaultImageConfig[17]=arguments[1];
	if(arguments[2])
		cloudLibrary.defaultImageConfig[1]=arguments[2];
	if(arguments[3])
		cloudLibrary.defaultImageConfig[3]=arguments[3];
	var imageConfig = cloudLibrary.defaultImageConfig.join("");
	if (imageSrc.indexOf(cloudLibrary.QINIU) > 0) {
		return imageSrc + imageConfig;
	} else {
		return imageSrc;
	}
}
</script>