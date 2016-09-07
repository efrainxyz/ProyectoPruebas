<%@page import="beans.OrdenCompraBean"%>
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
Vector<OrdenCompraBean> ordenesCompra=(Vector<OrdenCompraBean>)request.getAttribute("ordenesCompra");%>
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
            	<div class="col-sm-12" style="text-align:center; padding-right: 4%; padding-left: 4%;">
                    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosCompra" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">LISTAR HIST&Oacute;RICO DE COMPRAS</label>
                 	</div>
                    <%if(mensaje!=null){%>
						<div class="alert alert-danger"><%=mensaje%></div>
					<%}else{%>
					<div class="col-sm-12">
					<br>
					<table id="tabla" class="table table-hover table-bordered">
 						<thead>
 							<tr class="btn-primary">
 								<th style="text-align: center;" valign="middle">Nº de Orden</th>
					 			<th style="text-align: center;" valign="middle">Fecha de emisi&oacute;n</th>
					 			<th style="text-align: center;" valign="middle">Detalle</th>
					 		</tr>
						</thead>
 						<tbody>
		 					<% for(int i=0;i<ordenesCompra.size();i++){%>	
		 					<tr>
					 			<td style="text-align: center; vertical-align:middle;"><%=ordenesCompra.get(i).getIdOrdenCompra()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=ordenesCompra.get(i).getFechEmision()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><button class="btn-large btn btn-info" type="button" onClick="location.href='<%=request.getContextPath()%>/DetalleOrdenCompra?id=<%=ordenesCompra.get(i).getIdOrdenCompra()%>'"><b>Ver Detalle</b></button></td>
	 						</tr>
				 			<%}%>
			 			</tbody>
				  		</table>
				  		</div>
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