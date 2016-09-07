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
import daofactory.DAOFactory;

/**
 * Servlet implementation class RegistrarTallas
 */
@WebServlet("/RegistrarTallas")
public class ServletRegistrarTallas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrarTallas() {
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
				if(user.getRol().getNomTipo().equals("Colaborador")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Prenda prendadao=dao.getPrendaDao();
						Vector<TipoPrendaBean> prendas= prendadao.listarTiposPrendaColaborador();
						if(prendas.size()>0){
							request.setAttribute("prendas", prendas);
						}else{
							request.setAttribute("msj", "No hay prendas registradas");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/Colaborador/RegistrarTallas.jsp").forward(request, response);
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
				if(user.getRol().getNomTipo().equals("Colaborador")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Prenda prendadao=dao.getPrendaDao();
						Vector<TipoPrendaBean> prendas= prendadao.listarTiposPrenda();
						boolean flag=false;
						if(prendas.size()>0){
							request.setAttribute("prendas", prendas);
							Vector<PrendaBean> prendasColaborador= prendadao.obtenerTallaColaborador(user.getCodUsuario());
							if(prendasColaborador.size()>0){
								request.setAttribute("msj2", "Lo siento, usted ya realizo el registro de sus tallas");
							}else{
								for(int i=0;i<prendas.size();i++){
									PrendaBean prenda=new PrendaBean();
									prenda.setTalla(request.getParameter("talla"+prendas.get(i).getIdTip()));
									prenda.setPersona_idPersona(user.getCodUsuario());
									prenda.setTipoPrenda_idTip(prendas.get(i).getIdTip());
									flag=prendadao.agregarTallaColaborador(prenda);
								}
								if(flag){
									request.setAttribute("msj1", "Registro satisfactorio");
								}else{
									request.setAttribute("msj2", "Error en el registro de tallas");
								}
							}
						}else{
							request.setAttribute("msj", "No hay prendas registradas");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/Colaborador/RegistrarTallas.jsp").forward(request, response);
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
