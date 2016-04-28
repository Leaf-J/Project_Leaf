<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="masthead" style="margin-bottom: 10px;">
<%-- 	<div class="logo"
		style="background-image: url('${pageContext.request.contextPath}/images/leaf/common/clover.jpg')">
		<div style="float:right;margin:30 100;background:#ffffff">
			<a href="javascript:void(0);">登录</a> / <a href="javascript:void(0);">注册</a>
		</div>
	</div> --%>
	    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:400px;margin-top: 20%">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h3 class="modal-title" id="myModalLabel">
                        登录
                    </h3>
                </div>
                <div class="modal-body">
                    <div id="loginForm" class="form-horizontal" role="form" >
                        <div class="form-group" >
                            <label for="email" class="col-sm-3 control-label" style="font-weight: normal">邮箱</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="email" name="email"
                                       placeholder="请输入邮箱">
                            </div>
                            <div class="col-sm-3"></div>
                        </div>
                        <div class="form-group">
                            <label for="nickname" class="col-sm-3 control-label" style="font-weight: normal">密码</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="请输入密码">
                            </div>
                            <div class="col-sm-3"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-8">
                                <button name="submit" class="btn btn-success" style="width: 100%">登录</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
	    <div class="logo" >
        <div style="float:left;width: 500px;">
            <img src="${pageContext.request.contextPath}/images/leaf/common/clover_1.jpg" style="width: 100px;height: 101px;margin-left: 30px;"/><span style="font-size: 25px;font-family: Microsoft YaHei;color: #00c62d">幸运四叶草</span>
        </div>
        <div style="float:left;margin-top:30px;">
            <div class="input-group" style="width: 400px;">
                <input type="text" class="form-control"  placeholder="火影忍者">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">搜索</button>
      </span>
            </div>
        </div>
        <div style="float:left;margin-top:30px;">
            <div style="margin-left: 50px;margin-top:10px;font-size:17px;">
				<c:choose>
					<c:when test="${memberUser != null}">
						<span>欢迎，${memberUser.nickName }</span>
					</c:when>
					<c:otherwise>
		                <a href="javascript:void(0)" data-toggle="modal"
		                   data-target="" onclick="$('#myModal').modal()">登录</a> / <a href="${pageContext.request.contextPath}/front/register">注册</a>					
					</c:otherwise>
				</c:choose>
            </div>
        </div>
    </div>
	<div class="g_nav">
		<div class="g_nav_main">
			<ul class="nav nav-justified">
				<li class="active"><a href="${pageContext.request.contextPath }/front/index">首页</a></li>
				<li><a href="">电视剧</a></li>
				<li><a href="#">电影</a></li>
				<li><a href="#">综艺</a></li>
				<li><a href="${pageContext.request.contextPath }/front/album/animation">动漫</a></li>
				<li><a href="#">音乐</a></li>
				<li><a href="#">搞笑</a></li>
				<li><a href="#">游戏</a></li>
				<li><a href="#">时尚</a></li>
				<li><a href="#">体育</a></li>
			</ul>
		</div>
	</div>
</div>