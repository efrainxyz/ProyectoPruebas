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
<%Vector<UsuarioBean> usuarios=(Vector<UsuarioBean>)request.getAttribute("usuarios");%>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/table.js"></script>
	
</head>
<body style="background-image: url('images/02.png');background-repeat: no-repeat; background-position: center; background-position: right;">
    <div> 
        <jsp:include page="cabecera.jsp" flush="true"/>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12" style="text-align:center;padding-right: 4%; padding-left: 4%;">
                 	<div class="col-sm-12" style="text-align:center;padding-right: 4%; padding-left: 4%;">
                		<br><br>
                		<div id="header"><h1>DATOS PERSONALES</h1></div>
                 	</div>
                 	<div class="col-sm-12 form-group">
                 	<br>
                 	<br>
						<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 35px;">DNI:</label>
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="control-label" style="font-size: 35px;"><%=user.getPersona().getDni()%></label>
						</div>
				    </div>
                    <div class="col-sm-12 form-group">
						<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 35px;">Nombres:</label>
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="control-label" style="font-size: 35px;"><%=user.getPersona().getNomPer()%></label>
						</div>
				    </div>
				     <div class="col-sm-12 form-group">
						<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 35px;">Apellidos:</label>
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="control-label" style="font-size: 35px;"><%=user.getPersona().getApePat()+" "+user.getPersona().getApeMat()%></label>
						</div>
				    </div>
				    <div class="col-sm-12 form-group">
						<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 35px;">Direcci&oacute;n:</label>
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="control-label" style="font-size: 35px;"><%=user.getPersona().getDirecPer()%></label>
						</div>
				    </div>
				     <div class="col-sm-12 form-group">
						<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 35px;">Correo:</label>
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="control-label" style="font-size: 35px;"><%=user.getPersona().getCorreo()%></label>
						</div>
				    </div>
				    <div class="col-sm-12 form-group">
						<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 35px;">Tel&eacute;fono:</label>
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="control-label" style="font-size: 35px;"><%=user.getPersona().getTelefono()%></label>
						</div>
				    </div>
 				 </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript
    ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/ie10-viewport-bug-workaround.js"></script>
        <script src="<%=request.getContextPath()%>/js/ModalAdministradores.js"></script>
    </div>
</body>
</html>
<%}%>