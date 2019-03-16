<%@ page import="DAO.searchDAO" %>
<%@ page import="entity.wish" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wyx
  Date: 2019/3/10
  Time: 19:54
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
    <%
        List list=(List) session.getAttribute("searchwish");
        List<wish> wishList=searchDAO.getInstance().searchWish(list);
    %>
</head>
<body>

<ul class="layui-nav">
    <li class="layui-nav-item"><a href="main.jsp">首页</a></li>
    <li class="layui-nav-item layui-this">
        <a href="javascript:;">产品</a>
        <dl class="layui-nav-child">
            <dd><a href="">选项1</a></dd>
            <dd><a href="">选项2</a></dd>
            <dd><a href="">选项3</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item"><a href="">大数据</a></li>
    <li class="layui-nav-item">
        <a href="javascript:;">解决方案</a>
        <dl class="layui-nav-child">
            <dd><a href="">移动模块</a></dd>
            <dd><a href="">后台模版</a></dd>
            <dd class="layui-this"><a href="">选中项</a></dd>
            <dd><a href="">电商平台</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item"><a href="">社区</a></li>
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
<div style="padding: 20px; background-color: #F2F2F2;">
    <span class="layui-breadcrumb">
        <a href="main.jsp">首页</a>
        <a href="wish-detail.jsp?page=1">查看愿望单</a>
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
                            <th>愿望名称</th>
                            <th>计划实现时间</th>
                            <th>预计花费</th>
                            <th>愿望状态</th>
                            <th>许愿时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                            <%
                                int RowCount=wishList.size();           //记录总数
                                int PageCount=(RowCount-1)/10+1;          //总页数
                                int Page=1;               //待显示页码
                                Page=Integer.parseInt(request.getParameter("page"));
                                int Num=1;//计数器
                                int statusNum;
                                String status;
                                for (wish e:wishList ) {
                                    if(Num>(Page-1)*10&&Num<=Page*10){
    %>
                        <tbody>
                        <tr>
                            <td><%=e.getWishName()%></td>
                            <td><%=e.getPlanTime()%></td>
                            <td><%=e.getMoney()%></td>
                            <td>
                                <%
                                    statusNum=e.getWishstatus();
                                    if(statusNum==0){
                                        status="待实现";
                                    }else if (statusNum==1){
                                        status="已实现";
                                    }else {
                                        status="已放弃";
                                    }
                                %>
                                <%=status%>
                            </td>
                            <td><%=e.getPushTime()%></td>
                            <td><a class="layui-btn layui-btn-sm layui-btn-normal" href="wish-change.jsp?wishid=<%=e.getWishId()%>&status=2">编辑</a><a class="layui-btn layui-btn-sm layui-btn-danger">删除</a></td>
                            <%--<td><%=Num%></td>--%>
                            <%}
                            Num++;
                            %>
                        </tr>
                        </tbody>
                        <%
                            }
                            if(wishList.isEmpty()){
                            String message="但是您暂时还没有愿望，创建一个吧！";
                        %>
                        <%=message%>
                        <%
                            }
                        %>
                    </table>
                    <div style="text-align: center;">
                        第<%=Page%>页 共<%=PageCount%>页

                        <% if(Page>1){ %>

                        <a href="wish-search-result.jsp?page=<%=Page-1%>" class="layui-btn">上一页</a>

                        <% }else{ %>
                        <a class="layui-btn">上一页</a>
                        <%}%>
                        <% if(Page<PageCount){ %>

                        <a href="wish-search-result.jsp?page=<%=Page+1%>" class="layui-btn">下一页</a>

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
</html>
