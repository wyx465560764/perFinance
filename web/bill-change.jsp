<%@ page import="entity.bill" %>
<%@ page import="DAO.changeBillDAO" %><%--
  Created by IntelliJ IDEA.
  User: wyx
  Date: 2019/3/14
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>个人理财系统</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link rel="stylesheet" href="css/layui.css"  media="all">
    <script src="layui.js" charset="utf-8"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/layui.css" rel="stylesheet">
    <link rel="shortcut icon" href="/images/shortcuticon.ico" />
</head>
<body>
<ul class="layui-nav">
    <li class="layui-nav-item"><a href="main.jsp">首页</a></li>
    <%
        if(session.getAttribute("type").equals("1")){
    %>
    <li class="layui-nav-item"><a href="background.jsp">基金经理后台</a></li>
    <%
    }else {
    %>
    <li class="layui-nav-item layui-this">
        <a href="javascript:;">基金超市</a>
        <dl class="layui-nav-child">
            <dd><a href="user-position.jsp?page=1">我的持仓</a></dd>
            <dd><a href="market-first.jsp">全部基金</a></dd>
        </dl>
    </li>
    <%
        }
    %>
    <li class="layui-nav-item"><a href="bill-add.jsp">记账</a></li>
    <li class="layui-nav-item"><a href="bill-detail.jsp?page=1">账单</a></li>
    <li class="layui-nav-item"><a href="wish-detail.jsp?page=1">愿望单</a></li>
    <li class="layui-nav-item" style="float: right">
        <%--<div class="col-md-1">--%>
        <a href="../login-out.jsp">注销</a>
        <%--</div>--%>
    </li>
    <li class="layui-nav-item" style="float: right">
        <%--<div class="col-md-2">--%>
        <p align="right">欢迎你，<%=session.getAttribute("username")%></p>
        <%--</div>--%>

    </li>

</ul>
<script>
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    });
</script>

<%
    bill bill=changeBillDAO.getInstance().changeBillSelect(request.getParameter("billid"));
%>
<div style="padding: 20px; background-color: #F2F2F2;">
    <div style="height: 30px">
        <span class="layui-breadcrumb">
            <a href="main.jsp">首页</a>
            <a href="bill-detail.jsp?page=1">查看账单</a>
            <a><cite>修改账单</cite></a>
        </span>
    </div>
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-header">修改账单</div>
            <div class="layui-card-body">
                <script>
                    //Demo
                    layui.use('form', function(){
                        var form = layui.form;

                        //监听提交
                        form.on('submit(formDemo)', function(data){
                            layer.msg(JSON.stringify(data.field));
                            return false;
                        });
                    });
                </script>
                <script>
                    layui.use('laydate', function(){
                        var laydate = layui.laydate;

                        //常规用法
                        laydate.render({
                            elem: '#date'
                            ,calendar: true
                        });
                    });
                </script>
                <form class="layui-form" action="changebill" method="post">
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="width: auto">消费/收入类型</label>
                        <div class="layui-input-inline">
                            <select name="billname" lay-verify="required">
                                <option value="<%=bill.getBillName()%>"><%=bill.getBillName()%></option>
                                <option value="衣食住">衣食住</option>
                                <option value="收入">收入</option>
                                <option value="医疗保健教育">医疗保健教育</option>
                                <option value="人情">人情</option>
                                <option value="家庭设备和服务">家庭设备和服务</option>
                                <option value="交通和通讯">交通和通讯</option>
                                <option value="其他">其他</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">金额</label>
                        <div class="layui-input-inline">
                            <input type="text" name="money" required lay-verify="required|number" placeholder="输入内容" autocomplete="off" class="layui-input" value="<%=bill.getMoney()%>">
                        </div>
                        <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-inline">
                            <input type="text" name="remark" required lay-verify="required" placeholder="输入内容" autocomplete="off" class="layui-input" value="<%=bill.getRemark()%>">
                        </div>
                        <div class="layui-form-mid layui-word-aux">可以记录一下消费/收入的具体项目</div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: auto">消费/收入日期</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="date" placeholder="yyyy-MM-dd" name="spenttime" required lay-verify="required" value="<%=bill.getSpentTime()%>">
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="billid" value="<%=bill.getBillId()%>">
                    <input type="hidden" name="userid" value="<%=bill.getUserId()%>">
                    <button class="layui-btn layui-btn-fluid" lay-submit="">修改</button>
                </form>
            </div>
        </div>
    </div>

</div>
</body>
</html>
