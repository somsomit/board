<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>글 상세보기</title>
		<link rel="stylesheet" type="text/css" href="/include/css/board.css"/>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/include/js/common.js"></script>
		<script type="text/javascript">
		var butChk = 0; // 수정, 삭제 구분용
		$(function(){
			$("#pwdChk").hide();
			
			/* 파일 이미지 출력 */
			var file = "<c:out value='${boardDetail.b_file}'/>";
			if(file != ""){
				$("#fileImage").attr({
					src : "/uploadStorage/${boardDetail.b_file}",
					width : "450px",
					height : "400px"
				});
			}
			
			/* 목록 버튼 클릭 시 이벤트 */
			$("#boardList").click(function(){
				location.href="/board/boardList.som";
			});
			
			/* 수정 버튼 클릭 시 이벤트 */
			$("#updateForm").click(function(){
				$("#pwdChk").show();
				$("#msg").text("작성 시 입력한 비밀번호를 입력해주세요.").css("color", "#000099");
				butChk = 1;
			});
			
			/* 삭제 버튼 클릭 시 이벤트 */
			$("#boardDelete").click(function(){
				$("#pwdChk").show();
				$("#msg").text("작성 시 입력한 비밀번호를 입력해주세요.").css("color", "#000099");
				butChk = 2;
			});
			
			/* 비밀번호 확인 버튼 클릭 시 이벤트 */
			$("#pwdBut").click(function(){
				pwdConfirm();
			});
			
		});
		function pwdConfirm(){
			$.ajax({
				url : "/board/pwdConfirm.som",
				type : "POST",
				data : $("#f_pwd").serialize(),
				error : function(){
					alert("오류가 발생했습니다. 다시 시도해보세요.");
				},
				success : function(resultData){
					var goUrl = "";
					if(resultData == 0){
						$("#msg").text("작성 시 입력한 비밀번호가 일치하지 않습니다.").css("color", "red");
						$("#b_pwd").select();
					}else if(resultData == 1){
						$("#msg").text("");
						if(butChk == 1){
							goUrl = "/board/updateForm.som";
						}else if(butChk == 2){
							goUrl = "/board/boardDelete.som";
						}
						$("#f_data").attr("action", goUrl);
						$("#f_data").submit();
					}
				}
			});
		}
		</script>
	</head>
	<body>
		<div id="boardTit"><h3>글 상세</h3></div>
		<form name="f_data" id="f_data" method="POST">
			<input type="hidden" name="b_num" value="${boardDetail.b_num}"/>
			<input type="hidden" name="b_file" id="b_file" value="${boardDetail.b_file}"/>
		</form>
		<!-- 비밀번호 확인 버튼 및 추가 버튼 -->
		<table id="boardPwdBut">
			<tr>
				<td id="btd1">
					<div id="pwdChk">
						<form id="f_pwd" name="f_pwd">
							<input type="hidden" name="b_num" id="b_num" value="${boardDetail.b_num}"/>
							<label for="b_pwd" id="b_pwd"> 비밀번호 : </label>
							<input type="password" id="b_pwd" name="b_pwd"/>
							<input type="button" id="pwdBut" value="확인"/>
							<span id="msg"></span>
						</form>
					</div>
				</td>
				<td id="btd2">
					<input type="button" value="수정" id="updateForm">
					<input type="button" value="삭제" id="boardDelete">
					<input type="button" value="목록" id="boardList">
				</td>
			</tr>
		</table>
		<!-- 비밀번호 확인 버튼 및 추가 버튼 끝 -->
		<div id="boardDetail">
			<table>
				<colgroup>
					<col width="25%"/>
					<col width="25%"/>
					<col width="25%"/>
					<col width="25%"/>
				</colgroup>
				<tbody>
					<tr>
						<td class="ac">작성자</td>
						<td> ${boardDetail.b_name}</td>
						<td class="ac">작성일</td>
						<td> ${boardDetail.b_insertdate}</td>
					</tr>
					<tr>
						<td class="ac">제목</td>
						<td colspan="3">${boardDetail.b_title}</td>
					</tr>
					<tr class="ctr">
						<td class="ac">내용</td>
						<td colspan="3">${boardDetail.b_content}</td>
					</tr>
					<tr class="ctr">
						<td class="ac">첨부파일 </td>
						<td colspan="3"><img id="fileImage" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<jsp:include page="reply.jsp"></jsp:include>
	</body>
</html>