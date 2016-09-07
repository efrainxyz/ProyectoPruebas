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
import beans.OrdenCompraBean;
import beans.UsuarioBean;
import dao.interfaces.I_OrdenCompra;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletHistorialCompras
 */
@WebServlet("/HistorialCompras")
public class ServletHistorialCompras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletHistorialCompras() {
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
						I_OrdenCompra ordendao=dao.getOrdenCompraDao();
						Vector<OrdenCompraBean> ordenesCompra= ordendao.listarOrdenesCompra();
						if(ordenesCompra.size()>0){
							request.setAttribute("ordenesCompra", ordenesCompra);
						}else{
							request.setAttribute("msj", "No hay ninguna orden de compra registrada");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/EncargadoCompras/ListarOrdenesCompra.jsp").forward(request, response);
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
