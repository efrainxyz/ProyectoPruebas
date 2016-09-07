package daofactory;
import dao.interfaces.*;

public abstract class DAOFactory {

	public static final int MYSQL =1;
	public static final int SQLSERVER =2;
	public static final int ORACLE =3;
		
	public static DAOFactory getDAOFactory(int factory){
		switch(factory){
		case MYSQL: return new MySQLDaoFactory();
		case SQLSERVER:
			//return new SQLSERVERDAOFactory();
		case ORACLE:
			//return new ORACLEDAOFactory();
		default:
			return null;
		}
	}
	
	public abstract I_Usuario getUsuarioDao();
	public abstract I_Prenda getPrendaDao();
	public abstract I_Proveedor getProveedorDao();
	public abstract I_OrdenCompra getOrdenCompraDao();
	public abstract I_Incidencia getIncidenciaDao();
	public abstract I_SedeReparto getSedeRepartoDao();
}