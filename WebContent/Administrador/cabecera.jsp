<nav class="navbar navbar-inverse navbar-fixed-top" style="background:#0B0B61;">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=request.getContextPath()%>/Login"><b>SISTEMA DE VESTIMENTA BCP</b></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
            	<li><a href="<%=request.getContextPath()%>/ListarTallasColaboradores">Listar Tallas de Colaboradores</a></li>
            	<li><a href="<%=request.getContextPath()%>/ConsultarReporteCourier">Consultar Stock de Prendas por Sede</a></li>
            	<li><a href="<%=request.getContextPath()%>/MantenerUsuario">Mantener Asistente</a></li>
            	<li><a href="<%=request.getContextPath()%>/MantenerPrenda">Mantener Prenda</a></li>
            	<li><a href="<%=request.getContextPath()%>/Logout">Cerrar Sesi&oacute;n</a></li>
            </ul>
        </div>
    </div>
</nav>