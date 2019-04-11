<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화개봉리스트</title>
</head>
<body>
<script>
	function submitButton(index){
		if(index==1){
			document.asdasd.action="/update";
			document.cmd.value="update";
		}
		if(index==2){
			document.cmd.value="delete";
			document.asdasd.action="/delete";
		}
		document.asdasd.submit();
	}
</script>
<table border="1">
	<tr>
		<th>번호</th>
		<th>영화명</th>
		<th>년도</th>
		<th>국가</th>
		<th>제작사</th>
		<th>감독</th>
		<c:if test="${sessionScope.user!=null}">
		<th>수정</th>
		<th>삭제</th>
		</c:if>
	</tr>
	<c:if test="${fn:length(list)==0}">
	<tr>
		<td colspan="6">등록된 영화 개봉 리스트가 없습니다.</td>
	</tr>
	</c:if>
	<c:if test="${sessionScope.user==null}">
		<c:forEach items="${list}" var="movie">
		<tr style="curosr:pointer" onmouseover="this.style.backgroundColor='limegreen'" onmouseout="this.style.backgroundColor='white'"
		onclick="goPage('${movie.mi_num}')">
			<td>${movie.mi_num}</td>
			<td>${movie.mi_name}</td>
			<td>${movie.mi_year}</td>
			<td>${movie.mi_national}</td>
			<td>${movie.mi_vendor}</td>
			<td>${movie.mi_director}</td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${sessionScope.user!=null}">
		<a href="/views/movie/insert">개봉영화등록</a>
		<form method="post" id="asdasd">
		<c:forEach items="${list}" var="movie">
		<tr>
			<td>${movie.mi_num}</td>
			<td>${movie.mi_name}</td>
			<td>${movie.mi_year}</td>
			<td>${movie.mi_national}</td>
			<td>${movie.mi_vendor}</td>
			<td>${movie.mi_director}</td>
			<td><input type="button" onclick="submitBtn(1);" value="수정2"/></td>
			<td><input type="button" onclick="submitBtn(2);" value="삭제"/></td>
		</tr>
		</c:forEach>
		<input type="hidden" name="cmd">
		</form>
	</c:if>
</table>
<a href="/views/index">메인으로</a>
</body>
</html>