<%@page import="beans.TipoPrendaBean"%>
<%@page import="java.util.Vector"%>
<%@page import="beans.UsuarioBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%UsuarioBean user=(UsuarioBean) session.getAttribute("usuario");%>
<%if(user==null){
	request.setAttribute("mensaje", "No inicio sesión");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}else{%>
<!DOCTYPE html>
<html>
<head>
<%String mensaje=(String)request.getAttribute("msj");%>
 	<meta name="viewport" content="width=device-width" />
    <link rel="icon" href="<%=request.getContextPath()%>/images/bcp.ico">
    <title>Sistema de Vestimenta BCP</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/Bootstrap/css/dashboard.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/Bootstrap/js/ie-emulation-modes-warning.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.dataTables.css">
	<script src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
</head>
<body style="background-image: url('images/02.png');background-repeat: no-repeat; background-position: center; background-position: right;">
    <div> 
        <jsp:include page="cabecera.jsp" flush="true"/>
        <div class="container-fluid">
            <div class="row">
            <form id="form" name="form" method="post" action="<%=request.getContextPath()%>/RegistrarIngresoOrden">
                <div class="col-sm-12" style="text-align:center;">
                	<div class="col-sm-12" style="text-align:right;">
                		<button class="btn-large btn btn-danger" type="button" onClick="location.href='<%=request.getContextPath()%>/Login'"><b>Salir</b></button>
                 	</div>
                 	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosColaborador" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">ACTUALIZAR ESTADO DE ORDEN DE COMPRA</label>
                 	</div>
                 	<div class="col-sm-12 form-group">
                 	<br>
                 		<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">N° Orden de Compra:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" name="id" type="text" value="">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							<input type="hidden" name="accion" value="ingresoOrden">
							<button class="btn-large btn btn-primary" type="submit"><b>Continuar</b></button>
                		</div>
				    </div>
					<%if(mensaje!=null){%>
						<div class="col-sm-12 form-group alert alert-danger"><%=mensaje%></div>
					<%}%>
 				 </div>
 				 </form>
            </div>
        </div>

        <!-- Bootstrap core JavaScript
    ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/ie10-viewport-bug-workaround.js"></script>
    </div>
</body>
</html>
<%}%>