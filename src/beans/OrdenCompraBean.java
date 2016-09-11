package beans;

public class OrdenCompraBean {
	String idOrdenCompra;
	String fechEmision;
	String estadoOrdCom;
	double montoTotal;
	String Proveedor_idProveedor;
	int Persona_idPersona;
	ProveedorBean proveedor;
	public String getIdOrdenCompra() {
		return idOrdenCompra;
	}
	public void setIdOrdenCompra(String idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}
	public String getFechEmision() {
		return fechEmision;
	}
	public void setFechEmision(String fechEmision) {
		this.fechEmision = fechEmision;
	}
	public String getEstadoOrdCom() {
		return estadoOrdCom;
	}
	public void setEstadoOrdCom(String estadoOrdCom) {
		this.estadoOrdCom = estadoOrdCom;
	}
	public double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getProveedor_idProveedor() {
		return Proveedor_idProveedor;
	}
	public void setProveedor_idProveedor(String proveedor_idProveedor) {
		Proveedor_idProveedor = proveedor_idProveedor;
	}
	public int getPersona_idPersona() {
		return Persona_idPersona;
	}
	public void setPersona_idPersona(int persona_idPersona) {
		Persona_idPersona = persona_idPersona;
	}
	public ProveedorBean getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorBean proveedor) {
		this.proveedor = proveedor;
	}
}
