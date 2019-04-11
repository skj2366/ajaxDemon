<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/html/js/common/xhrCommonExam.js"></script>
</head>
<body>
<script>

	function callback(res){
		var res = JSON.parse(res);
		for(var addr of res.list){
			document.write(" " + addr.ad_num + ". ");
			document.write(", " + addr.ad_sido);
			document.write(", " + addr.ad_gugun);
			document.write(", " + addr.ad_dong);
			document.write(", " + addr.ad_lee);
			document.write(", " + addr.ad_ho);
			document.write("<br>");
		}
	}

	var aue = new AjaxUtilExam();
	aue.open('/addr2/list');
	aue.setCallback(callback);
	aue.send();
</script>

<label for="ad_dong">읍면동 : </label><input type="text" name="ad_dong" id="ad_dong" onkeypress="enter()">
<button onclick="search()">검색</button>
<select id="pageCount" name="pageCount" onchange="changePageCount(this)">
	<option value="10">10</option>
	<option value="20">20</option>
	<option value="30">30</option>
	<option value="40">40</option>
	<option value="50">50</option>
</select>
<table border="1">
	<tr>
		<th>번호</th>
		<th>시도</th>
		<th>구군</th>
		<th>동</th>
		<th>리</th>
		<th>번지</th>
		<th>호</th>
	</tr>
	<tbody id="tBody">
	</tbody>
</table>
<div id="dView"></div>
</body>
</html>