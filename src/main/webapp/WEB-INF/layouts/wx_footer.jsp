<%@ page language="java" pageEncoding="UTF-8" %>
	<div style="width:100%;height:50px;float:left;"></div>
	<div class="bottom">
		<a href="#"></a>
	</div>
	<script>
		$(document).scroll(function(){
			if(document.body.scrollTop > 0){
				$(".bottom").css("display","block");
			}else{
				$(".bottom").css("display","none");
			}
		});
	</script>