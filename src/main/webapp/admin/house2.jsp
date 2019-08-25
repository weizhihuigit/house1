<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript">
        $(function(){
            //使用datagrid显示区域
            $('#dg').datagrid({
                toolbar: "#ToolBar",  //显示工具栏
                title:"以审核出租房",
                url:'getYesPassHouse',  //服务器地址
                pagination:true,  //启用分页
                pageList:[3,6,9,15,20], //设置每页大小
                pageSize:3, //每页三条
                columns:[[
                    {field:"cb",checkbox:true},
                    {field:'id',title:'编号',width:100,align:'left'},
                    {field:'title',title:'标题',width:100,align:'left'},
                    {field:'tname',title:'类型',width:100,align:'left'},
                    {field:'floorage',title:'面积',width:100,align:'left'},
                    {field:'price',title:'价格',width:100,align:'left'},
                    {field:'contact',title:'联系方式',width:100,align:'left'},
                    {field:'dname',title:'区域',width:100,align:'left'},
                    {field:'sname',title:'街道',width:100,align:'left'},
                    {field:'s',title:'操作',width:200,align:'left',
                        formatter:function (value,row,index) {
                            var str="<a href='javascript:goPass("+row.id+")'>取消审核</a>"
                            return str;
                        }}
                ]]
            });
        });
        /*使用异步请求：实现调用审核的服务器接口*/
        function goPass(id){
            //发请送异步请求
            $.post("goNoPassHouse",{"id":id},function (data){
                if(data.result>0)
                {
                    //刷新
                    $('#dg').datagrid('load');
                }else{
                    $.messager.alert("提示","取消审核失败","warn");
                }
            },"json");
        }
        /*实现用户搜索功能*/
        function userSearch(){
            //取值
            var name=$("#inputname").val();
            var tel=$("#inputtel").val();
            //重新加载
            // $('#dg').datagrid('load',传递查询条件参数);
            $('#dg').datagrid('load',{
                name: name,
                telephone: tel
            });
        }
    </script>
</head>
<body STYLE="width: 100%;height: 100%">
<!--制作工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:addDialog()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:updateDialog()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:deleteMoreDistrict()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">多项删除</a>

        <a id="btn" href="javascript:userSearch();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
    </div>
</div>
<!--显示区域-->
<table id="dg"></table>


</body>
</html>
