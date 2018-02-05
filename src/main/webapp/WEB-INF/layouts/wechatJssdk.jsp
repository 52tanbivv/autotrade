<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${ctx}/sea-modules/component/tools/weixin/wx.config.js?v=${version}5"></script>
<script type="text/javascript" src="${ctx}/static/js/wlzy/WeixinApi.js?v=${version}5"></script>
<script type="text/javascript">
	/**
	 * 分享朋友圈或者发送给朋友
	 * @param title 分享标题
	 * @param desc 分享描述
	 * @param link  分享链接
	 * @param imgUrl 分享图标
	 * @param type 分享类型,music、video或link，不填默认为link
	 * @param dataUrl 如果type是music或video，则要提供数据链接，默认为空
	 */
	function onMenuShareAppMessage(title,desc,link,imgUrl,type,dataUrl){
		if(imgUrl==""){
			$.each($('.content img'),function(i,item){
			       if(item.src && imgUrl=="") {
			    	   imgUrl=item.src;
			       }
			    });
		}
		if(/YiXin/.test(navigator.userAgent)){
			
			document.addEventListener('YixinJSBridgeReady', function onBridgeReady() {
				YixinJSBridge.on('menu:share:appmessage', function(argv) {
					YixinJSBridge.invoke('sendAppMessage', {
						"img_url" :imgUrl,
						"link" : link,
						"desc" :desc,
						"title" : title
					}, function(res) {
						//alert(1+res);
						// document.location.href = mebtnopenurl;
					})
				});

				YixinJSBridge.on('menu:share:timeline', function(argv) {
					YixinJSBridge.invoke('shareTimeline', {
						"img_url" : imgUrl,
						"img_width" : "640",
						"img_height" : "640",
						"link" : link,
						"desc" : desc,
						"title" : title
					}, function(res) {
						//alert(2+res);
						// document.location.href = mebtnopenurl;
					});
				});
				
				//分享微博
				YixinJSBridge.on('menu:share:weibo', function (argv) {
					YixinJSBridge.invoke('shareWeibo', {
					    "content": desc,
					    "url": link,
					}, function (res) {
					    //不用处理，客户端会有分享结果提示
					});
				});
				
				//分享微信朋友圈
				
				
			}, false);
		}else{
			wx.ready(function () {
				if (typeof wx == "undefined"){
					
				}else{
					//获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
					wx.onMenuShareTimeline({
					    title: title, // 分享标题
					    link: link, // 分享链接
					    imgUrl: imgUrl, // 分享图标
					    success: function () { 
					        // 用户确认分享后执行的回调函数
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    }
					});
					//获取“分享给朋友”按钮点击状态及自定义分享内容接口
					wx.onMenuShareAppMessage({
					    title: title, // 分享标题
					    desc: desc, // 分享描述
					    link: link, // 分享链接
					    imgUrl: imgUrl, // 分享图标
					    type: type, // 分享类型,music、video或link，不填默认为link
					    dataUrl: dataUrl, // 如果type是music或video，则要提供数据链接，默认为空
					    success: function () { 
					        // 用户确认分享后执行的回调函数
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    }
					});
					/**增加qq分享享机制**/
					wx.onMenuShareQQ({
					    title: title, // 分享标题
					    desc: desc, // 分享描述
					    link: link, // 分享链接
					    imgUrl: imgUrl, // 分享图标
					    success: function () { 
					       // 用户确认分享后执行的回调函数
					    },
					    cancel: function () { 
					       // 用户取消分享后执行的回调函数
					    }
					});
				}
			});
		}
		
	}
	
</script>