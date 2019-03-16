<%@ page import="DAO.detailDAO" %>
<%@ page import="entity.wish" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wyx
  Date: 2019/3/9
  Time: 17:11
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


<div style="padding: 20px; background-color: #F2F2F2;">
    <div style="height: 30px">
        <span class="layui-breadcrumb">
            <a href="main.jsp">首页</a>
            <a><cite>查看愿望单</cite></a>
        </span>
    </div>
    <div class="layui-col-md12">
        <div class="layui-card">
            <blockquote class="layui-elem-quote">提示：将搜索条件空置可以忽视该搜索条件</blockquote>
            <div class="layui-card-body">
                <script>
                    layui.use('form', function(){
                        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
                        form.render();
                    });
                </script>
                <form class="layui-form" action="searchwish" method="post">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: 108px">愿望关键词</label>
                            <div class="layui-input-block">
                                <input type="text" name="wishname" required  lay-verify="required" autocomplete="off" class="layui-input" style="width: 200px" value="请输入关键词" onfocus="javascript:if(this.value=='请输入关键词')this.value='';" onblur="javascript:if(this.value=='')this.value='请输入关键词';">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: 95px">愿望状态</label>
                            <div class="layui-input-block">
                                <select name="status" lay-verify="required" style="width:100px">
                                    <option value=""></option>
                                    <option value="0">未实现</option>
                                    <option value="1">已实现</option>
                                    <option value="2">已放弃</option>
                                </select>
                            </div>
                        </div>
                        <script>
                            layui.use('laydate', function(){
                                var laydate = layui.laydate;
                                //年月选择器
                                laydate.render({
                                    elem: '#planmonth'
                                    ,type: 'date'
                                    ,range: true
                                    ,calendar: true
                                });
                                laydate.render({
                                    elem: '#wishmonth'
                                    ,type: 'date'
                                    ,range: true
                                    ,calendar: true
                                });
                            });
                        </script>
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: 120px">计划实现时间</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="planmonth" placeholder="-" name="planmonth">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: 90px">金额范围</label>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" name="price_min" placeholder="￥" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid">-</div>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" name="price_max" placeholder="￥" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: 95px">许愿时间</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="wishmonth" placeholder="-" name="wishmonth">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn layui-btn-lg">开始搜索</button>
                        </div>
                        <%--<div class="layui-card-body">--%>
                            <a class="layui-btn layui-btn-lg" style="float: right" href="wish-add.jsp">+添加愿望</a>
                        <%--</div>--%>
                    </div>
                </form>
            </div>
        </div>
        <script>
            //注意：导航 依赖 element 模块，否则无法进行功能性操作
            layui.use('element', function(){
                var element = layui.element;

                //…
            });
        </script>

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
                try {
                    List<wish> wishList= detailDAO.getInstance().selectWishByFirst(session.getAttribute("userid").toString());
                    int RowCount=wishList.size();           //记录总数
                    int PageCount=(RowCount-1)/10+1;          //总页数
                    int Page=1;               //待显示页码
                    Page=Integer.parseInt(request.getParameter("page"));
                    int Num=1;//计数器
                    String status;
                    int statusNum;
                    for (wish e:wishList)
                    {
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
                <td><a class="layui-btn layui-btn-sm layui-btn-normal" href="wish-change.jsp?wishid=<%=e.getWishId()%>&status=1">编辑</a><a class="layui-btn layui-btn-sm layui-btn-danger">删除</a></td>
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

            <a href="wish-detail.jsp?page=<%=Page-1%>" class="layui-btn">上一页</a>

            <% }else{ %>
            <a class="layui-btn">上一页</a>
            <%}%>
            <% if(Page<PageCount){ %>

            <a href="wish-detail.jsp?page=<%=Page+1%>" class="layui-btn">下一页</a>

            <% }else{ %>
            <a class="layui-btn">下一页</a>
            <%}%>
        </div>
        <%
            }catch (SQLException s){
                s.printStackTrace();
            }
        %>
    </div>
</div>
</body>
</html>
