<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>글 목록</title>
		<link rel="stylesheet" type="text/css" href="/include/css/board.css"/>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			$(function(){
				/* 검색 후 검색 대상과 검색 단어 출력 */
				if("<c:out value='${data.keyword}' />"!=""){
					$("#keyword").val("<c:out value='${data.keyword}' />");
					$("#search").val("<c:out value='${data.search}' />");
				}
				
				/* 검색 대상이 변경될 때마다 처리 이벤트 */
				$("#search").change(function(){
					if($("#search").val()=="all"){
						$("#keyword").val("전체 데이터를 조회합니다");
					}else if($("#search").val()!="all"){
						$("#keyword").val("");
						$("#keyword").focus();
					}
				});
				
				/* 한 페이지에 보여줄 레코드 수 변경 이벤트 */
				$("#pageSize").change(function(){
					alert("레코드수 바꿈");
					goPage(1);
				}); 
				/* 검색 버튼 클릭 시 처리 이벤트 */
				$("#searchData").click(function(){
					goPage(1);
				});
				
				/* 한 페이지에 보여줄 레코드 수 조회 후 선택한 값 그대로 유지 */
				if("<c:out value='${data.pageSize}'/>" != ""){
					$("#pageSize").val("<c:out value='${data.pageSize}' />");
				}
				
				/* 글쓰기 버튼 클릭 시 작성 페이지 이동 */
				$("#writeForm").click(function(){
					location.href="/board/writeForm.som";
				});
				
				/* 디테일 페이지로 이동 */
				$(".goDetail").click(function(){
					var b_num = $(this).parents("tr").attr("data-num");
					$("#b_num").val(b_num);
					$("#detailForm").attr({
						"method" : "get",
						"action" : "/board/boardDetail.som"
					});
					$("#detailForm").submit();
				});
			});
			
			/* 정렬 버튼 클릭 시 처리 함수 */
			function setOrder(order_by){
				alert("정렬 클릭");
				$("#order_by").val(order_by);
				alert($("#order_by").val());
				if($("#order_sc").val()=='DESC'){
					$("#order_sc").val('ASC');
				}else{
					$("#order_sc").val('DESC');
				}
				goPage(1);
			}
			
			/* 검색과 한 페이지에 보여줄 레코드 수 처리 및 페이징 위한 실질적 처리 함수 */
			function goPage(page){
				if($("#search").val()=="all"){
					$("#keyword").val("");
				}
				$("#page").val(page);
				$("#f_search").attr({
					"method" : "GET",
					"action" : "/board/boardList.som"
				});
				$("#f_search").submit();
			}
		</script>
	</head>
	<body>
		<div id="boardContatiner">
			<div id="boardTit"><h3>글목록</h3></div>
			<!-- 상세 페이지 이동을 위한 form -->
			<form name="detailForm" id="detailForm">
				<input type="hidden" name="b_num" id="b_num">
				<input type="hidden" name="page" value="${data.page}">
				<input type="hidden" name="pageSize" value="${data.pageSize}">
			</form>
			<%-- 검색 기능 --%>
			<div id="boardSearch">
				<form id="f_search" name="f_search">
					<input type="hidden" id="page" name="page" value="${data.page}" />
					<input type="hidden" id="order_by" name="order_by" value="${data.order_by}" />
					<input type="hidden" id="order_sc" name="order_sc" value="${data.order_sc}" />
					<table summary="검색">
						<colgroup>
							<col width="70%" />
							<col width="30%" />
						</colgroup>
						<tr>
							<td id="btd1">
								<label>검색조건</label>
								<select id="search" name="search">
									<option value="all">전체</option>
									<option value="b_title">제목</option>
									<option value="b_content">내용</option>
									<option value="b_name">작성자</option>
								</select>
								<input type="text" name="keyword" id="keyword" value="검색어를 입력하세요" />
								<input type="button" value="검색" id="searchData" />
							</td>
							<td id="btd2"> 한 페이지에
								<select id="pageSize" name="pageSize">
									<option value="5"> 5줄 </option>
									<option value="10"> 10줄 </option>
									<option value="20"> 20줄 </option>
									<option value="30"> 30줄 </option>
								</select>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<%-- 검색 기능 종료 --%>
			<%-- 리스트 시작 --%>
			<div id="boardList">
				<table summary="게시판 리스트">
					<colgroup>
						<col width="10%" />
						<col width="62%" />
						<col width="15%" />
						<col width="13%" />
					</colgroup>
					<thead>
						<tr>
							<th><a href="javascript:setOrder('B_NUM');">글번호
							<c:choose>
								<c:when test="${data.order_by == 'B_NUM' and data.order_sc == 'ASC'}">▲</c:when>
								<c:when test="${data.order_by == 'B_NUM' and data.order_sc == 'DESC'}">▼</c:when>
								<c:otherwise>▲</c:otherwise>
							</c:choose></a></th>
							<th>글제목</th>
							<th><a href="javascript:setOrder('B_INSERTDATE')">작성일
							<c:choose>
								<c:when test="${data.order_by == 'B_INSERTDATE' and data.order_sc == 'ASC'}">▲</c:when>
								<c:when test="${data.order_by == 'B_INSERTDATE' and data.order_sc == 'DESC'}">▼</c:when>
								<c:otherwise>▲</c:otherwise>
							</c:choose></a></th>
							<th class="borcle">작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty boardList}">
								<c:forEach var="board" items="${boardList}" varStatus="status">
									<tr align="center" data-num="${board.b_num}">
										<td>${count - (status.count-1)} </td>
										<td align="left">
											<span class="goDetail">${board.b_title}</span>
										</td>
										<td>${board.b_insertdate}</td>
										<td>${board.b_name}</td>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="4" align="center"> 등록된 게시물이 없습니다 </td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
		<%--리스트 종료 --%>
		<div id="boardBut">
			<input type="button" value="글쓰기" id="writeForm">
		</div>
		<div id="boardPage">
			<tag:paging page="${param.page}" total = "${total}" list_size = "${data.pageSize}" />
		</div>
	</body>
</html>