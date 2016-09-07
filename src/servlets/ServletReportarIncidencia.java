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
import beans.Detalle_IncidenciaBean;
import beans.IncidenciaBean;
import beans.ProveedorBean;
import beans.TipoPrendaBean;
import beans.UsuarioBean;
import dao.interfaces.I_Incidencia;
import dao.interfaces.I_Prenda;
import dao.interfaces.I_Proveedor;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletReportarIncidencia
 */
@WebServlet("/ReportarIncidencia")
public class ServletReportarIncidencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportarIncidencia() {
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
						I_Proveedor proveedordao=dao.getProveedorDao();
						I_Prenda prendadao=dao.getPrendaDao();
						ProveedorBean proveedor=proveedordao.obtenerProveedor();
						Vector<TipoPrendaBean> prendas= prendadao.listarTiposPrenda();
						if(prendas.size()>0){
							request.setAttribute("prendas", prendas);
						}
						if(proveedor!=null){
							request.setAttribute("proveedor", proveedor);
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					
					request.getRequestDispatcher("/Courier/ReporteIncidencia.jsp").forward(request, response);
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
						I_Incidencia incidenciadao=dao.getIncidenciaDao();
						I_Proveedor proveedordao=dao.getProveedorDao();
						I_Prenda prendadao=dao.getPrendaDao();
						ProveedorBean proveedor=proveedordao.obtenerProveedor();
						Vector<TipoPrendaBean> prendas= prendadao.listarTiposPrenda();
						if(prendas.size()>0){
							request.setAttribute("prendas", prendas);
						}
						if(proveedor!=null){
							request.setAttribute("proveedor", proveedor);
						}
						boolean flag=false;
						String [] codigosPrenda=request.getParameterValues("codigoPrenda");
						String [] tallas=request.getParameterValues("tallaPrenda");
						String [] cantidades=request.getParameterValues("cantidadPrenda");
						String [] descripciones=request.getParameterValues("descripcionIncidencia");
						String nroIncidencia=incidenciadao.generarNroIncidencia();
						if(codigosPrenda.length>0){
							IncidenciaBean incidencia=new IncidenciaBean();
							incidencia.setIdIncidencia(nroIncidencia);
							incidencia.setIdOrdenCompra(request.getParameter("nroOrden"));
							incidencia.setIdPersona(user.getCodUsuario());
							Vector<Detalle_IncidenciaBean> detalleincidencia=new Vector<Detalle_IncidenciaBean>();
							for(int i=0;i<codigosPrenda.length;i++){
								Detalle_IncidenciaBean detalle=new Detalle_IncidenciaBean();
								detalle.setId_incidencia(nroIncidencia);
								detalle.setId_tipoprenda(Integer.parseInt(codigosPrenda[i]));
								detalle.setCantidad(Integer.parseInt(cantidades[i]));
								detalle.setTalla(tallas[i]);
								detalle.setDescripcion(descripciones[i]);
								
								detalleincidencia.add(detalle);
							}
							flag=incidenciadao.registrarIncidenciaOrdenCompra(incidencia, detalleincidencia);
						}
						if(flag){
							request.setAttribute("msj1","Reporte de Incidencia Nro. "+nroIncidencia+" registrado exitosamente");
						}else{
							request.setAttribute("msj2","No se pudo registrar el reporte de incidencia, intente nuevamente");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					
					request.getRequestDispatcher("/Courier/ReporteIncidencia.jsp").forward(request, response);
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
