<%@ page import="entity.position" %>
<%@ page import="DAO.reviewOrderDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: wyx
  Date: 2019/3/18
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>个人理财系统-基金经理后台</title>
    <link rel="stylesheet" href="css/layui.css">
    <link rel="shortcut icon" href="/images/shortcuticon.ico" />
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><a href="background.jsp" style="color: #009688">基金经理后台</a></div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <%--<li class="layui-nav-item"><a href="main.jsp">主页</a></li>--%>
            <%--<li class="layui-nav-item"><a href="wish-detail.jsp?page=1">个人愿望单</a></li>--%>
            <%--<li class="layui-nav-item"><a href="bill-detail.jsp?page=1">个人账单</a></li>--%>
            <%--<li class="layui-nav-item"><a href="product-earn.jsp">理财收益管理</a></li>--%>
            <%--<li class="layui-nav-item">--%>
            <%--<a href="javascript:;">其它功能</a>--%>
            <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="">发布基金</a></dd>--%>
            <%--<dd><a href="">修改基金</a></dd>--%>
            <%--&lt;%&ndash;<dd><a href="">授权管理</a></dd>&ndash;%&gt;--%>
            <%--</dl>--%>
            <%--</li>--%>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <%--<a href="javascript:;">--%>
                <%--<img src="http://t.cn/RCzsdCq" class="layui-nav-img">--%>
                <p align="right">欢迎你，<%=session.getAttribute("username")%></p>
                <%--</a>--%>

            </li>
            <li class="layui-nav-item"><a href="login-out.jsp">注销</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">常用操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="product-earn.jsp?page=1">基金收益管理</a></dd>
                        <dd><a href="product-review.jsp?status=1&page=1">买入审核</a></dd>
                        <dd><a href="product-review.jsp?status=3&page=1">卖出审核</a></dd>
                    </dl>
                </li>
                <%--<li class="layui-nav-item">--%>
                <%--<a href="javascript:;">审核</a>--%>
                <%--<dl class="layui-nav-child">--%>
                <%--<dd><a href="javascript:;">买入审核</a></dd>--%>
                <%--<dd><a href="javascript:;">卖出审核</a></dd>--%>
                <%--</dl>--%>
                <%--</li>--%>
                <li class="layui-nav-item"><a href="product-change.jsp?page=1">修改基金</a></li>
                <li class="layui-nav-item"><a href="product-add.jsp">发布基金</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 20px; background-color: #F2F2F2;">
            <div style="height: 30px">
                <span class="layui-breadcrumb">
                    <%--<a href="main.jsp">首页</a>--%>
                    <a href="background.jsp">基金经理后台</a>
                    <a><cite><%if(request.getParameter("status").equals("1")){ %>买入审核
                    <%}else if(request.getParameter("status").equals("3")){%>卖出审核
                    <%}%></cite></a>
                </span>
            </div>
        </div>
        <div style="height: 30px"></div>
        <fieldset class="layui-elem-field">
            <legend><%if(request.getParameter("status").equals("1")){ %>买入审核
                <%}else if(request.getParameter("status").equals("3")){%>卖出审核
                <%}%></legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <colgroup>
                        <col width="150">
                        <col width="200">
                        <col>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>基金名称</th>
                        <th>买入单价</th>
                        <th>买入份数</th>
                        <th>认购总价值</th>
                        <th>申请时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        try {
                            List<position> positionList= reviewOrderDAO.getInstance().viewReview(Integer.valueOf(request.getParameter("status")),Integer.valueOf(session.getAttribute("userid").toString()));
                            int RowCount=positionList.size();           //记录总数
                            int PageCount=(RowCount-1)/10+1;          //总页数
                            int Page=1;               //待显示页码
                            Page=Integer.parseInt(request.getParameter("page"));
                            int Num=1;//计数器
                            String status;
                            int statusNum;
                            for (position e:positionList)
                            {
                                if(Num>(Page-1)*10&&Num<=Page*10){
                    %>
                    <tr>
                        <td><%=e.getProductname()%></td>
                        <td><%=e.getBuyprice()%></td>
                        <td><%=String.format("%.2f",e.getBuynum())%></td>
                        <td><%=e.getBuysum()%></td>
                        <td><%if(Integer.valueOf(request.getParameter("status"))==1){%>
                            <%=e.getPushtime()%>
                            <%}else if(Integer.valueOf(request.getParameter("status"))==3){%>
                            <%=e.getSelltime()%>
                            <%}%>
                        </td>
                        <td><%if(e.getOrderstatus()==1){%>
                            <a class="layui-btn layui-btn-normal layui-btn-sm" href="review?orderid=<%=e.getOrderid()%>&operate=1&status=1&page=<%=Page%>&num=<%=RowCount%>">通过申请</a>
                            <a class="layui-btn layui-btn-danger layui-btn-sm" href="review?orderid=<%=e.getOrderid()%>&operate=2&status=1&page=<%=Page%>&num=<%=RowCount%>">驳回</a>
                            <%}else if(e.getOrderstatus()==3){%>
                            <a class="layui-btn layui-btn-normal layui-btn-sm" href="review?orderid=<%=e.getOrderid()%>&operate=1&status=3&page=<%=Page%>&num=<%=RowCount%>">通过申请</a>
                            <a class="layui-btn layui-btn-danger layui-btn-sm" href="review?orderid=<%=e.getOrderid()%>&operate=2&status=3&page=<%=Page%>&num=<%=RowCount%>">驳回</a>
                            <%}%>
                        </td>
                    </tr>
                    </tbody>
                    <%
                            }
                        }
                    %>
                </table>
                <%if(positionList.isEmpty()){%>
                <%="暂无数据"%>
                <%}%>
                <div style="text-align: center;">
                    第<%=Page%>页 共<%=PageCount%>页

                    <% if(Page>1){ %>

                    <a href="product-review.jsp?page=<%=Page-1%>" class="layui-btn">上一页</a>

                    <% }else{ %>
                    <a class="layui-btn">上一页</a>
                    <%}%>
                    <% if(Page<PageCount){ %>

                    <a href="product-review.jsp?page=<%=Page+1%>" class="layui-btn">下一页</a>

                    <% }else{ %>
                    <a class="layui-btn">下一页</a>
                    <%}%>
                <%
        }catch (SQLException s){
        s.printStackTrace();
    }
    %>
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
