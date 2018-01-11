<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{
			font-size:10pt;
			text-align: center;
		}
		div {
			background: #87CEFA; 
			margin: 3px; 
			padding: 3px;
		}
		a {
			text-decoration: none;
		}
	</style>
  </head>
  
  <body>


<c:set var="URL" value="http://localhost:8080/bookstore/book?method" scope="page"/>

<div>
	<a href="<c:url value='http://localhost:8080/bookstore/cate?method=queryAll'/>">全部分类</a>
</div>

<div>
	<a href="<c:url value='${pageScope.URL}=queryByJavaSE'/>">JavaSE分类</a>
</div>

<div>
	<a href="<c:url value='${pageScope.URL}=queryByJavaEE'/>">JavaEE分类</a>
</div>

<div>
	<a href="<c:url value='${pageScope.URL}=queryByJavascript'/>">Javascript分类</a>
</div>
  </body>
</html>
