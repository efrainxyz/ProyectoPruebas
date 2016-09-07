<%@page import="beans.OrdenCompraBean"%>
<%@page import="beans.UsuarioBean"%>
<%@page import="java.util.Vector"%>
<%@page import="beans.DetalleOrdenBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%UsuarioBean user=(UsuarioBean) session.getAttribute("usuario");%>
<%if(user==null){
	request.setAttribute("mensaje", "No inicio sesión");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}else{%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<%Vector<OrdenCompraBean> ordenes = (Vector<OrdenCompraBean> ) request.getAttribute("ordenes"); 
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
                	<label for="buscaOrden" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">LISTA DE ORDENES DE COMPRA</label>
                 </div>
                 <%if(mensaje!=null){%>
                	 <div class="alert alert-danger"><%=mensaje%></div>
                 <%}else{%>
            	<div class="col-sm-12">
            		<br>
					<table id="tabla" class="table table-hover table-bordered">
 						<thead>
 							<tr class="btn-primary">
 								<th style="text-align: center;" valign="middle">C&oacute;digo de Orden</th>
					 			<th style="text-align: center;" valign="middle">Fecha</th>
					 			<th style="text-align: center;" valign="middle">Monto Total</th>
					 			<th style="text-align: center;" valign="middle">Seleccionar</th>
					 		</tr>
						</thead>
 						<tbody>
		 					<% for(int i=0;i<ordenes.size();i++){%>	
		 					<tr>
					 			<td style="text-align: center; vertical-align:middle;"><%=ordenes.get(i).getIdOrdenCompra()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=ordenes.get(i).getFechEmision()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=ordenes.get(i).getMontoTotal()%></td>
					 			<td style="text-align: center; vertical-align:middle;">
					 			<button class="btn-large btn btn-primary" type="button" onClick="window.opener.document.all.idOrden.value='<%=ordenes.get(i).getIdOrdenCompra()%>';
  								window.opener.document.all.nroOrden.value='<%=ordenes.get(i).getIdOrdenCompra()%>';
  								window.opener.document.all.fecha.value='<%=ordenes.get(i).getFechEmision()%>';window.close()"><b>seleccionar</b></button>
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