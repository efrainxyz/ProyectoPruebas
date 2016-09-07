package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DetalleOrdenBean;
import beans.OrdenCompraBean;
import beans.ProveedorBean;
import beans.Proveedor_TipoPrendaBean;
import beans.UsuarioBean;
import dao.interfaces.I_OrdenCompra;
import dao.interfaces.I_Proveedor;
import dao.interfaces.I_SedeReparto;
import daofactory.DAOFactory;

/**
 * Servlet implementation class GenerarOrdenCompra
 */
@WebServlet("/GenerarOrdenCompra")
public class ServletGenerarOrdenCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGenerarOrdenCompra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean) session.getAttribute("usuario");
		if(user!=null){
			try {
				if(user.getRol().getNomTipo().equals("EncargadoCompras")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Proveedor proveedordao=dao.getProveedorDao();
						ProveedorBean proveedor=proveedordao.obtenerProveedor();
						if(proveedor!=null){
							request.setAttribute("proveedor", proveedor);
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					
					request.getRequestDispatcher("/EncargadoCompras/OrdenCompra.jsp").forward(request, response);
				}else{
					request.setAttribute("mensaje", "Ingrese nuevamente.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				out.println(e.getMessage());
			}
		}else{
			request.setAttribute("mensaje", "Ingrese nuevamente.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean) session.getAttribute("usuario");
		if(user!=null){
			try {
				if(user.getRol().getNomTipo().equals("EncargadoCompras")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Proveedor proveedordao=dao.getProveedorDao();
						I_OrdenCompra ordencompradao=dao.getOrdenCompraDao();
						I_SedeReparto sederepartodao=dao.getSedeRepartoDao();
						ProveedorBean proveedor=proveedordao.obtenerProveedor();
						if(proveedor!=null){
							request.setAttribute("proveedor", proveedor);
						}
						boolean flag=false;
						String [] codigosPrenda=request.getParameterValues("codigoPrenda");
						String [] tallas=request.getParameterValues("tallaPrenda");
						String [] cantidades=request.getParameterValues("cantidadPrenda");
						double montoTotal=Double.parseDouble(request.getParameter("montoTotal"));
						int idProveedor=Integer.parseInt(request.getParameter("idProveedor"));
						String nroOrden=ordencompradao.generarNroOrdenCompra();
						if(codigosPrenda.length>0){
							OrdenCompraBean orden=new OrdenCompraBean();
							orden.setIdOrdenCompra(nroOrden);
							orden.setMontoTotal(montoTotal);
							orden.setProveedor_idProveedor(idProveedor);
							orden.setPersona_idPersona(user.getCodUsuario());
							Vector<DetalleOrdenBean> detalleorden=new Vector<DetalleOrdenBean>();
							for(int i=0;i<codigosPrenda.length;i++){
								Proveedor_TipoPrendaBean prendaDatos=proveedordao.obtenerDatosPrenda(Integer.parseInt(codigosPrenda[i]));
								DetalleOrdenBean detalle=new DetalleOrdenBean();
								detalle.setOrdenCompra_idOrdenCompra(nroOrden);
								detalle.setIdTipoPrenda(Integer.parseInt(codigosPrenda[i]));
								detalle.setCantidad(Integer.parseInt(cantidades[i]));
								detalle.setPrecioTotal(prendaDatos.getPrecioUnitario()*Integer.parseInt(cantidades[i]));
								detalle.setPrecioUnit(prendaDatos.getPrecioUnitario());
								detalle.setTallaPrenda(tallas[i]);
								
								detalleorden.add(detalle);
							}
							flag=ordencompradao.registarOrdenCompra(orden, detalleorden);
							sederepartodao.registrarSedeStock();
						}
						if(flag){
							request.setAttribute("msj1","Orden de Compra generada con Nro."+nroOrden);
						}else{
							request.setAttribute("msj2","Orden de Compra no generada");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					
					request.getRequestDispatcher("/EncargadoCompras/OrdenCompra.jsp").forward(request, response);
				}else{
					request.setAttribute("mensaje", "Ingrese nuevamente.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				out.println(e.getMessage());
			}
		}else{
			request.setAttribute("mensaje", "Ingrese nuevamente.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
