package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.SedeBean;
import beans.SedeXTipoPrendaBean;
import beans.TipoPrendaBean;
import dao.interfaces.I_SedeReparto;
import daofactory.MySQLDaoFactory;

public class MySql_SedeRepartoDao extends MySQLDaoFactory implements I_SedeReparto {

	
	@Override
	public boolean registrarSedeStock() throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		Vector<SedeXTipoPrendaBean> sedesxtipo = new Vector<SedeXTipoPrendaBean>();
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stm=con.createStatement();
			String query="SELECT d.idTip as codigoPrenda,c.idSede as codigoSede,count(*) as cantidad FROM prenda a INNER JOIN persona b ON a.persona_idPersona=b.idPersona INNER JOIN sede c ON b.sede_idSede=c.idSede INNER JOIN tipoprenda d ON a.tipoPrenda_idTip=d.idTip group by a.tipoPrenda_idTip,c.idSede";
			ResultSet rs =stm.executeQuery(query);
			SedeXTipoPrendaBean objsedexprenda=null;
			while( rs.next() ){
				objsedexprenda=new SedeXTipoPrendaBean();
				objsedexprenda.setIdSede(rs.getInt("codigoSede"));
				objsedexprenda.setIdTipoPrenda(rs.getInt("codigoPrenda"));
				objsedexprenda.setCantidad(rs.getInt("cantidad"));
				sedesxtipo.add(objsedexprenda);
			}
			Statement stm1;
			int fils=0;
			stm1=con.createStatement();
			stm1.executeUpdate("delete from sedextipoprenda");
			for(int i=0;i<sedesxtipo.size();i++){
				stm1=con.createStatement();
				String query1="insert into sedextipoprenda(idSede,idTipoPrenda,cantidad,estado) values('"+sedesxtipo.get(i).getIdSede()+"',"+sedesxtipo.get(i).getIdTipoPrenda()+",'"+sedesxtipo.get(i).getCantidad()+"','1')";
				fils=stm1.executeUpdate(query1);
			}
			if(fils>=1){
				flag=true;
			}else{
				flag=false;
			}
			con.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return flag;
	}

	@Override
	public Vector<SedeXTipoPrendaBean> listarStockPrendas() throws Exception {
		// TODO Auto-generated method stub
		Vector<SedeXTipoPrendaBean> sedesxtipo = new Vector<SedeXTipoPrendaBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select *, sum(a.cantidad) as cantidadTotal from sedextipoprenda a INNER JOIN sede b ON a.idSede=b.idSede INNER JOIN tipoprenda c ON a.idTipoPrenda=c.idTip where a.estado='1' group by a.idTipoPrenda";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			SedeXTipoPrendaBean objsedexprenda=null;
			while( rs.next() ){
				objsedexprenda=new SedeXTipoPrendaBean();
				objsedexprenda.setIdSede(rs.getInt("a.idSede"));
				objsedexprenda.setIdTipoPrenda(rs.getInt("a.idTipoPrenda"));
				objsedexprenda.setCantidad(rs.getInt("cantidadTotal"));
				objsedexprenda.setEstado(rs.getString("a.estado"));
				
				SedeBean sede=new SedeBean();
				sede.setIdSede(rs.getInt("b.idSede"));
				sede.setNomSede(rs.getString("b.nomSede"));
				sede.setDirecSede(rs.getString("b.direcSede"));
				sede.setTelefono(rs.getString("b.telefono"));
				objsedexprenda.setSede(sede);
				
				TipoPrendaBean tipoprenda=new TipoPrendaBean();
				tipoprenda.setIdTip(rs.getInt("c.idTip"));
				tipoprenda.setNomTip(rs.getString("c.nomTip"));
				objsedexprenda.setTipoPrenda(tipoprenda);
				
				sedesxtipo.add(objsedexprenda);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return sedesxtipo;
	}

	@Override
	public Vector<SedeXTipoPrendaBean> listarTotalPrendasXSede()
			throws Exception {
		// TODO Auto-generated method stub
		Vector<SedeXTipoPrendaBean> sedesxtipo = new Vector<SedeXTipoPrendaBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from sedextipoprenda a INNER JOIN sede b ON a.idSede=b.idSede INNER JOIN tipoprenda c ON a.idTipoPrenda=c.idTip  order by b.idSede, c.idTip";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			SedeXTipoPrendaBean objsedexprenda=null;
			while( rs.next() ){
				objsedexprenda=new SedeXTipoPrendaBean();
				objsedexprenda.setIdSede(rs.getInt("a.idSede"));
				objsedexprenda.setIdTipoPrenda(rs.getInt("a.idTipoPrenda"));
				objsedexprenda.setCantidad(rs.getInt("a.cantidad"));
				objsedexprenda.setEstado(rs.getString("a.estado"));
				
				SedeBean sede=new SedeBean();
				sede.setIdSede(rs.getInt("b.idSede"));
				sede.setNomSede(rs.getString("b.nomSede"));
				sede.setDirecSede(rs.getString("b.direcSede"));
				sede.setTelefono(rs.getString("b.telefono"));
				objsedexprenda.setSede(sede);
				
				TipoPrendaBean tipoprenda=new TipoPrendaBean();
				tipoprenda.setIdTip(rs.getInt("c.idTip"));
				tipoprenda.setNomTip(rs.getString("c.nomTip"));
				objsedexprenda.setTipoPrenda(tipoprenda);
				
				sedesxtipo.add(objsedexprenda);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return sedesxtipo;
	}

	@Override
	public Vector<SedeBean> listarSedesaEntregar() throws Exception {
		// TODO Auto-generated method stub
		Vector<SedeBean> sedesxtipo = new Vector<SedeBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from sedextipoprenda a INNER JOIN sede b ON a.idSede=b.idSede INNER JOIN tipoprenda c ON a.idTipoPrenda=c.idTip group by a.idSede";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			SedeBean sede=null;
			while( rs.next() ){
				sede=new SedeBean();
				sede.setIdSede(rs.getInt("b.idSede"));
				sede.setNomSede(rs.getString("b.nomSede"));
				sede.setDirecSede(rs.getString("b.direcSede"));
				sede.setTelefono(rs.getString("b.telefono"));
				
				sedesxtipo.add(sede);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return sedesxtipo;
	}
	
	@Override
	public boolean actualizarEstadoEntrega(int idSede) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="update sedextipoprenda set estado='2' where idSede="+idSede;
			int filas=stmt.executeUpdate(query);
			if(filas>=1){
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return flag;
	}


	public Vector<SedeBean> listarSedes() throws Exception {
		// TODO Auto-generated method stub
		Vector<SedeBean> sedes = new Vector<SedeBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from sede";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			SedeBean sede=null;
			while( rs.next() ){
				sede=new SedeBean();
				sede.setIdSede(rs.getInt("idSede"));
				sede.setNomSede(rs.getString("nomSede"));

				sedes.add(sede);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return sedes;
	}

}
