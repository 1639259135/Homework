<%--
  Created by IntelliJ IDEA.
  User: lanou3g
  Date: 2018/1/4
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册账号</title>
    <style type="text/css">
        body{
            background-color: #d8ffd7;
        }
        ol li{
            display: inline;
        }
        label,input{
            width: 400px;
            height:40px;
            position: absolute;
            font-size: 18px
        }
    </style>
</head>
<body>
<div style="background-color: white">
    <ol>
        <li><img src="img/网易云.png" style="width: 50px;height: 25px;"></li>
        <li style="font-size: 20px">网易云</li>
        <li style="font-size: 17px">账号注册</li>
    </ol>
</div>

<div style="position: fixed;left: 15%;top: 15%;
        background-color: #d6eaff; width: 70%;height:70%;">

    <div style="position: fixed; left:45%;top: 28%;
            width: 120px;font-size:20px">
        邮箱号注册
    </div>

    <form action="register" method="post">


        <label style="left: 30%;top: 32%" for="username" >邮箱地址</label>
        <input style="left: 40%;top: 30%" placeholder="请输入网易云邮箱地址"
               onfocus="inputFocus(this)" onblur="inputBlur(this)"
                type="text" name="username" id="username">


        <label style="left: 30%;top: 45%" for="password" >登录密码</label>
        <input style="left: 40%;top: 45%" placeholder="请输入密码"
               onfocus="inputFocus(this)" onblur="inputBlur(this)"
                type="password" name="password" id="password">


        <input id="next"  value="下一步"
                style="background-color: dodgerblue;height: 50px;left: 40%;top: 65%;"
                onmousedown="mousedown()" onmouseup="mouseup()"
                type="submit">



    </form>


    <input type="checkbox" id="service"
           style="width: 15px;height:15px;left: 40%;top: 55%;">
    <label style="position: fixed;left: 44%;top: 53%;" for="service">
        同意并遵守<span style="color: dodgerblue"> 《服务条款》</span></label>


    <div id="um" style="color: red;position: fixed;font-size: 18px;left: 65%;top: 37%;
                visibility: hidden" >
        请使用邮箱注册</div>
    <div id="pd" style="color: red;position: fixed;font-size: 18px;left: 65%;top: 47.5%;
                visibility: hidden">
        密码长度不少于6位,不多于16位</div>

</div>

</body>
<script type="text/javascript">
    //    function inputFocus1() {
    //        var um = document.getElementById("um");
    //        var pd = document.getElementById("pd");
    //        um.style.visibility = "visible";
    //        pd.style.visibility = "visible";
    //    }
    //    function inputBlur1() {
    //        var um = document.getElementById("um");
    //        var pd = document.getElementById("pd");
    //        um.style.visibility = "hidden";
    //        pd.style.visibility = "hidden";
    //    }
    //
    function mousedown() {
        var register = document.getElementById("next");
        register.style.backgroundColor = "green"
    }
    function mouseup() {
        var register = document.getElementById("next");
        register.style.backgroundColor = "dodgerblue"
    }

    function inputFocus(ele) {
        // var a = document.getElementById("a");
        ele.style.width = "450px";
        ele.style.height = "50px";
        ele.style.borderColor = "red";
        ele.style.fontSize = "23px";
    }
    function inputBlur(ele) {
        // var b = document.getElementById("a");
        ele.style.width = "400px";
        ele.style.height = "40px";
        ele.style.borderColor = "";
        ele.style.fontSize = "18px";
    }

    var mydate=new Date();
    var minutes = mydate.getMinutes();
    var hours = mydate.getHours();
    var month=mydate.getMonth()+1;
    var day= mydate.getDate();
    var yar= mydate.getYear();
    document.write(yar+"-"+month+"-"+day+"\t"+hours+":"+minutes);

</script>
</html>
