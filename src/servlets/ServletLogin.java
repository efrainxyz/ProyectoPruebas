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


@WebServlet("/Login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean) session.getAttribute("usuario");
		
		if(user!=null){
			try {
				if(user.getRol().getNomTipo().equals("Colaborador")){
					request.getRequestDispatcher("/Colaborador/Inicio.jsp").forward(request, response);
				}else if(user.getRol().getNomTipo().equals("Administrador")){
					request.getRequestDispatcher("/Administrador/Inicio.jsp").forward(request, response);
				}else if(user.getRol().getNomTipo().equals("EncargadoCompras")){
					request.getRequestDispatcher("/EncargadoCompras/Inicio.jsp").forward(request, response);
				}else if(user.getRol().getNomTipo().equals("Courier")){
					request.getRequestDispatcher("/Courier/Inicio.jsp").forward(request, response);
				}else if(user.getRol().getNomTipo().equals("Asistente")){
					request.getRequestDispatcher("/Asistente/Inicio.jsp").forward(request, response);
				}
			} catch (Exception e) {
				out.println(e.getMessage());
			}
		}else{
			request.setAttribute("mensaje", "Ingrese nuevamente.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try{
			
			String uss=request.getParameter("user").replaceAll("'or'1'='1", "");
			String pass= request.getParameter("pass").replaceAll("'or'1'='1", "");
			if(uss.equals("")||pass.equals("")){
				request.setAttribute("mensaje", "Ingrese su usuario y clave");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Usuario usuariodao = dao.getUsuarioDao();
				UsuarioBean ses=usuariodao.validar(uss,pass);
				if(ses!=null){
					HttpSession session= request.getSession();
					session.setMaxInactiveInterval(-1);
					session.setAttribute("usuario", ses);
					if(ses.getRol().getNomTipo().equals("Colaborador")){
						request.getRequestDispatcher("/Colaborador/Inicio.jsp").forward(request, response);
					}else if(ses.getRol().getNomTipo().equals("Administrador")){
						request.getRequestDispatcher("/Administrador/Inicio.jsp").forward(request, response);
					}else if(ses.getRol().getNomTipo().equals("EncargadoCompras")){
						request.getRequestDispatcher("/EncargadoCompras/Inicio.jsp").forward(request, response);
					}else if(ses.getRol().getNomTipo().equals("Courier")){
						request.getRequestDispatcher("/Courier/Inicio.jsp").forward(request, response);
					}else if(ses.getRol().getNomTipo().equals("Asistente")){
						request.getRequestDispatcher("/Asistente/Inicio.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("mensaje", "Datos Incorrectos");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}
		}catch (Exception e) {
			out.print(e.getMessage());
		}	
	}
}
