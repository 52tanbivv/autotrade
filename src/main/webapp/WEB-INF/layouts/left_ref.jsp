<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<div id="sidebar">
	<!-- Wrapper for scrolling functionality -->
	<div class="sidebar-scroll">
		<!-- Sidebar Content -->
		<div class="sidebar-content">
			<!-- Brand -->
			<a id="indexLogin_logo" href="${portalUrl}" class="sidebar-brand"></a>
			<!-- END Brand -->

			<!-- User Info -->
			<div class="sidebar-section sidebar-user clearfix">
				<!-- div class="sidebar-user-avatar">
					<a href="#" id="abcd"> <%-- ${ctx}/static/pixelcave/backend/img/placeholders/avatars/avatar2.jpg --%>
						<img src="<shiro:principal property="iconpath"/>">
					</a>

				</div>
				<div class="sidebar-user-name">
					<span style="font-size: 12px"><shiro:principal
							property="name" /></span>
				</div -->
				<!-- div class="sidebar-user-links">
					<a href="#" data-toggle="tooltip"
						data-placement="bottom" title="Profile"><i class="gi gi-user"></i></a>
					<a href="#" data-toggle="tooltip"
						data-placement="bottom" title="Messages"><i
						class="gi gi-envelope"></i></a> -->
					<!-- Opens the user settings modal that can be found at the bottom of each page (page_footer.html in PHP version) -->
					<!-- a href="javascript:openUserSettings();" title="修改信息" id="cancel" name="cancel"><i
						class="gi gi-cogwheel"></i></a> <a href="${ctx}/logout"
						data-toggle="tooltip" data-placement="bottom" title="退出"><i
						class="gi gi-exit"></i></a>
				</div -->
				
			</div>
			<!-- END User Info -->

			<!-- Theme Colors -->
			<!-- Change Color Theme functionality can be found in js/app.js - templateOptions() -->
			<!-- ul class="sidebar-section sidebar-themes clearfix">
				<li class="active"><a href="javascript:void(0)"
					class="themed-background-dark-default themed-border-default"
					data-theme="default" data-toggle="tooltip" title="Default Blue"></a>
				</li>
				<li><a href="javascript:void(0)"
					class="themed-background-dark-night themed-border-night"
					data-theme="${ctx}/static/pixelcave/backend/css/themes/night.css"
					data-toggle="tooltip" title="Night"></a></li>
				<li><a href="javascript:void(0)"
					class="themed-background-dark-amethyst themed-border-amethyst"
					data-theme="${ctx}/static/pixelcave/backend/css/themes/amethyst.css"
					data-toggle="tooltip" title="Amethyst"></a></li>
				<li><a href="javascript:void(0)"
					class="themed-background-dark-modern themed-border-modern"
					data-theme="${ctx}/static/pixelcave/backend/css/themes/modern.css"
					data-toggle="tooltip" title="Modern"></a></li>
				<li><a href="javascript:void(0)"
					class="themed-background-dark-autumn themed-border-autumn"
					data-theme="${ctx}/static/pixelcave/backend/css/themes/autumn.css"
					data-toggle="tooltip" title="Autumn"></a></li>
				<li><a href="javascript:void(0)"
					class="themed-background-dark-flatie themed-border-flatie"
					data-theme="${ctx}/static/pixelcave/backend/css/themes/flatie.css"
					data-toggle="tooltip" title="Flatie"></a></li>
				<li><a href="javascript:void(0)"
					class="themed-background-dark-spring themed-border-spring"
					data-theme="${ctx}/static/pixelcave/backend/css/themes/spring.css"
					data-toggle="tooltip" title="Spring"></a></li>
				<li><a href="javascript:void(0)"
					class="themed-background-dark-fancy themed-border-fancy"
					data-theme="${ctx}/static/pixelcave/backend/css/themes/fancy.css"
					data-toggle="tooltip" title="Fancy"></a></li>
				<li><a href="javascript:void(0)"
					class="themed-background-dark-fire themed-border-fire"
					data-theme="${ctx}/static/pixelcave/backend/css/themes/fire.css"
					data-toggle="tooltip" title="Fire"></a></li>
			</ul -->
			<!-- END Theme Colors -->

			<!-- Sidebar Navigation -->
			<ul class="sidebar-nav" id="left_menu">
				<c:forEach items="${funItems}" var="item">
						<li id="${item.tabid}" name="${item.tabid}">
							<a
							<c:if test="${item.url != '' && item.url != null}"> 
							href="${ctx}/${item.url}?appid=${item.id}"
							class="fontColor_black"
							</c:if>
							<c:if test="${item.parentid=='0'}">
								style="border-top:1px solid #e5e5e5;cursor:default;color:#32ad64;padding-top:5px;font-weight:bold;"
							</c:if>
							style="color:#2ba2e0;">
								<span>${item.functionname}</span>
								<c:if test="${item.parentid=='0'}">
									<i style="background:url(${item.icopath}) no-repeat center;background-size:18px;"></i>
								</c:if>
							</a>
						</li>
				</c:forEach>
				<li>
			</ul>
			<!-- END Sidebar Navigation -->


		</div>
		<!-- END Sidebar Content -->
	</div>
	<!-- END Wrapper for scrolling functionality -->
