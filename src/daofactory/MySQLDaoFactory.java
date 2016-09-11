package daofactory;
import java.sql.Connection;
import java.sql.DriverManager;

import dao.interfaces.*;
import dao.mysql.*;

public class MySQLDaoFactory extends DAOFactory{
		
	public static Connection obtenerConexion(){
		
		Connection con=null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/base de datos de sw2";
			String user = "root";
			String password ="root";
			con = DriverManager.getConnection(url,user,password);
		
		} catch (Exception e) {
		// TODO: handle exception
		
			System.out.print(e.getMessage());
			System.out.print("Connection Failed!");
			e.printStackTrace();		
		}
		return con;
	
	}
	
	@Override
	public I_Usuario getUsuarioDao() {
		// TODO Auto-generated method stub
		return new MySql_UsuarioDao();
	}
	
	@Override
	public I_Prenda getPrendaDao() {
		// TODO Auto-generated method stub
		return new MySql_PrendaDao();
	}
	
	@Override
	public I_Proveedor getProveedorDao() {
		// TODO Auto-generated method stub
		return new MySql_ProveedorDao();
	}
	
	@Override
	public I_OrdenCompra getOrdenCompraDao() {
		// TODO Auto-generated method stub
		return new MySql_OrdenCompraDao();
	}
	
	@Override
	public I_Incidencia getIncidenciaDao() {
		// TODO Auto-generated method stub
		return new MySql_IncidenciaDao();
	}
	
	@Override
	public I_SedeReparto getSedeRepartoDao() {
		// TODO Auto-generated method stub
		return new MySql_SedeRepartoDao();
	}
}
