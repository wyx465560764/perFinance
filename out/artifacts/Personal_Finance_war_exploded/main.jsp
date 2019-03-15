<%@ page import="DAO.viewWishDAO" %>
<%@ page import="entity.bill" %>
<%@ page import="entity.wish" %>
<%@ page import="entity.monthBill" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.viewBillDAO" %>
<%@ page import="DAO.halfYearBillDAO" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
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
  <script>
      //注意：导航 依赖 element 模块，否则无法进行功能性操作
      layui.use('element', function(){
          var element = layui.element;

          //…
      });
  </script>
    <div class="container-fluid">
        <div style="padding: 20px; background-color: #F2F2F2;">
            <div style="height: 30px">
                <span class="layui-breadcrumb">
                    <a><cite>首页</cite></a>
                </span>
            </div>
            <div class="layui-row layui-col-space15">
            <%
                try {
                    List<monthBill> monthBillList=halfYearBillDAO.getInstance().halfYearBill(session.getAttribute("userid").toString());
            %>
            <div class="layui-col-md6">
                <div class="layui-card">
                    <div class="layui-card-header"><strong style="font-size: 20px">近期消费情况（最大显示六个月，未记账月份不列出）</strong></div>
                    <div id="container" style="height: 300px"></div>
                    <script type="text/javascript" src="js/echarts.js"></script>
                    <script type="text/javascript">
                        var dom = document.getElementById("container");
                        var myChart = echarts.init(dom);
                        var app = {};
                        option = null;
                        option = {
                            xAxis: {
                                type: 'category',
                                boundaryGap: false,
                                data: [
                                <%
                                for(int i=0;i<6;i++){
                                    if (monthBillList.size()<i+1)break;
                        %>
                                    '<%=monthBillList.get(i).getMonth()%>',
                                    // 'Tue',
                                    // 'Wed',
                                    // 'Thu',
                                    // 'Fri',
                                    // 'Sat',
                                    // 'Sun'
                                    <%
                                }%>
                                ]


                                // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']

                            },
                            yAxis: {
                                type: 'value'
                            },
                            series: [{
                                data: [
                                    <%
                               for(int i=0;i<6;i++){

                                   if (monthBillList.size()<i+1){ break;}
                       %>
                                    '<%=monthBillList.get(i).getSum()%>',
                                    // 820,
                                    // 932,
                                    // 901,
                                    // 934,
                                    // 1290,
                                    // 1330,
                                    // 120,
                                    <%
                              }%>
                                ],
                                type: 'line',
                                areaStyle: {},
                                itemStyle : { normal: {label : {show: true}}}

                            }]
                        };
                        ;
                        if (option && typeof option === "object") {
                            myChart.setOption(option, true);
                        }
                    </script>
                    <%}catch (SQLException s){
                    s.printStackTrace();
                    }%>
                </div>
                </div>
                <div class="layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header"><strong style="font-size: 20px">近期新闻</strong></div>
                        <div class="layui-card-body">
                            结合 layui 的栅格系统<br>
                            轻松实现响应式布局
                        </div>
                    </div>
                </div>
            </div>
			<%--<div class="layui-carousel" id="carousel1">--%>
				<%--<div carousel-item>--%>
					<%--<div><img src="img/轮播图1.png" height="100%" width="100%"></div>--%>
					<%--<div><img src="img/轮播图2.png" height="100%" width="100%"></div>--%>
					<%--<div><img src="img/轮播图3.png" height="100%" width="100%"></div>--%>
				<%--</div>--%>
			<%--</div>--%>
			<!-- 条目中可以是任意内容，如：<img src=""> -->

			<script src="layui.js"></script>
			<script>
                layui.use('carousel', function(){
                    var carousel = layui.carousel;
                    //建造实例
                    carousel.render({
                        elem: '#carousel1'
                        ,width: '100%' //设置容器宽度
                        ,arrow: 'always' //始终显示箭头
                        //,anim: 'updown' //切换动画方式
                          ,height: '500px'
                         ,auto:true
                    });
                });
			</script>
			<div class="layui-tab layui-tab-card">
				<ul class="layui-tab-title">
					<li class="layui-this">愿望单</li>
					<li>消费/收入</li>
				</ul>
				<div class="layui-tab-content" style="height:100%;">
					<div class="layui-tab-item layui-show">
						<table class="layui-table" lay-skin="row">
							<thead>
							<tr>
								<th>
									编号
								</th>
								<th>
									愿望名称
								</th>
								<th>
									计划消费时间
								</th>
								<th>
									花费
								</th>
							</tr>
							</thead>
							<%
								try {
									List<wish> wishList= viewWishDAO.getInstance().selectByUser(session.getAttribute("userid").toString());
									int wishNum=1;
									for (wish e:wishList)
									{
							%>
							<tbody>
							<tr>
								<td>
									<%=wishNum%>
								</td>
								<td>
									<%=e.getWishName()%>
								</td>
								<td>
									<%=e.getPlanTime()%>
								</td>
								<td>
									<%=e.getMoney()%>
									<%++wishNum;%>
								</td>
							</tr>
							</tbody>
							<%
								}
								if(wishList.isEmpty()){
									String message="您暂时还没有记账，创建一个吧！";
							%>
							<%=message%>
							<%
									}
								}catch (SQLException s){
									s.printStackTrace();
								}
							%>
						</table>
						<%--<button class="layui-btn" href="index.jsp">管理愿望单</button>--%>
						<a href="wish-detail.jsp?page=1" class="layui-btn layui-btn-fluid">管理愿望单</a>
						<%--<button type="button" class="btn btn-success btn-block">--%>
						<%--管理愿望单--%>
						<%--</button>--%>
					</div>
					<div class="layui-tab-item">
						<table class="layui-table" lay-skin="row">
							<thead>
							<tr>
								<th>
									编号
								</th>
								<th>
									消费名称
								</th>
								<th>
									消费时间
								</th>
								<th>
									花费金额
								</th>
							</tr>
							</thead>
							<%
								try {
									List<bill> billList=viewBillDAO.getInstance().selectByUser(session.getAttribute("userid").toString());
									int billnum=1;
									for (bill b:billList){
							%>
							<tbody>
							<tr>
								<td>
									<%=billnum%>
								</td>
								<td>
									<%=b.getBillName()%>
								</td>
								<td>
									<%=b.getSpentTime()%>
								</td>
								<td>
									<%=b.getMoney()%>
								</td>
							</tr>
							</tbody>
							<%
									billnum++;
								}
								if (billList.isEmpty()){
									String billNull="还没有记账记录，去添加一个吧！";
							%>
							<%=billNull%>
							<%
									}
								}catch (SQLException s){
									s.printStackTrace();
								}
							%>
						</table>
						<a href="bill-detail.jsp?page=1" class="layui-btn layui-btn-fluid">管理账单</a>
					</div>
				</div>
            </div>
			<%--<div class="row">--%>
				<%--<div class="col-md-4">--%>
					<%--<h3>--%>
						<%--这是你最近消费愿望单--%>
					<%--</h3>--%>

				<%--</div>--%>
				<%--<!--<div class="col-md-1">-->--%>
				<%--<!--</div>-->--%>
				<%--<div class="col-md-6">--%>
					<%--<h3>--%>
						<%--这是您最近的消费账单--%>
					<%--</h3>--%>

				<%--</div>--%>
				<%--<div class="col-md-2">--%>
					<%--<h3>--%>
						<%--点击下方按钮开始记账--%>
					<%--</h3>--%>

					<%--&lt;%&ndash;<button type="button" class="btn btn-success btn-block">&ndash;%&gt;--%>
						<%--&lt;%&ndash;记账&ndash;%&gt;--%>
					<%--&lt;%&ndash;</button>&ndash;%&gt;--%>
				<%--</div>--%>
			<%--</div>--%>
    </div>
</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <%--<script src="js/scripts.js"></script>--%>
  </body>
</html>