<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<input type="hidden" name="path" id="path" value="<%= request.getContextPath() %>"/>
	<div class="navbar navbar-inverse">
		<div class="navbar-header">
			<a class="navbar-brand" href="${linkTo[IndexController].index()}">
				<img src="<%= request.getContextPath() %>/assets/images/logo.png" alt="">
				<span>PATRIMONIO</span>
			</a>

			<ul class="nav navbar-nav pull-right visible-xs-block">
				<li><a data-toggle="collapse" data-target="#navbar-mobile"><i class="icon-tree5"></i></a></li>
			</ul>
		</div>

		<div class="navbar-collapse collapse" id="navbar-mobile">
			<ul class="nav navbar-nav navbar-right">

			</ul>
		</div>
	</div>    