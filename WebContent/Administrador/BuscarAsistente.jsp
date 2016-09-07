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
<%UsuarioBean usuario = (UsuarioBean)request.getAttribute("usus"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>

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
<jsp:include page="cabecera.jsp" flush="true"/>
<div class="container-fluid">
            <div class="row">
            	<div class="col-sm-12" style="text-align:left;">
                	<br>
                 </div>
                 
            	<div class="col-sm-12">
            		<br>
					<form method="post" action="<%=request.getContextPath()%>/modificarUsuario">
						<div class="row">
						
							<div class="col-md-6">
								<label for="buscaOrden" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">Datos Personales</label><br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" value="" >Código :</label> <input name="id" class="form-control" value="<%=usuario.getCodUsuario()%>" readonly="readonly"></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Nombre : </label> <input name="nombre" class="form-control" value="<%=usuario.getPersona().getNomPer() %>" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Apellido Paterno: </label> <input name="apepat" class="form-control"  value="<%=usuario.getPersona().getApePat() %>" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Apellido Materno : </label> <input name="apemat" class="form-control"  value="<%=usuario.getPersona().getApeMat() %>" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Dirección : </label> <input name="direccion" class="form-control" value="<%=usuario.getPersona().getDirecPer() %>"  required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Telefono : </label> <input name="telefono" class="form-control" value="<%=usuario.getPersona().getTelefono() %>" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Correo : </label> <input name="correo" class="form-control" value="<%=usuario.getPersona().getCorreo() %>" required></br>
							</div>
							<div class="col-md-6">
								<label for="buscaOrden" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">Datos de Usuario</label><br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Usuario : </label> <input name="usuario" class="form-control" value="<%=usuario.getUsuario() %>" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Sede : </label> <input name="sede" class="form-control" value="<%=usuario.getPersona().getSede().getNomSede() %>" readonly="readonly"></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Rol: </label> <input name="rol" class="form-control"  value="<%=usuario.getRol().getNomTipo() %>" readonly="readonly"></br>
							</div>
						</div>
						<button class="btn btn-primary" type="submit">Modificar</button>
							<button class="btn btn-danger" type="button" onclick="location.href='<%=request.getContextPath()%>/MantenerUsuario'">Cancelar</button>
					</form>
  				</div>
  				
			</div>
</div>
</body>
</html>
<%}%>