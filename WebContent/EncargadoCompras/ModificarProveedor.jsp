<%@page import="beans.ProveedorBean"%>
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
<%String mensaje=(String)request.getAttribute("msj");
String mensaje1=(String)request.getAttribute("msj1");
String mensaje2=(String)request.getAttribute("msj2");
ProveedorBean proveedor=(ProveedorBean) request.getAttribute("proveedor");%>
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
	<script src="<%=request.getContextPath()%>/js/Fecha.js"></script>
</head>
<body>
    <div> 
        <jsp:include page="cabecera.jsp" flush="true"/>
        <div class="container-fluid">
            <div class="row">
            <div class="col-sm-12"></div>
            <div class="col-sm-12" style="text-align:right; padding-right: 5%; padding-left: 5%;">
                		<button class="btn-large btn btn-primary" type="button" onClick="location.href='<%=request.getContextPath()%>/MantenerProveedores'"><b>Regresar</b></button>
            </div>
            <form id="form" name="form" method="post" action="<%=request.getContextPath()%>/ModificarProveedor">
            	<div class="col-sm-12" style="text-align:center;">
                	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosProveedor" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">MODIFICAR PROVEEDOR</label>
                 	</div>
                 	<div class="col-sm-5" style="text-align:left;">
	                 	<div class="col-sm-12 form-group">
	                 	<br>
							<div class="col-sm-12" style="text-align: left;">
								<label for="codigoProveedor" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">RUC:</label>
								<div class="col-sm-9" style="text-align: left;">
									<input class="form-control" type="number" min="0" name="id" readonly value="<%=proveedor.getIdProveedor()%>">
								</div>
							</div>	
						</div>
	                   <div class="col-sm-12 form-group">
							<div class="col-sm-12" style="text-align: left;">
								<label for="razonSocial" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">Raz&oacute;n Social:</label>
								<div class="col-sm-9" style="text-align: left;">
									<input class="form-control" type="text" name="rsMod" maxlength="45" required value="<%=proveedor.getRazonSoc()%>">
								</div> 
							</div>
					    </div>
					    <div class="col-sm-12 form-group">
							<div class="col-sm-12" style="text-align: left;">
								<label for="direccion" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">Direcci&oacute;n:</label>
								<div class="col-sm-9" style="text-align: left;">
									<input class="form-control" type="text" name="dirMod" maxlength="45" required value="<%=proveedor.getDirecProve()%>">
								</div>
							</div>
					    </div>
					    <div class="col-sm-12 form-group">
							<div class="col-sm-12" style="text-align: left;">
								<label for="direccion" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">Telefono:</label>
								<div class="col-sm-9" style="text-align: left;">
									<input class="form-control" type="text" name="telMod" maxlength="13" required value="<%=proveedor.getTelefono()%>">
								</div>
							</div>
					    </div>
				    </div>
				    <div class="col-sm-7" style="text-align:right;">
				    	<img src="<%=request.getContextPath()%>/images/03.jpg" style="float:right;">
				    </div>
				    <div  class="col-sm-12 form-group" style="text-align:center;">
                		<br>
                		<button class="btn-large btn btn-primary" type="submit"><b>Modificar</b></button>
                		<br>
                 	</div>
                 	<%if(mensaje1!=null){%>
						<div class="col-sm-12 form-group alert alert-success"><%=mensaje1%></div>
					<%}%>
					<%if(mensaje2!=null){%>
						<div class="col-sm-12 form-group alert alert-danger"><%=mensaje2%></div>
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