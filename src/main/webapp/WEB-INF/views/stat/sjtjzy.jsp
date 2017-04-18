<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>数据统计主页</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/stat/css/sjtj.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/bootstrap/css335/bootstrap.min.css">
</head>
<style>
.icon_jian{ font-family:simsun;}
a{color:#000;text-decoration:none;}
</style>
<body>
<div class="iphone">
	<div class="WZY_top01">
    	<div class="row">
          <div class="col-sm-12" style="position:relative;">
            <img src="<%=path%>/resources/img/wzy/logo.png" class="img-responsive">
           <a href="<%=path%>/wfw/zy/zhuye?openId=${openId}"><img style="top:18%;right:20px;" class="Home_btn" 
            	src="<%=path%>/resources/img/wzy/FH.png" width="34" height="34"></a>
          </div>
    	</div>
    </div>
    <div class="New_main01">
    	<div class="container">
            <section style=" display:block">
            <table class="table table-striped" style=" margin-top:20px;">
            	<tr>
                    <td>
                    <a href="<%=path%>/stat/echarts/yxbl?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-log-in" style="color:#F4541F"></span>
                       	     &nbsp;迎新办理情况统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              			</a>
                    </td>
                </tr>
                <tr>
                    <td>
                      <a href="<%=path%>/stat/echarts/lxbl?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-send" style="color:#F4870D"></span>
                       	     &nbsp;离校办理情况统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              		  </a>
                    </td>
                </tr>
               <tr>
                    <td>
                      <a href="<%=path%>/stat/echarts/teacher1?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-education" style="color:#F0CC0F"></span>
                       	     &nbsp;院系师资统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              		  </a>
                    </td>
                </tr>
               <tr>
                    <td>
                      <a href="<%=path%>/stat/echarts/yxbjs?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-duplicate" style="color:#B5EB0E"></span>
                       	     &nbsp;院系班级数量统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              		  </a>
                    </td>
                </tr>
                 <tr>
                    <td>
                      <a href="<%=path%>/stat/echarts/yxbj?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-stats" style="color:#50EF04"></span>
                       	     &nbsp;院系专业班级人数统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              		   </a>
                    </td>
                </tr>
               <tr>
                    <td>
                       <a href="<%=path%>/stat/echarts/fjxsz?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-knight" style="color:#0BEDA4"></span>
                       	     &nbsp;非教学单位及师资统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              			</a>
                    </td>
                </tr>
                <tr>
                    <td>
                     <a href="<%=path%>/stat/echarts/xygk?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-copy" style="color:#10B7E9"></span>
                       	     &nbsp;各学院挂科人数统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              			</a>
                    </td>
                </tr>
                <tr>
                    <td>
                      <a href="<%=path%>/stat/echarts/zygk?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-open-file" style="color:#3D9DF7"></span>
                       	     &nbsp;各专业挂科人数统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              			</a>
                    </td>
                </tr>
                <tr>
                    <td>
                      <a href="<%=path%>/stat/echarts/kskc?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-import" style="color:#3D77FC"></span>
                       	     &nbsp;各院系开设课程统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              		   </a>
                    </td>
                </tr>
                <tr>
                    <td>
                     <a href="<%=path%>/stat/echarts/jszj?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-saved" style="color:#B80DEF"></span>
                       	     &nbsp;教职工—在借图书统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              		   </a>
                    </td>
                </tr>
                 <tr>
                    <td>
                      <a href="<%=path%>/stat/echarts/xszj?openId=${openId}">
                    	<div class="col-sm-12">
                        	<div class="row">
                              <div class="col-xs-10">
                              	<span class="glyphicon glyphicon-record" style="color:#F81B90"></span>
                       	     &nbsp;学生—在借图书统计
                              </div>
                              <div class="col-xs-1">
                              	<span><small class="icon_jian">></small></span>
                              </div>
                            </div>
              			</div>
              		   </a>
                    </td>
                </tr>
            </table>
            </section>
          <%--   <div class="row">
              <div class="col-sm-12" style="text-align:center">
                <a href="<%=path%>/wfw/zy/zhuye?openId=${openId}"><button class="btn btn-success">
               			 返回
                </button></a>
              </div>
            </div> --%>
        </div>
    </div>
    
    <div class="WZY_foot01" style="height:30px;">
    	<div class="row">
          <div class="col-sm-12">
            <img src="<%=path%>/resources/img/wzy/BQ.png" class="img-responsive">
          </div>
    	</div>
    </div>
</div>
<script>
	
</script>
</html>