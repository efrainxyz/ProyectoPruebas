package beans;

public class Detalle_IncidenciaBean {
	String id_incidencia;
	int id_tipoprenda;
	String talla;
	int cantidad;
	String descripcion;
	public String getId_incidencia() {
		return id_incidencia;
	}
	public void setId_incidencia(String id_incidencia) {
		this.id_incidencia = id_incidencia;
	}
	public int getId_tipoprenda() {
		return id_tipoprenda;
	}
	public void setId_tipoprenda(int id_tipoprenda) {
		this.id_tipoprenda = id_tipoprenda;
	}
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
