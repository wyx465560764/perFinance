<%@ page import="java.util.List" %>
<%@ page import="entity.earn" %>
<%@ page import="DAO.unfixedIncomedetailDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="entity.productDetail" %>
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
                    <dd><a href="">我的持仓</a></dd>
                    <dd><a href="">全部基金</a></dd>
                    <dd class="layui-this"><a href="">我的收益</a></dd>
                    <%--<dd><a href="">电商平台</a></dd>--%>
                </dl>
            </li>
            <%
                }
            %>
            <%--<li class="layui-nav-item layui-this">--%>
            <%--<a href="javascript:;">产品</a>--%>
            <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="">选项1</a></dd>--%>
            <%--<dd><a href="">选项2</a></dd>--%>
            <%--<dd><a href="">选项3</a></dd>--%>
            <%--</dl>--%>
            <%--</li>--%>
            <li class="layui-nav-item"><a href="">记账</a></li>
            <li class="layui-nav-item"><a href="">账单</a></li>
            <li class="layui-nav-item"><a href="">愿望单</a></li>


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
                        <dd><a href="javascript:;">货币型</a></dd>
                        <dd><a href="javascript:;">理财型</a></dd>
                        <dd><a href="javascript:;">债券型</a></dd>
                        <dd><a href="javascript:;">QDII</a></dd>
                        <dd><a href="javascript:;">养老FOF</a></dd>
                        <dd><a href="javascript:;">股票型</a></dd>
                        <dd><a href="javascript:;">混合型</a></dd>
                        <dd><a href="javascript:;">指数型</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">我的持仓</a></li>
                <li class="layui-nav-item"><a href="">我的收益</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 20px; background-color: #F2F2F2;">
            <div style="height: 30px">
                <span class="layui-breadcrumb">
                    <a href="main.jsp">首页</a>
                    <a href="market-first.jsp">基金超市</a>
                    <a><cite>基金详细</cite></a>
                </span>
            </div>
        </div>
        <%--<div style="height: 20%">&nbsp</div>--%>
        <fieldset class="layui-elem-field">
            <legend>基金详情</legend>
            <div class="layui-field-box">
                <div style="padding: 20px; background-color: #F2F2F2;">
                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md6">
                        <div class="layui-card">
                            <%
                                try {
                                    List<earn> monthlist=unfixedIncomedetailDAO.getInstance().selectUnfixedIncome(Integer.valueOf(request.getParameter("productid")),1);
                            %>
                            <div class="layui-card-header">近一个月收益变化</div>
                            <div class="layui-card-body">
                                <div id="container1" style="height: 250%"></div>
                                <script type="text/javascript" src="js/echarts.common.min.js"></script>
                                <script type="text/javascript">
                                    var dom = document.getElementById("container1");
                                    var myChart = echarts.init(dom);
                                    var app = {};
                                    option = null;
                                    option = {
                                        xAxis: {
                                            type: 'category',
                                            boundaryGap: false,
                                            data: [
                                                <%
                                                for(earn et:monthlist){

                                                %>
                                                '<%=et.getPushtime()%>',
                                                <%
                                                }
                                                %>]
                                        },
                                        yAxis: {
                                            type: 'value'
                                        },
                                        series: [{
                                            data: [
                                                <%
                                                for(earn ep:monthlist){
                                                %>
                                                '<%=ep.getPrice()%>',
                                                <%
                                                }
                                                %>],
                                            type: 'line',
                                            areaStyle: {},
                                            itemStyle : { normal: {label : {show: true}}}
                                        }]
                                    };
                                    if (option && typeof option === "object") {
                                        myChart.setOption(option, true);
                                    }
                                </script>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md6">
                        <div class="layui-card">
                            <div class="layui-card-header">近一年收益变化</div>
                            <%
                                List<earn> yearlist=unfixedIncomedetailDAO.getInstance().selectUnfixedIncome(Integer.valueOf(request.getParameter("productid")),12);
                            %>
                            <div class="layui-card-body">
                                <div id="container2" style="height: 250%"></div>
                                <script type="text/javascript" src="js/echarts.common.min.js"></script>
                                <script type="text/javascript">
                                    var dom = document.getElementById("container2");
                                    var myChart = echarts.init(dom);
                                    var app = {};
                                    option = null;
                                    option = {
                                        xAxis: {
                                            type: 'category',
                                            boundaryGap: false,
                                            data: [
                                                <%
                                                for(earn ey:yearlist){
                                                %>
                                                '<%=ey.getPushtime()%>',
                                                <%
                                                }
                                                %>]
                                        },
                                        yAxis: {
                                            type: 'value'
                                        },
                                        series: [{
                                            data: [
                                                <%
                                                for(earn eyp:yearlist){
                                                %>
                                                '<%=eyp.getPrice()%>',
                                                <%
                                                }
                                                %>],
                                            type: 'line',
                                            areaStyle: {},
                                            itemStyle : { normal: {label : {show: true}}}
                                        }]
                                    };
                                    if (option && typeof option === "object") {
                                        myChart.setOption(option, true);
                                    }
                                </script>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md12">
                        <div class="layui-card">
                            <%
                                productDetail productDetail=unfixedIncomedetailDAO.getInstance().selecteproductDetail(Integer.valueOf(request.getParameter("productid")));
                            %>
                            <div class="layui-card-header">基金档案</div>
                            <div class="layui-card-body">
                                <div class="layui-row">
                                    <div class="layui-col-xs6">
                                        基金全称：<%=productDetail.getProductname()%>
                                    </div>
                                    <div class="layui-col-xs3">
                                        基金类型：<%=productDetail.getType()%>
                                    </div>
                                    <div class="layui-col-xs3">
                                        基金收益类型：<%if(productDetail.getEarntype()==1){%>
                                        <%="固收"%>
                                        <%}else {%>
                                        <%="不定收益"%>
                                        <%}%>
                                    </div>
                                </div>
                                <div class="layui-row">
                                    <div class="layui-col-xs3">
                                        最新基金规模：<%=productDetail.getNowprice()*productDetail.getSum()%>
                                    </div>
                                    <div class="layui-col-xs3">
                                        最新单位净值：<%=productDetail.getNowprice()%>
                                    </div>
                                    <div class="layui-col-xs3">
                                        份额总数：<%=productDetail.getSum()%>
                                    </div>
                                    <div class="layui-col-xs3">
                                        剩余可购份额数：<%=productDetail.getOver()%>
                                    </div>
                                </div>
                                <div class="layui-row">
                                    <div class="layui-col-xs4">
                                        预期年化收益率（仅供参考）：<%=productDetail.getExpectedincome()%>
                                    </div>
                                    <div class="layui-col-xs8">
                                        备注：<%=productDetail.getRemark()%>
                                    </div>
                                </div>
                                <div class="layui-row">
                                    <div class="layui-col-xs4">
                                        基金经理姓名：<%=productDetail.getUsername()%>
                                    </div>
                                    <div class="layui-col-xs8">
                                        基金经理简介：<%=productDetail.getManagercontext()%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
            <%--<div class="layui-field-box">--%>
                <%--<fieldset class="layui-elem-field layui-field-title">--%>
                    <%--<legend>固收型基金</legend>--%>
                <%--</fieldset>--%>
                <%--<button class="layui-btn layui-btn-lg">理财型</button>--%>
                <%--<button class="layui-btn layui-btn-lg">货币型</button>--%>
            <%--</div>--%>
            <%--<div class="layui-field-box">--%>
                <%--<fieldset class="layui-elem-field layui-field-title">--%>
                    <%--<legend>不定收益型基金</legend>--%>
                <%--</fieldset>--%>
                <%--<a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=股票型&page=1">股票型</a>--%>
                <%--<a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=债券型&page=1">债券型</a>--%>
                <%--<a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=混合型&page=1">混合型</a>--%>
                <%--<a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=指数型&page=1">指数型</a>--%>
                <%--<a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=QDII&page=1">QDII</a>--%>
                <%--<a class="layui-btn layui-btn-lg" href="market-unfixedincome.jsp?type=养老FOF&page=1">养老FOF</a>--%>
            <%--</div>--%>
        </fieldset>
    </div>

    <%
        }catch (SQLException s){
            s.printStackTrace();
        }
    %>
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
