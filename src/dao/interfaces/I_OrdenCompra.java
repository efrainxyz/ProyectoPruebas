package dao.interfaces;

import java.util.Vector;
import beans.DetalleOrdenBean;
import beans.OrdenCompraBean;

public interface I_OrdenCompra {
	public String generarNroOrdenCompra() throws Exception;
	public boolean registarOrdenCompra(OrdenCompraBean orden, Vector<DetalleOrdenBean> detalle) throws Exception;
	public Vector<OrdenCompraBean> listarOrdenesCompra() throws Exception;
	public Vector<DetalleOrdenBean> ObtenerDetalleOrdenCompra(String id)throws Exception;
	public boolean registrarIngresoOrden(String idOrden)throws Exception;
	public Vector<OrdenCompraBean> listarOrdenesCompraIngresadas() throws Exception;
}
