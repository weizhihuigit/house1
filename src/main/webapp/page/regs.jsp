<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
<script language="JavaScript">
    $(function(){  //加载事件
        //添加点击事件  当前使用的是jquery,后期可以改为vue(了解)
        $("#checkButton").click(function(){
            $.post("checkUserName",{"username":$("#inputName").val()},function(data){  //发送异步请求
                if(data.result==1)
                    $("#showNotice").html("用名存在，不可用").css("color","red");
                else
                    $("#showNotice").html("OK").css("color","green");
            },"json");
        })
    });
    $(function () {
        $("#checkpsw").click(function () {
           var i=$("#psw").val()
           var i1=$("#psw1").val()
            if (i!=(i1))
                $("#showpsw").html("密码不一致").css("color","red");
            else
                $("#showpsw").html("OK").css("color","green");

        })
    })
</script>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新用户注册</DT>
  <DD class=past>填写个人信息</DD></DL>
<DIV class=box>
<FORM action="reg" method="post" name="form1">
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><INPUT class=text type=text name="name" id="inputName"><span id="showNotice"></span><input type="button" id="checkButton" value="检查用户名是否存在"></TD></TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><INPUT class=text type=password name=password id="psw"></TD></TR>
  <TR>
    <TD class=field>确认密码：</TD>
    <TD><INPUT class=text type=password name=repassword id="psw1"><span id="showpsw"></span><input type="button" id="checkpsw" value="密码是否一致"> </TD></TR>
  <TR>
    <TD class=field>电　　话：</TD>
    <TD><INPUT class=text type=text name=telephone> </TD></TR>
 </TBODY></TABLE>
<DIV class=buttons>
<INPUT value=立即注册 type="submit">
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
