<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>基金超市</title>
    <link rel="stylesheet" href="css/layui.css">
    <link rel="shortcut icon" href="/images/shortcuticon.ico" />
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">基金超市</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
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


        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <p align="right">欢迎你，<%=session.getAttribute("username")%></p>
            </li>
            <li class="layui-nav-item">
                <a href="../login-out.jsp">注销</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item"><a href="market-first.jsp">基金超市</a></li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">基金类型</a>
                    <dl class="layui-nav-child">
                        <dd><a href="market-fixedincome.jsp?type=1&page=1">货币型</a></dd>
                        <dd><a href="market-fixedincome.jsp?type=2&page=1">理财型</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=3&page=1">债券型</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=4&page=1">QDII</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=5&page=1">养老FOF</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=6&page=1">股票型</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=7&page=1">混合型</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=8&page=1">指数型</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="user-position.jsp?page=1">我的持仓</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 20px; background-color: #F2F2F2;">
            <div style="height: 30px">
                <span class="layui-breadcrumb">
                    <a href="main.jsp">首页</a>
                    <a><cite>基金超市</cite></a>
                </span>
            </div>
        </div>
        <%--<div style="height: 20%">&nbsp</div>--%>
        <fieldset class="layui-elem-field">
            <legend>选择投资的基金</legend>
            <div class="layui-field-box">
                <fieldset class="layui-elem-field layui-field-title">
                    <legend>固收型基金</legend>
                </fieldset>
                <a class="layui-btn layui-btn-lg" href="market-fixedincome.jsp?type=1&page=1">货币型</a>
                <a class="layui-btn layui-btn-lg" href="market-fixedincome.jsp?type=2&page=1">理财型</a>
            </div>
            <div class="layui-field-box">
                <fieldset class="layui-elem-field layui-field-title">
                    <legend>不定收益型基金</legend>
                </fieldset>
                <a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=6&page=1">股票型</a>
                <a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=3&page=1">债券型</a>
                <a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=7&page=1">混合型</a>
                <a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=8&page=1">指数型</a>
                <a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=4&page=1">QDII</a>
                <a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=5&page=1">养老FOF</a>
            </div>
        </fieldset>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        投资有风险，理财需谨慎
    </div>
</div>
<script src="layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>
