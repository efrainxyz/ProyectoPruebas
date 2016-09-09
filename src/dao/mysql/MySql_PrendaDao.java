package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.PersonaBean;
import beans.PrendaBean;
import beans.TipoPrendaBean;
import dao.interfaces.I_Prenda;
import daofactory.MySQLDaoFactory;

public class MySql_PrendaDao extends MySQLDaoFactory implements I_Prenda {

	@Override
	public Vector<TipoPrendaBean> listarTiposPrenda() throws Exception {
		// TODO Auto-generated method stub
		Vector<TipoPrendaBean> prendas = new Vector<TipoPrendaBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			
			String query="select * from tipoprenda";
			Statement stmt = con.createStatement();
			//Cuando haces un select se usa "executeQuery"
			ResultSet rs =stmt.executeQuery(query);
			TipoPrendaBean objprenda=null;
			while( rs.next() ){
				objprenda=new TipoPrendaBean();
				objprenda.setIdTip(rs.getInt(1));
				objprenda.setNomTip(rs.getString(2));
				objprenda.setDescripcion(rs.getString(3));
				objprenda.setEstado(rs.getInt(4));
				
				prendas.add(objprenda);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return prendas;
	}
	
	public Vector<TipoPrendaBean> listarTiposPrendaColaborador() throws Exception {
		// TODO Auto-generated method stub
		Vector<TipoPrendaBean> prendas = new Vector<TipoPrendaBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			
			String query="select * from tipoprenda where estado='1'";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			TipoPrendaBean objprenda=null;
			while( rs.next() ){
				objprenda=new TipoPrendaBean();
				objprenda.setIdTip(rs.getInt(1));
				objprenda.setNomTip(rs.getString(2));
				objprenda.setDescripcion(rs.getString(3));
				objprenda.setEstado(rs.getInt(4));
				
				prendas.add(objprenda);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return prendas;
	}
	

	@Override
	public boolean agregarTallaColaborador(PrendaBean prenda) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stm=con.createStatement();
			String query="insert into prenda(talla,persona_idPersona,tipoPrenda_idTip) values('"+prenda.getTalla()+"',"+prenda.getPersona_idPersona()+","+prenda.getTipoPrenda_idTip()+")";
			int filas=stm.executeUpdate(query);
			if(filas==1){
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean modificarTallaColaborador(PrendaBean prenda) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stm=con.createStatement();
			String query="update prenda set talla='"+prenda.getTalla()+"' where persona_idPersona="+prenda.getPersona_idPersona()+" and tipoPrenda_idTip="+prenda.getTipoPrenda_idTip();
			int filas=stm.executeUpdate(query);
			if(filas==1){
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return flag;
	}

	@Override
	public Vector<PrendaBean> obtenerTallaColaborador(int id_persona)
			throws Exception {
		// TODO Auto-generated method stub
		Vector<PrendaBean> prendasColaboradores = new Vector<PrendaBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from prenda where persona_idPersona="+id_persona+"";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			PrendaBean objprenda=null;
			while( rs.next() ){
				objprenda=new PrendaBean();
				objprenda.setIdPrenda(rs.getInt("idPrenda"));
				objprenda.setTalla(rs.getString("talla"));
				objprenda.setPersona_idPersona(rs.getInt("persona_idPersona"));
				objprenda.setTipoPrenda_idTip(rs.getInt("tipoPrenda_idTip"));
				
				prendasColaboradores.add(objprenda);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return prendasColaboradores;
	}

	@Override
	public Vector<PersonaBean> listarColaboradoresCompletados() throws Exception {
		// TODO Auto-generated method stub
		Vector<PersonaBean> colaboradoresCompletados = new Vector<PersonaBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select b.idPersona, b.nomPer,b.apePat,b.apeMat from prenda a INNER JOIN persona b on a.persona_idPersona=b.idPersona group by b.idPersona";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			PersonaBean objPersona=null;
			while( rs.next() ){
				objPersona=new PersonaBean();
				objPersona.setIdPersona(rs.getInt("b.idPersona"));
				objPersona.setNomPer(rs.getString("b.nomPer"));
				objPersona.setApePat(rs.getString("b.apePat"));
				objPersona.setApeMat(rs.getString("b.apeMat"));
				
				colaboradoresCompletados.add(objPersona);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return colaboradoresCompletados;
	}

	@Override
	public Vector<PersonaBean> listarColaboradoresFaltantes() throws Exception {
		// TODO Auto-generated method stub
		Vector<PersonaBean> colaboradoresFaltantes = new Vector<PersonaBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select a.idPersona, a.nomPer, a.apePat, a.apeMat from persona a INNER JOIN usuario b ON a.idPersona=b.codUsuario where a.idPersona <> all (select b.idPersona from prenda a INNER JOIN persona b on a.persona_idPersona=b.idPersona group by b.idPersona) and b.rol_idRol=1";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			PersonaBean objPersona=null;
			while( rs.next() ){
				objPersona=new PersonaBean();
				objPersona.setIdPersona(rs.getInt("a.idPersona"));
				objPersona.setNomPer(rs.getString("a.nomPer"));
				objPersona.setApePat(rs.getString("a.apePat"));
				objPersona.setApeMat(rs.getString("a.apeMat"));
				
				colaboradoresFaltantes.add(objPersona);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return colaboradoresFaltantes;
	}

	@Override
	public boolean estadoTipoPrenda(int id , int estado) throws Exception {
		boolean flag = false;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			Statement stmt = con.createStatement();
			if(estado==1){
				String query = "update tipoprenda set estado='0' where idTip="+id;
				int fila =stmt.executeUpdate(query);
				if(fila==1){
					flag=true;
				}
			}else{
				String query = "update tipoprenda set estado='1' where idTip="+id;
				int fila =stmt.executeUpdate(query);
				if(fila==1){
					flag=true;
				}
				
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean modificarTipoPrenda(TipoPrendaBean prenda) throws Exception {
		
		boolean flag = false;
		
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stm=con.createStatement();
			String query="update tipoprenda set nomTip='"+prenda.getNomTip()+"' , descripcion='"+prenda.getDescripcion()+"' where idTip="+prenda.getIdTip();
			int filas=stm.executeUpdate(query);
			if(filas==1){
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean agregarPrenda(TipoPrendaBean prenda) throws Exception {
		
		boolean flag = false;
		
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stm=con.createStatement();
			String query="insert into tipoprenda(nomTip,descripcion,estado) values('"+prenda.getNomTip()+"','"+prenda.getDescripcion()+"','1')";
		
			int filas=stm.executeUpdate(query);
			if(filas==1){
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean existePrenda(String prenda) throws Exception {
		boolean flag = false;
		
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from tipoprenda where nomTip like '"+prenda+"%'";
			
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			
			while( rs.next() ){
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return flag;
	}

	
	

}
