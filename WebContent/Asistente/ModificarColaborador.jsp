<%@page import="beans.DetalleOrdenBean"%>
<%@page import="beans.ProveedorBean"%>
<%@page import="beans.PrendaBean"%>
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
UsuarioBean colaborador=(UsuarioBean)request.getAttribute("colaborador");%>
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
                <div class="col-sm-12" style="text-align:center;">
               	 	<div class="col-sm-12" style="text-align:right;">
                		<button class="btn-large btn btn-danger" type="button" onClick="location.href='<%=request.getContextPath()%>/MantenerCuentasTemporales'"><b>Salir</b></button>
                	</div>
                	<%if(mensaje!=null){%>
						<div class="col-sm-12 form-group alert alert-danger"><%=mensaje%></div>
					<%}else{%>
                 	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosColaborador" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">DATOS DEL COLABORADOR</label>
                 	</div>
                 	<form id="form" name="form" method="post" action="<%=request.getContextPath()%>/ModificarColaborador">
                    <div class="col-sm-12 form-group">
                    	<div class="col-sm-6" style="text-align: left;">
							<div class="col-sm-6" style="text-align: left;">
								<label for="codigoColaborador" class="col-sm-4 control-label" style="text-align: right; font-size: 20px;">C&oacute;digo del Colaborador:</label>
								<div class="col-sm-8" style="text-align: left;">
									<input class="form-control" readonly="readonly" name="id" type="text" value="<%=colaborador.getCodUsuario()%>">
								</div>
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							<div class="col-sm-6" style="text-align: left;">
								<label for="codigoColaborador" class="col-sm-4 control-label" style="text-align: right; font-size: 20px;">DNI:</label>
								<div class="col-sm-8" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="<%=colaborador.getPersona().getDni()%>">
								</div>
							</div>
						</div>
				    </div>
				    <div class="col-sm-12 form-group">
                    	<div class="col-sm-6" style="text-align: left;">
							<div class="col-sm-6" style="text-align: left;">
								<label for="codigoColaborador" class="col-sm-4 control-label" style="text-align: right; font-size: 20px;">Nombres:</label>
								<div class="col-sm-8" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="<%=colaborador.getPersona().getNomPer()%>">
								</div>
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							<div class="col-sm-6" style="text-align: left;">
								<label for="codigoColaborador" class="col-sm-4 control-label" style="text-align: right; font-size: 20px;">Apellido Paterno:</label>
								<div class="col-sm-8" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="<%=colaborador.getPersona().getApePat()%>">
								</div>
							</div>
						</div>
				    </div>
				    <div class="col-sm-12 form-group">
                    	<div class="col-sm-6" style="text-align: left;">
							<div class="col-sm-6" style="text-align: left;">
								<label for="codigoProveedor" class="col-sm-4 control-label" style="text-align: right; font-size: 20px;">Apellido Materno:</label>
								<div class="col-sm-8" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="<%=colaborador.getPersona().getApeMat()%>">
								</div>
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							<div class="col-sm-6" style="text-align: left;">
								<label for="codigoProveedor" class="col-sm-4 control-label" style="text-align: right; font-size: 20px;">Sexo:</label>
								<div class="col-sm-8" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="<%if(colaborador.getPersona().getSexo().equals("M")){%>Masculino<%}else{%>Femenino<%}%>">
								</div>
							</div>
						</div>
				    </div>
				    <div class="col-sm-12 form-group">
                    	<div class="col-sm-6" style="text-align: left;">
							<div class="col-sm-6" style="text-align: left;">
								<label for="codigoColaborador" class="col-sm-4 control-label" style="text-align: right; font-size: 20px;">Correo:</label>
								<div class="col-sm-8" style="text-align: left;">
									<input class="form-control" required="required" name="correoColaborador"  type="text" value="<%=colaborador.getPersona().getCorreo()%>">
								</div>
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							<div class="col-sm-6" style="text-align: left;">
								<label for="codigoColaborador" class="col-sm-4 control-label" style="text-align: right; font-size: 20px;">Sede:</label>
								<div class="col-sm-8" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="<%=colaborador.getPersona().getSede().getNomSede()%>">
								</div>
							</div>
						</div>
				    </div>
                	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosCuentaTemporal" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">CUENTA TEMPORAL</label>
                 	</div>
                 	<div class="col-sm-12 form-group">
                    	<div class="col-sm-4" style="text-align: left;">
							<div class="col-sm-12" style="text-align: left;">
								<label for="codigoColaborador" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Usuario:</label>
								<div class="col-sm-6" style="text-align: left;">
									<input class="form-control" required="required" name="usuarioColaborador"  type="text" value="<%=colaborador.getUsuario()%>">
								</div>
							</div>
						</div>
						<div class="col-sm-4" style="text-align: left;">
							<div class="col-sm-12" style="text-align: left;">
								<label for="codigoColaborador" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Contrase&ntilde;a:</label>
								<div class="col-sm-6" style="text-align: left;">
									<input class="form-control" required="required" name="claveColaborador" type="text" value="<%=colaborador.getClave()%>">
								</div>
							</div>
						</div>
						<div class="col-sm-4" style="text-align: left;">
							<div class="col-sm-12" style="text-align: left;">
								<label for="codigoColaborador" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Estado:</label>
								<div class="col-sm-6" style="text-align: left;">
									<select size="1" name="estadoColaborador" class="form-control" required="required">
											<option value="">Seleccionar</option>
						 					<option value="1" <%if(colaborador.getEstadoUsu()==1){%> selected="selected" <%}%>>Habilitado</option>
						 					<option value="0" <%if(colaborador.getEstadoUsu()==0){%> selected="selected" <%}%>>Deshabilitado</option>
						 			</select>
								</div>
							</div>
						</div>
				    </div>
                 	<div  class="col-sm-12 form-group" style="text-align:right;">
                		<br>
                			<button class="btn-large btn btn-primary" type="submit"><b>Aceptar</b></button>
                			<button class="btn-large btn btn-danger" type="button" onClick="location.href='<%=request.getContextPath()%>/MantenerCuentasTemporales'"><b>Regresar</b></button>
                 		<br>
                 	</div>
                 	</form>
                 	<%if(mensaje1!=null){%>
						<div class="col-sm-12 form-group alert alert-success"><%=mensaje1%></div>
					<%}%>
                 	<%if(mensaje2!=null){%>
						<div class="col-sm-12 form-group alert alert-danger"><%=mensaje2%></div>
					<%}%>
					<%}%>
                 </div>	
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