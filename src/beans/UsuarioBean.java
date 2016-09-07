package beans;

public class UsuarioBean {
	int codUsuario;
	String usuario;
	String clave;
	int estadoUsu;
	String fechActi;
	String fechDesact;
	int rol_idRol;
	RolBean rol;
	PersonaBean persona;
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getEstadoUsu() {
		return estadoUsu;
	}
	public void setEstadoUsu(int estadoUsu) {
		this.estadoUsu = estadoUsu;
	}
	public String getFechActi() {
		return fechActi;
	}
	public void setFechActi(String fechActi) {
		this.fechActi = fechActi;
	}
	public String getFechDesact() {
		return fechDesact;
	}
	public void setFechDesact(String fechDesact) {
		this.fechDesact = fechDesact;
	}
	public int getRol_idRol() {
		return rol_idRol;
	}
	public void setRol_idRol(int rol_idRol) {
		this.rol_idRol = rol_idRol;
	}
	public RolBean getRol() {
		return rol;
	}
	public void setRol(RolBean rol) {
		this.rol = rol;
	}
	public PersonaBean getPersona() {
		return persona;
	}
	public void setPersona(PersonaBean persona) {
		this.persona = persona;
	}
	
}
