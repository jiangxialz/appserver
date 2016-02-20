<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("cxf", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Could not find page</title>
	<style type="text/css">
		body { 
			background: #fff url('${cxf}images/bg_404.jpg') repeat;
		}
		div.dialog {
			background: transparent url('${cxf}images/page-not-found.jpg') no-repeat;
			height: 306px;
			margin: 50px auto;
			width: 485px;
		}
	</style>
</head>
<body>
  <div class="dialog">
  </div>
</body>
</html>

