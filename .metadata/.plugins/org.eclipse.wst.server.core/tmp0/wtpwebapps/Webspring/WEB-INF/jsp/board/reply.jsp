<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<title>comment</title>
		<link rel="stylesheet" type="text/css" href="/include/css/reply.css"/>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			/* 기본 덧글 목록 불러오기 */
			var b_num = "<c:out value='${boardDetail.b_num}' />";
			listAll(b_num);
			
			/* 덧글 내용 저장 */
			$(document).on("click","#replyInsert", function(){
				alert("저장 버튼 클릭");
				var insertUrl = "/replies/replyInsert.som";
				$.ajax({
					url : insertUrl,
					type : "POST",
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType : "text",
					data : JSON.stringify({
						b_num : b_num,
						r_name : $("#r_name").val(),
						r_pwd : $("#r_pwd").val(),
						r_content : $("#r_content").val()
					}),
					error : function(){
						alert("오류가 발생했습니다. 다시 시도해주세요.");
					},
					success : function(resultData){
						if(resultData=="SUCCESS"){
							alert("덧글 등록이 완료되었습니다.");
							dataReset();
							listAll(b_num);
						}
					}
				});
			});
			
			/* 수정 버튼 클릭 시 수정폼 출력 */
			$(document).on("click", ".update_form", function(){
				alert("수정클릭");
				var conText = $(this).parents("li").children().eq(1).html(); // 내용
				console.log("[log] conText : " + conText);
				$(this).parents("li").find("input[type='button']").hide();
				$(this).parents("li").children().eq(0).html();
				var conArea = $(this).parents("li").children().eq(1);				
				conArea.html("");
				
				var data = "<textarea name='content' id='content'>" + conText + "</textarea>";
				data += "<input type='button' class='update_btn' value='수정완료'>";
				data += "<input type='button' class='reset_btn' value='수정취소'>";
				conArea.html(data);
			});
			
			/* 글 수정 Ajax */
			$(document).on("click", ".update_btn", function(){
				var r_num = $(this).parents("li").attr("data-num");
				var r_content = $("#content").val();
				$.ajax({
					url : '/replies/' + r_num + '.som',
					type : 'PUT',
					headers : {
							"Content-Type" : "application/json",
							"X-HTTP-Method-Override" : "PUT"},
					data : JSON.stringify({
							r_content:r_content}),
					dataType : 'text',
					success : function(result){
							console.log("result : " + result);
							if(result == 'SUCCESS'){
								alert("수정되었습니다.");
								listAll(b_num);
						}
					}
				});
			});
			
			/* 글 삭제 Ajax */
			$(document).on("click", ".delete_btn", function(){
				alert("삭제 클릭");
				var r_num = $(this).parents("li").attr("data-num");
				console.log("r_num : " + r_num);
				
				if(confirm("선택하신 댓글을 삭제하시겠습니까?")){
					$.ajax({
						type : 'delete',
						url : '/replies/' + r_num + ".som",
						headers : {
							"Content-Type" : "application/json",
							"X-HTTP-Method-Override" : "DELETE"
								},
						dataType : 'text',
						success : function(result){
							console.log("result : " + result);
							if(result == "SUCCESS"){
								alert("삭제되었습니다.");
								listAll(b_num);
							}
						}
					});
				}
			});
			
			// 리스트 요청 험수
			function listAll(b_num){
				$("#comment_list").html("");
				var url = "/replies/all/" + b_num + ".som";
				$.getJSON(url, function(data){
					console.log(data.length);
					
					$(data).each(function(){
						var r_num = this.r_num;
						var r_name = this.r_name;
						var r_content = this.r_content;
						var r_insertdate = this.r_insertdate;
						addNewItem(r_num, r_name, r_content, r_insertdate);
					});
				}).fail(function(){
					alert("덧글 목록을 불러오는데 실패했습니다.");
				});
			}
			
			// 새로운 글 화면 추가하는 함수
			function addNewItem(r_num, r_name, r_content, r_insertdate){
				// 새 글 추가될 li태그 객체
				var new_li = $("<li>");
				new_li.attr("data-num", r_num);
				new_li.addClass("comment_item");
				
				// 작성자 정보 지정될 <p>태그
				var writer_p = $("<p>");
				writer_p.addClass("writer");
				
				// 작성자 정보 이름
				var name_span = $("<span>");
				name_span.addClass("name");
				name_span.html(r_name + "님");
				
				// 작성일시
				var insertdate_span = $("<span>");
				insertdate_span.html("/" + r_insertdate + " ");
				
				// 수정 버튼
				var up_input = $("<input>");
				up_input.attr({"type" : "button", "value" : "수정하기"});
				up_input.addClass("update_form");
				
				// 삭제 버튼
				var del_input = $("<input>");
				del_input.attr({"type" : "button", "value" : "삭제하기"});
				del_input.addClass("delete_btn");
				
				// 내용
				var content_p = $("<p>");
				content_p.addClass("con");
				content_p.html(r_content);
				
				// 조립
				writer_p.append(name_span).append(insertdate_span).append(up_input).append(del_input);
				new_li.append(writer_p).append(content_p);
				$("#comment_list").append(new_li);
			}
			
			// input 태그 초기화
			function dataReset(){
				$("r_name").val("");
				$("r_pwd").val("");
				$("r_content").val("");
			}
		</script>
	</head>
	<body>
		<div id="replyContainer">
			<h1></h1>
			<div id="comment_write">
				<form id="comment_form">
					<div>
						<label for="r_name"> 작성자 </label>
						<input type="text" name="r_name" id="r_name" />
						<label for="r_pwd"> 비밀번호 </label>
						<input type="password" name="r_pwd" id="r_pwd" />
						<input type="button" id="replyInsert" value="댓글쓰기" />
					</div>
					<div>
						<label for="r_content"> 댓글 내용 </label>
						<textarea name="r_content" id="r_content"></textarea>
					</div>
				</form>
			</div>
			<ul id="comment_list">
				<!-- 여기에 동적 생성 요소가 들어가게 됨 -->
			</ul>
		</div>
	</body>
</html>