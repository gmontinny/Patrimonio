<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>    
<input type="hidden" name="path" id="path" value="<%= request.getContextPath() %>"/>

    
	<div class="navbar navbar-inverse">
		<div class="navbar-header">
			<a class="navbar-brand" href="${linkTo[AdministrativoController].index()}">
				<img src="<%= request.getContextPath() %>/assets/images/logo.png" alt="">
				<span>PATRIMONIO</span>
			</a>

			<ul class="nav navbar-nav pull-right visible-xs-block">
				<li><a data-toggle="collapse" data-target="#navbar-mobile"><i class="icon-tree5"></i></a></li>
			</ul>
		</div>

		<div class="navbar-collapse collapse" id="navbar-mobile">
			<ul class="nav navbar-nav">
				<c:if test="${usuario.tipousuario == 2 }">
					<li class="dropdown active">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-stack2 position-left"></i> Cadastros <span class="caret"></span>
						</a>
	
						<ul class="dropdown-menu dropdown-menu-right">
							<c:forEach items="${menuList}" var="menu">
								<c:if test="${menu.tabela.tipotabela == 2}">
									<li><a href="javascript:" id="menu-${menu.tabela.urltabela}" onclick="cadastros('${menu.tabela.urltabela}');"><i class="${menu.tabela.icontabela}"></i> ${menu.tabela.desctabela}</a></li>
								</c:if>	
							</c:forEach>
						</ul>
					</li>
				</c:if>
				<c:if test="${usuario.tipousuario == 1 }">
					<li class="dropdown active">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-people position-left"></i> Administrador <span class="caret"></span>
						</a>
	
						<ul class="dropdown-menu dropdown-menu-right">
							<c:forEach items="${menuList}" var="menu">
								<c:if test="${menu.tabela.tipotabela == 1 }">
									<li><a href="javascript:" id="menu-${menu.tabela.urltabela}" onclick="cadastros('${menu.tabela.urltabela}');"><i class="${menu.tabela.icontabela}"></i> ${menu.tabela.desctabela}</a></li>
								</c:if>	
							</c:forEach>
						</ul>
					</li>
				</c:if>				
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown dropdown-user">
					<a class="dropdown-toggle" data-toggle="dropdown">
						<c:if test="${not empty usuario.imagemusuario}">
							<img src="${usuario.imagemusuario}" alt="">
						</c:if>
						<c:if test="${empty usuario.imagemusuario}">
							<img src="<%= request.getContextPath() %>/assets/images/placeholder.jpg" alt="">
						</c:if>
						<span>${usuario.nomusuario}</span>
						<i class="caret"></i>
					</a>

					<ul class="dropdown-menu dropdown-menu-right">
						<li><a href="javascript:" onclick="dadosUsuarios();"><i class="icon-user-plus"></i>Meu Cadastro</a></li>						
						<li class="divider"></li>
						<li><a href="<%= request.getContextPath() %>/autenticacao/logout"><i class="icon-switch2"></i> Logout</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>