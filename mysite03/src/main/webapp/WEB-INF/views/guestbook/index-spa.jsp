<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var render = function(vo,mode){
	var htmls="";
	htmls = "<li data-no='"+vo.no+"'>"+
				"<strong>"+vo.name+"</strong>"+
				"<p>"+vo.content+"</p>"+
				"<strong></strong> "+
				"<a href='' data-no='"+vo.no+"'>삭제</a>"+
			"</li>";

	$("#list-guestbook")[mode?"prepend":"append"](htmls);
}
var fetch=function(sno){
	$.ajax({
		url:"${pageContext.request.contextPath }/guestbook/api/?sno="+sno,
		type:"get",
		dataType:"json",
		success:function(response){
			if(response.result==="fail"){
				console.error(response.message);
				return;
			}
			//console.log(response.data);
			response.data.forEach(function(vo){
				render(vo);
			});

		},
	});
}
var messageBox = function(title,message,callback){
	$("#dialog-message p").text(message);
	$("#dialog-message" ).attr("title",title)
		.dialog({
			width:340,
			height:170,
			modal:true,
			buttons:{
				"확인":function(){
					$(this).dialog('close');
				}
			},
			close:function(){
				callback && callback();	
			}
	});
	
}
$(function(){
	$(window).scroll(function(){
		var $window = $(this);		
		var $document = $(document);
		var windowHeight = $window.height();
		var documentHeight = $document.height();
		var scrollTop = $window.scrollTop();
		
		if(documentHeight < windowHeight+scrollTop+10){
			var sno = $("#list-guestbook li:last-child").data("no") || 0;
			fetch(sno);
		}
	});
	
	//read
	fetch(0);
	
	//create
	$("#add-form").submit(function(event){
		event.preventDefault();

		var $name=$("#input-name");
		var $password=$("#input-password");
		var	$content=$("#tx-content");

		// validation & messagebox
		if ($name.val() === "") {
			messageBox("방명록","이름이 비어있습니다.",function(){
				$name.val("").focus();
			});
			return;
		}
		
		if ($password.val() === "") {
			messageBox("방명록","비밀번호가 비어있습니다.",function(){
				$password.val("").focus();
			});
			return;
		}
		if ($content.val() === "") {
			messageBox("방명록","내용이 비어있습니다.",function(){
				$content.val("").focus();
			});
			return;
		}
		
		var vo = {
			name:$name.val(),
			password:$password.val(),
			content:$content.val()
		};
		
		$.ajax({
			url:"${pageContext.request.contextPath }/guestbook/api/",
			type:"post",
			dataType:"json",
			contentType:"application/json",
			data:JSON.stringify(vo),
			success:function(response){
				//console.log(response);
				if(response.result==="fail"){
					console.error(response.message);
					return;
				}
				$("#add-form")[0].reset();
				render(response.data,true);
			},
		});
	});
	
	//delete
	//삭제 다이알로그 jQuery 객체 미리 만들기
	var $dialogDelete = $("#dialog-delete-form").dialog({
		autoOpen:false,
		modal: true,
		buttons:{
			"삭제" : function(){
				var deleteNo = $("#hidden-no").val();
				console.log("삭제하기",deleteNo);
				$.ajax({
					url:"${pageContext.request.contextPath }/guestbook/api/"+deleteNo,
					type:"delete",
					dataType:"json",
					data:"password="+$("#password-delete").val(),
					success:function(response){
						if(response.result==="fail"){
							console.error(response.message);
							return;
						}
						//console.log(response.data);
						if(response.data){
							$dialogDelete.dialog('close');
							var dNo = response.data;
							$("li[data-no='"+dNo+"']").remove();
						}else{
							$("#passworeError").css("display", "block");
						}
							$("#password-delete").val("");
					},
				});
			},
			"취소": function(){
				//console.log("삭제 다이알로그의 폼 데이터 리셋");
				$("#password-delete").val("");
				$(this).dialog('close');
			}
		}
	});
	
	//메세지 삭제 버튼 click 이벤트 처리
	// document를 클릭 했을때 #list-guestbook li 이면 해당 메소드 실행
	$(document).on('click',"#list-guestbook li a",function(event){
		event.preventDefault();
		//console.log($(this).attr("data-no"));
		$("#hidden-no").val($(this).attr("data-no"));
		$dialogDelete.dialog('open');
	});

});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/hearder.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">
				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
				<p class="validateTips error" id="passworeError" style="display:none;color: red;">비밀번호가 틀립니다.</p>
				<form>
					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position: absolute; top: -1000px">
				</form>
			</div>
			<div id="dialog-message" title="" style="display: none">
				<p></p>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>