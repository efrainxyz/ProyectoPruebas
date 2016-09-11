<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%String mensaje=(String)request.getAttribute("mensaje");%>
<meta charset="utf-8">
<meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible' />
<title>Sistema de Vestimenta BCP</title>
	
	<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	
	<!--[if IE 6]>
	<style type="text/css" media="screen">
			/*I don't feel like messing with pngs for this browser... sorry*/
			#gradient {display:none;}
	</style>
	<![endif]-->
	
	 <!-- Le styles -->
	 <link rel="icon" href="<%=request.getContextPath()%>/images/bcp.ico">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Bootstrap/css/typica-login.css">
</head>
<body>
	<div class="container">
		<div id="login-wraper">
        	<form class="login-form form-horizontal" action="<%=request.getContextPath()%>/Login" method="post">
            	<legend style="color: white;">Iniciar Sesi&oacute;n<span class="blue"></span></legend>
                <div class="body">
                	<div class="form-group">
                    	<label for="user" class="col-sm-5 control-label" style="color: white;">Usuario:</label>
                    	<div class="col-sm-7">
                    		<input type="text" id="user" class="form-control" name="user" placeholder="Ingrese su usuario" required="required">
                   		</div>
                   	</div>
                   	<div class="form-group">
	                    <label for="pass" class="col-sm-5 control-label" style="color: white;">Contrase&ntilde;a:</label>
	                    <div class="col-sm-7">
	                    	<input type="password" id="pass" class="form-control" name="pass" placeholder="Ingrese su contraseña" required="required">
	                	</div>
	                </div>
	                <div class="form-group">
	                	<button  class="btn-large clickSubmit btn btn-warning" type="submit">Ingresar</button>
	                </div>
	                <%if(mensaje!=null){%>
					<label style="color:red; font-size: 14px;"><b><%=mensaje%></b></label>
					<%}%>
                </div>
            </form>
        </div>
    </div>
		 <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/Bootstrap/js/backstretch.min.js"></script>
    <script src="<%=request.getContextPath()%>/Bootstrap/js/typica-login.js"></script>
		
</body>
</html>

<!-- ESTO ES UNA PRUEBA DE GITHUB--- PULL Y COMMIT -->
<!-- SOMOS DOMINGO CSMRR CEVICHE  !!!!!!!!!!!!- -->
<!-- tengo miedo hacer commit- -->
