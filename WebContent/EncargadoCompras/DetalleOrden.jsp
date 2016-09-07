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
Vector<DetalleOrdenBean> detalleorden=(Vector<DetalleOrdenBean>)request.getAttribute("detalleorden");
ProveedorBean proveedor=(ProveedorBean)request.getAttribute("proveedor");%>
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
                	<div class="col-sm-12"></div>
                	<div class="col-sm-12" style="text-align:right; padding-right: 5%; padding-left: 5%;">
                		<button class="btn-large btn btn-primary" type="button" onClick="location.href='<%=request.getContextPath()%>/HistorialCompras'"><b>Regresar</b></button>
           			</div>
                <%if(mensaje!=null){%>
                	<div class="col-sm-12 alert alert-danger"><%=mensaje%></div>
                <%}else{%>
                <div id="PrintArea">
                	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosColaborador" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">ORDEN DE COMPRA</label>
                 	</div>
                 	<div class="col-sm-12 form-group">
                 	<br>
                 		<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Orden de compra N°:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="<%=detalleorden.get(0).getOrdenCompra_idOrdenCompra()%>">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Fecha:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="<%=detalleorden.get(0).getOrden().getFechEmision()%>">
							</div>
						</div>
				    </div>
				    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosProveedor" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">PROVEEDOR</label>
                 	</div>
                    <div class="col-sm-12 form-group">
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">C&oacute;digo:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="<%=proveedor.getIdProveedor()%>">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							
						</div>
				    </div>
				    <div class="col-sm-12 form-group">
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Raz&oacute;n Social:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="<%=proveedor.getRazonSoc()%>">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							
						</div>
				    </div>
				    <div class="col-sm-12 form-group">
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Direcci&oacute;n:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="<%=proveedor.getDirecProve()%>">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							
						</div>
				    </div>
				    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="tallasUniformes" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">DETALLE DE LA ORDEN</label>
                 	</div>
				     <div class="col-sm-12 form-group">
				     <br>
					     <table id="tabla" class="table table-hover table-bordered">
	 						<thead>
	 							<tr class="btn-primary">
	 								<th style="text-align: center;" valign="middle">C&oacute;digo de Prenda</th>
						 			<th style="text-align: center;" valign="middle">Nombre</th>
						 			<th style="text-align: center;" valign="middle">Talla</th>
						 			<th style="text-align: center;" valign="middle">Cantidad</th>
						 			<th style="text-align: center;" valign="middle">Precio Unitario</th>
						 			<th style="text-align: center;" valign="middle">Precio Total</th>
						 		</tr>
							</thead>
	 						<tbody>
			 					<% for(int i=0;i<detalleorden.size();i++){%>	
			 					<tr>
						 			<td style="text-align: center; vertical-align:middle;"><%=detalleorden.get(i).getIdTipoPrenda()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=detalleorden.get(i).getTipoPrenda().getNomTip()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=detalleorden.get(i).getTallaPrenda()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=detalleorden.get(i).getCantidad()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=detalleorden.get(i).getPrecioUnit()%></td>
						 			<td style="text-align: center; vertical-align:middle;"><%=detalleorden.get(i).getPrecioTotal()%></td>
						 		</tr>
					 			<%}%>
				 			</tbody>
	  					</table>
				    </div>
				    <div class="col-sm-12" style="text-align:right;">
                		<label for="montoTotal" class="col-sm-11 control-label" style="text-align: right; font-size: 20px; color: darkblue;">Monto Total: </label>
                		<div class="col-sm-1" style="text-align: left;">
								<input class="form-control" style="text-align: right;" readonly="readonly" type="text" value="<%=detalleorden.get(0).getOrden().getMontoTotal()%>" name="montoTotal" id="montoTotal">
						</div>
                 	</div>
                 	</div>
				    <div  class="col-sm-12 form-group" style="text-align:center;">
                		<br>
                		<button id="imprime" class="btn-large btn btn-primary"><b>Imprimir</b></button>
                		<br>
                		<script src='<%=request.getContextPath()%>/js/impresion/jquery.PrintArea.js'></script>
				    	<script type="text/javascript">
							$(document).ready(function() {
							    $("#imprime").click(function () {
							    	$("div#PrintArea").printArea();
							    })
							});
						</script>
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