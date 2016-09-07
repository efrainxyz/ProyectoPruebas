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
            	<li><a href="<%=request.getContextPath()%>/GenerarOrdenCompra">Generar Orden de Compra de Uniformes</a></li>
            	<li><a href="<%=request.getContextPath()%>/HistorialCompras">Listar Hist&oacute;rico de Compras</a></li>
            	<li><a href="<%=request.getContextPath()%>/MantenerProveedores">Mantener Proveedor</a></li>
            	<li><a href="<%=request.getContextPath()%>/Logout">Cerrar Sesi&oacute;n</a></li>
            </ul>
        </div>
    </div>
</nav>