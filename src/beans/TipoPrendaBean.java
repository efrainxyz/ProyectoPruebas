package beans;

public class TipoPrendaBean {

	int idTip;
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	String nomTip;
	String descripcion;
	int estado;
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getIdTip() {
		return idTip;
	}
	public void setIdTip(int idTip) {
		this.idTip = idTip;
	}
	public String getNomTip() {
		return nomTip;
	}
	public void setNomTip(String nomTip) {
		this.nomTip = nomTip;
	}
	
}
