<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>�� ���� ȭ��</title>
		<link rel="stylesheet" type="text/css" href="/include/css/board.css"/>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/include/js/common.js"></script>
		<script type="text/javascript" src="/include/js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript">
			$(function(){
				
				CKEDITOR.replace('editor');
				
				/* ���� ��ư Ŭ�� �̺�Ʈ */
				$("#boardUpdate").click(function(){
					alert("���� ����");
					$("#f_writeForm").attr({
						"method" : "POST",
						"action" : "/board/boardUpdate.som"
					});
					$("#f_writeForm").submit();
				});
				/* ��� ��ư Ŭ�� �̺�Ʈ */
				$("#boardList").click(function(){
					location.href="/board/boardList.som";
				});
			});
		</script>
	</head>
	<body>
		<div id="boardTit"><h3>�� ����</h3></div>
		<form id="f_writeForm" name="f_writeForm" enctype="multipart/form-data">
			<input type="hidden" name="b_num" id="b_num" value="${updateData.b_num}" />
			<input type="hidden" name="b_file" id="b_file" value="${updateData.b_file}" />
			<table id="boardWrite">
				<tr>
					<td>�ۼ���</td>
					<td><input type="text" name="b_name" id="b_name" value="${updateData.b_name}"/></td>
				</tr>
				<tr>
					<td>������</td>
					<td><input type="text" name="b_title" id="b_title" value="${updateData.b_title}" /></td>
				</tr>
				<tr>
					<td>����</td>
					<td height="200">
					<textarea name="b_content" id="editor" rows="10" cols="70">
					${updateData.b_content}</textarea></td>
				</tr>
				<tr>
					<td>÷������</td>
					<td><input type="file" name="file" id="file"></td>
				</tr>
				<tr>
					<td>��й�ȣ</td>
					<td><input type="password" name="b_pwd" id="b_pwd"/>
					<label>������ ��й�ȣ�� �Է����ּ���.</label></td>
				</tr>
			</table>
		</form>
		<div id="boardBut">
			<input type="button" value="����" class="but" id="boardUpdate" />
			<input type="button" value="���" class="but" id="boardList" />
		</div>
	</body>
</html>