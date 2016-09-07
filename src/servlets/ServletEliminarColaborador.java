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
import beans.UsuarioBean;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletEliminarColaborador
 */
@WebServlet("/EliminarColaborador")
public class ServletEliminarColaborador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarColaborador() {
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
				if(user.getRol().getNomTipo().equals("Asistente")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Usuario usuariodao=dao.getUsuarioDao();
						boolean flag=usuariodao.eliminarUsuario(Integer.parseInt(request.getParameter("id")));
						if(flag){
							request.setAttribute("msj1", "Eliminación exitosa");
						}else{
							request.setAttribute("msj2", "Error. No se elimino el colaborador");
						}
						Vector<UsuarioBean> colaboradores= usuariodao.listarUsuarios();
						if(colaboradores.size()>0){
							request.setAttribute("colaboradores", colaboradores);
						}else{
							request.setAttribute("msj", "No hay ningun colaborador registrado");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/Asistente/MantenerCuentasTemporales.jsp").forward(request, response);
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
