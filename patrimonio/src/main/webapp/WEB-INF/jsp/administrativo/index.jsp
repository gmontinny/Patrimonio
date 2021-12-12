<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/cadastro/admin.js"></script>
	<script>
		jQuery(document).ready(function() {
			Admin.init();
		});   	
	</script>

</head>

<body class="navbar-bottom" onLoad="javascript:window.history.forward(0);" onpageshow="if (event.persisted) window.history.forward(0);">

	<!-- Main navbar -->
		<jsp:include page="../../../includes/navAdmin.jsp"/>
	<!-- /main navbar -->



	<!-- Page container -->
	<div class="page-container">

		<!-- Page content -->
		<div class="page-content">

		</div>
		<!-- /page content -->

	</div>
	<!-- /page container -->


	<!-- Footer -->
		<jsp:include page="../../../includes/rodapeAdmin.jsp"/>
	<!-- /footer -->

</body>
</html>

    