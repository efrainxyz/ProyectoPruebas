package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ProveedorBean;
import beans.UsuarioBean;
import dao.interfaces.I_Proveedor;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletRegistrarProveedor
 */
@WebServlet("/RegistrarProveedor")
public class ServletRegistrarProveedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrarProveedor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/EncargadoCompras/RegistrarProveedor.jsp").forward(request, response);
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
				if(user.getRol().getNomTipo().equals("EncargadoCompras")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Proveedor proveedordao=dao.getProveedorDao();
						Vector<ProveedorBean> proveedor= proveedordao.listarProveedores();
						System.out.print("llega aqui 1 ?  " +request.getParameter("ruc"));
		
						boolean flag=false;
						if(proveedor.size()>0){
							
							request.setAttribute("proveedor", proveedor);
							Vector<ProveedorBean> proveedorB= proveedordao.buscarProveedor(Integer.parseInt(request.getParameter("ruc")));
							if(proveedorB.size()>0){
								request.setAttribute("msj2", "Lo sentimos, este proveedor ya existe.");
							}else{
								System.out.print("llega aqui 3");
								ProveedorBean datosPr=new ProveedorBean();
								datosPr.setIdProveedor(Integer.parseInt(request.getParameter("ruc")));
								datosPr.setRazonSoc(request.getParameter("razonSoc"));;
								datosPr.setDirecProve(request.getParameter("direccion"));
								datosPr.setTelefono(request.getParameter("telefono"));
								flag=proveedordao.agregarProveedor(datosPr);
								
								
								if(flag){
									request.setAttribute("msj1", "Registro satisfactorio");
								}else{
									request.setAttribute("msj2", "Error en el registro de proveedor.");
								}
							}
						}else{
							request.setAttribute("msj", "Error.");
						}
					} catch (NumberFormatException  e) {
						System.out.print(e.getMessage());
						e.printStackTrace();
					}
					request.getRequestDispatcher("/EncargadoCompras/RegistrarProveedor.jsp").forward(request, response);
				}else{
					request.setAttribute("mensaje", "Ingrese nuevamente.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				out.println(e.getMessage());
				e.printStackTrace();
			}
		}else{
			request.setAttribute("mensaje", "Ingrese nuevamente.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	public static void main(String[] args) {
		Integer a = new Integer("12345678909");
		System.out.print(a);
	}
	

}
