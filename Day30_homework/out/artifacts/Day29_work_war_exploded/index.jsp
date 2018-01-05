<%@ page import="java.util.List" %>
<%@ page import="bean.Book" %><%--
  Created by IntelliJ IDEA.
  User: lanou3g
  Date: 2018/1/2
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="js/jquery-3.2.1.min.js"></script>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    if (session.getAttribute("username") != null){
  %>
    <h1>登录成功</h1>
  <br><br>
    <%
      Object username = session.getAttribute("username");
      Object password = session.getAttribute("password");
      out.write("用户名:" + username.toString() + "\n密码:" + password.toString());
    %>
  <br><br>
    <h3>书籍信息</h3>
    <table border="2">
      <tr>
        <th>BID</th>
        <th>书名</th>
        <th>作者</th>
        <th>价格</th>
      </tr>
    <%
      List list = (List) session.getAttribute("list");
      if (list != null){
        for (int i = 0; i < list.size(); i++) {
          Book book = (Book) list.get(i);
    %>
    <tr>
      <td>
        <%
          out.write(book.getBid() + "");
        %>
      </td>

      <td>
        <%
          out.write(book.getBookname());
        %>
      </td>

      <td>
        <%
          out.write(book.getAuthor());
        %>
      </td>

      <td>
        <%
          out.write(book.getPrice() + "");
        %>
      </td>
    </tr>
    <%
        }
      }
    %>
  </table>
  <%
    }else{
  %>
  <h3><a href="login.html">请重新登录</a></h3>
  <%
    }
  %>
  <br><br>
  <h3>
  <a href="login.html">
    <%
    session.setAttribute("username",session.getAttribute("username"));
    %>
    退出
  </a>
  </h3>
  </body>
<script type="text/javascript">
//$(function () {
//    $.getJSON("http://localhost:8080/login",function (data) {
//        console.log(data);
//            $('<h2>').text("账号: " + data["username"] + "\t密码: " + data["password"]);
//    })
//})
</script>
</html>
