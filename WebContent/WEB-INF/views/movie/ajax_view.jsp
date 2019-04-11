<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>영화명</th>
			<th>년도</th>
			<th>국가</th>
			<th>제작사</th>
			<th>감독</th>
			<th>삭제</th>
		</tr>
		<tbody id="tbody">
		</tbody>
	</table>
	<c:if test="${sessionScope.user!=null }">
		<button onclick="deleteMovie()">삭제</button>
	</c:if>
	<script>
		function deleteMovie(){

			xhr.open('POST', '/am/delete');
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var result = JSON.parse(xhr.response);
					alert(result.msg);
					if(result.url){
						location.href = result.url;	
					}
				}
			}
			xhr.send('mi_num=${param.miNum}');
		}
		var xhr = new XMLHttpRequest();
		xhr.open('GET', '/am/${param.miNum}');
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var movie = JSON.parse(xhr.response);
				var html = '';
				html += '<tr onmouseover="this.style.background=\'red\'"';
				html += ' onmouseout="this.style.background=\'\'">';
				html += '<td>' + movie['mi_num'] + '</td>';
				html += '<td>' + movie['mi_name'] + '</td>';
				html += '<td>' + movie['mi_year'] + '</td>';
				html += '<td>' + movie['mi_national'] + '</td>';
				html += '<td>' + movie['mi_vendor'] + '</td>';
				html += '<td>' + movie['mi_director'] + '</td>';
				html += '<th><input type="button" value="삭제"/></th>';
				html += '</tr>';

				document.querySelector("#tbody").innerHTML = html;
			}
		}
		xhr.send();
	</script>
</body>
</html>