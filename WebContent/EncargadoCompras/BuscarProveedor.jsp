<%@page import="beans.UsuarioBean"%>
<%@page import="java.util.Vector"%>
<%@page import="beans.ProveedorBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%UsuarioBean user=(UsuarioBean) session.getAttribute("usuario");%>
<%if(user==null){
	request.setAttribute("mensaje", "No inicio sesión");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}else{%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<%Vector<ProveedorBean> proveedor = (Vector<ProveedorBean> ) request.getAttribute("proveedor"); 
String mensaje=(String)request.getAttribute("msj");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar Prenda</title>
	<link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/Bootstrap/css/dashboard.css" rel="stylesheet">
    <link rel="icon" href="<%=request.getContextPath()%>/images/bcp.ico">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.dataTables.css">
	<script src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/table.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css" type="text/css"/>
</head>
<body alink="purple" vlink="darkblue">
<div class="container-fluid">
            <div class="row">
            	<div class="col-sm-12" style="text-align:left;">
                	<label for="buscaPrenda" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">LISTA DE PROVEEDORES</label>
                 </div>
                  <%if(mensaje!=null){%>
                	 <div class="alert alert-danger"><%=mensaje%></div>
                 <%}else{%>
            	<div class="col-sm-12">
            		<br>
					<table id="tabla" class="table table-hover table-bordered">
 						<thead>
 							<tr class="btn-primary">
 								<th style="text-align: center;" valign="middle">C&oacute;digo de Proveedor</th>
					 			<th style="text-align: center;" valign="middle">Raz&oacute;n Social</th>
					 			<th style="text-align: center;" valign="middle">Direcci&oacute;n</th>
					 			<th style="text-align: center;" valign="middle">Telefono</th>
					 			<th style="text-align: center;" valign="middle">Seleccionar</th>
					 		</tr>
						</thead>
 						<tbody>
		 					<% for(int i=0;i<proveedor.size();i++){%>	
		 					<tr>
					 			<td style="text-align: center; vertical-align:middle;"><%=proveedor.get(i).getIdProveedor()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=proveedor.get(i).getRazonSoc()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=proveedor.get(i).getDirecProve()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=proveedor.get(i).getTelefono()%></td>
					 			<td style="text-align: center; vertical-align:middle;">
					 			<button class="btn-large btn btn-primary" type="button" onClick="window.opener.document.all.codigoProveedor.value='<%=proveedor.get(i).getIdProveedor()%>';
  								window.opener.document.all.razonSocial.value='<%=proveedor.get(i).getRazonSoc()%>';
  								window.opener.document.all.direccion.value='<%=proveedor.get(i).getDirecProve()%>';
  								window.opener.document.all.idProveedor.value='<%=proveedor.get(i).getIdProveedor()%>';window.close()"><b>seleccionar</b></button>
					 			</td>
					 		</tr>
				 			<%}%>
			 			</tbody>
  					</table>
  				</div>
  				<%}%>
			</div>
</div>
</body>
</html>
<%}%>