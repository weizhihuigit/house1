<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
  <META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <LINK rel=stylesheet type=text/css href="../css/style.css">
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
              $("#type_id").val(${condition.tid});
          },"json");

          //1.发送异步请求获取区域，进行显示
          $.post("getAllDistrict",null,function (data) {
              for(var i=0;i<data.length;i++){
                  //创建节点
                  var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                  //追加节点
                  $("#district_id").append(node);
              }

              //设置选中项
              $("#district_id").val(${condition.did});

              loadStreet();  //加载街道
          },"json");

          //给区域添加改变事件
          $("#district_id").change(loadStreet);

          //加载街道   代码复用
          function loadStreet(){
              //获取区域编号
              var did=$("#district_id").val();
              if(did!="") {
                  //发送异步请求加载街道数据
                  //清空原有数据项
                  $("#street_id>option:gt(0)").remove();
                  $.post("getStreetByDid", {"did": did}, function (data) {

                      for (var i = 0; i < data.length; i++) {
                          //创建节点
                          var node = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                          //追加节点
                          $("#street_id").append(node);
                      }
                      //设置选中项
                      $("#street_id").val(${condition.sid});
                  }, "json");
              }
          }

      });

      //带条件的分页
      function goPage(pageNum){
          //1.将页码设置到表单
          $("#setPage").val(pageNum);
          //2.提交表单
          $("#sform").submit();   //js提交表单，相当于点击了提交按钮
      }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
  <DIV id=logo><IMG src="../images/logo.gif"></DIV>
  <input type="button" value="登陆" onclick="location.href='login.jsp'">
</DIV>
<DIV id=navbar class=wrap>
  <DL class="search clearfix">
    <FORM id=sform method=post action="searchHouse">
      <input  type="hidden" id="setPage" name="page" value="1"/>
      标题：<INPUT class=text type=text name=title value="${condition.title}">
      区域:<select id="district_id"  name="did">
      <option value="">请选择</option>
    </select>
      街道:<select id="street_id" name="sid">
      <option value="">请选择</option>
    </select>
      类型:<select id="type_id" name="tid">
      <option value="">请选择</option>
    </select>
      价格:<input type="text" name="startPrice" size="8" value="${condition.startPrice}">-<input name="endPrice" type="text" size="8" value="${condition.endPrice}">
      <INPUT  value=搜索房屋 type=submit name=search>

    </FORM></DL></DIV>
<DIV class="main wrap">
  <TABLE class=house-list>
    <TBODY>
    <c:forEach items="${pageInfo.list}" var="h">
      <TR>
        <TD class=house-thumb><span><A href="getHouseById?id=${h.id}" target="_blank"><img src="http://localhost:81/${h.path}" width="100" height="75" alt=""></a></span></TD>
        <TD>
          <DL>
            <DT><A href="getHouseById?id=${h.id}" target="_blank">${h.title}</A></DT>
            <DD>${h.dname}${h.sname},${h.floorage}平米<BR>联系方式：${h.contact}</DD></DL></TD>
        <TD class=house-type>${h.tname}</TD>
        <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD></TR>
    </c:forEach>
    </TBODY></TABLE>
  <DIV class=pager>
    <UL>
      <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
      <LI><A href="javascript:goPage(${pageInfo.prePage==0?1:pageInfo.prePage})">上一页</A></LI>
      <LI><A href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage})">下一页</A></LI>
      <LI><A href="javascript:goPage(${pageInfo.pages})">末页</A></LI></UL><SPAN
          class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
  <DL>
    <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
    <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
