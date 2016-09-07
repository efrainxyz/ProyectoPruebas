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
	<script type="text/javascript">
function Validar()
{
if(document.form.sexo[0].checked==false && document.form.sexo[1].checked==false){
	 document.getElementById('msj').innerHTML = 'Debe seleccionar el sexo'; 	 	
     	return false;
  }
if(document.getElementById("pass2").value!=document.getElementById("pass1").value){
	document.getElementById('msj2').innerHTML = 'Las contraseñas no son iguales.';
	return false
}

if(document.getElementById("sel").value=0){
	document.getElementById('msj3').innerHTML = 'Debe asignar una sede.';
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
                	<label for="agregarPrenda" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">Agregar Prenda</label>
                 </div>
                 
              
               
            	<div class="col-sm-12">
            		<br>
					<form name="form" method="post" onSubmit="return Validar()" action="<%=request.getContextPath()%>/AgregarAsistente" >
						<div class="row">
						
							<div class="col-md-6">
								<label for="buscaOrden" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">Datos Personales</label><br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Dni : </label> <input name="dni" class="form-control" type="number" ></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Nombre : </label> <input name="nombre" class="form-control" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Apellido Paterno: </label> <input name="apepat" class="form-control" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Apellido Materno : </label> <input name="apemat" class="form-control"   required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Sexo : </label> 
									<label class="checkbox-inline" required>
									  <input type="radio" name="sexo" value="M">Hombre
									</label>
									<label class="checkbox-inline">
									  <input type="radio" name="sexo" value="F">Mujer
									</label><br>
									<label id="msj" class="control-label" style="text-align: left; font-size: 15px; color: red;" ></label>
									<br><br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Dirección : </label> <input name="direccion" class="form-control"  required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Telefono : </label> <input name="telefono" class="form-control" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Correo : </label> <input name="correo" class="form-control" required></br>
							</div>
							<div class="col-md-6">
								<label for="agregarAsistente" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">Datos de Usuario</label><br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Sede : </label> 
								<label id="msj3" class="control-label" style="text-align: left; font-size: 15px; color: red;" ></label>
										<select id="opcion" name="sede" class="form-control" required>
										<option id="sel" value="0">Seleccione..</option>
										
										<%for(int i=0;i<sede.size();i++){ %>
										  <option  value="<%=sede.get(i).getIdSede()%>" ><%=sede.get(i).getNomSede() %></option>
										  <%} %>
										</select></br>
							
										</br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Usuario : </label> <input name="usuario" class="form-control" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Contraseña : </label> <input name="clave" id="pass1" type="password" class="form-control" required></br>
								<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Repetir Contraseña: </label> <input name="clave" id="pass2" type="password" class="form-control" required></br>
								<label id="msj2" class="control-label" style="text-align: left; font-size: 15px; color: red;" ></label>
							</div>
						</div>
						<button class="btn btn-primary" type="submit">Agregar</button>
							<button class="btn btn-danger" type="button" onclick="location.href='<%=request.getContextPath()%>/MantenerAsistente'">Cancelar</button>
					</form>
  				</div>
  			
			</div>
</div>
</body>
</html>
<%}%>