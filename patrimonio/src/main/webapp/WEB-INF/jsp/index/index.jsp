<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Patrimonio</title>
	
	<jsp:include page="../../../includes/estilos.jsp"/>
	<jsp:include page="../../../includes/javascript.jsp"/>
	
	<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/cadastro/login.js"></script>
	<script>
		jQuery(document).ready(function() {
			Login.init();
		});   	
	</script>
</head>

<body class="login-container" onLoad="javascript:window.history.forward(0);" onpageshow="if (event.persisted) window.history.forward(0);">

	<!-- Main navbar -->
		<jsp:include page="../../../includes/navbar.jsp"/>
	<!-- /main navbar -->


	<!-- Page container -->
	<div class="page-container">

		<!-- Page content -->
		<div class="page-content">

			<!-- Main content -->
			<div class="content-wrapper">

				<!-- Content area -->
				<div class="content">

					<!-- Simple login form -->
						<div id="cadastro-login">
							<jsp:include page="login.jsp"/>
						</div>
					<!-- /simple login form -->
					
					<!-- Esqueceu Senha -->
						<div id="cadastro-esqueceu" style="display: none;">
							<jsp:include page="esqueceu.jsp"/>
						</div>
					<!-- /esqueceu senha -->

					<!-- Footer -->
						<jsp:include page="../../../includes/rodape.jsp"/>
					<!-- /footer -->

				</div>
				<!-- /content area -->

			</div>
			<!-- /main content -->

		</div>
		<!-- /page content -->

	</div>
	<!-- /page container -->

</body>
</html>
