package dao.interfaces;
import java.util.Vector;

import beans.PersonaBean;
import beans.UsuarioBean;

public interface I_Usuario {
	public UsuarioBean validar(String usuario, String clave) throws Exception;
	public PersonaBean obtenerPersona(int id_usuario) throws Exception;
	public UsuarioBean obtenerUsuario(int id_usuario) throws Exception;
	public Vector<PersonaBean> obtenerCorreosFaltantes() throws Exception;
	public Vector<UsuarioBean> listarUsuarios() throws Exception;
	public Vector<UsuarioBean> listarUsuarios2() throws Exception;
	public boolean modificarUsuario(UsuarioBean usuario, String correo) throws Exception;
	public boolean modificarUsuario2(UsuarioBean usuario) throws Exception;
	public boolean agregar(UsuarioBean usuario) throws Exception;
	public boolean eliminarUsuario(int idUsuario) throws Exception;
	public boolean activarUsuarios(Vector<UsuarioBean> listaaactivar,int cantidadDias) throws Exception;
}
