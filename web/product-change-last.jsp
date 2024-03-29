<%@ page import="entity.product" %>
<%@ page import="DAO.changeEarnDAO" %>
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
    <script src="layui.js"></script>
    <link rel="shortcut icon" href="/images/shortcuticon.ico" />
    <script src="layui.js"></script>
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
                    <a href="product-change.jsp?page=1">修改基金</a>
                    <a><cite>修改基金详情</cite></a>
                </span>
            </div>
        </div>
        <div style="height: 30px"></div>
        <fieldset class="layui-elem-field">
            <legend>修改基金</legend>
            <div class="layui-field-box">
                <div style="padding: 20px; background-color: #F2F2F2;">
                    <div class="layui-row layui-col-space15">
                        <div class="layui-col-md12">
                            <div class="layui-card">
                                <div class="layui-card-header">请再次确认信息</div>
                                <div class="layui-card-body">
                                    <%
                                        try {
                                            product product= changeEarnDAO.getInstance().changeEarnSelect(request.getParameter("productid"));

                                    %>
                                    <label style="">基金名称：<%=product.getProductname()%></label><br>
                                    <label style="">目前单份报价：<%=product.getNowprice()%></label><br>
                                    <label style="">基金类型：<%=product.getType()%></label><br>
                                    <label style="">收益类型：<%
                                        int statusNum=product.getEarntype();
                                        String status;
                                        if(statusNum==1){
                                            status="固收";
                                        }else if (statusNum==2){
                                            status="不定收益";
                                        }else {
                                            status="无法读取收益类型";
                                        }
                                    %>
                                        <%=status%></label><br>
                                    <label style="">剩余份数/总份数：<%=String.format("%.2f",product.getOver())%>/<%=String.format("%.2f",product.getSum())%></label><br>
                                </div>

                            </div>
                            <div class="layui-card">
                                <%--<div class="layui-card-header">请再次确认信息</div>--%>
                                <div class="layui-card-body">
                                    <script>
                                        //Demo
                                        layui.use(['form', 'layedit', 'laydate'], function(){
                                            var form = layui.form
                                                ,layer = layui.layer
                                                ,layedit = layui.layedit
                                                ,laydate = layui.laydate;
                                            //监听提交
                                            form.on('submit(demo1)', function(data){
                                                layer.alert(JSON.stringify(data.field), {
                                                    title: '最终的提交信息'
                                                })
                                                return false;
                                            });
                                            form.verify({
                                                min: function(value){
                                                    if(value<(<%=product.getSum()-product.getOver()%>)){
                                                        return '基金的最小份数不能小于已认购的份数';
                                                    }
                                                }
                                            });
                                        });
                                    </script>
                                    <form class="layui-form" action="changeproduct" method="post">
                                        <div class="layui-field-box">
                                            <div class="layui-form-item">
                                                <div class="layui-inline">
                                                    <label class="layui-form-label">基金名称</label>
                                                    <div class="layui-input-inline">
                                                        <input type="text" name="productname" lay-verify="required" autocomplete="off" class="layui-input" value="<%=product.getProductname()%>">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="layui-form-item">
                                                <div class="layui-inline">
                                                    <label class="layui-form-label">基金份数</label>
                                                    <div class="layui-input-inline">
                                                        <input type="text" name="sum" lay-verify="required|number|min" autocomplete="off" class="layui-input" value="<%=product.getSum()%>">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="layui-form-item  layui-form-text">
                                                <label class="layui-form-label">备注</label>
                                                <div class="layui-input-block">
                                                    <textarea class="layui-textarea" name="remark" lay-verify="required" autocomplete="off"><%=product.getRemark()%></textarea>
                                                </div>
                                            </div>
                                            <input type="hidden" name="productid" value="<%=request.getParameter("productid")%>">
                                            <input type="hidden" name="userid" value="<%=session.getAttribute("userid")%>">
                                            <div class="layui-form-item">
                                                <div class="layui-input-block">
                                                    <button class="layui-btn" lay-submit="">立即提交</button>
                                                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <%
                                        }catch (SQLException s){
                                            s.printStackTrace();
                                        }
                                    %>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </fieldset>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        投资有风险，理财需谨慎
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>
