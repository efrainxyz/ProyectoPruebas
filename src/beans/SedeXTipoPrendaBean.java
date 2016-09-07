package beans;

public class SedeXTipoPrendaBean {
	int idSede;
	int idTipoPrenda;
	int cantidad;
	String estado;
	SedeBean sede;
	TipoPrendaBean tipoPrenda;
	
	public int getIdSede() {
		return idSede;
	}
	public void setIdSede(int idSede) {
		this.idSede = idSede;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public SedeBean getSede() {
		return sede;
	}
	public void setSede(SedeBean sede) {
		this.sede = sede;
	}
	public TipoPrendaBean getTipoPrenda() {
		return tipoPrenda;
	}
	public void setTipoPrenda(TipoPrendaBean tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}
}
