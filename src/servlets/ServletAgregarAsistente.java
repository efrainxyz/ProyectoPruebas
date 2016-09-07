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
					
					System.out.println(request.getParameter("dni")+" "+request.getParameter("nombre")+" "
							+request.getParameter("apepat")+" "+request.getParameter("apemat")+" "
							+request.getParameter("sexo")+" "+request.getParameter("direccion")+" "
							+request.getParameter("telefono")+" "+request.getParameter("correo")+" "
							+request.getParameter("sede"));
					
					PersonaBean persona= new PersonaBean();
					persona.setDni(request.getParameter("dni"));
					persona.setNomPer(request.getParameter("nombre"));
					persona.setApePat(request.getParameter("apepat"));
					persona.setApeMat(request.getParameter("apemat"));
					persona.setSexo(request.getParameter("sexo"));
					persona.setDirecPer(request.getParameter("direccion"));
					persona.setTelefono(request.getParameter("telefono"));
					persona.setCorreo(request.getParameter("correo"));
					persona.setSede_idSede(Integer.parseInt(request.getParameter("sede")));
					
					UsuarioBean usuario = new UsuarioBean();
					usuario.setPersona(persona);
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
