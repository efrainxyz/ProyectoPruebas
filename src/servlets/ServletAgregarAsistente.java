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
import beans.PersonaBean;
import beans.SedeBean;
import beans.TipoPrendaBean;
import beans.UsuarioBean;
import dao.interfaces.I_OrdenCompra;
import dao.interfaces.I_Prenda;
import dao.interfaces.I_Proveedor;
import dao.interfaces.I_SedeReparto;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;
/**
 * Servlet implementation class ServletAgregarPrenda
 */
@WebServlet("/AgregarAsistente")
public class ServletAgregarAsistente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAgregarAsistente() {
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
					I_SedeReparto sedes=dao.getSedeRepartoDao();
					Vector<SedeBean> sede= sedes.listarSedes();
					if(sede.size()>0){
						request.setAttribute("sede", sede);
					}else{
						request.setAttribute("msj", "No hay ordenes de compra registradas");
					}
						
					request.getRequestDispatcher("/Administrador/AgregarAsistente.jsp").forward(request, response);
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
		
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean) session.getAttribute("usuario");
		if(user!=null){
			try {
				if(user.getRol().getNomTipo().equals("Administrador")){
					
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Usuario usu=dao.getUsuarioDao();
					PersonaBean persona= new PersonaBean();
					
					String accion=request.getParameter("accion");
					if(accion.equals("verificar")){
						System.out.println(Integer.parseInt(request.getParameter("codusuario")));
						
						persona=usu.obtenerPersona(Integer.parseInt(request.getParameter("codusuario")));
						 if(persona==null){
								 request.setAttribute("msj1","Usuario no disponible , intente de nuavamente.");
								 request.getRequestDispatcher("/Administrador/AgregarAsistente.jsp").forward(request, response);
								 
							 }else{
								 request.setAttribute("msj2","Usuario Disponible!");
								 request.setAttribute("codusuario", persona.getIdPersona());
								 request.setAttribute("correo",persona.getCorreo());
								 request.getRequestDispatcher("/Administrador/AgregarAsistente.jsp").forward(request, response);
							 }
							
					}else if(accion.equals("agregar")){
						
							UsuarioBean usuario = new UsuarioBean();
							usuario.setCodUsuario(Integer.parseInt(request.getParameter("codusuario2")));
							usuario.setUsuario(request.getParameter("usuario"));
							usuario.setClave(request.getParameter("clave"));
							
							boolean flag=usu.agregar(usuario);
							
							if(flag){
								request.setAttribute("msj1","El asistente se agrego con éxito.");
							}else{
								request.setAttribute("msj2","El asistente no ha sido agregado.");
							}
							
							Vector<UsuarioBean> usuarios= usu.listarUsuarios2();
							if(usuarios.size()>0){
								request.setAttribute("usuario", usuarios);
								
							}else{
								request.setAttribute("msj", "No hay asistentes registrados");
							}
							
							request.getRequestDispatcher("/Administrador/MantenerAsistente.jsp").forward(request, response);
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
