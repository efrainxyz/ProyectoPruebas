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
import beans.ProveedorBean;
import beans.UsuarioBean;
import dao.interfaces.I_OrdenCompra;
import dao.interfaces.I_Proveedor;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletRegistrarIngresoOrden
 */
@WebServlet("/RegistrarIngresoOrden")
public class ServletRegistrarIngresoOrden extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrarIngresoOrden() {
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
				if(user.getRol().getNomTipo().equals("Courier")){
					request.getRequestDispatcher("/Courier/IngresoOrden.jsp").forward(request, response);
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
				if(user.getRol().getNomTipo().equals("Courier")){
					String accion=request.getParameter("accion");
					if(accion.equals("ingresoOrden")){
						String redi="";
						try {
							DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
							I_Proveedor proveedordao=dao.getProveedorDao();
							I_OrdenCompra detalleordendao=dao.getOrdenCompraDao();
							ProveedorBean proveedor=proveedordao.obtenerProveedor();
							if(proveedor!=null){
								request.setAttribute("proveedor", proveedor);
							}
							Vector<DetalleOrdenBean> detalleorden= detalleordendao.ObtenerDetalleOrdenCompra(request.getParameter("id"));
							if(detalleorden.size()>0){
								request.setAttribute("detalleorden", detalleorden);
								redi="/Courier/RegistroIngresoOrden.jsp";
							}else{
								request.setAttribute("msj", "No existe orden de compra");
								redi="/Courier/IngresoOrden.jsp";
							}
						} catch (Exception e) {
							System.out.print(e.getMessage());
						}
						request.getRequestDispatcher(redi).forward(request, response);
					}else if(accion.equals("registroIngresoOrden")){
						try {
							DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
							I_Proveedor proveedordao=dao.getProveedorDao();
							I_OrdenCompra detalleordendao=dao.getOrdenCompraDao();
							ProveedorBean proveedor=proveedordao.obtenerProveedor();
							if(proveedor!=null){
								request.setAttribute("proveedor", proveedor);
							}
							boolean flag=detalleordendao.registrarIngresoOrden(request.getParameter("id"));
							
							if(flag){
								request.setAttribute("msj", "Orden de Compra actualizada, el estado se cambio de Pendiente ha Recibido");
							}else{
								request.setAttribute("msj1", "Error al Actualizar");
							}
							
							Vector<DetalleOrdenBean> detalleorden= detalleordendao.ObtenerDetalleOrdenCompra(request.getParameter("id"));
							if(detalleorden.size()>0){
								request.setAttribute("detalleorden", detalleorden);
							}else{
								request.setAttribute("msj2", "No existe orden de compra");
							}
						} catch (Exception e) {
							System.out.print(e.getMessage());
						}
						request.getRequestDispatcher("/Courier/RegistroIngresoOrden.jsp").forward(request, response);
					}
					
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
