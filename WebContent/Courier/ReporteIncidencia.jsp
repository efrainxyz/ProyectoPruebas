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
<%String mensaje1=(String)request.getAttribute("msj1");
String mensaje2=(String)request.getAttribute("msj2");
Vector<TipoPrendaBean> prendas=(Vector<TipoPrendaBean>)request.getAttribute("prendas");
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
                	<div class="col-sm-12" style="text-align:right;">
                		<button class="btn-large btn btn-danger" type="button" onClick="location.href='<%=request.getContextPath()%>/Login'"><b>Salir</b></button>
                 	</div>
                 	<%if(mensaje1!=null){%>
						<div class="col-sm-12 form-group alert alert-success"><%=mensaje1%></div>
					<%}%>
					
					<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosOrden" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">DATOS DE LA ORDEN DE COMPRA</label>
                 	</div>
                 	<form name="form1" action="<%=request.getContextPath()%>/BuscarOrden" method="get" target="Buscar Orden" onSubmit="window.open('','Buscar Orden','toolbar=no,location=no,menubar=no,scrollbars=yes,height=600,width=900')">
				    <div class="col-sm-12 form-group">
                 	<br>
                 		<div class="col-sm-6" style="text-align: left;">
							<label for="nroOrden" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Orden de compra N°:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="" name="idOrden" id="idOrden">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							<button class="btn-large btn btn-primary" type="submit"><b>Buscar Orden de Compra</b></button>
						</div>
				    </div>
				    <div class="col-sm-12 form-group">
                 	<br>
                 		<div class="col-sm-6" style="text-align: left;">
							<label for="fecha" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Fecha:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="" name="fecha" id="fecha">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							
						</div>
				    </div>
				    <div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorOrden">Busque la orden de compra la cual reportara la incidencia</div>
                 	</form>
                 	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosProveedor" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">DATOS DEL PROVEEDOR</label>
                 	</div>
                    <div class="col-sm-12 form-group">
						<div class="col-sm-6" style="text-align: left;">
							<label for="codigoProveedor" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">C&oacute;digo:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="" name="codigoProveedor" id="codigoProveedor">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							
						</div>
				    </div>
				    <div class="col-sm-12 form-group">
						<div class="col-sm-6" style="text-align: left;">
							<label for="razonSocial" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Raz&oacute;n Social:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="" name="razonSocial" id="razonSocial">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							
						</div>
				    </div>
				    <div class="col-sm-12 form-group">
						<div class="col-sm-6" style="text-align: left;">
							<label for="direccion" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Direcci&oacute;n:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="" name="direccion" id="direccion">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							
						</div>
				    </div>
                	
				    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosColaborador" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">DESCRIPCI&Oacute;N DE LA INCIDENCIA</label>
                 	</div>
                 	<div class="col-sm-12 form-group">
                 	<br>
                 		<div class="col-sm-4" style="text-align: left;">
							<label for="prenda" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Prenda:</label>
							<div class="col-sm-6" style="text-align: left;">
								<select class="form-control" size="1" name="prenda" id="prenda" required="required">
									<option value="">Seleccionar</option>
									<%for(int i=0;i<prendas.size();i++){%>
									<option value="<%=prendas.get(i).getIdTip()%>"><%=prendas.get(i).getNomTip()%></option>
									<%}%>
								</select>
							</div>
						</div>
						<div class="col-sm-4" style="text-align: left;">
							<label for="talla" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Talla:</label>
							<div class="col-sm-6" style="text-align: left;">
								<select class="form-control" size="1" name="talla" id="talla" required="required">
									<option value="">Seleccionar</option>
									<option value="S">S</option>
									<option value="M">M</option>
									<option value="L">L</option>
									<option value="XL">XL</option>
								</select>
							</div>
						</div>
						<div class="col-sm-4" style="text-align: left;">
							<label for="cantidad" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Cantidad:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" type="number" name="cantidad" id="cantidad" required="required" value="">
							</div>
						</div>
				    </div>
				    <div class="col-sm-12" style="text-align:left;">
                		
                		<label for="datosIncidencia" class="control-label" style="text-align: left; font-size: 20px; color: darkblue;">Descripci&oacute;n de la Incidencia</label>
                 	</div>
				    <div class="col-sm-12 form-group">
                 	<br>
                 		<textarea rows="5" cols="5" class="form-control" name="descripcion" id="descripcion"></textarea>
				    </div>
				    <div class="col-sm-12 form-group" style="text-align:left;">
                 	<br>
                 		<button class="btn-large btn btn-primary" onclick="insertarDetalle()" type="button"><b>Agregar</b></button>
                 		<script>
							function insertarDetalle() {
								if(document.getElementById("nroOrden").value==""){
									document.getElementById("errorOrden").style.display = "block";
									document.getElementById("errorAgregar").style.display = "none";
								}else if(document.getElementById("prenda").value==""||document.getElementById("prenda").value==""||document.getElementById("talla").value==""||document.getElementById("cantidad").value==""||document.getElementById("descripcion").value==""){
									document.getElementById("errorOrden").style.display = "none";
									document.getElementById("errorAgregar").style.display = "block";
								}else{
									document.getElementById("errorOrden").style.display = "none";
									document.getElementById("errorAgregar").style.display = "none";
									var boton = document.createElement("button");
									boton.type = "button";
									boton.className = "btn-large btn btn-danger";
									boton.innerHTML="X";
									boton.setAttribute("onClick", "eliminarDetalle(this)");
									
									var hddcod = document.createElement('input');
									hddcod.type='hidden';
									hddcod.name = 'codigoPrenda'; 
									hddcod.value= document.getElementById("prenda").value;
									
									var hddtalla = document.createElement('input');
									hddtalla.type='hidden';
									hddtalla.name = 'tallaPrenda'; 
									hddtalla.value= document.getElementById("talla").value;
									
									var hddcant = document.createElement('input');
									hddcant.type='hidden';
									hddcant.name = 'cantidadPrenda'; 
									hddcant.value= document.getElementById("cantidad").value;
									
									var hdddesc = document.createElement('input');
									hdddesc.type='hidden';
									hdddesc.name = 'descripcionIncidencia'; 
									hdddesc.value= document.getElementById("descripcion").value;
									
									var table = document.getElementById("tabla");
								    var row = table.insertRow();
								    var cell1 = row.insertCell(0);
								    var cell2 = row.insertCell(1);
								    var cell3 = row.insertCell(2);
								    var cell4 = row.insertCell(3);
								    var cell5 = row.insertCell(4);
								    if(document.getElementById("prenda").value=="1"){
								    	cell1.innerHTML = "Pantalon";
								    }else if(document.getElementById("prenda").value=="2"){
								    	cell1.innerHTML = "Camisa";
								    }else if(document.getElementById("prenda").value=="3"){
								    	cell1.innerHTML = "Saco";
								    }else if(document.getElementById("prenda").value=="4"){
								    	cell1.innerHTML = "Chompa";
								    }if(document.getElementById("prenda").value=="5"){
								    	cell1.innerHTML = "Pantalon Denim";
								    }
									cell1.appendChild(hddcod);
								    cell2.innerHTML = document.getElementById("talla").value;
								    cell2.appendChild(hddtalla);
								    cell3.innerHTML = document.getElementById("cantidad").value;
								    cell3.appendChild(hddcant);
								    cell4.innerHTML = document.getElementById("descripcion").value;
								    cell4.appendChild(hdddesc);
								    cell5.appendChild(boton);
								}
							}
							function eliminarDetalle(t){
								var td = t.parentNode;
						        var tr = td.parentNode;
						        var table = tr.parentNode;
						        table.removeChild(tr);
						    }
						</script>
				    </div>
				    <div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorAgregar">Error. Seleccione los campos de la incidencia para agregar</div>
                 	<form id="form" name="form" method="post" action="<%=request.getContextPath()%>/ReportarIncidencia">
				     <div class="col-sm-12 form-group">
				     <br>
					     <table id="tabla" class="table table-hover table-bordered">
 						<thead>
 							<tr class="btn-primary">
 								<th style="text-align: center;" valign="middle">Prenda</th>
					 			<th style="text-align: center;" valign="middle">Talla</th>
					 			<th style="text-align: center;" valign="middle">Cantidad</th>
					 			<th style="text-align: center;" valign="middle">Descripci&oacute;n de Incidencia</th>
					 			<th style="text-align: center;" valign="middle">Eliminar</th>
					 		</tr>
						</thead>
 						<tbody>
			 			</tbody>
				  		</table>
				    </div>
				   <div  class="col-sm-12 form-group" style="text-align:right;">
                		<input type="hidden" name="idProveedor" value="<%=proveedor.getIdProveedor()%>">
                		<input type="hidden" name="nroOrden" id="nroOrden" value="">
                		<br>
                			<button class="btn-large btn btn-primary" onclick="return validar(this.form)" type="submit"><b>Registrar Reporte de Incidencias</b></button>
                		<br>
                		<script>
							function validar(){
								var table = document.getElementById("tabla");
								var tam=table.rows.length;
								if(form.nroOrden.value==""||tam==1){
									document.getElementById("errorGenerar").style.display = "block";
									return false;
								}else{
									document.getElementById("errorGenerar").style.display = "none";
									return true;
								}
							}
						</script>
                 	</div>
                 	<div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorGenerar">Ingrese todos los campos</div>
                 	</form>
                 	<%if(mensaje2!=null){%>
						<div class="col-sm-12 form-group alert alert-danger"><%=mensaje2%></div>
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