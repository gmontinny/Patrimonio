<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<% response.setStatus(404); %> 

<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Patrimonio</title>

	<jsp:include page="includes/estilos.jsp"/>
	<jsp:include page="includes/javascript.jsp"/>

</head>

<body>

	<!-- Main navbar -->
		<jsp:include page="includes/navbar.jsp"/>
	<!-- /main navbar -->


	<!-- Page container -->
	<div class="page-container login-container">

		<!-- Page content -->
		<div class="page-content">

			<!-- Main content -->
			<div class="content-wrapper">

				<!-- Content area -->
				<div class="content">

					<!-- Error wrapper -->
					<div class="container-fluid text-center">
						<h1 class="error-title">404</h1>
						<h6 class="text-semibold content-group">Oops, pagina não encontrada!</h6>

						<div class="row">
							<div class="col-lg-4 col-lg-offset-4 col-sm-6 col-sm-offset-3">
								<form action="#" class="main-search">
									<div class="row">
										<div class="col-sm-12">
											<a href="<%= request.getContextPath() %>/" class="btn btn-primary btn-block content-group"><i class="icon-circle-left2 position-left"></i> Retornar </a>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- /error wrapper -->


					<!-- Footer -->
						<jsp:include page="includes/rodape.jsp"/>
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
