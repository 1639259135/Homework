<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>我的订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">

	<c:remove var="key" scope="session"/>
	<c:remove var="value" scope="session"/>

	<c:forEach var="orderMap" items="${sessionScope.orderListMap}" step="1">

	<tr bgcolor="gray" bordercolor="gray">
		<td colspan="6">
			订单编号：${orderMap.key.oid}　成交时间：${orderMap.key.orderTime}　金额：<font color="red"><b>${orderMap.key.price}</b></font>　

				<c:if test="${orderMap.key.state == 0}">
					<a href="<c:url value='/jsps/order/desc.jsp'/>">
						<c:set var="key" scope="session" value="${orderMap.key}"/>
						<c:set var="value" scope="session" value="${orderMap.value}"/>
						付款</a>
					<%--http://localhost:8080/bookstore/order?method=alterOrder&oid=${orderMap.key.oid}--%>
				</c:if>
				<c:if test="${orderMap.key.state == 1}">
					已付款,等待发货
				</c:if>
				<c:remove var="state" scope="session"/>
			<a href="<c:url value='http://localhost:8080/bookstore/order?method=removeOrder&oid=${orderMap.key.oid}'/>">确认收货</a>
					<%--订单结束--%>
		</td>
	</tr>

		<c:forEach var="OIMap" items="${orderMap.value}" step="1">

				<tr bordercolor="gray" align="center">
					<td width="15%">
						<div><img src="<c:url value='${OIMap.value.image}'/>" height="75"/></div>
					</td>
					<td>书名：${OIMap.value.bname}</td>
					<td>单价：${OIMap.value.price}</td>
					<td>作者：${OIMap.value.author}</td>
					<td>数量：${OIMap.key.count}</td>
					<td>小计：${OIMap.key.subtotal}</td>
				</tr>

		</c:forEach>

	</c:forEach>


</table>
  </body>
</html>
