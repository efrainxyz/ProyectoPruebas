package beans;

public class DetalleOrdenBean {
	String ordenCompra_idOrdenCompra;
	int idTipoPrenda;
	int cantidad;
	double precioTotal;
	double precioUnit;
	String tallaPrenda;
	TipoPrendaBean tipoPrenda;
	OrdenCompraBean orden;
	
	public String getOrdenCompra_idOrdenCompra() {
		return ordenCompra_idOrdenCompra;
	}
	public void setOrdenCompra_idOrdenCompra(String ordenCompra_idOrdenCompra) {
		this.ordenCompra_idOrdenCompra = ordenCompra_idOrdenCompra;
	}
	public int getIdTipoPrenda() {
		return idTipoPrenda;
	}
	public void setIdTipoPrenda(int idTipoPrenda) {
		this.idTipoPrenda = idTipoPrenda;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public double getPrecioUnit() {
		return precioUnit;
	}
	public void setPrecioUnit(double precioUnit) {
		this.precioUnit = precioUnit;
	}
	public TipoPrendaBean getTipoPrenda() {
		return tipoPrenda;
	}
	public void setTipoPrenda(TipoPrendaBean tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}
	public String getTallaPrenda() {
		return tallaPrenda;
	}
	public void setTallaPrenda(String tallaPrenda) {
		this.tallaPrenda = tallaPrenda;
	}
	public OrdenCompraBean getOrden() {
		return orden;
	}
	public void setOrden(OrdenCompraBean orden) {
		this.orden = orden;
	}
	
}
