<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	function changePageCount(obj){
		alert("페이지당 게시물 수를 " + obj.value + "개로 설정하였습니다.");
		location.href="/addr/list?page=${page}&pageCount=" + obj.value + "&ad_dong=${param.ad_dong}";
	}
	function search(){
		var ad_dong = document.querySelector('#ad_dong').value;
		location.href="/addr/list?pageCount=${pageCount}&ad_dong=" + ad_dong;
		document.querySelector('#ad_dong').value = ad_dong;
	}
	function enter(){
		if(event.keyCode==13){
			search();
		}
	}
</script>

<select name="ad_sido">
<c:forEach items="${asList}" var="sido">
<option value="${sido}">${sido}</option>
</c:forEach>
</select>

<select name="ad_gugun">
<c:forEach items="${agList}" var="gugun">
<option value="${gugun}">${gugun}</option>
</c:forEach>
</select>
<label for="ad_dong">읍면동 : </label><input type="text" name="ad_dong" id="ad_dong" value="${param.ad_dong}" onkeypress="enter()">
<button onclick="search()">검색</button><br>
<select name="pageCount" onchange="changePageCount(this)">
	<option value="10"
	<c:if test="${pageCount==10}">
		selected
	</c:if>
	>10</option>
	<option value="20"
	<c:if test="${pageCount==20}">
		selected
	</c:if>
	>20</option>
	<option value="30"
	<c:if test="${pageCount==30}">
		selected
	</c:if>
	>30</option>
	<option value="40"
	<c:if test="${pageCount==40}">
		selected
	</c:if>
	>40</option>
	<option value="50"
	<c:if test="${pageCount==50}">
		selected
	</c:if>
	>50</option>
</select>

	<table border="1">
		<tr>
			<th style="background-color: aqua">번호</th>
			<th style="background-color: aqua;">시도</th>
			<th style="background-color: aqua;">구군</th>
			<th style="background-color: aqua;">동</th>
			<th style="background-color: aqua;">리</th>
			<th style="background-color: aqua;">번지</th>
			<th style="background-color: aqua;">호</th>
		</tr>
		<c:forEach items="${list}" var="addr">
			<tr>
				<td style="background-color: pink;">${addr.ad_num}</td>
				<td style="background-color: pink;">${addr.ad_sido}</td>
				<td style="background-color: pink;">${addr.ad_gugun}</td>
				<td style="background-color: pink;"><a href="/addr/view?ad_num=${addr.ad_num}&page=${page}&pageCount=${pageCount}&ad_dong=${param.ad_dong}">${addr.ad_dong}</a></td>
				<td style="background-color: pink;">${addr.ad_lee}</td>
				<td style="background-color: pink;">${addr.ad_bunji}</td>
				<td style="background-color: pink;">${addr.ad_ho}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7" align="center">
			<c:if test="${page!=1}">
				<a href="/addr/list?pageCount=${pageCount}&totalPageCnt=${totalPageCnt}&ad_dong=${param.ad_dong}">처음</a>
				<a href="/addr/list?page=${page-1}&pageCount=${pageCount}&totalPageCnt=${totalPageCnt}&ad_dong=${param.ad_dong}">-1</a>
			</c:if>	
			<c:if test="${page>10}">
				<a href="/addr/list?page=${page-10}&pageCount=${pageCount}&totalPageCnt=${totalPageCnt}&ad_dong=${param.ad_dong}">-10</a>
			</c:if>
				<c:forEach var="p" begin="${fBlock}" end="${lBlock}">
					<c:if test="${p!=page}">
						<a href="/addr/list?page=${p}&pageCount=${pageCount}&totalPageCnt=${totalPageCnt}&ad_dong=${param.ad_dong}">[${p}]</a>
					</c:if>
					<c:if test="${p==page}">
						<b>[${p}]</b>
					</c:if>
				</c:forEach>
			<c:if test="${page<totalPageCnt}">
				<a href="/addr/list?page=${page+1}&pageCount=${pageCount}&totalPageCnt=${totalPageCnt}&ad_dong=${param.ad_dong}">+1</a>
			</c:if>
			<c:if test="${page<totalPageCnt-9}">
				<a href="/addr/list?page=${page+10}&pageCount=${pageCount}&totalPageCnt=${totalPageCnt}&ad_dong=${param.ad_dong}">+10</a>
			</c:if>
			<c:if test="${page!=totalPageCnt}">
				<a href="/addr/list?page=${totalPageCnt}&pageCount=${pageCount}&totalPageCnt=${totalPageCnt}&ad_dong=${param.ad_dong}">끝</a>
			</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="right" style="background-color: lightgreen;">총 갯수 : ${totalCnt}</td>
		</tr>
	</table>
	
</body>
</html>