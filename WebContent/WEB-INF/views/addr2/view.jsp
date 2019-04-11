<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" id="addrTable" align ="center">
		<tr>
			<th>번호</th>
			<td>${addr.ad_num}
			<input type="hidden" id="adNum" value="${addr.ad_num}"></td>
			
		</tr>
		<tr>
			<th>주소코드</th>
			<td><input type="text" id="adCode" value="${addr.ad_code}"></td>
		</tr>
		<tr>
			<th>시도</th>
			<td><input type="text" id="adSido" value="${addr.ad_sido}"></td>
		</tr>
		<tr>
			<th>구군</th>
			<td><input type="text" id="adGugun" value="${addr.ad_gugun}"></td>
		</tr>
		<tr>
			<th>읍면동</th>
			<td><input type="text" id="adDong" value="${addr.ad_dong}"></td>
		</tr>
		<tr>
			<th>리</th>
			<td><input type="text" id="adLee" value="${addr.ad_lee}"></td>
		</tr>
		<tr>
			<th>번지</th>
			<td><input type="text" id="adBunji" value="${addr.ad_bunji}"></td>
		</tr>
		<tr>
			<th>호수</th>
			<td><input type="text" id="adHo" value="${addr.ad_ho}"></td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="background-color: pink">
				<button style="background-color: pink" onclick="updateAddr()">수정</button>
				<button style="background-color: pink" onclick="closeTable()">닫기</button><br><br>
				<button style="background-color: pink" onclick="deleteAddr()">삭제</button>
			</td>
		</tr>
	</table>
<script>
	function closeTable(){
		document.querySelector('#addrTable').style.display='none';
	}
</script>
</body>
</html>