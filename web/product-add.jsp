<%--
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
        <!-- 内容主体区域 -->
        <%--<div style="padding: 15px;">内容主体区域</div>--%>
        <div style="padding: 20px; background-color: #F2F2F2;">
            <div style="height: 30px">
                <span class="layui-breadcrumb">
                    <%--<a href="main.jsp">首页</a>--%>
                    <a href="background.jsp">基金经理后台</a>
                    <a><cite>发布基金</cite></a>
                </span>
            </div>
        </div>
        <script>
            //Demo
            layui.use('form', function(){
                var form = layui.form;

                //监听提交
                form.on('submit(demo1)', function(data){
                    layer.msg(JSON.stringify(data.field));
                    return false;
                });
                form.verify({
                    min: function(value){
                        if(value<0){
                            return '输入值必须大于0';
                        }
                    }
                });
            });
        </script>
        <div style="height: 30px"></div>
        <fieldset class="layui-elem-field">
            <legend>发布基金</legend>
            <form class="layui-form layui-form-pane" action="addproduct" method="post">
                <div class="layui-field-box">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">基金名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="productname" lay-verify="required" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">基金类型</label>
                            <div class="layui-input-block" style="width: auto;">
                                <select name="typeid" lay-filter="type" lay-verify="required">
                                    <option value=""></option>
                                    <option value="1">货币型</option>
                                    <option value="2">理财型</option>
                                    <option value="3">债券型</option>
                                    <option value="4">QDII</option>
                                    <option value="5">养老FOF</option>
                                    <option value="6">股票型</option>
                                    <option value="7">混合型</option>
                                    <option value="8">指数型</option>
                                </select>
                            </div>
                        </div>
                        <%--<div class="layui-inline">--%>
                            <%--<label class="layui-form-label">收益类型</label>--%>
                            <%--<div class="layui-input-block" style="width: auto;">--%>
                                <%--<select name="earntype" lay-filter="aihao" lay-verify="required">--%>
                                    <%--<option value=""></option>--%>
                                    <%--<option value="1">固收</option>--%>
                                    <%--<option value="2">不定收益</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">发行份数</label>
                        <div class="layui-input-inline">
                            <input type="text" name="sum" lay-verify="required|number|min" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="width: auto">每份初始价格</label>
                        <div class="layui-input-inline">
                            <input type="text" name="nowprice" lay-verify="required|number|min" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="width: auto">预计年化收益率</label>
                        <div class="layui-input-inline">
                            <input type="text" name="expectedincome" lay-verify="required|number" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-form-mid layui-word-aux">该输入框的单位已设定为%，无需再次输入%</div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                            <textarea name="remark" placeholder="请输入内容" class="layui-textarea" lay-verify="required"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit="">立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </div>
            </form>
        </fieldset>
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
</div>
</body>
</html>