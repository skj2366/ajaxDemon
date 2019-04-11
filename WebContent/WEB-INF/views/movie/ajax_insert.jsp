<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	영화제목 :
	<input type="text" name="mi_name">
	<br> 제작년도:
	<input type="text" name="mi_year">
	<br> 국가:
	<input type="text" name="mi_national">
	<br> 제작사:
	<input type="text" name="mi_vendor">
	<br> 감독 :
	<input type="text" name="mi_director">
	<br>
	<button onclick="insertMovie()">영화등록</button>
	<script>
	function insertMovie(){
		var inputs = document.querySelectorAll('input[name]');
		var params ='';
		for(var input of inputs){
			params += input.name + '=' + input.value + '&';
		}
	var xhr = new XMLHttpRequest();
	xhr.open('POST','/am/insert');
	xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			var result = JSON.parse(xhr.response);
			alert(result.msg);
			if(result.url){
				location.href = result.url;
			}
		}
		
	}
		xhr.send(params);
	}
	</script>

</body>
</html>