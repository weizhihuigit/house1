<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Object o=session.getAttribute("userInfo");
    if(o==null)
        out.print("<script>alert('你没有登入，或者登入时间失效，请登入！给老子滚起走，请登入');location.href='login.jsp';</script>");
%>
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
    <TITLE>青鸟租房 - 用户管理</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK
        rel=stylesheet type=text/css href="../css/style.css">

    <META name=GENERATOR ></HEAD>
<script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
<script language="JavaScript">
    function delHouse(obj,id) {
        var delNode=$(obj).parent().parent().parent();
        $.post("delHouse",{"id":id},function (data) {
            if (data.result>0){
                delNode.remove();
            }
        },"json")
    }
</script>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
    <DIV class=search><span style="color:black; font-size: 20px;">欢迎:${sessionScope.userInfo.name}</span><LABEL class="ui-green searchs"><a href="fabu.jsp" title="">发布房屋信息</a></LABEL>
        <LABEL class=ui-green><INPUT onclick='document.location="login.jsp"' value="退出" type=button name=search></LABEL>
    </DIV></DIV>
<DIV class="main wrap">
    <DIV id=houseArea>
        <TABLE class=house-list>
            <TBODY>
            <c:forEach items="${pageInfo.list}" var="h">
                <TR>
                    <TD class=house-thumb><SPAN><A href="getHouseById?id=${h.id}" target="_blank"><img src="http://localhost:81/${h.path}" width="100" height="75"></A></SPAN></TD>
                    <TD>
                        <DL>
                            <DT><A href="getHouseById?id=${h.id}" target="_blank">${h.title}</A></DT>
                            <DD>${h.dname}${h.sname}-${h.floorage}平米<BR>联系方式：${h.contact},类型:${h.tname} </DD></DL></TD>
                    <td>
                        <c:choose>
                            <c:when test="${h.ispass==1}">已审核</c:when>
                            <c:otherwise>未审核</c:otherwise>
                        </c:choose>
                    </td>
                    <TD class=house-type><LABEL class=ui-green><INPUT onclick="location.href='getSingleHouse?id=${h.id}'" value="修改" type=button name=search></LABEL></TD>
                    <TD class=house-price><LABEL class=ui-green><INPUT onclick="delHouse(this,${h.id})" value="删除" type=button name=search></LABEL></TD>
                </TR>
            </c:forEach>
            </TBODY></TABLE></DIV>
    <DIV class=pager>
        <UL>
            <c:forEach begin="1" end="${pageInfo.pages}" step="1" var="i">
                <LI class=current><A id=widget_338868862
                                     href="getHouse?page=${i}"
                                     parseContent="true" showError="true" targets="houseArea"
                                     ajaxAfterValidation="false" validate="false"
                                     dojoType="struts:BindAnchor">${i}</A>
                </LI>
            </c:forEach>
        </UL><SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
