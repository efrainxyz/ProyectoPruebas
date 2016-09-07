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

import beans.TipoPrendaBean;
import beans.UsuarioBean;
import dao.interfaces.I_Prenda;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletEliminarTipoPrenda
 */
@WebServlet("/estadoTipoPrenda")
public class ServletEstadoTipoPrenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEstadoTipoPrenda() {
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
					I_Prenda prendadao=dao.getPrendaDao();
			
					boolean flag= prendadao.estadoTipoPrenda(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("estado")));
					
					if(flag){
						request.setAttribute("msj1", "Eliminación exitosa");
					}else{
						request.setAttribute("msj2", "Error. No se elimino el colaborador");
					}
		
					
					Vector<TipoPrendaBean> prendas= prendadao.listarTiposPrenda();
					if(prendas.size()>0){
						request.setAttribute("prendas", prendas);
					}else{
						request.setAttribute("msj", "No hay prendas registradas");
					}
					
					request.getRequestDispatcher("/Administrador/MantenerPrenda.jsp").forward(request, response);
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
