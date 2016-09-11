package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.DetalleOrdenBean;
import beans.PersonaBean;
import beans.PrendaBean;
import beans.ProveedorBean;
import beans.Proveedor_TipoPrendaBean;
import beans.SedeBean;
import beans.TipoPrendaBean;
import beans.UsuarioBean;
import dao.interfaces.I_Proveedor;
import daofactory.MySQLDaoFactory;

public class MySql_ProveedorDao extends MySQLDaoFactory implements I_Proveedor{

	@Override
	public ProveedorBean obtenerProveedor() throws Exception {
		// TODO Auto-generated method stub
		ProveedorBean objProveedor = null;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from proveedor";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			
			if(rs.next()){
				objProveedor= new ProveedorBean();
				objProveedor.setIdProveedor(rs.getString("idProveedor"));
				objProveedor.setRazonSoc(rs.getString("razonSoc"));
				objProveedor.setDirecProve(rs.getString("direcProve"));
				objProveedor.setTelefono(rs.getString("telefono"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return objProveedor;	
	}

	@Override
	public Vector<DetalleOrdenBean> obtenerListaPrendas() throws Exception {
		// TODO Auto-generated method stub
		Vector<DetalleOrdenBean> prendas = new Vector<DetalleOrdenBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="SELECT b.tipoPrenda_idTip,c.nomTip, b.talla, a.precioUnitario, count(*) *a.precioUnitario as  precioTotal, count(*) as cantidad FROM proveedor_tipoprenda a INNER JOIN prenda b ON a.idTipoPrenda=b.tipoPrenda_idTip INNER JOIN tipoprenda c ON b.tipoPrenda_idTip=c.idTip group by b.talla, b.tipoPrenda_idTip order by b.tipoPrenda_idTip,b.talla";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			DetalleOrdenBean objprenda=null;
			while( rs.next() ){
				objprenda=new DetalleOrdenBean();
				objprenda.setIdTipoPrenda(rs.getInt("b.tipoPrenda_idTip"));
				objprenda.setCantidad(rs.getInt("cantidad"));
				objprenda.setPrecioUnit(rs.getDouble("a.precioUnitario"));
				objprenda.setPrecioTotal(rs.getDouble("precioTotal"));
				objprenda.setTallaPrenda(rs.getString("b.talla"));
				
				TipoPrendaBean objTipoPrenda=new TipoPrendaBean();
				objTipoPrenda.setNomTip(rs.getString("c.nomTip"));
				
				objprenda.setTipoPrenda(objTipoPrenda);
				
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
	public Proveedor_TipoPrendaBean obtenerDatosPrenda(int codigoPrenda)
			throws Exception {
		// TODO Auto-generated method stub
		Proveedor_TipoPrendaBean objPrenda = null;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from proveedor_tipoprenda a INNER JOIN proveedor b ON a.idProveedor=b.idProveedor INNER JOIN tipoprenda c ON a.idTipoPrenda=c.idTip where a.idTipoPrenda="+codigoPrenda;
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			
			if(rs.next()){
				objPrenda= new Proveedor_TipoPrendaBean();
				objPrenda.setIdProveedor(rs.getString("idProveedor"));
				objPrenda.setIdTipoPrenda(rs.getInt("idTipoPrenda"));
				objPrenda.setPrecioUnitario(rs.getDouble("precioUnitario"));
				
				ProveedorBean objProveedor=new ProveedorBean();
				objProveedor.setIdProveedor(rs.getString("idProveedor"));
				objProveedor.setRazonSoc(rs.getString("razonSoc"));
				objProveedor.setDirecProve(rs.getString("direcProve"));
				objProveedor.setTelefono(rs.getString("telefono"));
				objPrenda.setProveedor(objProveedor);
				
				TipoPrendaBean objTipoPrenda=new TipoPrendaBean();
				objTipoPrenda.setIdTip(rs.getInt("idTip"));
				objTipoPrenda.setNomTip(rs.getString("nomTip"));
				objPrenda.setTipoPrenda(objTipoPrenda);
				
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return objPrenda;
	}
	
	public Vector<ProveedorBean> listarProveedores() throws Exception {
		// TODO Auto-generated method stub
		Vector<ProveedorBean> proveedores = new Vector<ProveedorBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from proveedor";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			ProveedorBean objproveedor=null;
			while( rs.next() ){
				objproveedor= new ProveedorBean();
				objproveedor.setIdProveedor(rs.getString(1));
				objproveedor.setRazonSoc(rs.getString(2));
				objproveedor.setDirecProve(rs.getString(3));
				objproveedor.setTelefono(rs.getString(4));
				objproveedor.setEstado(rs.getInt(5));
				
				proveedores.add(objproveedor);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return proveedores;
	}
	
	public boolean agregarProveedor(ProveedorBean proveedor) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stm=con.createStatement();
			String query="insert into proveedor(idProveedor,razonSoc,direcProve,telefono) values('"+proveedor.getIdProveedor()+"','"+proveedor.getRazonSoc()+"','"+proveedor.getDirecProve()+"','"+proveedor.getTelefono()+"')";
			System.out.print(query);
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
	
	public Vector<ProveedorBean> buscarProveedor(String id_proveedor)
			throws Exception {
		// TODO Auto-generated method stub
		Vector<ProveedorBean> proveedor = new Vector<ProveedorBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from proveedor where idProveedor="+id_proveedor+"";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			ProveedorBean objproveedor=null;
			while( rs.next() ){
				objproveedor=new ProveedorBean();
				objproveedor.setIdProveedor(rs.getString("idProveedor"));
				objproveedor.setRazonSoc(rs.getString("razonSoc"));
				objproveedor.setDirecProve(rs.getString("direcProve"));
				objproveedor.setTelefono(rs.getString("telefono"));
				
				proveedor.add(objproveedor);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return proveedor;
	}
	
	public boolean eliminarProveedor(String idProveedor) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			Statement stmt = con.createStatement();
			String query = "delete from proveedor where idProveedor='"+idProveedor+"'";
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
	
	public ProveedorBean buscarProveedor2(String id_proveedor) throws Exception {
		// TODO Auto-generated method stub
		ProveedorBean objproveedor = null;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from proveedor where idProveedor='"+id_proveedor+"'";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			while(rs.next()){
				
				objproveedor=new ProveedorBean();
				objproveedor.setIdProveedor(rs.getString("idProveedor"));
				objproveedor.setRazonSoc(rs.getString("razonSoc"));
				objproveedor.setDirecProve(rs.getString("direcProve"));
				objproveedor.setTelefono(rs.getString("telefono"));
				
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return objproveedor;
	}
	
	public boolean modificarProveedor(ProveedorBean proveedor) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			Statement stmt = con.createStatement();
			String query = "update proveedor set razonSoc='"+proveedor.getRazonSoc()+"' ,direcProve='"+proveedor.getDirecProve()+"',telefono='"+proveedor.getTelefono()+"'  where idProveedor='"+proveedor.getIdProveedor()+"'";
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
	public boolean estadoProveedor(String id , int estado) throws Exception {
		boolean flag = false;
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			Statement stmt = con.createStatement();
			if(estado==1){
				String query = "update proveedor set estado='0' where idProveedor="+id;
				int fila =stmt.executeUpdate(query);
				if(fila==1){
					flag=true;
				}
			}else{
				String query = "update proveedor set estado='1' where idProveedor="+id;
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
	public Vector<ProveedorBean> listarProveedorXEstado() throws Exception {
		// TODO Auto-generated method stub
		Vector<ProveedorBean> proveedores = new Vector<ProveedorBean>();
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String query="select * from proveedor where estado='1'";
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(query);
			ProveedorBean objproveedor=null;
			while( rs.next() ){
				objproveedor= new ProveedorBean();
				objproveedor.setIdProveedor(rs.getString(1));
				objproveedor.setRazonSoc(rs.getString(2));
				objproveedor.setDirecProve(rs.getString(3));
				objproveedor.setTelefono(rs.getString(4));
				objproveedor.setEstado(rs.getInt(5));
				
				proveedores.add(objproveedor);
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return proveedores;
	}

}
