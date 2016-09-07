package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ServletModificarColaborador
 */
@WebServlet("/ModificarColaborador")
public class ServletModificarColaborador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarColaborador() {
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
						UsuarioBean colaborador= usuariodao.obtenerUsuario(Integer.parseInt(request.getParameter("id")));
						if(colaborador!=null){
							request.setAttribute("colaborador", colaborador);
						}else{
							request.setAttribute("msj", "No existe el colaborador");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/Asistente/ModificarColaborador.jsp").forward(request, response);
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
				if(user.getRol().getNomTipo().equals("Asistente")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Usuario usuariodao=dao.getUsuarioDao();
						UsuarioBean colaboradorModificado=new UsuarioBean();
						colaboradorModificado.setCodUsuario(Integer.parseInt(request.getParameter("id")));
						colaboradorModificado.setUsuario(request.getParameter("usuarioColaborador"));
						colaboradorModificado.setClave(request.getParameter("claveColaborador"));
						colaboradorModificado.setEstadoUsu(Integer.parseInt(request.getParameter("estadoColaborador")));
						
						boolean flag=usuariodao.modificarUsuario(colaboradorModificado, request.getParameter("correoColaborador"));
						
						if(flag){
							request.setAttribute("msj1", "Actualizacion satisfactorio");
						}else{
							request.setAttribute("msj2", "No se pudo actualizar los datos");
						}
						
						UsuarioBean colaborador= usuariodao.obtenerUsuario(Integer.parseInt(request.getParameter("id")));
						if(colaborador!=null){
							request.setAttribute("colaborador", colaborador);
						}else{
							request.setAttribute("msj", "No existe el colaborador");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/Asistente/ModificarColaborador.jsp").forward(request, response);
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
