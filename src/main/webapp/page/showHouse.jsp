<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
  <META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
  <script language="JavaScript">
      $(function(){  //加载事件
           //1.发送异步请求获取类型，进行显示
          $.post("getAllType",null,function (data) {
              for(var i=0;i<data.length;i++){
                  //创建节点
                 var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                 //追加节点
                  $("#type_id").append(node);
              }

              //设置选中项
              $("#type_id").val(${h.typeId});
          },"json");

          //1.发送异步请求获取区域，进行显示
          $.post("getAllDistrict",null,function (data) {
              for(var i=0;i<data.length;i++){
                  //创建节点
                  var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                  //追加节点
                  $("#district_id").append(node);
              }
              // 设置区域选中项
              $("#district_id").val(${h.did});

              $("#type_id").val(${h.typeId});
              loadStreet();  //加载街道
          },"json");

          //给区域添加改变事件
          $("#district_id").change(loadStreet);

          //加载街道   代码复用
          function loadStreet(){
                  //获取区域编号
                  var did=$("#district_id").val();
                  //发送异步请求加载街道数据
                  //清空原有数据项
                  $("#street_id>option").remove();
                  $.post("getStreetByDid",{"did":did},function (data) {
                      for(var i=0;i<data.length;i++){
                          //创建节点
                          var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                          //追加节点
                          $("#street_id").append(node);
                      }

                      //设置街道的选中项
                      $("#street_id").val(${h.streetId});
                  },"json");
          }

      });
  </script>

</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post enctype="multipart/form-data" action=updateHouse>
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD class=field colspan="2">${info}</TD>
  <TR>
  <TR>
    <TD class=field>标　　题：<INPUT id=d class=text value="${h.id}" type=hidden name=id></TD>
    <TD><INPUT id=add_action_title class=text value="${h.title}" type=text name=title> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text" id="type_id" name=typeId></SELECT></TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage value="${h.floorage}" class=text type=text
name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price value="${h.price}" class=text type=text name=price> </TD></TR>
  <TR>
    <TD class=field>发布日期：</TD>
    <TD><INPUT class=text type=date name=pubdate value="<fmt:formatDate value="${h.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text id="district_id" name=district_id></SELECT>
      街：<SELECT class=text
        name=streetId id="street_id">

      </SELECT> </TD></TR>

  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT value="${h.contact}" id=add_action_contact class=text type=text name=contact> </TD></TR>
  <TR>
  <TR>
    <TD class=field>图片：</TD>
    <TD>
      <INPUT id=pic class=text type=hidden name=delimage value="${h.path}">
      <img src="http://localhost:81/${h.path}" height="50" width="40" />
      <INPUT id=pic class=text type=file name=pfile> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${h.description}</TEXTAREA></TD></TR></TBODY></TABLE>
<DIV class=buttons><INPUT  value=更新 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