</div>

<!-- User Settings, modal which opens from Settings link (found in top right user menu) and the Cog link (found in sidebar user info) -->
<div id="modal-user-settings" class="modal fade" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header text-center">
				<h2 class="modal-title">
					<i class="fa fa-pencil"></i> 用户基本信息设置
				</h2>
			</div>
			<!-- END Modal Header -->

			<!-- Modal Body -->
			<div class="modal-body">
				<form id="profileSetForm" class="form-horizontal form-bordered"
					onsubmit="return false;">
					<fieldset>
						<div class="form-group">
							<label class="col-md-4 control-label">登录名</label>
							<div class="col-md-8">
								<p class="form-control-static" name="profileSetForm_loginname" id="profileSetForm_loginname"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label" for="user-settings-email">姓名</label>
							<div class="col-md-8">
								<input type="text" id="profileSetForm_name"
									name="profileSetForm_name" required="required"
									class="form-control" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label" for="user-settings-email">绑定秘钥</label>
							<div class="col-md-8">
								<input type="text" id="profileSetForm_keysalt"
									readonly="readonly" required="required"
									name="profileSetForm_keysalt" class="form-control"
									value="">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-4 control-label" for="user-settings-email">手机</label>
							<div class="col-md-8">
								<input type="text" id="profileSetForm_phone" required="required"
									name="profileSetForm_phone" class="form-control"
									value="">
							</div>
						</div>

					</fieldset>
					<fieldset>
						<div class="form-group">
							<label class="col-md-4 control-label"
								for="profileSetForm_password">新密码</label>
							<div class="col-md-8">
								<input type="password" id="profileSetForm_password"
									name="profileSetForm_password" class="form-control"
									placeholder="输入登录密码">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label"
								for="profileSetForm_repassword">确认密码</label>
							<div class="col-md-8">
								<input type="password" id="profileSetForm_repassword"
									name="profileSetForm_repassword" class="form-control"
									equalTo="#profileSetForm_password" placeholder="再次输入登录密码">
							</div>
						</div>
					</fieldset>
					<div class="form-group form-actions">
						<div class="col-xs-12 text-right">
							<button type="button" class="btn btn-sm btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" id="profileSetSubmit"
								name="profileSetSubmit" class="btn btn-sm btn-primary">保存</button>
						</div>
					</div>
				</form>
			</div>
			<!-- END Modal Body -->
		</div>
	</div>
</div>
<!-- END User Settings -->

<%@ include file="/WEB-INF/layouts/footer_resource_ref.jsp"%>

<script type="text/javascript">
	
	$(document).ready(
			function() {

				//getEjcd("${baseModel.paraentAppId}", '232');
				setTimeout("changeMenuClass()", 300);//单位毫秒   

				$("#profileSetSubmit").click(
						function() {
						if (isNaN($('#profileSetForm_phone').val())) {
								alert("只能输入数字");
								return ;
							} else if($('#profileSetForm_phone').val().length != 11){
								alert("号码必须为11位");
								return ;
							}else if($('#profileSetForm_password').val() != $('#profileSetForm_repassword').val()){
								alert("两次密码输入不一致，请重新输入");
								return ;
							}else{
								profileSet();
							}
					});
				
				$("#cancel").click(
						function() {
							$('#profileSetForm')[0].reset();
					});
			});

	function changeMenuClass() {
		$("#left_menu").removeClass("active");
		$("#${baseModel.currentTabId}").addClass("active");
// 		$("#${baseModel.parentTabId}").addClass("active");

	}

	function profileSet() {
		if (confirm("是否确定继续操作?")) {
			var url = "${ctx}/profile";
			var submitData = {
				id : "${userModel.id}",
				name : $("#profileSetForm_name").val(),
				phone : $("#profileSetForm_phone").val(),
				plainPassword : $("#profileSetForm_password").val()
			};

			$.post(url, submitData, function(data) {
				alert(data);
				$("#modal-user-settings").modal("hide");
			});
		}

	}

	function getEjcd(parentid, tabid) {
		//获取二级菜单
		var str_id = "ejcd" + parentid;

		$("#left_menu.active").removeClass("active");
		if ($("#" + tabid).hasClass("active") == true) {
			$("#" + tabid + ".active").removeClass("active");
			$("#" + str_id + ".active").removeClass("active");
		} else {

			var submitData = {
				parentid : parentid
			};
			var url = "${ctx}/index/ajax_query_ejcd";
			$.get(url, submitData, function(data) {

				$("#" + str_id).html(data);
			});
		}

	}
	
	function openUserSettings(){
		var url = "${ctx}/profile/getUserById";
		var submitData = {
			id : "${userModel.id}"
		};

		$.post(url, submitData, function(data) {
			var datas = eval("(" + data + ")");
			$("#profileSetForm_loginname").text(datas.loginName);
			$("#profileSetForm_name").val(datas.name);
			$("#profileSetForm_keysalt").val(datas.keysalt);
			$("#profileSetForm_phone").val(datas.phone);
			
			$("#modal-user-settings").modal("show");
		});
	}
</script>
