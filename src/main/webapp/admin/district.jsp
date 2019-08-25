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
              title:"区域信息",
              url:'district',  //服务器地址
              pagination:true,  //启用分页
              pageList:[3,6,9,15,20], //设置每页大小
              pageSize:3, //每页三条
              columns:[[
                  {field:"cb",checkbox:true},
                  {field:'id',title:'编号',width:400,align:'left'},
                  {field:'name',title:'区域名称',width:400,align:'left'},
                  {field:'s',title:'操作',width:200,align:'left',
                      formatter:function (value,row,index) {
                          var str="<a href='javascript:delDistrict("+row.id+")'>删除</a>|<a href='javascript:updateDialog()'>修改</a>|<a href='javascript:selectStreet("+row.id+")'>搜索街道</a>"
                          return str;
                      }}
              ]]
          });
      });
      //点击添加，打开窗口
      function addDialog(){
          //alert("我要添加区域");
          $('#AddDialog').window('setTitle',"添加区域");
          $('#AddDialog').window('open');
      }
      //关闭对话框
      function CloseAddDialog(id){
          $('#'+id).window('close'); //关闭
      }
      function SaveDistrict(){
          //1.获取数据，发送异步请求实现添加
          //$.post("addDistrict",给服务器传参,function(data){},"json");
          /* $.post("addDistrict",{"name":$("#name2").val()},function(data){
               alert(data);
           },"json");*/

          //2.使用easuyi插件以异步方式提交表单
          $('#form1').form('submit',{
              url:"addDialog",
              success:function(data){  //data表示的字符串
                  //将返回的json字符串转化为json对象
                  data=$.parseJSON(data);
                  if(data.result>0){
                      $.messager.alert('>>提示','添加成功！','info');  //提示框
                      $('#AddDialog').window('close'); //关闭
                  }else{
                      $.messager.alert('>>提示','添加失败！','error');
                      $('#AddDialog').window('close'); //关闭
                  }
              }
          });
      }
      //显示修改的对话框
      function updateDialog(){
          //判断用户选择
          //1.获取dagagrid的选中行
          var SelectRows = $("#dg").datagrid('getSelections');  //返回数组
          if(SelectRows.length==1){
              $('#updateDialog').window('setTitle',"编辑区域");
              $('#updateDialog').window('open');

              //获取当前行的数据:获取主键，查询数据库获取单行数据
              //如果当显示的数据在dagagrid中存在，无需查询数据库，如果当显示的数据在datagrid中不全，需要查询数据库获单条
              //发异步请求查询数据库
              $.post("getOneDistrict",{"id":SelectRows[0].id},function(data){
                  $("#form2").form('load',data); //将对象还原表单
              },"json");
          }else{
              $.messager.alert('>>提示','请选择您要修改的信息!','warn');  //提示框
          }
      }
      //更新区域
      function updateDistrict(){
          //取值-->发送异步请求到服务-->实现修改
          //2.使用easuyi插件以异步方式提交表单
          $('#form2').form('submit',{
              url:"updateDistrict",
              success:function(data){  //data表示的字符串
                  //将返回的json字符串转化为json对象
                  data=$.parseJSON(data);
                  if(data.result>0){
                      //刷新datagrid
                      $('#dg').datagrid("reload");
                      $.messager.alert('>>提示','修改成功！','info');  //提示框
                      $('#updateDialog').window('close'); //关闭
                  }else{
                      $.messager.alert('>>提示','修改失败！','error');
                      $('#updateDialog').window('close'); //关闭
                  }
              }
          })
      }
      //单条删除
      function delDistrict(id) {
       $.messager.confirm('>>提示','确认删除吗？',function (r) {
           if (r){
               $.post("delDistrict",{"id":id},function (data) {
                if (data.result>0){
                    $('#dg').datagrid("reload");
                }else {
                    $.messager.alert('>>提示','删除失败！','error');
                }
               },"json")
           }
       })
      }
      //批量删除区域
      function deleteMoreDistrict(){
          //1.获取dagagrid的选中行
          var SelectRows = $("#dg").datagrid('getSelections');  //返回数组
          //2.判断是否选择的行
          if(SelectRows.length==0){
              $.messager.alert('>>提示','请选择后再进行删除.','warn');
          }else{
              //确认删除
              $.messager.confirm('>>提示','确定删除吗?想好在点!',function(r){
                  if(r){  //删除
                      //3.获取选中项的值,拼接字符串 格式:1,2,3
                      var val="";
                      for (var i=0;i<SelectRows.length;i++){
                          val=val+SelectRows[i].id+",";
                      }
                      val=val.substring(0,val.length-1);
                      //4.发送异步请求到服务器实现删除
                      $.post("delMoreDistrict",{"ids":val},function(data){
                          if(data.result>0){
                              //刷新datagrid
                              $('#dg').datagrid("reload");
                          } else{
                              $.messager.alert('>>提示','删除多项失败！','error');
                          }
                      },"json");
                  }
              });
          }
      }
      /*打开显示街道的对话框*/
      function selectStreet(did){
          $('#showStreetDialog').window('setTitle',"街道信息");
          $('#showStreetDialog').window("open");
          $("#did").val(did)
          //发请求绑定数据
          $('#dgStreet').datagrid({
              url:'getStreetByDid?did='+did,
              title:"街道信息",
              //toolbar:"#ToolBar",  //指定工具栏
              pagination:true,
              pageList:[3,6,9,15,20],
              pageSize:3,
              //singleSelect:true,
              columns:[[
                  {field:"cb",checkbox:true},
                  {field:'id',title:'编号',width:100},
                  {field:'name',title:'街道名称',width:100,
                      formatter: function(value,row,index){
                          var str="<input type='text' class='ss' value='"+value+"'>";
                          return str;
                      }},
                  {field:'s',title:'操作',width:200,
                      formatter: function(value,row,index){
                          var str="<a href='javascript:updateStreet("+row.id+","+index+")'>更新</a>";
                          return str;
                      }
                  }
              ]]
          });
      }
      //更新区域
      function updateStreet(id,index){
          //取值-->发送异步请求到服务-->实现修改
          //2.使用easuyi插件以异步方式提交表单
          var name=$('.ss').get(index).value
          $.post("updateStreet",{"id":id,"name":name},function(data){
              if(data.result>0){
                  $.messager.alert('>>提示','修改成功！','info');
              }else{
                  $.messager.alert('>>提示','修改失败！','error');
              }
          },"json");
          $('#dgStreet').datagrid('reload');
      }
      function addDialogStreet() {
          $('#form3').form('submit',{
              url:"addDialogStreet",
              success:function(data){  //data表示的字符串
                  //将返回的json字符串转化为json对象
                  data=$.parseJSON(data);
                  if(data.result>0){
                      //刷新datagrid
                      $('#dg').datagrid("reload");
                      $.messager.alert('>>提示','添加成功！','info');  //提示框
                      $('#updateDialog').window('close'); //关闭
                  }else{
                      $.messager.alert('>>提示','添加失败！','error');
                      $('#updateDialog').window('close'); //关闭
                  }
              }
          })
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
    </div>
</div>
   <!--显示区域-->
   <table id="dg"></table>
<!--添加对话框-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form method="post" action="addDistrict" id="form1" name="form1">
        区域名称:<input type="text" name="name" id="name2">
        <%--<input type="submit" value="添加">--%>
    </form>
</div>
<!--添加对话框的按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDistrict()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseAddDialog('AddDialog')"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<!--修改对话框-->
<div id="updateDialog" class="easyui-dialog" buttons="#updateDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form method="post" action="updateDialog" id="form2" name="form1">
                 <input type="hidden" name="id">
        区域名称:<input type="text" name="name" id="name3">
    </form>
</div>
<!--显示街道对话框-->
<div id="showStreetDialog" class="easyui-dialog" buttons="#updateDialogButtons"
     style="width: 500px; height: 350px; padding: 10px 20px;" closed="true">
    <table id="dgStreet"></table>
    <hr></hr>
    <form method="post" action="addDistrict"  name="form1" id="form3">
        <input type="hidden" name="districtId" id="did">
        街道名称:<input type="text" name="name" id="streetId">
        <input type="button" value="添加" onclick="location.href='javascript:addDialogStreet()'">
    </form>
</div>
</body>
</html>
