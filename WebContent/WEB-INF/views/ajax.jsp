<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/html/js/common.js"></script>
</head>
<body>
	<script>
		function callback(res){
			var res = JSON.parse(res);
			for(var addr of res.list){
				document.write(" " + addr.ad_num + ". " + addr.ad_sido + ", " + addr.ad_gugun);
				document.write(", " + addr.ad_dong);
				document.write(", " + addr.ad_lee);
				document.write(", " + addr.ad_bunji);
				document.write(", " + addr.ad_ho);
				document.write(", " + addr.ad_roadcode);
				document.write(", " + addr.ad_isbase);
				document.write(", " + addr.ad_orgnum);
				document.write(", " + addr.ad_subnum);
				document.write(", " + addr.ad_jinum);
				document.write("<br>");
			}
		}
		
		var au = new AjaxUtil();
		var config = {
				url : '/addr2/list',
				callback : callback
		}
		au.open(config);
		au.setCallback(callback);
		au.send();
	</script>
</body>
</html>