package dao.interfaces;

import java.util.Vector;
import beans.DetalleOrdenBean;
import beans.ProveedorBean;
import beans.Proveedor_TipoPrendaBean;

public interface I_Proveedor {
	public ProveedorBean obtenerProveedor() throws Exception;
	public Vector<DetalleOrdenBean> obtenerListaPrendas() throws Exception;
	public Proveedor_TipoPrendaBean obtenerDatosPrenda(int codigoPrenda)throws Exception;
	public Vector<ProveedorBean> listarProveedores() throws Exception;
	public boolean agregarProveedor(ProveedorBean proveedor) throws Exception;
	public Vector<ProveedorBean> buscarProveedor(int id_proveedor) throws Exception;
	public boolean eliminarProveedor(int idProveedor) throws Exception;
	public ProveedorBean buscarProveedor2(int id_proveedor) throws Exception ;
	public boolean modificarProveedor(ProveedorBean proveedor) throws Exception;
}
