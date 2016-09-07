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
                	<label for="buscaOrden" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">El producto a modificar : <%=request.getParameter("nombre") %></label>
                 </div>
                 
            	<div class="col-sm-12">
            		<br>
					<form method="get" action="<%=request.getContextPath()%>/modificarPrenda">
					
					
					<input type="hidden" name="id" value="<%=request.getParameter("id") %>">
					<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" name="id" value="" >Código : <%=request.getParameter("id") %></label></br>
					<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Nuevo nombre : </label> <input name="nuevonombre" class="form-control" required></br>
					<label class="control-label" style="text-align: left; font-size: 15px; color: darkblue;" >Nueva descripcion : </label> <input name="nuevadescripcion" class="form-control" required></br></br>
					<button class="btn btn-primary" type="submit">Modificar</button>
					<button class="btn btn-danger" type="button" onclick="location.href='<%=request.getContextPath()%>/MantenerPrenda'">Cancelar</button>
					</form>
  				</div>
  				
			</div>
</div>
</body>
</html>
<%}%>