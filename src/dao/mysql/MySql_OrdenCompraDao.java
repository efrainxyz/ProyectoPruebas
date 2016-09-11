package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.DetalleOrdenBean;
import beans.OrdenCompraBean;
import beans.ProveedorBean;
import beans.TipoPrendaBean;
import dao.interfaces.I_OrdenCompra;
import daofactory.MySQLDaoFactory;

public class MySql_OrdenCompraDao extends MySQLDaoFactory implements I_OrdenCompra {

	@Override
	public String generarNroOrdenCompra() throws Exception {
		// TODO Auto-generated method stub
		String CodigoOrdenCompraGenerada ="";
		String numOrden="";
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="SELECT date(sysdate())+0 AS fecha from dual;";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			if( rs.next()){
				numOrden="OC-"+rs.getString("fecha");
			}
			Statement stmt1 = con.createStatement();
			String query1="SELECT count(*) as num FROM ordencompra where idOrdenCompra LIKE '"+numOrden+"%'";
			ResultSet rs1 =stmt1.executeQuery(query1);
			CodigoOrdenCompraGenerada=numOrden+"00";
			if( rs1.next()){
				if(rs1.getInt("num")>9){
					CodigoOrdenCompraGenerada=numOrden+rs1.getString("num");
				}else{
					CodigoOrdenCompraGenerada=numOrden+"0"+rs1.getString("num");
				}
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return CodigoOrdenCompraGenerada;
	}
	
	@Override
	public boolean registarOrdenCompra(OrdenCompraBean orden,
			Vector<DetalleOrdenBean> detalle) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stm=con.createStatement();
			String query="insert into ordencompra(idOrdenCompra,fechEmision,estadoOrdCom,montoTotal,proveedor_idProveedor,persona_idPersona) values('"+orden.getIdOrdenCompra()+"',sysdate(),'1',"+orden.getMontoTotal()+","+orden.getProveedor_idProveedor()+","+orden.getPersona_idPersona()+")";
			int filas=stm.executeUpdate(query);
			if(filas==1){
				flag=true;
			}
			if(flag){
				Statement stm1;
				int fils=0;
				for(int i=0;i<detalle.size();i++){
					stm1=con.createStatement();
					String query1="insert into ordencompraxprenda(ordenCompra_idOrdenCompra,idTipoPrenda,cantidad,precioTotal,precioUnit,tallaPrenda) values('"+detalle.get(i).getOrdenCompra_idOrdenCompra()+"',"+detalle.get(i).getIdTipoPrenda()+","+detalle.get(i).getCantidad()+",'"+detalle.get(i).getPrecioTotal()+"','"+detalle.get(i).getPrecioUnit()+"','"+detalle.get(i).getTallaPrenda()+"')";
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

	@Override
	public Vector<OrdenCompraBean> listarOrdenesCompra() throws Exception {
		// TODO Auto-generated method stub
		Vector<OrdenCompraBean> ordenes = new Vector<OrdenCompraBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="SELECT idOrdenCompra,date(fechEmision) as fechEmision,estadoOrdCom,montoTotal,persona_idPersona,proveedor_idProveedor  FROM ordencompra order by fechEmision desc";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			OrdenCompraBean objorden=null;
			while( rs.next() ){
				objorden=new OrdenCompraBean();
				objorden.setIdOrdenCompra(rs.getString("idOrdenCompra"));
				objorden.setFechEmision(rs.getString("fechEmision"));
				objorden.setEstadoOrdCom(rs.getString("estadoOrdCom"));
				objorden.setMontoTotal(rs.getDouble("montoTotal"));
				objorden.setPersona_idPersona(rs.getInt("persona_idPersona"));
				objorden.setProveedor_idProveedor("proveedor_idProveedor");
				
				ordenes.add(objorden);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return ordenes;
	}

	@Override
	public Vector<DetalleOrdenBean> ObtenerDetalleOrdenCompra(String id) throws Exception {
		// TODO Auto-generated method stub
		Vector<DetalleOrdenBean> detalleOrden = new Vector<DetalleOrdenBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select *,date(b.fechEmision) as fechaEmision from ordencompraxprenda a INNER JOIN ordencompra b ON a.ordenCompra_idOrdenCompra=b.idOrdenCompra INNER JOIN tipoprenda c ON a.idTipoPrenda=c.idTip INNER JOIN proveedor d ON d.idProveedor=b.proveedor_idProveedor where a.ordenCompra_idOrdenCompra='"+id+"'";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			DetalleOrdenBean objdetalle=null;
			while( rs.next() ){
				
				ProveedorBean  objprove= new ProveedorBean();
				objprove.setRazonSoc(rs.getString("d.razonSoc"));
				objprove.setIdProveedor(rs.getString("d.idProveedor"));
				objprove.setDirecProve(rs.getString("d.direcProve"));
				
				
				objdetalle=new DetalleOrdenBean();
				objdetalle.setOrdenCompra_idOrdenCompra(rs.getString("a.ordenCompra_idOrdenCompra"));
				objdetalle.setIdTipoPrenda(rs.getInt("a.idTipoPrenda"));
				objdetalle.setPrecioUnit(rs.getDouble("a.precioUnit"));
				objdetalle.setCantidad(rs.getInt("a.cantidad"));
				objdetalle.setTallaPrenda(rs.getString("a.tallaPrenda"));
				objdetalle.setPrecioTotal(rs.getDouble("a.precioTotal"));
				
				OrdenCompraBean orden=new OrdenCompraBean();
				orden.setIdOrdenCompra(rs.getString("b.idOrdenCompra"));
				orden.setFechEmision(rs.getString("fechaEmision"));
				orden.setEstadoOrdCom(rs.getString("b.estadoOrdCom"));
				orden.setMontoTotal(rs.getDouble("b.montoTotal"));
				orden.setPersona_idPersona(rs.getInt("b.persona_idPersona"));
				orden.setProveedor_idProveedor(rs.getString("b.proveedor_idProveedor"));
				orden.setProveedor(objprove);
				objdetalle.setOrden(orden);
				
				TipoPrendaBean tipoPrenda=new TipoPrendaBean();
				tipoPrenda.setIdTip(rs.getInt("c.idTip"));
				tipoPrenda.setNomTip(rs.getString("c.nomTip"));
				
				objdetalle.setTipoPrenda(tipoPrenda);
				
				detalleOrden.add(objdetalle);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return detalleOrden ;
	}

	@Override
	public boolean registrarIngresoOrden(String idOrden) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="update ordencompra set estadoOrdCom='2' where idOrdenCompra='"+idOrden+"';";
			int filas=stmt.executeUpdate(query);
			if(filas==1){
				flag=true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public Vector<OrdenCompraBean> listarOrdenesCompraIngresadas()
			throws Exception {
		// TODO Auto-generated method stub
		Vector<OrdenCompraBean> ordenes = new Vector<OrdenCompraBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="SELECT idOrdenCompra,date(fechEmision) as fechEmision,estadoOrdCom,montoTotal,persona_idPersona,proveedor_idProveedor FROM ordencompra where estadoOrdCom='2' order by fechEmision desc";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			OrdenCompraBean objorden=null;
			while( rs.next() ){
				objorden=new OrdenCompraBean();
				objorden.setIdOrdenCompra(rs.getString("idOrdenCompra"));
				objorden.setFechEmision(rs.getString("fechEmision"));
				objorden.setEstadoOrdCom(rs.getString("estadoOrdCom"));
				objorden.setMontoTotal(rs.getDouble("montoTotal"));
				objorden.setPersona_idPersona(rs.getInt("persona_idPersona"));
				objorden.setProveedor_idProveedor(rs.getString("proveedor_idProveedor"));
				
				ordenes.add(objorden);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return ordenes;
	}

}
