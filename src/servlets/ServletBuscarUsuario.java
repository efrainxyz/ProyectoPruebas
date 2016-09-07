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
import beans.TipoPrendaBean;
import beans.UsuarioBean;
import dao.interfaces.I_OrdenCompra;
import dao.interfaces.I_Prenda;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;
/**
 * Servlet implementation class ServletAgregarPrenda
 */
@WebServlet("/buscarUsuario")
public class ServletBuscarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean) session.getAttribute("usuario");
		if(user!=null){
			try {
				if(user.getRol().getNomTipo().equals("Administrador")){
					
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Usuario usuariodao=dao.getUsuarioDao();
					
					int id=Integer.parseInt( request.getParameter("id"));
					
					UsuarioBean usuario= usuariodao.obtenerUsuario(id);
					
					request.setAttribute("usus",usuario);
					System.out.println(usuario.getPersona().getNomPer());
					
					request.getRequestDispatcher("/Administrador/BuscarAsistente.jsp").forward(request, response);
					
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
	
	}

}
