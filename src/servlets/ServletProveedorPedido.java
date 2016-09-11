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
 * Servlet implementation class ServletProveedorPedido
 */
@WebServlet("/ProveedorPedido")
public class ServletProveedorPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProveedorPedido() {
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
						Vector<ProveedorBean> proveedor= proveedordao.listarProveedorXEstado();
						if(proveedor.size()>0){
							request.setAttribute("proveedor", proveedor);
						}else{
							request.setAttribute("msj", "No hay proveedores registrados.");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					
					request.getRequestDispatcher("/EncargadoCompras/BuscarProveedor.jsp").forward(request, response);
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
	}

}
