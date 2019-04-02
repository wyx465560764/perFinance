<%@ page import="entity.position" %>
<%@ page import="DAO.viewPositionDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Date" %>
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
                        <dd><a href="market-fixedincome.jsp?type=货币型&page=1">货币型</a></dd>
                        <dd><a href="market-fixedincome.jsp?type=理财型&page=1">理财型</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=股票型&page=1">债券型</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=QDII&page=1">QDII</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=养老FOF&page=1">养老FOF</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=股票型&page=1">股票型</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=混合型&page=1">混合型</a></dd>
                        <dd><a href="market-unfixedincome.jsp?type=指数型&page=1">指数型</a></dd>
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
                    <a href="market-first.jsp">基金超市</a>
                    <a><cite>我的持仓</cite></a>
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
                <th>基金类型</th>
                <th>收益类型</th>
                <th>购入时单位净值(元)</th>
                <th>持有金额(元)</th>
                <th>累计收益(元)</th>
                <th>订单状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <%
                try {
                    List<position> positionList= viewPositionDAO.getInstance().selectPosition(Integer.valueOf(session.getAttribute("userid").toString()));
                    int RowCount=positionList.size();           //记录总数
                    int PageCount=(RowCount-1)/10+1;          //总页数
                    int Page=1;               //待显示页码
                    Page=Integer.parseInt(request.getParameter("page"));
                    int Num=1;//计数器
                    for (position e:positionList)
                    {
                        if(Num>(Page-1)*10&&Num<=Page*10){
            %>
            <tr>
                <th><%=e.getProductname()%></th>
                <th><%=e.getType()%></th>
                <th><%if(e.getEarntype()==1){%><%="固收"%>
                <%}else if(e.getEarntype()==2){%><%="不定收益"%><%}%>
                </th>
                <th><%=e.getBuyprice()%></th>
                <th><%if(e.getOrderstatus()==4){%>
                    <%=String.format("%.2f",e.getSellprice()*e.getBuynum())%>
                <%}else if(e.getOrderstatus()==5){%>
                    <%}else {%>
                    <%=String.format("%.2f",e.getNowprice()*e.getBuynum())%>
                <%}%></th>
                <th><%if((e.getOrderstatus()==2||e.getOrderstatus()==3)&&e.getEarntype()==2){%>
                <%=String.format("%.2f",(e.getNowprice()-e.getBuyprice())*e.getBuynum())%>
                <%}else if(e.getOrderstatus()==4&&e.getEarntype()==2){%>
                    <%=String.format("%.2f",(e.getSellprice()-e.getBuyprice())*e.getBuynum())%>
                    <%}%>
                    <%if((e.getOrderstatus()==2||e.getOrderstatus()==3)&&e.getEarntype()==1){
                    int days = (int) ((new Date().getTime() - e.getPushtime().getTime()) / (1000*3600*24));
                %>
                <%=String.format("%.2f",(days*e.getExpectedincome())/365)%>
                    <%}else if(e.getOrderstatus()==4&&e.getEarntype()==1){
                        int days = (int) (e.getSelltime().getTime() - e.getPushtime().getTime()) / (1000*3600*24);
                    %>
                    <%=String.format("%.2f",(days*e.getExpectedincome())/365)%>
                    <%}%>
                </th>
                <th><%if(e.getOrderstatus()==1){%>
                    <%="申购审核中"%>
                    <%}else if(e.getOrderstatus()==2){%>
                    <%="持有"%>
                    <%}else if(e.getOrderstatus()==3){%>
                    <%="申请赎回中"%>
                    <%}else if(e.getOrderstatus()==4){%>
                    <%="赎回完成"%>
                    <%}else if(e.getOrderstatus()==5){%>
                    <%="申购失败"%>
                    <%}%>
                </th>
                <th><%if(e.getOrderstatus()==1){%>
                    <a class="layui-btn layui-btn-danger layui-btn-sm" href="reviewuser?orderid=<%=e.getOrderid()%>&operate=1&page=<%=Page%>&num=<%=RowCount%>">取消申购</a>
                    <%}else if(e.getOrderstatus()==2){%>
                    <a class="layui-btn layui-btn-normal layui-btn-sm" href="reviewuser?orderid=<%=e.getOrderid()%>&operate=2&page=<%=Page%>&num=<%=RowCount%>">卖出</a>
                    <%}else if(e.getOrderstatus()==3){%>
                    <a class="layui-btn layui-btn-danger layui-btn-sm" href="reviewuser?orderid=<%=e.getOrderid()%>&operate=3&page=<%=Page%>&num=<%=RowCount%>">取消赎回</a>
                    <%}%>
                </th>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
        <%if(positionList.isEmpty()){%>
        <%="暂无数据"%>
        <%}%>
        <div style="text-align: center;">
            第<%=Page%>页 共<%=PageCount%>页

            <% if(Page>1){ %>

            <a href="user-position.jsp?page=<%=Page-1%>" class="layui-btn">上一页</a>

            <% }else{ %>
            <a class="layui-btn">上一页</a>
            <%}%>
            <% if(Page<PageCount){ %>

            <a href="user-position.jsp?page=<%=Page+1%>" class="layui-btn">下一页</a>

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
