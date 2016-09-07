package beans;

public class Proveedor_TipoPrendaBean {
	int idProveedor;
	int idTipoPrenda;
	double precioUnitario;
	ProveedorBean proveedor;
	TipoPrendaBean tipoPrenda;
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public int getIdTipoPrenda() {
		return idTipoPrenda;
	}
	public void setIdTipoPrenda(int idTipoPrenda) {
		this.idTipoPrenda = idTipoPrenda;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public ProveedorBean getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorBean proveedor) {
		this.proveedor = proveedor;
	}
	public TipoPrendaBean getTipoPrenda() {
		return tipoPrenda;
	}
	public void setTipoPrenda(TipoPrendaBean tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}
	
}
