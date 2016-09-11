<%@page import="beans.ProveedorBean"%>
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
Vector<ProveedorBean> proveedores=(Vector<ProveedorBean>)request.getAttribute("proveedores");%>
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
            	<div class="col-sm-12"></div>
            	<div class="col-sm-12" style="text-align:left; padding-right: 5%; padding-left: 5%;">
                		<button class="btn-large btn btn-success" type="button" onClick="location.href='<%=request.getContextPath()%>/RegistrarProveedor'"><b>Registrar Proveedor</b></button>
                </div>
              	<div class="col-sm-12" style="text-align:center; padding-right: 4%; padding-left: 4%;">
                    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosColaborador" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;"> MANTENER PROVEEDORES</label>
                 	</div>
                    <%if(mensaje!=null){%>
						<div class="alert alert-danger"><%=mensaje%></div>
					<%}else{%>
					<form id="form" name="form" method="post" action="<%=request.getContextPath()%>/MantenerProveedores">
					<div class="col-sm-12">
					<br>
						<table id="tabla" class="table table-hover table-bordered">
	 						<thead>
	 							<tr class="btn-primary">
	 								<th style="text-align: center;" valign="middle">RUC del proveedor</th>
						 			<th style="text-align: center;" valign="middle">Raz&oacute;n Social</th>
						 			<th style="text-align: center;" valign="middle">Direcci&oacute;n</th>
						 			<th style="text-align: center;" valign="middle">Telefono</th>
						 			<th style="text-align: center;" valign="middle">Modificar</th>
						 			<th style="text-align: center;" valign="middle">Eliminar</th>
						 		</tr>
							</thead>
	 						<tbody>
			 					<% for(int i=0;i<proveedores.size();i++){%>	
			 					<tr>
			 						<td style="text-align: center; vertical-align:middle;"><%=proveedores.get(i).getIdProveedor()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=proveedores.get(i).getRazonSoc()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=proveedores.get(i).getDirecProve()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=proveedores.get(i).getTelefono()%></td>
						 			<td style="text-align: center; vertical-align:middle;">
						 				<button class="btn-large btn btn-info" type="button" onClick="location.href='<%=request.getContextPath()%>/ModificarProveedor?id=<%=proveedores.get(i).getIdProveedor()%>'"><b>Modificar</b></button>
						 			</td>
									<td style="text-align: center; vertical-align:middle;">
							 			<%if(proveedores.get(i).getEstado()==1){ %><button class="btn-large btn btn-info" type="button" onClick="location.href='<%=request.getContextPath()%>/EstadoProveedor?id=<%=proveedores.get(i).getIdProveedor()%>&estado=<%=proveedores.get(i).getEstado()%>'"><b>Bloquear</b></button><%}
					 					else{%><button class="btn-large btn btn-danger" type="button" onClick="location.href='<%=request.getContextPath()%>/EstadoProveedor?id=<%=proveedores.get(i).getIdProveedor()%>&estado=<%=proveedores.get(i).getEstado()%>'"><b>Habilitar</b></button> <%}%>
					 				</td>
							 		
		 						</tr>
					 			<%}%>
				 			</tbody>
				  		</table>
				  	<%if(mensaje1!=null){%>
						<div class="alert alert-success"><%=mensaje1%></div>
					<%}%>
					<%if(mensaje2!=null){%>
						<div class="alert alert-danger"><%=mensaje2%></div>
					<%}%>
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