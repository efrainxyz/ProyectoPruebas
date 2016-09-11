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

import beans.ProveedorBean;
import beans.UsuarioBean;
import dao.interfaces.I_Proveedor;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletModificarProveedores
 */
@WebServlet("/ModificarProveedor")
public class ServletModificarProveedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarProveedor() {
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
						ProveedorBean proveedor= proveedordao.buscarProveedor2(request.getParameter("id"));
											
						if(proveedor!=null){
							request.setAttribute("proveedor", proveedor);
						}else{
							request.setAttribute("msj", "No existe el proveedor.");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/EncargadoCompras/ModificarProveedor.jsp").forward(request, response);
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
						ProveedorBean proveedorModificado=new ProveedorBean();
						proveedorModificado.setIdProveedor(request.getParameter("id"));
						proveedorModificado.setRazonSoc(request.getParameter("rsMod"));
						proveedorModificado.setDirecProve(request.getParameter("dirMod"));
						proveedorModificado.setTelefono(request.getParameter("telMod"));
						
						boolean flag=proveedordao.modificarProveedor(proveedorModificado);
						
						if(flag){
							request.setAttribute("msj1", "Actualizacion satisfactoria.");
						}else{
							request.setAttribute("msj2", "No se pudo actualizar los datos.");
						}
						
						ProveedorBean proveedor= proveedordao.buscarProveedor2(request.getParameter("id"));
						if(proveedor!=null){
							request.setAttribute("proveedor", proveedor);
						}else{
							request.setAttribute("msj", "No existe el proveedor.");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/EncargadoCompras/ModificarProveedor.jsp").forward(request, response);
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
