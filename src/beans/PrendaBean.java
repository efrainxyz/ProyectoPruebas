package beans;

public class PrendaBean {

	int idPrenda;
	String talla;
	int persona_idPersona;
	int tipoPrenda_idTip;
	PersonaBean persona;
	TipoPrendaBean tipoPrensa;
	public int getIdPrenda() {
		return idPrenda;
	}
	public void setIdPrenda(int idPrenda) {
		this.idPrenda = idPrenda;
	}
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
	public int getPersona_idPersona() {
		return persona_idPersona;
	}
	public void setPersona_idPersona(int persona_idPersona) {
		this.persona_idPersona = persona_idPersona;
	}
	public int getTipoPrenda_idTip() {
		return tipoPrenda_idTip;
	}
	public void setTipoPrenda_idTip(int tipoPrenda_idTip) {
		this.tipoPrenda_idTip = tipoPrenda_idTip;
	}
	public PersonaBean getPersona() {
		return persona;
	}
	public void setPersona(PersonaBean persona) {
		this.persona = persona;
	}
	public TipoPrendaBean getTipoPrensa() {
		return tipoPrensa;
	}
	public void setTipoPrensa(TipoPrendaBean tipoPrensa) {
		this.tipoPrensa = tipoPrensa;
	}
	
	
}
