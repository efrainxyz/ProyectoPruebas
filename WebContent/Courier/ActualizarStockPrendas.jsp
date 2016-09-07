<%@page import="beans.SedeBean"%>
<%@page import="beans.SedeXTipoPrendaBean"%>
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
String mensaje3=(String)request.getAttribute("msj3");
Vector<SedeXTipoPrendaBean> prendasSede=(Vector<SedeXTipoPrendaBean>)request.getAttribute("prendasSede");
Vector<SedeXTipoPrendaBean> stockPrendas=(Vector<SedeXTipoPrendaBean>)request.getAttribute("stockPrendas");
Vector<TipoPrendaBean> tiposPrenda=(Vector<TipoPrendaBean>)request.getAttribute("tiposPrenda");
Vector<SedeBean> listaSedes=(Vector<SedeBean>)request.getAttribute("listaSedes");
int a=0;%>
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
<body style="background-image: url('images/02.png');background-repeat: no-repeat; background-position: center; background-position: right;">
    <div> 
        <jsp:include page="cabecera.jsp" flush="true"/>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12" style="text-align:center;">
                	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosReporte" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">ACTUALIZAR STOCK DE PRENDA</label>
                 	</div>
                	<div class="col-sm-12" style="text-align:center;">
                		<br>
                		<div class="col-sm-6" style="text-align: left;">
							<label for="fecha" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">Fecha:</label>
							<label for="fechaActual" class="col-sm-9 control-label" style="text-align:left; font-size: 20px;"><script type="text/javascript">fecha()</script></label>
						</div>
						<div class="col-sm-6" style="text-align: right;">
							<label for="fecha" class="col-sm-9 control-label" style="text-align: right; font-size: 20px;">Hora:</label>
							<label for="fechaActual" class="col-sm-3 control-label" style="text-align:left; font-size: 20px;"><script type="text/javascript">inicio()</script></label>
						</div>
                 	</div>
                 	<%if(mensaje1!=null){%>
                		<div class="col-sm-12 alert alert-danger"><%=mensaje1%></div>
                	<%}else{%>
                	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosStockTotal" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">STOCK TOTAL DE PRENDAS</label>
                 	</div>
                 	<div class="col-sm-12 form-group">
                 	<br>
						<table id="tabla" class="table table-hover table-bordered">
 						<thead>
 							<tr class="btn-primary">
 								<th style="text-align: center;" valign="middle">C&oacute;digo</th>
					 			<th style="text-align: center;" valign="middle">Prenda</th>
					 			<th style="text-align: center;" valign="middle">Cantidad Total</th>
					 		</tr>
						</thead>
 						<tbody>
		 					<% for(int i=0;i<stockPrendas.size();i++){%>	
		 					<tr>
					 			<td style="text-align: center; vertical-align:middle;"><%=stockPrendas.get(i).getIdTipoPrenda()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=stockPrendas.get(i).getTipoPrenda().getNomTip()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=stockPrendas.get(i).getCantidad()%></td>
					 		</tr>
				 			<%}%>
			 			</tbody>
  					</table>
				    </div>
                    <%if(mensaje2!=null){%>
						<div class="col-sm-12 form-group alert alert-success"><%=mensaje2%></div>
					<%}%>
				    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosSedeXPrenda" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">DATOS SEDE X PRENDA</label>
                 	</div>
				     <div class="col-sm-12 form-group">
				     <br>
				     	<table id="tabla" class="table table-hover table-bordered">
 						<thead>
 							<tr class="btn-primary">
 								<th style="text-align: center;" valign="middle">Sede/Prenda</th>
 								<% for(int i=0;i<tiposPrenda.size();i++){%>	
 									<th style="text-align: center;" valign="middle"><%=tiposPrenda.get(i).getNomTip()%></th>
 								<%}%>
					 			<th style="text-align: center;" valign="middle">Estado</th>
					 			<th style="text-align: center;" valign="middle">Acci&oacute;n</th>
					 		</tr>
						</thead>
 						<tbody>
		 					<% for(int i=0;i<listaSedes.size();i++){a+=4;%>
		 						
		 					<tr>
					 			<td style="text-align: center; vertical-align:middle;"><%=listaSedes.get(i).getNomSede()%></td>
					 			<% for(int j=0;j<tiposPrenda.size();j++){%>	
					 			<td style="text-align: center; vertical-align:middle;"><%=prendasSede.get(j).getCantidad()%></td>
					 			<%}%>
					 			<td style="text-align: center; vertical-align:middle;"><%if(prendasSede.get(i+a).getEstado().equals("1")){%>Pendiente<%}else{%>Entregado<%}%></td>
					 			<td style="text-align: center; vertical-align:middle;">
					 			
					 			<form method="post" action="<%=request.getContextPath()%>/ActualizarStockPrenda" onSubmit="if(!confirm('¿Esta seguro de realizar el cambio?')){return false;}">
					 				<button class="btn-large btn btn-primary" <%if(prendasSede.get(i+a).getEstado().equals("2")){%> disabled="disabled" <%}%> type="submit"><b>Actualizar</b></button>
					 				<input type="hidden" name="id" value="<%=listaSedes.get(i).getIdSede()%>">
					 			</form>
					 			
					 			</td>
					 		</tr>
				 			<%}%>
			 			</tbody>
  					</table>
				    </div>
				    <div  class="col-sm-12 form-group" style="text-align:center;">
                		<br>
                		<button class="btn-large btn btn-danger" type="button" onClick="location.href='<%=request.getContextPath()%>/Login'"><b>Salir</b></button>
                		<br>
                 	</div>
                 	<%if(mensaje!=null){%>
						<div class="col-sm-12 form-group alert alert-success"><%=mensaje%></div>
					<%}%>
					<%if(mensaje3!=null){%>
						<div class="col-sm-12 form-group alert alert-danger"><%=mensaje3%></div>
					<%}}%>
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