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
                title:"审核出租房",
                url:'getNoPassHouse',  //服务器地址
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
                            var str="<a href='javascript:goPass("+row.id+")'>审核</a>"
                            return str;
                        }}
                ]]
            });
        });
        /*使用异步请求：实现调用审核的服务器接口*/
        function goPass(id){
            //发请送异步请求
            $.post("goYesPassHouse",{"id":id},function (data){
                if(data.result>0)
                {
                    //刷新
                    $('#dg').datagrid('load');
                }else{
                    $.messager.alert("提示","审核失败","warn");
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
</head>
<body STYLE="width: 100%;height: 100%">
<!--制作工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <FORM id=sform method=post action="searchHouse1">
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
        </FORM>
    </div>
</div>
<!--显示区域-->
<table id="dg"></table>


</body>
</html>
