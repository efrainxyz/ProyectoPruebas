<%@page import="beans.ProveedorBean"%>
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
	<script src="<%=request.getContextPath()%>/js/Fecha.js"></script>
</head>
<body>
    <div> 
        <jsp:include page="cabecera.jsp" flush="true"/>
        <div class="container-fluid">
            <div class="row">
            	<div class="col-sm-12" style="text-align:center;">
                	<%if(mensaje1!=null){%>
						<div class="col-sm-12 form-group alert alert-success"><%=mensaje1%></div>
					<%}%>
					<form name="formBuscarP" action="<%=request.getContextPath()%>/ProveedorPedido" method="get" target="Buscar Prenda" onSubmit="window.open('','Buscar Prenda','toolbar=no,location=no,menubar=no,scrollbars=yes,height=600,width=900')">
                	<div class="col-sm-12" style="text-align:center;">
                		<br>
                		<div class="col-sm-3" style="text-align: left;">
							<label for="nroOrden" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;"></label>
							<div class="col-sm-6" style="text-align: left;">
							</div>
						</div>
						<div class="col-sm-9" style="text-align: right;">
							<label for="fecha" class="col-sm-9 control-label" style="text-align: right; font-size: 20px;">Fecha de Emisi&oacute;n:</label>
							<label for="fechaActual" class="col-sm-3 control-label" style="text-align:left; font-size: 20px;"><script type="text/javascript">fecha()</script></label>
						</div>
                 	</div>
                	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosProveedor" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">DATOS DEL PROVEEDOR</label>
                 	</div>
                 	<div class="col-sm-6" style="text-align:left;">
	                 	<div class="col-sm-12 form-group">
	                 	<br>
							<div class="col-sm-12" style="text-align: left;">
								<label for="codigoProveedor" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">C&oacute;digo:</label>
								<div class="col-sm-7" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" name="codigoProveedor" id="codigoProveedor">
								</div>
								<div class="col-sm-2" style="text-align: left;">
									<button class="btn-large btn btn-primary" type="submit"><b>Buscar Proveedor</b></button>
								</div>
							</div>	
						</div>
	                   <div class="col-sm-12 form-group">
							<div class="col-sm-12" style="text-align: left;">
								<label for="razonSocial" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">Raz&oacute;n Social:</label>
								<div class="col-sm-7" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" name="razonSocial" id="razonSocial">
								</div>
							</div>
					    </div>
					    <div class="col-sm-12 form-group">
							<div class="col-sm-12" style="text-align: left;">
								<label for="direccion" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">Direcci&oacute;n:</label>
								<div class="col-sm-7" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" name="direccion" id="direccion">
								</div>
							</div>
					    </div>
				    </div>
				    <div class="col-sm-5" style="text-align:right;">
				    	<img src="images/03.jpg" style="float:right;">
				    </div>
				    </form>
				    <form name="form1" action="<%=request.getContextPath()%>/PrendaPedido" method="get" target="Buscar Prenda" onSubmit="window.open('','Buscar Prenda','toolbar=no,location=no,menubar=no,scrollbars=yes,height=600,width=900')">
				    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosPrenda" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">DATOS DE LA PRENDA</label>
                 	</div>
                 	<div class="col-sm-6" style="text-align:left;">
	                 	<div class="col-sm-12 form-group">
	                 	<br>
							<div class="col-sm-12" style="text-align: left;">
								<label for="codigoPrenda" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">C&oacute;digo de Prenda:</label>
								<div class="col-sm-7" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="" name="codPrenda" id="codPrenda">
								</div>
								<div class="col-sm-2" style="text-align: left;">
									<button class="btn-large btn btn-primary" type="submit"><b>Buscar Prenda</b></button>
								</div>
							</div>
					    </div>
	                    <div class="col-sm-12 form-group">
							<div class="col-sm-12" style="text-align: left;">
								<label for="nombrePrenda" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">Nombre:</label>
								<div class="col-sm-7" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="" name="nomPrenda" id="nomPrenda">
								</div>
								<div class="col-sm-2" style="text-align: left;"></div>
							</div>
					    </div>
				    </div>
				    <div class="col-sm-6" style="text-align:left;"></div>
				    <div class="col-sm-8 form-group">
				    	<div class="col-sm-12" style="text-align: left;">
							<div class="col-sm-12" style="text-align: center;">
								<label for="talla" class="col-sm-2 control-label" style="text-align: right; font-size: 20px;">Talla:</label>
								<div class="col-sm-2" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="" name="tallaPrenda" id="prendaTalla">
								</div>
								<label for="cantidad" class="col-sm-2 control-label" style="text-align: right; font-size: 20px;">Cantidad:</label>
								<div class="col-sm-2" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="" name="cantidadPrenda" id="prendaCantidad">
								</div>
								<label for="precioUnitario" class="col-sm-2 control-label" style="text-align: right; font-size: 20px;">Precio Unitario:</label>
								<div class="col-sm-2" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="" name="precioUnitPrenda" id="precioUnitPrenda">
								</div>
							</div>
						</div>	
				    </div>
				    <div class="col-sm-8 form-group">
				    	<div class="col-sm-12" style="text-align: left;">
							<div class="col-sm-12" style="text-align: center;">
								<label for="precioTotal" class="col-sm-2 control-label" style="text-align: right; font-size: 20px;">Precio Total:</label>
								<div class="col-sm-3" style="text-align: left;">
									<input class="form-control" readonly="readonly" type="text" value="" name="precioTotalPrenda" id="precioTotalPrenda">
								</div>
							</div>
						</div>	
				    </div>
				    </form>
				    <div  class="col-sm-12 form-group" style="text-align:left;">
                		<br>
                		<button class="btn-large btn btn-primary" id="btnAgregar" onclick="insertarDetalle()" type="button"><b>Agregar Prenda</b></button>
                		<br>
                		<script>
							function insertarDetalle() {
								if(document.getElementById("codPrenda").value==""){
									document.getElementById("errorAgregar").style.display = "block";
								}else{
									
									var cod=document.getElementById("codPrenda").value;
									var nom=document.getElementById("nomPrenda").value;
									var tall=document.getElementById("prendaTalla").value;
									alert(cod+" "+nom+" "+tall);
									
									
									
									if(recorrerTabla(cod,tall)==true){
										
										alert("Usted ya agrego este producto");
									}else{
									document.getElementById("errorAgregar").style.display = "none";
									var boton = document.createElement("button");
									boton.type = "button";
									boton.className = "btn-large btn btn-danger";
									boton.innerHTML="X";
									boton.setAttribute("onClick", "eliminarDetalle(this)");
									
									var hddcod = document.createElement('input');
									hddcod.type='hidden';
									hddcod.name = 'codigoPrenda';
									hddcod.value= document.getElementById("codPrenda").value;
									
									var hddtalla = document.createElement('input');
									hddtalla.type='hidden';
									hddtalla.name = 'tallaPrenda'; 
									hddtalla.value= document.getElementById("prendaTalla").value;
									
									var hddcant = document.createElement('input');
									hddcant.type='hidden';
									hddcant.name = 'cantidadPrenda'; 
									hddcant.value= document.getElementById("prendaCantidad").value;
									
									var table = document.getElementById("tabla");
								    var row = table.insertRow();
								    var cell1 = row.insertCell(0);
								    var cell2 = row.insertCell(1);
								    var cell3 = row.insertCell(2);
								    var cell4 = row.insertCell(3);
								    var cell5 = row.insertCell(4);
								    var cell6 = row.insertCell(5);
								    var cell7 = row.insertCell(6);
								    cell1.innerHTML = document.getElementById("codPrenda").value;
								    cell1.appendChild(hddcod);
								    cell2.innerHTML = document.getElementById("nomPrenda").value;
								    cell3.innerHTML = document.getElementById("prendaTalla").value;
								    cell3.appendChild(hddtalla);
								    cell4.innerHTML = document.getElementById("prendaCantidad").value;
								    cell4.appendChild(hddcant);
								    cell5.innerHTML = document.getElementById("precioUnitPrenda").value;
								    cell6.innerHTML = document.getElementById("precioTotalPrenda").value;
								    cell7.appendChild(boton);
								    document.getElementById("montoTotal").value=parseFloat(document.getElementById("montoTotal").value.replace(",", "."))+parseFloat(document.getElementById("precioTotalPrenda").value.replace(",", "."));
									}
								}
							}
								function eliminarDetalle(t){
								var td = t.parentNode;
						        var tr = td.parentNode;
						        var table = tr.parentNode;
						        table.removeChild(tr);
						        document.getElementById("montoTotal").value=parseFloat(document.getElementById("montoTotal").value.replace(",", "."))-parseFloat(tr.cells[5].innerText.replace(",", "."));
							}
							
							function recorrerTabla(cod,tall){
								var tableReg = document.getElementById('tabla');
								
								var cellsOfRow="";
								var found=false;
								var compareWith="";
					 		
								
								// Recorremos todas las filas con contenido de la tabla
								for (var i = 1; i < tableReg.rows.length; i++){
										cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
										
										// Recorremos todas las celdas
										for (var j = 0; j < 3 && !found; j++)
										{
												alert("EL valor  "+cellsOfRow[j].innerHTML.toString());
											compareWith = cellsOfRow[j].innerHTML.toUpperCase();
											// Buscamos el texto en el contenido de la celda
											if ((compareWith.indexOf(tall) > -1) && (compareWith.indexOf(cod) > -1))
											{
												found = true;
											}
										}
								}
								
								return found;
							}
							
								
								
							
							
								
								
							
						</script>
                 	</div>
                 	<div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorAgregar">Buscar la prenda que desee agregar</div>
                 	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosPrenda" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">DETALLE DE LA ORDEN</label>
                 	</div>
                 	<form id="form" name="form" method="post" action="<%=request.getContextPath()%>/GenerarOrdenCompra">
				     <div class="col-sm-12 form-group">
				     <br>
				     	<table id="tabla" class="table table-hover table-bordered">
 						<thead>
 							<tr class="btn-primary">
 								<th style="text-align: center;" valign="middle">C&oacute;digo de Prenda</th>
					 			<th style="text-align: center;" valign="middle">Nombre</th>
					 			<th style="text-align: center;" valign="middle">Talla</th>
					 			<th style="text-align: center;" valign="middle">Cantidad</th>
					 			<th style="text-align: center;" valign="middle">Precio Unitario S/.</th>
					 			<th style="text-align: center;" valign="middle">Precio Total S/.</th>
					 			<th style="text-align: center;" valign="middle">Eliminar</th>
					 		</tr>
						</thead>
 						<tbody>
			 			</tbody>
				  		</table>
				    </div>
				    <div class="col-sm-12" style="text-align:right;">
                		<br>
                		<label for="montoTotal" class="col-sm-9 control-label" style="text-align: right; font-size: 30px; color: darkblue;">Monto Total: </label>
                		<div class="col-sm-3" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="0" name="montoTotal" id="montoTotal">
								<input type="hidden" name="idProveedor" id="idProveedor">
						</div>
                 	</div>
                 	<div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorGenerar">Usted debe agregar por lo menos una prenda para generar la orden.</div>
                 	<div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorGenerar2">Usted debe buscar al proveedor para poder generar la orden.</div>
                 	<div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorGenerar3">Usted debe buscar al proveedor y agregar por lo menos una prenda para poder generar la orden.</div>
				    <div  class="col-sm-12 form-group" style="text-align:right;">
                		<br>
                		 <div  class="col-sm-8 form-group" style="text-align:right;"></div>
                		<div  class="col-sm-2 form-group" style="text-align:center;"><button class="btn-large btn btn-primary" onclick="return validar(this.form)" type="submit"><b>Generar Orden</b></button></div>
                		<div  class="col-sm-2 form-group" style="text-align:center;"><button class="btn-large btn btn-danger" type="button" onClick="location.href='<%=request.getContextPath()%>/Login'"><b>Salir</b></button></div>
                		<br>
                		<script>
							function validar(){
								if(form.montoTotal.value=="0"&&form.idProveedor.value!=""){
									document.getElementById("errorGenerar").style.display = "block";
									document.getElementById("errorGenerar2").style.display = "none";
									document.getElementById("errorGenerar3").style.display = "none";
									return false;
								}else if(form.montoTotal.value=="0"&&form.idProveedor.value==""){
									document.getElementById("errorGenerar").style.display = "none";
									document.getElementById("errorGenerar2").style.display = "none";
									document.getElementById("errorGenerar3").style.display = "block";
									return false;
									
								} else if (form.montoTotal.value!="0"&&form.idProveedor.value==""){
									document.getElementById("errorGenerar").style.display = "none";
									document.getElementById("errorGenerar2").style.display = "block";
									document.getElementById("errorGenerar3").style.display = "none";
									return false;
									
								} else {
									document.getElementById("errorGenerar").style.display = "none";
									document.getElementById("errorGenerar2").style.display = "none";
									document.getElementById("errorGenerar3").style.display = "none";
									return true;
								}
							}
						</script>
						
                 	</div>
                 	<%if(mensaje2!=null){%>
						<div class="col-sm-12 form-group alert alert-danger"><%=mensaje2%></div>
					<%}%>
					</form>
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