package beans;

public class PersonaBean {

	int idPersona;
	String dni;
	String nomPer;
	String apePat;
	String apeMat;
	String sexo;
	String direcPer;
	String correo;
	String telefono;
	int sede_idSede;
	SedeBean sede;
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNomPer() {
		return nomPer;
	}
	public void setNomPer(String nomPer) {
		this.nomPer = nomPer;
	}
	public String getApePat() {
		return apePat;
	}
	public void setApePat(String apePat) {
		this.apePat = apePat;
	}
	public String getApeMat() {
		return apeMat;
	}
	public void setApeMat(String apeMat) {
		this.apeMat = apeMat;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDirecPer() {
		return direcPer;
	}
	public void setDirecPer(String direcPer) {
		this.direcPer = direcPer;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getSede_idSede() {
		return sede_idSede;
	}
	public void setSede_idSede(int sede_idSede) {
		this.sede_idSede = sede_idSede;
	}
	public SedeBean getSede() {
		return sede;
	}
	public void setSede(SedeBean sede) {
		this.sede = sede;
	}
	
}
