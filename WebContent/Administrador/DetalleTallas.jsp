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
Vector<TipoPrendaBean> prendas=(Vector<TipoPrendaBean>)request.getAttribute("prendas");
Vector<PrendaBean> prendasColaborador=(Vector<PrendaBean>)request.getAttribute("prendasColaborador");
UsuarioBean colaborador=(UsuarioBean)request.getAttribute("colaborador");%>
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
                <%if(mensaje!=null){%>
                	<div class="col-sm-12 alert alert-danger"><%=mensaje%></div>
                <%}else{%>
                	<div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="datosColaborador" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">DATOS DEL COLABORADOR</label>
                 	</div>
                 	<div class="col-sm-12 form-group">
                 	<br>
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Nombres:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="<%=colaborador.getPersona().getNomPer()%>">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">C&oacute;digo de Contrataci&oacute;n:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="<%=colaborador.getPersona().getIdPersona()%>">
							</div>
						</div>
				    </div>
                    <div class="col-sm-12 form-group">
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">Apellidos:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="<%=colaborador.getPersona().getApePat()+" "+colaborador.getPersona().getApeMat()%>">
							</div>
						</div>
						<div class="col-sm-6" style="text-align: left;">
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;">DNI:</label>
							<div class="col-sm-6" style="text-align: left;">
								<input class="form-control" readonly="readonly" type="text" value="<%=colaborador.getPersona().getDni()%>">
							</div>
						</div>
				    </div>
				    <div class="col-sm-12" style="text-align:left;">
                		<br>
                		<label for="tallasUniformes" class="control-label" style="text-align: left; font-size: 30px; color: darkblue;">TALLAS DE UNIFORMES</label>
                 	</div>
				     <div class="col-sm-12 form-group">
				     	<%for(int i=0;i<prendas.size();i++){%>
						<div class="col-sm-6" style="text-align: left;">
						<br>
							<label for="usuario" class="col-sm-6 control-label" style="text-align: right; font-size: 20px;"><%=prendas.get(i).getNomTip()+" : "%><label style="color: red;">*</label></label>
							<div class="col-sm-6" style="text-align: left;">
								<select class="form-control" size="1" name="talla<%=prendas.get(i).getIdTip()%>" disabled="disabled">
									<option value="S" <%if(mensaje2==null){for(int j=0;j<prendasColaborador.size();j++){if(prendas.get(i).getIdTip()==prendasColaborador.get(j).getTipoPrenda_idTip() && prendasColaborador.get(j).getTalla().equals("S")){%> selected="selected" <%}}}%>>S</option>
									<option value="M" <%if(mensaje2==null){for(int j=0;j<prendasColaborador.size();j++){if(prendas.get(i).getIdTip()==prendasColaborador.get(j).getTipoPrenda_idTip() && prendasColaborador.get(j).getTalla().equals("M")){%> selected="selected" <%}}}%>>M</option>
									<option value="L" <%if(mensaje2==null){for(int j=0;j<prendasColaborador.size();j++){if(prendas.get(i).getIdTip()==prendasColaborador.get(j).getTipoPrenda_idTip() && prendasColaborador.get(j).getTalla().equals("L")){%> selected="selected" <%}}}%>>L</option>
									<option value="XL" <%if(mensaje2==null){for(int j=0;j<prendasColaborador.size();j++){if(prendas.get(i).getIdTip()==prendasColaborador.get(j).getTipoPrenda_idTip() && prendasColaborador.get(j).getTalla().equals("XL")){%> selected="selected" <%}}}%>>XL</option>
								</select>
							</div>
						</div>
						<%}%>
				    </div>
				    <div  class="col-sm-12 form-group" style="text-align:center;">
                		<br>
                		<button class="btn-large btn btn-primary" type="button" onClick="location.href='<%=request.getContextPath()%>/ListarTallasColaboradores'"><b>Cerrar</b></button>
                		<br>
                 	</div>
                 	<%if(mensaje1!=null){%>
						<div class="col-sm-12 form-group alert alert-success"><%=mensaje1%></div>
					<%}%>
					<%if(mensaje2!=null){%>
						<div class="col-sm-12 form-group alert alert-danger"><%=mensaje2%></div>
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