package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.Detalle_IncidenciaBean;
import beans.IncidenciaBean;
import dao.interfaces.I_Incidencia;
import daofactory.MySQLDaoFactory;

public class MySql_IncidenciaDao extends MySQLDaoFactory implements I_Incidencia{

	@Override
	public String generarNroIncidencia() throws Exception {
		// TODO Auto-generated method stub
		String CodigoIncidenciaCompraGenerada ="";
		String numIncidencia="";
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="SELECT date(sysdate())+0 AS fecha from dual;";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			if( rs.next()){
				numIncidencia="IN-"+rs.getString("fecha");
			}
			Statement stmt1 = con.createStatement();
			String query1="SELECT count(*) as num FROM incidencia where idIncidencia LIKE '"+numIncidencia+"%'";
			ResultSet rs1 =stmt1.executeQuery(query1);
			CodigoIncidenciaCompraGenerada=numIncidencia+"00";
			if( rs1.next()){
				if(rs1.getInt("num")>9){
					CodigoIncidenciaCompraGenerada=numIncidencia+rs1.getString("num");
				}else{
					CodigoIncidenciaCompraGenerada=numIncidencia+"0"+rs1.getString("num");
				}
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return CodigoIncidenciaCompraGenerada;
	}

	@Override
	public boolean registrarIncidenciaOrdenCompra(IncidenciaBean incidencia,
			Vector<Detalle_IncidenciaBean> detalle) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stm=con.createStatement();
			String query="insert into incidencia(idIncidencia,fecha,idOrdenCompra,idPersona) values('"+incidencia.getIdIncidencia()+"',sysdate(),'"+incidencia.getIdOrdenCompra()+"',"+incidencia.getIdPersona()+")";
			int filas=stm.executeUpdate(query);
			if(filas==1){
				flag=true;
			}
			if(flag){
				Statement stm1;
				int fils=0;
				for(int i=0;i<detalle.size();i++){
					stm1=con.createStatement();
					String query1="insert into detalle_incidencia(id_incidencia,id_tipoprenda,talla,cantidad,descripcion) values('"+detalle.get(i).getId_incidencia()+"',"+detalle.get(i).getId_tipoprenda()+",'"+detalle.get(i).getTalla()+"',"+detalle.get(i).getCantidad()+",'"+detalle.get(i).getDescripcion()+"')";
					fils=stm1.executeUpdate(query1);
				}
				if(fils>=1){
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
