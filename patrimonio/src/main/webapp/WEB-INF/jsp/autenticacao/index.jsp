<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patrimonio</title>
<link href="<%= request.getContextPath() %>/assets/css/preloader.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/core/libraries/jquery.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/preload/preloader.js"></script>
</head>
<body onLoad="javascript:window.history.forward(0);" onpageshow="if (event.persisted) window.history.forward(0);">
	<div id="preloader">    		
		<div class="container">
        	<span class="lodingmessage">Aguarde enquanto redireciona</span> 
            <form name="frm_preloader" id="frm_preloader" action="<%= request.getContextPath() %>/administrativo/index" method="post">
            <input type="hidden" name="opcao" id="opcao"/>
        	<div class="default"><div class="change" style="width: 0%;"></div></div>
            </form>
        </div>
    </div>    
</body>
</html>