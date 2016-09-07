package dao.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.PersonaBean;
import beans.RolBean;
import beans.SedeBean;
import beans.UsuarioBean;
import dao.interfaces.I_Usuario;
import daofactory.MySQLDaoFactory;

public class MySql_UsuarioDao extends MySQLDaoFactory implements I_Usuario {

	@Override
	public UsuarioBean validar(String usuario, String clave) throws Exception {
		// TODO Auto-generated method stub
		UsuarioBean objusuario=null;
		try {
			
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="select * from usuario a INNER JOIN rol b ON a.rol_idRol=b.idRol INNER JOIN persona c ON a.codUsuario=c.idPersona INNER JOIN sede d ON c.sede_idSede=d.idSede where a.usuario='"+usuario+"' and a.clave='"+clave+"' and a.estadoUsu='1'";
			System.out.println(query);
			ResultSet rs= stmt.executeQuery(query);
			if( rs.next() ){
				objusuario= new UsuarioBean();
				objusuario.setCodUsuario(rs.getInt("codUsuario"));
				objusuario.setUsuario(rs.getString("usuario"));
				objusuario.setClave(rs.getString("clave"));
				objusuario.setEstadoUsu(rs.getInt("estadoUsu"));
				objusuario.setFechActi(rs.getString("fechActi"));
				objusuario.setFechDesact(rs.getString("fechDesact"));
				objusuario.setRol_idRol(rs.getInt("rol_idRol"));
				
				RolBean objrol=new RolBean();
				objrol.setIdRol(rs.getInt("idRol"));
				objrol.setNomTipo(rs.getString("nomTipo"));
				
				objusuario.setRol(objrol);
				
				PersonaBean objpersona=new PersonaBean();
				objpersona.setIdPersona(rs.getInt("idPersona"));
				objpersona.setDni(rs.getString("dni"));
				objpersona.setNomPer(rs.getString("nomPer"));
				objpersona.setApePat(rs.getString("apePat"));
				objpersona.setApeMat(rs.getString("apeMat"));
				objpersona.setSexo(rs.getString("sexo"));
				objpersona.setDirecPer(rs.getString("direcPer"));
				objpersona.setCorreo(rs.getString("correo"));
				objpersona.setTelefono(rs.getString("telefono"));
				objpersona.setSede_idSede(rs.getInt("sede_idSede"));
				
				SedeBean objsede=new SedeBean();
				objsede.setIdSede(rs.getInt("idSede"));
				objsede.setNomSede(rs.getString("nomSede"));
				objsede.setDirecSede(rs.getString("direcSede"));
				objsede.setTelefono(rs.getString("telefono"));
				
				objpersona.setSede(objsede);
				
				objusuario.setPersona(objpersona);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return objusuario;
	}

	@Override
	public UsuarioBean obtenerUsuario(int id_usuario) throws Exception {
		UsuarioBean objusuario = null;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from usuario a INNER JOIN persona b ON a.codUsuario=b.idPersona INNER JOIN sede c on b.sede_idSede=c.idSede INNER JOIN rol d on d.idRol=a.rol_idRol where a.codUsuario="+id_usuario;
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			
			if(rs.next()){
				
				RolBean objrol = new RolBean();
				objrol.setNomTipo(rs.getString("d.nomTipo"));
				
				objusuario= new UsuarioBean();
				objusuario.setCodUsuario(rs.getInt("a.codUsuario"));
				objusuario.setUsuario(rs.getString("a.usuario"));
				objusuario.setClave(rs.getString("a.clave"));
				objusuario.setEstadoUsu(rs.getInt("a.estadoUsu"));
				objusuario.setFechActi(rs.getString("a.fechActi"));
				objusuario.setFechDesact(rs.getString("a.fechDesact"));
				objusuario.setRol_idRol(rs.getInt("a.rol_idRol"));
				
				PersonaBean objpersona=new PersonaBean();
				objpersona.setIdPersona(rs.getInt("b.idPersona"));
				objpersona.setDni(rs.getString("b.dni"));
				objpersona.setNomPer(rs.getString("b.nomPer"));
				objpersona.setApePat(rs.getString("b.apePat"));
				objpersona.setApeMat(rs.getString("b.apeMat"));
				objpersona.setSexo(rs.getString("b.sexo"));
				objpersona.setDirecPer(rs.getString("b.direcPer"));
				objpersona.setCorreo(rs.getString("b.correo"));
				objpersona.setTelefono(rs.getString("b.telefono"));
				objpersona.setSede_idSede(rs.getInt("b.sede_idSede"));
				
				SedeBean objsede=new SedeBean();
				objsede.setIdSede(rs.getInt("c.idSede"));
				objsede.setNomSede(rs.getString("c.nomSede"));
				objsede.setDirecSede(rs.getString("c.direcSede"));
				objsede.setTelefono(rs.getString("c.telefono"));
				
				objpersona.setSede(objsede);
				objusuario.setRol(objrol);
				objusuario.setPersona(objpersona);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return objusuario;
	}
	
	@Override
	public Vector<PersonaBean> obtenerCorreosFaltantes() throws Exception {
		// TODO Auto-generated method stub
		Vector<PersonaBean> colaboradoresCorreosFaltantes = new Vector<PersonaBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select a.correo from persona a INNER JOIN usuario b ON a.idPersona=b.codUsuario where a.idPersona <> all (select b.idPersona from prenda a INNER JOIN persona b on a.persona_idPersona=b.idPersona group by b.idPersona) and b.rol_idRol=1";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			PersonaBean objPersona=null;
			while( rs.next() ){
				objPersona=new PersonaBean();
				objPersona.setCorreo(rs.getString("a.correo"));
				
				colaboradoresCorreosFaltantes.add(objPersona);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return colaboradoresCorreosFaltantes;
	}
	
	@Override
	public Vector<UsuarioBean> listarUsuarios() throws Exception {
		// TODO Auto-generated method stub
		Vector<UsuarioBean> trabajadores = new Vector<UsuarioBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from usuario a INNER JOIN persona b ON a.codUsuario=b.idPersona INNER JOIN sede c on b.sede_idSede=c.idSede where a.rol_idRol=1";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			UsuarioBean objusuario=null;
			while( rs.next() ){
				objusuario= new UsuarioBean();
				objusuario.setCodUsuario(rs.getInt("a.codUsuario"));
				objusuario.setUsuario(rs.getString("a.usuario"));
				objusuario.setClave(rs.getString("a.clave"));
				objusuario.setEstadoUsu(rs.getInt("a.estadoUsu"));
				objusuario.setFechActi(rs.getString("a.fechActi"));
				objusuario.setFechDesact(rs.getString("a.fechDesact"));
				objusuario.setRol_idRol(rs.getInt("a.rol_idRol"));
				
				PersonaBean objpersona=new PersonaBean();
				objpersona.setIdPersona(rs.getInt("b.idPersona"));
				objpersona.setDni(rs.getString("b.dni"));
				objpersona.setNomPer(rs.getString("b.nomPer"));
				objpersona.setApePat(rs.getString("b.apePat"));
				objpersona.setApeMat(rs.getString("b.apeMat"));
				objpersona.setSexo(rs.getString("b.sexo"));
				objpersona.setDirecPer(rs.getString("b.direcPer"));
				objpersona.setCorreo(rs.getString("b.correo"));
				objpersona.setTelefono(rs.getString("b.telefono"));
				objpersona.setSede_idSede(rs.getInt("b.sede_idSede"));
				
				SedeBean objsede=new SedeBean();
				objsede.setIdSede(rs.getInt("c.idSede"));
				objsede.setNomSede(rs.getString("c.nomSede"));
				objsede.setDirecSede(rs.getString("c.direcSede"));
				objsede.setTelefono(rs.getString("c.telefono"));
				
				objpersona.setSede(objsede);
				
				objusuario.setPersona(objpersona);
				
				
				trabajadores.add(objusuario);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return trabajadores;
	}
	
	
	
	public Vector<UsuarioBean> listarUsuarios2() throws Exception {
		// TODO Auto-generated method stub
		Vector<UsuarioBean> trabajadores = new Vector<UsuarioBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from usuario a INNER JOIN persona b ON a.codUsuario=b.idPersona INNER JOIN sede c on b.sede_idSede=c.idSede INNER JOIN rol d on d.idRol=a.rol_idRol and a.rol_idRol='5'";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			UsuarioBean objusuario=null;
			while( rs.next() ){
				RolBean objrol = new RolBean();
				objrol.setNomTipo(rs.getString("d.nomTipo"));
				
				objusuario= new UsuarioBean();
				objusuario.setCodUsuario(rs.getInt("a.codUsuario"));
				objusuario.setUsuario(rs.getString("a.usuario"));
				objusuario.setClave(rs.getString("a.clave"));
				objusuario.setEstadoUsu(rs.getInt("a.estadoUsu"));
				objusuario.setFechActi(rs.getString("a.fechActi"));
				objusuario.setFechDesact(rs.getString("a.fechDesact"));
				objusuario.setRol_idRol(rs.getInt("a.rol_idRol"));
				
				PersonaBean objpersona=new PersonaBean();
				objpersona.setIdPersona(rs.getInt("b.idPersona"));
				objpersona.setDni(rs.getString("b.dni"));
				objpersona.setNomPer(rs.getString("b.nomPer"));
				objpersona.setApePat(rs.getString("b.apePat"));
				objpersona.setApeMat(rs.getString("b.apeMat"));
				objpersona.setSexo(rs.getString("b.sexo"));
				objpersona.setDirecPer(rs.getString("b.direcPer"));
				objpersona.setCorreo(rs.getString("b.correo"));
				objpersona.setTelefono(rs.getString("b.telefono"));
				objpersona.setSede_idSede(rs.getInt("b.sede_idSede"));
				
				SedeBean objsede=new SedeBean();
				objsede.setIdSede(rs.getInt("c.idSede"));
				objsede.setNomSede(rs.getString("c.nomSede"));
				objsede.setDirecSede(rs.getString("c.direcSede"));
				objsede.setTelefono(rs.getString("c.telefono"));
				
				
				
				objpersona.setSede(objsede);
				objusuario.setRol(objrol);
				objusuario.setPersona(objpersona);
				
				
				
				trabajadores.add(objusuario);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return trabajadores;
	}
	
	

	@Override
	public boolean modificarUsuario(UsuarioBean usuario,String correo) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			Statement stmt = con.createStatement();
			String query = "update usuario set usuario='"+usuario.getUsuario()+"' ,clave='"+usuario.getClave()+"' ,estadoUsu='"+usuario.getEstadoUsu()+"'  where codUsuario='"+usuario.getCodUsuario()+"'";
			int fila =stmt.executeUpdate(query);
			if(fila==1){
					PreparedStatement pstmt;
					pstmt = con.prepareStatement("update persona set correo='"+correo+"' where idPersona='"+usuario.getCodUsuario()+"'");
					pstmt.executeUpdate();
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return flag;
	}

	
	public boolean modificarUsuario2(UsuarioBean usuario) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			Statement stmt = con.createStatement();
			String query = "update usuario set usuario='"+usuario.getUsuario()+"'   where codUsuario='"+usuario.getCodUsuario()+"'";
			int fila =stmt.executeUpdate(query);
			if(fila==1){
					PreparedStatement pstmt;
					pstmt = con.prepareStatement("update persona set nomPer='"+usuario.getPersona().getNomPer()+"',apePat='"+usuario.getPersona().getApePat()+"',apeMat='"+usuario.getPersona().getApeMat()+"',direcPer='"+usuario.getPersona().getDirecPer()+"',correo='"+usuario.getPersona().getCorreo()+"', telefono='"+usuario.getPersona().getTelefono()+"'   where idPersona='"+usuario.getCodUsuario()+"'");
					pstmt.executeUpdate();
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return flag;
	}

	public boolean agregar(UsuarioBean usuario) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			Statement stmt = con.createStatement();
			String quer="SELECT (idPersona+1) as codigo FROM persona ORDER BY idPersona DESC LIMIT 1";
			ResultSet rs =stmt.executeQuery(quer);
			int codigo=0;
			while (rs.next())
			{
			   codigo = rs.getInt(1);
			  
			}
			
					String query = "insert into persona (idPersona,dni,nomPer,apePat,apeMat,sexo,direcPer,correo,telefono,sede_idSede) "
					+ "values ('"+codigo+"','"+usuario.getPersona().getDni()+"','"+usuario.getPersona().getNomPer()+"',"
					+ "'"+usuario.getPersona().getApePat()+"','"+usuario.getPersona().getApeMat()+"',"
					+ "'"+usuario.getPersona().getSexo()+"','"+usuario.getPersona().getDirecPer()+"',"
					+ "'"+usuario.getPersona().getCorreo()+"','"+usuario.getPersona().getTelefono()+"',"
					+ "'"+usuario.getPersona().getSede_idSede()+"')";
			System.out.println(query);
			int fila =stmt.executeUpdate(query);
			if(fila==1){
					PreparedStatement pstmt;
					pstmt = con.prepareStatement("insert into usuario (codUsuario,usuario,clave,estadoUsu,rol_idRol) "
							+ "values('"+codigo+"','"+usuario.getUsuario()+"',"
							+ "'"+usuario.getClave()+"','1','5')");
					System.out.println(pstmt);
					pstmt.executeUpdate();
					
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	
	@Override
	public boolean eliminarUsuario(int idUsuario) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			Statement stmt = con.createStatement();
			String query = "delete from usuario where codUsuario="+idUsuario;
			int fila =stmt.executeUpdate(query);
			if(fila==1){
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean activarUsuarios(Vector<UsuarioBean> listaaactivar,int cantidadDias)
			throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			for(int i=0;i<listaaactivar.size();i++){
				Statement stm=con.createStatement();
				String query="update usuario set estadoUsu='1', fechActi=sysdate(),fechDesact=(SELECT DATE_ADD(NOW(), INTERVAL "+cantidadDias+" DAY)) where codUsuario='"+listaaactivar.get(i).getCodUsuario()+"'";
				int filas=stm.executeUpdate(query);
				if(filas==1){
					flag=true;
				}else{
					flag=false;
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return flag;
	}

}
