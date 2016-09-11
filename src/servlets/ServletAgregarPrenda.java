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
import dao.interfaces.I_Proveedor;
import daofactory.DAOFactory;
/**
 * Servlet implementation class ServletAgregarPrenda
 */
@WebServlet("/AgregarPrenda")
public class ServletAgregarPrenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAgregarPrenda() {
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
				
					request.getRequestDispatcher("/Administrador/AgregarPrenda.jsp").forward(request, response);
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
					I_Prenda prendadao=dao.getPrendaDao();
					String nombre=request.getParameter("nuevonombre");
					
					
					boolean flag2=prendadao.existePrenda(nombre);
					if(flag2){
						System.out.println("pase por servelt existeprenda? ");
						request.setAttribute("msj","La prenda que desea agregar ya existe	");
						request.getRequestDispatcher("/Administrador/AgregarPrenda.jsp").forward(request, response);
					}else{
						
					TipoPrendaBean prenda = new TipoPrendaBean();
					prenda.setNomTip(request.getParameter("nuevonombre"));
					prenda.setDescripcion(request.getParameter("nuevadescripcion"));
					boolean flag=prendadao.agregarPrenda(prenda);
					
					if(flag){
						request.setAttribute("msj","La prenda se agrego con éxito.");
					}else{
						request.setAttribute("msj2","La prenda no ha sido agregada.");
					}
					
					Vector<TipoPrendaBean> prendas= prendadao.listarTiposPrenda();
					if(prendas.size()>0){
						request.setAttribute("prendas", prendas);
						
					}else{
						request.setAttribute("msj", "No hay prendas registradas");
					}
					
					request.getRequestDispatcher("/Administrador/MantenerPrenda.jsp").forward(request, response);
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
