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
<%String mensaje=(String)request.getAttribute("msj");
String mensaje1=(String)request.getAttribute("msj1");
String mensaje2=(String)request.getAttribute("msj2");
Vector<ProveedorBean> proveedor=(Vector<ProveedorBean>)request.getAttribute("proveedor");%>
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
            <div class="col-sm-12"></div>
            <div class="col-sm-12" style="text-align:right; padding-right: 5%; padding-left: 5%;">
                		<button class="btn-large btn btn-primary" type="button" onClick="location.href='<%=request.getContextPath()%>/MantenerProveedores'"><b>Regresar</b></button>
            </div>
            <form id="form" name="form" method="post" action="<%=request.getContextPath()%>/RegistrarProveedor">
            	<div class="col-sm-12" style="text-align:center;">
                	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosProveedor" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">REGISTRAR A NUEVO PROVEEDOR</label>
                 	</div>
                 	<div class="col-sm-5" style="text-align:left;">
	                 	<div class="col-sm-12 form-group">
	                 	<br>
							<div class="col-sm-12" style="text-align: left;">
								<label for="codigoProveedor" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">RUC :<label style="color: red;">*</label></label>
								<div class="col-sm-9" style="text-align: left;">
									<input class="form-control" type="text" name="ruc" id="ruc"  placeholder="Ingrese el RUC del proveedor">
								</div>
							</div>	
						</div>
	                   <div class="col-sm-12 form-group">
							<div class="col-sm-12" style="text-align: left;">
								<label for="razonSocial" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">Raz&oacute;n Social:<label style="color: red;">*</label></label>
								<div class="col-sm-9" style="text-align: left;">
									<input class="form-control" type="text" name="razonSoc" id="razonSoc" maxlength="45" placeholder="Ingrese la raz&oacute;n social del proveedor">
								</div>
							</div>
					    </div>
					    <div class="col-sm-12 form-group">
							<div class="col-sm-12" style="text-align: left;">
								<label for="direccion" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">Direcci&oacute;n:<label style="color: red;">*</label></label>
								<div class="col-sm-9" style="text-align: left;">
									<input class="form-control" type="text" name="direccion" id="direccion" maxlength="45" placeholder="Ingrese la direcci&oacute;n del proveedor">
								</div>
							</div>
					    </div>
					    <div class="col-sm-12 form-group">
							<div class="col-sm-12" style="text-align: left;">
								<label for="direccion" class="col-sm-3 control-label" style="text-align: right; font-size: 20px;">Telefono:<label style="color: red;">*</label></label>
								<div class="col-sm-9" style="text-align: left;">
									<input class="form-control" type="text" name="telefono" id="telefono" maxlength="7" placeholder="Ejemplo: 541-4509">
								</div>
							</div>
					    </div>
				    </div>
				    <div class="col-sm-7" style="text-align:right;">
				    	<img src="<%=request.getContextPath()%>/images/03.jpg" style="float:right;">
				    </div>
				    <div  class="col-sm-12 form-group" style="text-align:left;">
                		<label for="aviso" class="col-sm-6 control-label"  style="color: red;">Es obligatorio llenar los campos (*)</label>
                 	</div>
				    <div  class="col-sm-12 form-group" style="text-align:center;">
                		<br>
                		<button class="btn-large btn btn-primary" onclick="return validar(this.form)" type="submit"><b>Registrar</b></button>
                		<br>
                 	</div>
                 	<div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorRegistrar">El RUC del proveedor debe tener 11 dígitos.</div>
                 	<div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorRegistrar2">Usted debe completar todos los campos.</div>
                 	<div class="col-sm-12 form-group alert alert-danger" style="display: none;" id="errorRegistrar3">Usted debe ingresar un telefono valido.</div>
                 	<%if(mensaje1!=null){%>
						<div class="col-sm-12 form-group alert alert-success"><%=mensaje1%></div>
					<%}%>
					<%if(mensaje2!=null){%>
						<div class="col-sm-12 form-group alert alert-danger"><%=mensaje2%></div>
					<%}%>
					<script>
					function validar(){
						if(form.ruc.value=="" || form.razonSoc.value=="" || form.direccion.value=="" || form.telefono.value==""){
							document.getElementById("errorRegistrar").style.display = "none";
							document.getElementById("errorRegistrar2").style.display = "block";
							document.getElementById("errorRegistrar3").style.display = "none";
							
							return false;
															
						} else if(form.ruc.value<=10000000000 || form.ruc.value>=99999999999){
							document.getElementById("errorRegistrar").style.display = "block";
							document.getElementById("errorRegistrar2").style.display = "none";
							document.getElementById("errorRegistrar3").style.display = "none";
							
							return false;
						} else if(form.telefono.value<=2000000 || form.telefono.value>=9999999){
							
							document.getElementById("errorRegistrar").style.display = "none";
							document.getElementById("errorRegistrar2").style.display = "none";
							document.getElementById("errorRegistrar3").style.display = "block";
							return false;
						}else {
							
							return true;
						}
						
					}
							
					</script>
				 </div>
 				 </form>
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