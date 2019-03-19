<%@ page import="entity.bill" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.detailDAO" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: wyx
  Date: 2019/3/11
  Time: 19:46
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
    <%
        if(session.getAttribute("type").equals("1")){
    %>
    <li class="layui-nav-item"><a href="background.jsp">基金经理后台</a></li>
    <%
        }
    %>
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
    <div style="height: 30px">
        <span class="layui-breadcrumb">
            <a href="main.jsp">首页</a>
            <a><cite>查看账单</cite></a>
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
                <form class="layui-form" action="searchbill" method="post">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: 108px">备注关键词</label>
                            <div class="layui-input-block">
                                <input type="text" name="remark" required  lay-verify="required" autocomplete="off" class="layui-input" style="width: 200px" value="请输入关键词" onfocus="javascript:if(this.value=='请输入关键词')this.value='';" onblur="javascript:if(this.value=='')this.value='请输入关键词';">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: 95px">账单类型</label>
                            <div class="layui-input-block">
                                <select name="billname" lay-verify="required" style="width:100px">
                                    <option value=""></option>
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
                        <script>
                            layui.use('laydate', function(){
                                var laydate = layui.laydate;
                                //年月选择器
                                laydate.render({
                                    elem: '#billmonth'
                                    ,type: 'date'
                                    ,range: true
                                    ,calendar: true
                                });
                                laydate.render({
                                    elem: '#pushmonth'
                                    ,type: 'date'
                                    ,range: true
                                    ,calendar: true
                                });
                            });
                        </script>
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: 125px">消费/收入时间</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="billmonth" placeholder="-" name="billmonth">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width:95px">金额范围</label>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" name="price_min" placeholder="￥" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid">-</div>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" name="price_max" placeholder="￥" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width: 95px;height:35px">记账时间</label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="pushmonth" placeholder="-" name="pushmonth">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn layui-btn-lg">开始搜索</button>
                        </div>
                        <a class="layui-btn layui-btn-lg" style="float: right" href="bill-add.jsp">+记账</a>
                    </div>
                </form>

            </div>
        </div>
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
                try{
                    List<bill> billList=detailDAO.getInstance().selectBillByFirst(session.getAttribute("userid").toString());
                    int RowCount=billList.size();           //记录总数
                    int PageCount=(RowCount-1)/10+1;          //总页数
                    int Page;               //待显示页码
                    Page=Integer.parseInt(request.getParameter("page"));
                    int Num=1;//计数器
                    double Spent=0;
                    double Income=0;
                    for (bill e:billList)
                    {
                        if(e.getType()==1){
                            Income=e.getMoney()+Income;
                        }else {
                            Spent=e.getMoney()+Spent;
                        }
                        if(Num>(Page-1)*10&&Num<=Page*10){
            %>
            <tbody>
            <tr>
                <td><%=e.getBillName()%></td>
                <td><%=e.getSpentTime()%></td>
                <td><%=e.getMoney()%></td>
                <td><%=e.getPushTime()%></td>
                <td><%=e.getRemark()%></td>
                <td><a class="layui-btn layui-btn-sm layui-btn-normal" href="bill-change.jsp?billid=<%=e.getBillId()%>&status=1">编辑</a><a class="layui-btn layui-btn-sm layui-btn-danger" href="deletebill?billid=<%=e.getBillId()%>&status=1&page=<%=Page%>&num=<%=RowCount%>">删除</a></td>
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

            <a href="bill-detail.jsp?page=<%=Page-1%>" class="layui-btn">上一页</a>

            <% }else{ %>
            <a class="layui-btn">上一页</a>
            <%}%>
            <% if(Page<PageCount){ %>

            <a href="bill-detail.jsp?page=<%=Page+1%>" class="layui-btn">下一页</a>

            <% }else{ %>
            <a class="layui-btn">下一页</a>
            <%}%>
        </div>
    </div>
</div>
<ul class="layui-nav" style="position: fixed;bottom:0">
    <li class="layui-nav-item" style="float: right">你已经累计消费<%=Spent%>元，收入<%=Income%>元</li>
</ul>
<%
    }catch (SQLException s){
        s.printStackTrace();
    }
%>
</body>
</html>
