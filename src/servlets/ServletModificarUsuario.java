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

import beans.PersonaBean;
import beans.PrendaBean;
import beans.TipoPrendaBean;
import beans.UsuarioBean;
import dao.interfaces.I_Prenda;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ListarTallasColaboradores
 */
@WebServlet("/modificarUsuario")
public class ServletModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarUsuario() {
        super();
       
    }

	/**
	 * Esto es lo que mostrara al JSP por el metodo doGEt.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean) session.getAttribute("usuario");
		if(user!=null){
			try {
				if(user.getRol().getNomTipo().equals("Administrador")){
					
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Usuario usuario=dao.getUsuarioDao();
					
					PersonaBean persona = new PersonaBean();
					persona.setNomPer(request.getParameter("nombre"));
					persona.setApePat(request.getParameter("apepat"));
					persona.setApeMat(request.getParameter("apemat"));
					persona.setDirecPer(request.getParameter("direccion"));
					persona.setTelefono(request.getParameter("telefono"));
					persona.setCorreo(request.getParameter("correo"));
					
					UsuarioBean usu = new UsuarioBean();
					usu.setUsuario(request.getParameter("usuario"));
					usu.setCodUsuario(Integer.parseInt(request.getParameter("id")));
					usu.setPersona(persona);
					
					boolean flag=usuario.modificarUsuario2(usu);
					
					if(flag){
						request.setAttribute("msj1", "Modificacion exitosa");
					}else{
						request.setAttribute("msj2", "Error. No se modifico el asistente");
					}
					
					Vector<UsuarioBean> usuarios= usuario.listarUsuarios2();
					if(usuarios.size()>0){
						request.setAttribute("usuario", usuarios);
					}else{
						request.setAttribute("msj", "No hay prendas registradas");
					}
					request.getRequestDispatcher("/Administrador/MantenerAsistente.jsp").forward(request, response);
					
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
