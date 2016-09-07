function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

$("#nuevo_usuario").click(function (eve) {
	$("#modal-content").load(getContextPath()+"/Usuario?accion=Registrar");
});

$(".editar_usuario").click(function (eve) {
    $("#modal-content").load(getContextPath()+"/Usuario?accion=Editar&id=" + $(this).data("id"));
});

$(".detalle_usuario").click(function (eve) {
    $("#modal-content").load(getContextPath()+"/Administrador/Detalle/" + $(this).data("id"));
});