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
import beans.PrendaBean;
import beans.TipoPrendaBean;
import beans.UsuarioBean;
import dao.interfaces.I_Prenda;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class DetalleColaboradorPrenda
 */
@WebServlet("/DetalleColaboradorPrenda")
public class ServletDetalleColaboradorPrenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDetalleColaboradorPrenda() {
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
				if(user.getRol().getNomTipo().equals("Administrador")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Prenda prendadao=dao.getPrendaDao();
						I_Usuario usuariodao=dao.getUsuarioDao();
						Vector<TipoPrendaBean> prendas= prendadao.listarTiposPrenda();
						if(prendas.size()>0){
							request.setAttribute("prendas", prendas);
							Vector<PrendaBean> prendasColaborador= prendadao.obtenerTallaColaborador(Integer.parseInt(request.getParameter("id")));
							UsuarioBean colaborador=usuariodao.obtenerUsuario(Integer.parseInt(request.getParameter("id")));
							if(prendasColaborador.size()>0){
								request.setAttribute("prendasColaborador", prendasColaborador);
								request.setAttribute("colaborador", colaborador);
							}else{
								request.setAttribute("msj", "Error no se encontro detalle");
							}
						}else{
							request.setAttribute("msj", "No hay prendas registradas");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					
					request.getRequestDispatcher("/Administrador/DetalleTallas.jsp").forward(request, response);
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
