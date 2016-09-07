<%@page import="beans.PersonaBean"%>
<%@page import="beans.UsuarioBean"%>
<%@page import="java.util.Vector"%>
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
Vector<UsuarioBean> colaboradores=(Vector<UsuarioBean>)request.getAttribute("colaboradores");%>
 	<meta name="viewport" content="width=device-width" />
    <link rel="icon" href="<%=request.getContextPath()%>/images/bcp.ico">
    <title>Sistema de Vestimenta BCP</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/Bootstrap/css/dashboard.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.dataTables.css">
	<script src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/table.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css" type="text/css"/>
</head>
<body>
    <div> 
        <jsp:include page="cabecera.jsp" flush="true"/>
        <div class="container-fluid">
            <div class="row">
            	<div class="col-sm-12" style="text-align:right;">
                		<button class="btn-large btn btn-danger" type="button" onClick="location.href='<%=request.getContextPath()%>/Login'"><b>Salir</b></button>
                </div>
              	<div class="col-sm-12" style="text-align:center; padding-right: 4%; padding-left: 4%;">
                    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosColaborador" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;"> ACTUALIZAR CUENTAS TEMPORALES</label>
                 	</div>
                    <%if(mensaje!=null){%>
						<div class="alert alert-danger"><%=mensaje%></div>
					<%}else{%>
					<form id="form" name="form" method="post" action="<%=request.getContextPath()%>/MantenerCuentasTemporales">
					<div class="col-sm-12">
					<br>
						<table id="tabla" class="table table-hover table-bordered">
	 						<thead>
	 							<tr class="btn-primary">
	 								<th style="text-align: center;" valign="middle">
	 									<input onclick="chck();" type="checkbox" class="btn-large" value="" name="checkGeneral" id="checkGeneral">
	 									<script>
											function chck() {
												 if (document.getElementById('checkGeneral').checked){
													 var x = document.getElementsByName("ids");
													 var i;
													 for (i = 0; i < x.length; i++) {
													     if (x[i].type == "checkbox") {
													         x[i].checked = true;
													     }
													 }
												  } else {
													  var x = document.getElementsByName("ids");
													  var i;
													  for (i = 0; i < x.length; i++) {
														if (x[i].type == "checkbox") {
														   	x[i].checked = false;
														}
													  }
												  }
												
											}
										</script>
	 								</th> H = Habilitado ,  D = Desahabilitado
	 								<th style="text-align: center;" valign="middle">C&oacute;digo del colaborador</th>
						 			<th style="text-align: center;" valign="middle">DNI</th>
						 			<th style="text-align: center;" valign="middle">Nombre</th>
						 			<th style="text-align: center;" valign="middle">Apellido Paterno</th>
						 			<th style="text-align: center;" valign="middle">Apellido Materno</th>
						 			<th style="text-align: center;" valign="middle">Sexo</th>
						 			<th style="text-align: center;" valign="middle">Correo</th>
						 			<th style="text-align: center;" valign="middle">Sede</th>
						 			<th style="text-align: center;" valign="middle">Usuario</th>
						 			<th style="text-align: center;" valign="middle">Contrase&ntilde;a</th>
						 			<th style="text-align: center;" valign="middle">Estado</th>
						 			<th style="text-align: center;" valign="middle">Acci&oacute;n</th>
						 		</tr>
							</thead>
	 						<tbody>
			 					<% for(int i=0;i<colaboradores.size();i++){%>	
			 					<tr>
			 						<td style="text-align: center; vertical-align:middle;"><input type="checkbox" class="form-control" name="ids" value="<%=colaboradores.get(i).getPersona().getIdPersona()%>" id="checkIndividual"></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=colaboradores.get(i).getPersona().getIdPersona()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=colaboradores.get(i).getPersona().getDni()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=colaboradores.get(i).getPersona().getNomPer()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=colaboradores.get(i).getPersona().getApePat()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=colaboradores.get(i).getPersona().getApeMat()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%if(colaboradores.get(i).getPersona().getSexo().equals("M")){%>Masculino<%}else{%>Femenino<%}%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=colaboradores.get(i).getPersona().getCorreo()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=colaboradores.get(i).getPersona().getSede().getNomSede()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=colaboradores.get(i).getUsuario()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=colaboradores.get(i).getClave()%></td>
						 			<td style="text-align: center; vertical-align:middle;">
						 				<select size="1"  disabled="disabled" class="form-control">
						 					<option><%if(colaboradores.get(i).getEstadoUsu()==1){%>H<%}else{%>D<%}%></option>
						 				</select>
						 			</td>
						 			<td style="text-align: center; vertical-align:middle;">
						 				<button class="btn-large btn btn-info" type="button" onClick="location.href='<%=request.getContextPath()%>/ModificarColaborador?id=<%=colaboradores.get(i).getCodUsuario()%>'"><b>Modificar</b></button>
							 			<button class="btn-large btn btn-danger" type="button" onClick="if(confirm('¿Estas seguro de eliminar al colaborador?')){location.href='<%=request.getContextPath()%>/EliminarColaborador?id=<%=colaboradores.get(i).getCodUsuario()%>'}else{return false;}"><b>Eliminar</b></button>
							 		</td>
		 						</tr>
					 			<%}%>
				 			</tbody>
				  		</table>
				  	</div>
				  	<div class="col-sm-12" style="text-align:center;">
				  	<br>
                		<label for="mensajeEnviar" class="control-label" style="text-align: left; font-size: 20px; color: darkblue;">ACTIVAR CUENTAS TEMPORALES</label>
                		<button class="btn-large btn btn-primary" onclick="activar();" type="button"><b>Activar</b></button>
                		<script type="text/javascript">
                			function activar(){
                				document.getElementById('mensajeEnviar').style.display='block';
                			}
                			function desactivar(){
                				document.getElementById('mensajeEnviar').style.display='none';
                			}
                		</script>
                	<%if(mensaje1!=null){%>
						<div class="alert alert-success"><%=mensaje1%></div>
					<%}%>
					<%if(mensaje2!=null){%>
						<div class="alert alert-danger"><%=mensaje2%></div>
					<%}%>
                	</div>
                	<div class="col-sm-12" style="text-align:center; display: none" id="mensajeEnviar">
                		<br>
                		<div class="col-sm-12" style="text-align:center;">
	                		<label for="mensaje" class="col-sm-12 control-label" style="text-align: center; font-size: 20px; color: darkblue;">¿Por cuantos días estara activo las cuentas temporales?</label>
	                	</div>
                		<div class="col-sm-12" style="text-align:center;">
                			<div class="col-sm-4" style="text-align:right;">
                			</div>
	                		<div class="col-sm-4" style="text-align:right;">
	                			<input name="cantidadDias" required="required" type="number" min="1" max="30" class="form-control">
	                		</div>
                		</div>
                		<div class="col-sm-12" style="text-align:center;">
	                		<br>
	                		<button class="btn-large btn btn-primary" onclick="return validar();" type="submit"><b>Aceptar</b></button>
	                		<button class="btn-large btn btn-danger" onclick="desactivar();" type="button"><b>Cancelar</b></button>
	                		<br>
	                		<script>
								function validar() {
									var x = document.getElementsByName("ids");
									var i;
									for (i = 0; i < x.length; i++) {
										if (x[i].type == "checkbox") {
											if(x[i].checked){
												document.getElementById("errorEnviar").style.display='none';
									    		return true;
									    	}
									    }
									}
									document.getElementById("errorEnviar").style.display='block';
									return false;
								}
							</script>
                		</div>
                		<div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorEnviar">Seleccione algun colaborador</div>
                	</div>
                	</form>
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