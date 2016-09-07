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

import dao.interfaces.I_Prenda;
import dao.interfaces.I_SedeReparto;
import daofactory.DAOFactory;
import beans.SedeBean;
import beans.SedeXTipoPrendaBean;
import beans.TipoPrendaBean;
import beans.UsuarioBean;

/**
 * Servlet implementation class ServletActualizarStockPrenda
 */
@WebServlet("/ActualizarStockPrenda")
public class ServletActualizarStockPrenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActualizarStockPrenda() {
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
				if(user.getRol().getNomTipo().equals("Courier")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_SedeReparto sederepartodao=dao.getSedeRepartoDao();
						I_Prenda prendadao=dao.getPrendaDao();
						Vector<SedeXTipoPrendaBean> stockPrendas=sederepartodao.listarStockPrendas();
						Vector<SedeXTipoPrendaBean> prendasSede=sederepartodao.listarTotalPrendasXSede();
						Vector<TipoPrendaBean> tiposPrenda=prendadao.listarTiposPrenda();
						Vector<SedeBean> listaSedes=sederepartodao.listarSedesaEntregar();
						if(prendasSede.size()>0){
							request.setAttribute("prendasSede",prendasSede);
							request.setAttribute("tiposPrenda",tiposPrenda);
							request.setAttribute("stockPrendas",stockPrendas);
							request.setAttribute("listaSedes",listaSedes);
							if(stockPrendas.size()<=0){
								request.setAttribute("msj2","Todas las prendas fueron entregadas");
							}
						}else{
							request.setAttribute("msj1","Orden de compra no ha sido generada");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/Courier/ActualizarStockPrendas.jsp").forward(request, response);
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
				if(user.getRol().getNomTipo().equals("Courier")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_SedeReparto sederepartodao=dao.getSedeRepartoDao();
						I_Prenda prendadao=dao.getPrendaDao();
						boolean flag=false;
						flag=sederepartodao.actualizarEstadoEntrega(Integer.parseInt(request.getParameter("id")));
						if(flag){
							request.setAttribute("msj","Actualización exitosa");
						}else{
							request.setAttribute("msj3","Error. Intentelo nuevamente");
						}
						Vector<SedeXTipoPrendaBean> stockPrendas=sederepartodao.listarStockPrendas();
						Vector<SedeXTipoPrendaBean> prendasSede=sederepartodao.listarTotalPrendasXSede();
						Vector<TipoPrendaBean> tiposPrenda=prendadao.listarTiposPrenda();
						Vector<SedeBean> listaSedes=sederepartodao.listarSedesaEntregar();
						if(prendasSede.size()>0){
							request.setAttribute("prendasSede",prendasSede);
							request.setAttribute("tiposPrenda",tiposPrenda);
							request.setAttribute("stockPrendas",stockPrendas);
							request.setAttribute("listaSedes",listaSedes);
							if(stockPrendas.size()<=0){
								request.setAttribute("msj2","Todas las prendas fueron entregadas");
							}
						}else{
							request.setAttribute("msj1","Orden de compra no ha sido generada");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/Courier/ActualizarStockPrendas.jsp").forward(request, response);
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
