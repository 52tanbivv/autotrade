<%@ page language="java" pageEncoding="UTF-8" %>
<link href="${ctx}/static/styles/wxStyle/css/myfooter.css" rel="stylesheet" type="text/css"/>
<div class="bottom"></div>
		<div class="home">
			<ul>
				<li>
					<a href="${ctx}/yqdt/yqdt/wxIndex/${orgcode}/${fromusername}">
						<div><img alt="school" src="${ctx}/static/styles/wxStyle/images/footer/0.png" /></div>
					</a>
				</li>
				<li>
					<a href="${ctx}/yqdt/yqdt/getXxjsForWx/${orgcode}/0/${fromusername}">
						<div><img alt="school" src="${ctx}/static/styles/wxStyle/images/schoolintro.png" /></div>
					</a>
				</li>		
				<li>
					<a href="${ctx}/yqdt/yqdt/getXxxwForWx/${orgcode}/0/${fromusername}">
						<div><img alt="school" src="${ctx}/static/styles/wxStyle/images/Dynamiccampus.png" /></div>
					</a>
				</li>
				<c:if test="${errorCode==0}">
				<li>
					<a href="${ctx}/klxx/wlzy/getKlxxItemsForWx/${orgcode}/${fromusername}">
						<div><img alt="school" src="${ctx}/static/styles/wxStyle/images/happystudy.png" /></div>
					</a>
				</li>
				<li>
					<a href="${ctx}/klxx/wlzy/getGrkjItemsForWx/${orgcode}/${fromusername}">
						<div><img alt="school" src="${ctx}/static/styles/wxStyle/images/album.png" /></div>
					</a>
				</li>
				</c:if>	
				<c:if test="${errorCode==-100}">
				<li>
					<a href="${ctx}/yqdt/yqdt/bindingError">
						<div><img alt="school" src="${ctx}/static/styles/wxStyle/images/happystudy.png" /></div>
					</a>
				</li>
				<li>
					<a href="${ctx}/yqdt/yqdt/bindingError">
						<div><img alt="school" src="${ctx}/static/styles/wxStyle/images/album.png" /></div>
					</a>
				</li>
				</c:if>	
				<c:if test="${errorCode==-200}">
				<li>
					<a href="${ctx}/yqdt/yqdt/payError">
						<div><img alt="school" src="${ctx}/static/styles/wxStyle/images/happystudy.png" /></div>
					</a>
				</li>
				<li>
					<a href="${ctx}/yqdt/yqdt/payError">
						<div><img alt="school" src="${ctx}/static/styles/wxStyle/images/album.png" /></div>
					</a>
				</li>
				</c:if>	
			</ul>
</div>

