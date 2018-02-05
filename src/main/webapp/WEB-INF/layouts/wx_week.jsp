<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript">
$(document).ready(function() {
	$("#left_search").attr("placeholder","周期列表");
	getMyShare();
	});
	
	function getMyShare(){
		// 提示页面正在加载
		
		var submitData = {
				orgcode: '${orgcode}',
				fromusername: '${fromusername}',
				campusid: '${campusid}',
				type: '${type}',
				key: '${key}',
				xqbm: '${xqbm}'
			};  
		var url="${ctx}/jyhd/mrcpref/getAjax_WeekForWx";
		$.get(url,submitData,
	      	function(data){
			$("#weekList").html(data);
	      });
	}
</script>
<nav id="menu">

	<ul id="weekList">
		
	</ul>
</nav>
