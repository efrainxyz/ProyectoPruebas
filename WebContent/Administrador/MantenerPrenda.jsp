<%@page import="beans.PersonaBean"%>
<%@page import="beans.UsuarioBean"%>
<%@page import="beans.TipoPrendaBean"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%UsuarioBean user=(UsuarioBean) session.getAttribute("usuario");%>
<%if(user==null){
	request.setAttribute("mensaje", "No inicio sesi�n");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}else{%>
<!DOCTYPE html>
<html>
<head>
<%String mensaje=(String)request.getAttribute("msj");
String mensaje2=(String)request.getAttribute("msj2");
Vector<TipoPrendaBean> prendas=(Vector<TipoPrendaBean>)request.getAttribute("prendas");
%>
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
            	
              	
                <div class="col-sm-12" style="text-align:center; padding-right: 4%; padding-left: 4%;">
                    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosColaborador" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">LISTA DE TIPOS DE PRENDA</label>
                 	</div>
                 <%if(mensaje!=null){%>
						<div class="col-sm-12">
							<div class="alert alert-success"><%=mensaje%></div>
						</div>
					<%}if(mensaje2!=null){%>
						<div class="col-sm-12">
							<div class="alert alert-danger"><%=mensaje2%></div>
						</div>
					<%} %>
						
						
					
					<br>
					<table id="tabla" class="table table-hover table-bordered">
					
 						<thead>
 							<tr class="btn-primary">
 								<th style="text-align: center;" valign="middle">C&oacute;digo de Prenda</th>
					 			<th style="text-align: center;" valign="middle">Nombre</th>
					 			<th style="text-align: center;" valign="middle">Descripcion</th>
					 			<th style="text-align: center;" valign="middle">Modificar</th>
					 			<th style="text-align: center;" valign="middle">Estado</th>
					 			
					 			
					 		</tr>
						</thead>
 						<tbody>
		 					<%for(int i=0;i<prendas.size();i++){%>
		 					<tr>
					 			<td style="text-align: center; vertical-align:middle;"><%=prendas.get(i).getIdTip()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=prendas.get(i).getNomTip()%></td>
					 			<td style="text-align: center; vertical-align:middle;"><%=prendas.get(i).getDescripcion()%></td> 
					 			<td style="text-align: center; vertical-align:middle;"><button class="btn-large btn btn-succes" type="button" onClick="location.href='<%=request.getContextPath()%>/buscarPrenda?id=<%=prendas.get(i).getIdTip()%>&nombre=<%=prendas.get(i).getNomTip() %>&descripcion=<%=prendas.get(i).getDescripcion() %>'" ><b>Modificar</b></button></td>
					 			<td style="text-align: center; vertical-align:middle;"><%if(prendas.get(i).getEstado()==1){ %><button class="btn-large btn btn-info" type="button" onClick="location.href='<%=request.getContextPath()%>/estadoTipoPrenda?id=<%=prendas.get(i).getIdTip()%>&estado=<%=prendas.get(i).getEstado()%>'"><b>Bloquear</b></button><%}
					 			else{%><button class="btn-large btn btn-danger" type="button" onClick="location.href='<%=request.getContextPath()%>/estadoTipoPrenda?id=<%=prendas.get(i).getIdTip()%>&estado=<%=prendas.get(i).getEstado()%>'"><b>Habilitar</b></button> <%}%></td>
	 							
	 						</tr>
				 			<%} %>
				 			
			 			</tbody>
				  		</table>
  					</div>
  					
  					<div class="col-sm-12" style="text-align:center;">
  						<br>
  						<button class="btn-large btn btn-success" onclick="location.href='<%=request.getContextPath()%>/AgregarPrenda'">Agregar</button>
                	</div>
                	<div class="col-sm-12" style="text-align:center;">
  					
  					</div>
  					
                	
					
					
					
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