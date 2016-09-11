<%@page import="beans.SedeBean"%>
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
<%
Vector<SedeBean> sede=(Vector<SedeBean>) request.getAttribute("sede");
String mensaje1=(String)request.getAttribute("msj1");
String mensaje2=(String)request.getAttribute("msj2");
%>
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
	<script> 
	function comprobarClave(){ 
		if(document.getElementById("pass2").value!=document.getElementById("pass1").value){
			document.getElementById('msj2').innerHTML = 'Las contraseñas no son iguales.';
			return false
		}
		return true
   	
	}
</script> 
	
</head>
<body alink="purple" vlink="darkblue">
<div class="container-fluid">
            <div class="row">
            <jsp:include page="cabecera.jsp" flush="true"/>
            	<div class="col-sm-12" style="text-align:left;">
                	<label for="agregarPrenda" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">Agregar Asistente</label>
                 </div>
                 
              
                 <%if(mensaje1!=null){%>
						<div class="col-sm-12">
							<div class="alert alert-danger"><%=mensaje1%></div>
						</div>
					<%}%>
					
            	<div class="col-sm-12">
            		<br>
            		
					<form name="form" method="post"  action="<%=request.getContextPath()%>/AgregarAsistente?accion=verificar" >
						<div class="row">
							<div class="col-md-12">
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Codigo de persona : </label> <input name="codusuario" class="form-control" required></br>
							</div>
						</div>
						<button class="btn btn-success" type="submit">Verificar</button></br>
				
					</form>
					<%if(mensaje2!=null){%>
					</br>
						<div class="col-sm-12">
							<div class="alert alert-success"><%=mensaje2%></div>
						</div>
					
					<form name="form1" method="post" onsubmit="return comprobarClave()" action="<%=request.getContextPath()%>/AgregarAsistente?accion=agregar" >
						<div class="row">
							<div class="col-md-12">
								<input name="codusuario2" class="hidden	" value="<%=request.getAttribute("codusuario") %>" class="form-control" readonly="readonly"></br>
								<label for="agregarAsistente" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">Datos de Usuario</label><br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Usuario : </label> <input name="usuario" class="form-control" value="<%=request.getAttribute("correo") %>" readonly="readonly"></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Contraseña : </label> <input name="clave" id="pass1" type="password" class="form-control" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Repetir Contraseña: </label> <input name="clave2" id="pass2" type="password" class="form-control" required></br>
								<label id="msj2" class="control-label" style="text-align: left; font-size: 15px; color: red;" ></label>
							</div>
						</div>
						<button class="btn btn-primary" type="submit">Agregar</button>
							<button class="btn btn-danger" type="button" onclick="location.href='<%=request.getContextPath()%>/MantenerAsistente'">Cancelar</button>
					</form>
  				</div>
  			<%} %>
			</div>
</div>
</body>
</html>
<%}%>