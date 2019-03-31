<%@ page import="entity.product" %>
<%@ page import="DAO.viewIncomeDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DAO.recentMonthIncomeDAO" %>
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
                <li class="layui-nav-item"><a href="user-position.jsp?page=1">我的持仓</a></li>
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
                    <a><cite><%=request.getParameter("type")%></cite></a>
                </span>
            </div>
        </div>
        <%--<div style="height: 20%">&nbsp</div>--%>
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col width="200">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>基金名称</th>
                <th>单位净值(元)</th>
                <th>剩余可购份数</th>
                <th>月涨跌幅</th>
                <th>年涨跌幅</th>
                <th>操作</th>
            </tr>
            </thead>
            <%
                try {
                    List<product> productList= viewIncomeDAO.getInstance().selectUnfixedIcome(request.getParameter("type"));
                    int RowCount=productList.size();           //记录总数
                    int PageCount=(RowCount-1)/10+1;          //总页数
                    int Page=1;               //待显示页码
                    Page=Integer.parseInt(request.getParameter("page"));
                    int Num=1;//计数器
                    double monthIncome;
                    for (product e:productList)
                    {
                        if(Num>(Page-1)*10&&Num<=Page*10){
            %>
            <tbody>
            <tr>
                <td><%=e.getProductname()%></td>
                <td><%=e.getNowprice()%></td>
                <td><%=e.getOver()%></td>
                <td><%
                    monthIncome=(e.getNowprice()-recentMonthIncomeDAO.getInstance().monthIncome(e.getProductid(),1))/e.getNowprice();
                    if(monthIncome==1){
                        %>
                    <%=0%>
                    <%
                    }else {
                        %>
                    <%=String.format("%.2f", monthIncome*100)+"%"%>
                    <%
                    }
                %>
                </td>
                <td><%
                    monthIncome=(e.getNowprice()-recentMonthIncomeDAO.getInstance().monthIncome(e.getProductid(),12))/e.getNowprice();
                    if(monthIncome==1){
                %>
                    <%=0%>
                    <%
                    }else {
                    %>
                    <%=String.format("%.2f", monthIncome*100)+"%"%>
                    <%
                        }
                    %></td>
                <td><a class="layui-btn layui-btn-normal layui-btn-sm" href="unfixed-income-detail.jsp?productid=<%=e.getProductid()%>">详细</a></td>
            </tr>
            <%--<%--%>
                <%--}%>--%>
            </tbody>
            <%
                }
                }
            %>
        </table>
        <%if(productList.isEmpty()){%>
        <%="暂无数据"%>
        <%}%>
        <div style="text-align: center;">
            第<%=Page%>页 共<%=PageCount%>页

            <% if(Page>1){ %>

            <a href="market-unfixedincome.jsp?page=<%=Page-1%>" class="layui-btn">上一页</a>

            <% }else{ %>
            <a class="layui-btn">上一页</a>
            <%}%>
            <% if(Page<PageCount){ %>

            <a href="market-unfixedincome.jsp?page=<%=Page+1%>" class="layui-btn">下一页</a>

            <% }else{ %>
            <a class="layui-btn">下一页</a>
            <%}%>
        </div>
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
