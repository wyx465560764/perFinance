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
                        <dd><a href="market-unfixedincome.jsp?type=债券型&page=1">债券型</a></dd>
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
                    <a><cite>基金详细</cite></a>
                </span>
            </div>
        </div>
        <%--<div style="height: 20%">&nbsp</div>--%>
        <fieldset class="layui-elem-field">
            <legend>基金详情</legend>
            <div class="layui-field-box">
                <div style="padding: 20px; background-color: #F2F2F2;">
                    <%
                        try {
                            List<earn> monthlist=unfixedIncomedetailDAO.getInstance().selectUnfixedIncome(Integer.valueOf(request.getParameter("productid")),1);
                    %>
                    <div class="layui-row layui-col-space15">
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
                                            最新基金规模(元)：<%=productDetail.getNowprice()*productDetail.getSum()%>
                                        </div>
                                        <div class="layui-col-xs3">
                                            最新单位净值(元)：<%=productDetail.getNowprice()%>
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
                                            预期年化收益率（仅供参考）：<%=productDetail.getExpectedincome()%>%
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
                        <div class="layui-col-md12">
                            <div class="layui-card">
                                <div class="layui-card-header">认购</div>
                                <script src="layui.js" charset="utf-8"></script>
                                <script>
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
                                        //自定义验证规则
                                        form.verify({
                                            contact: function(value){
                                                if(value><%=productDetail.getOver()*productDetail.getNowprice()%>){
                                                    return '超出可认购数量';
                                                }
                                            }
                                        });
                                    });
                                </script>
                                <form class="layui-form" action="submitorder" method="post">
                                    <div class="layui-card-body">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <label class="layui-form-label">投入资金</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="number" lay-verify="required|number|contact" autocomplete="off" class="layui-input" >
                                                </div>
                                                元
                                            </div>
                                        </div>
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <label class="layui-form-label">该账户实名认证身份证</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="identity" lay-verify="required|identity" autocomplete="off" class="layui-input">
                                                </div>
                                            </div>
                                            <a href="identityBinding.jsp" target="_blank">没有实名认证？立即实名认证</a>
                                        </div>
                                    </div>
                                    <input type="hidden" name="productid" value="<%=request.getParameter("productid")%>">
                                    <%--</div>--%>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button class="layui-btn layui-btn-danger" lay-submit="">立即提交</button>
                                        </div>
                                    </div>
                                </form>

                                <%--<a class="layui-btn layui-btn-fluid layui-btn-lg  layui-btn-radius">立即认购</a>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>