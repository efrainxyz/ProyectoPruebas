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
String mensaje1=(String)request.getAttribute("msj1");
String mensaje2=(String)request.getAttribute("msj2");
Vector<PersonaBean> colaboradoresFaltantes=(Vector<PersonaBean>)request.getAttribute("colaboradoresFaltantes");%>
 	<meta name="viewport" content="width=device-width" />
    <link rel="icon" href="<%=request.getContextPath()%>/images/bcp.ico">
    <title>Sistema de Vestimenta BCP</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/Bootstrap/css/dashboard.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/Fecha.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.dataTables.css">
	<script src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/table.js"></script>
	<link href="<%=request.getContextPath()%>/css/calendario.css" type="text/css" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/calendar.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/calendar-es.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/calendar-setup.js" type="text/javascript"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css" type="text/css"/>
</head>
<body>
    <div> 
        <jsp:include page="cabecera.jsp" flush="true"/>
        <div class="container-fluid">
            <div class="row">
            	<div class="col-sm-12" style="text-align:right;">
                		<button class="btn-large btn btn-danger" type="button" onClick="location.href='<%=request.getContextPath()%>/Login'"><b>Salir</b></button>
                </div>
              	<div class="col-sm-12" style="text-align:right;">
			  		<ul class="nav nav-tabs">
			 	 		<li role="presentation"><a href="<%=request.getContextPath()%>/ListarTallasColaboradores">Completados</a></li>
			  			<li role="presentation" class="active"><a href="#">Faltantes</a></li>
					</ul>
				</div>
                <div class="col-sm-12" style="text-align:center; padding-right: 4%; padding-left: 4%;">
                    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosColaborador" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">LISTA DE COLABORADORES FALTANTES</label>
                 	</div>
                    <%if(mensaje!=null){%>
						<div class="alert alert-danger"><%=mensaje%></div>
					<%}else{%>
					<div class="col-sm-12">
					<br>
					<table id="tabla" class="table table-hover table-bordered">
 						<thead>
 							<tr class="btn-primary">
 								<th style="text-align: center;" valign="middle">C&oacute;digo de contrataci&oacute;n</th>
					 			<th style="text-align: center;" valign="middle">Nombre</th>
					 			<th style="text-align: center;" valign="middle">Apellido Paterno</th>
					 			<th style="text-align: center;" valign="middle">Apellido Materno</th>
					 		</tr>
						</thead>
 						<tbody>
		 					<% for(int i=0;i<colaboradoresFaltantes.size();i++){%>	
		 					<tr>
					 			<td style="text-align: center; vertical-align:middle;"><%=colaboradoresFaltantes.get(i).getIdPersona()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=colaboradoresFaltantes.get(i).getNomPer()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=colaboradoresFaltantes.get(i).getApePat()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=colaboradoresFaltantes.get(i).getApeMat()%></td>
					 		</tr>
				 			<%}%>
			 			</tbody>
  					</table>
  					</div>
  					<div class="col-sm-12" style="text-align:center;">
  						<br>
  						<form id="form" name="form" method="post" action="<%=request.getContextPath()%>/EnviarRecordatorio" onSubmit="if(!confirm('¿Estas seguro de enviar un recordatorio a todos los Colaboradores Pendientes?')){return false;}">
                		<label for="mensajeEnviar" class="control-label" style="text-align: left; font-size: 20px; color: darkblue;">MENSAJE RECORDATORIO</label>
                		<button class="btn-large btn btn-primary" type="submit"><b>Enviar</b></button>
                		</form>
                	</div>
                	<%if(mensaje1!=null){%>
						<div class="col-sm-12 form-group alert alert-success"><%=mensaje1%></div>
					<%}%>
					<%if(mensaje2!=null){%>
						<div class="col-sm-12 form-group alert alert-danger"><%=mensaje2%></div>
					<%}%>
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