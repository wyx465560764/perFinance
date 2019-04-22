<%@ page import="entity.bill" %>
<%@ page import="DAO.searchDAO"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: wyx
  Date: 2019/3/11
  Time: 20:13
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
<%
    try{
        List<bill> billList=searchDAO.getInstance().searchBill((List) session.getAttribute("searchbill"));


%>
<script>
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    });
</script>
<div style="padding: 20px; background-color: #F2F2F2;">
    <span class="layui-breadcrumb">
        <a href="main.jsp">首页</a>
        <a href="bill-detail.jsp?page=1">查看账单</a>
        <a><cite>搜索结果</cite></a>
    </span>
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">搜索结果</div>
                <div class="layui-card-body">
                    <table class="layui-table">
                        <colgroup>
                            <col width="150">
                            <col width="200">
                            <col>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>类型</th>
                            <th>消费/收入时间</th>
                            <th>花费/收入</th>
                            <th>记账时间</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <%
                            int RowCount=billList.size();           //记录总数
                            int PageCount=(RowCount-1)/10+1;          //总页数
                            int Page=1;               //待显示页码
                            Page=Integer.parseInt(request.getParameter("page"));
                            int Num=1;//计数器
                            double Spent=0;
                            double Income=0;
                            for (bill e:billList ) {
                                if(e.getType()==1){
                                    Income=e.getMoney()+Income;
                                }else {
                                    Spent=e.getMoney()+Spent;
                                }
                                if(Num>(Page-1)*10&&Num<=Page*10){
                        %>
                        <tbody>
                        <tr>
                            <td><%=e.getBillNameText()%></td>
                            <td><%=e.getSpentTime()%></td>
                            <td><%=e.getMoney()%></td>
                            <td><%=e.getPushTime()%></td>
                            <td><%=e.getRemark()%></td>
                            <td><a class="layui-btn layui-btn-sm layui-btn-normal" href="bill-change.jsp?billid=<%=e.getBillId()%>&status=1">编辑</a><a class="layui-btn layui-btn-sm layui-btn-danger" href="deletebill?billid=<%=e.getBillId()%>&status=2&page=<%=Page%>&num=<%=RowCount%>">删除</a></td>
                            <%--<td><%=Num%></td>--%>
                            <%}
                                Num++;
                            %>
                        </tr>
                        </tbody>
                        <%
                            }
                            if(billList.isEmpty()){
                                String message="您暂时还没有记账，创建一个吧！";
                        %>
                        <%=message%>
                        <%
                            }
                        %>
                    </table>
                    <div style="text-align: center;">
                        第<%=Page%>页 共<%=PageCount%>页

                        <% if(Page>1){ %>

                        <a href="bill-search-result.jsp?page=<%=Page-1%>" class="layui-btn">上一页</a>

                        <% }else{ %>
                        <a class="layui-btn">上一页</a>
                        <%}%>
                        <% if(Page<PageCount){ %>

                        <a href="bill-search-result.jsp?page=<%=Page+1%>" class="layui-btn">下一页</a>

                        <% }else{ %>
                        <a class="layui-btn">下一页</a>
                        <%}%>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<ul class="layui-nav" style="position: fixed;bottom:0">
    <li class="layui-nav-item" style="float: right">在搜索结果中已经累计消费<%=Spent%>元，收入<%=Income%>元</li>
</ul>
<%
    }catch (SQLException s){
        s.printStackTrace();
    }
%>
</html>
