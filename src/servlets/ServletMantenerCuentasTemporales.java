package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import beans.UsuarioBean;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletMantenerColaboradores
 */
@WebServlet("/MantenerCuentasTemporales")
public class ServletMantenerCuentasTemporales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMantenerCuentasTemporales() {
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
				if(user.getRol().getNomTipo().equals("Asistente")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Usuario usuariodao=dao.getUsuarioDao();
						Vector<UsuarioBean> colaboradores= usuariodao.listarUsuarios();
						if(colaboradores.size()>0){
							request.setAttribute("colaboradores", colaboradores);
						}else{
							request.setAttribute("msj", "No hay ningun colaborador registrado");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/Asistente/MantenerCuentasTemporales.jsp").forward(request, response);
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
				if(user.getRol().getNomTipo().equals("Asistente")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Usuario usuariodao=dao.getUsuarioDao();
						String [] codigosColaborador=request.getParameterValues("ids");
						int cantidadDias=Integer.parseInt(request.getParameter("cantidadDias"));
						boolean flag=false;
						Vector<UsuarioBean> listaaactivar=new Vector<UsuarioBean>();
						for(int i=0;i<codigosColaborador.length;i++){
							UsuarioBean colaborador=new UsuarioBean();
							colaborador.setCodUsuario(Integer.parseInt(codigosColaborador[i]));
							listaaactivar.add(colaborador);
						}
						flag=usuariodao.activarUsuarios(listaaactivar,cantidadDias);
						if(flag){
							for(int i=0;i<listaaactivar.size();i++){
								String correoUsuario="systemsevenperu@gmail.com";
								String password="systemseven7";
								UsuarioBean colaborador=usuariodao.obtenerUsuario(listaaactivar.get(i).getCodUsuario());
									Properties props = new Properties();
									props.setProperty("mail.smtp.host", "smtp.gmail.com");
						            props.setProperty("mail.smtp.starttls.enable", "true");
						            props.setProperty("mail.smtp.port", "587");
						            props.setProperty("mail.smtp.user", correoUsuario);
						            props.setProperty("mail.smtp.auth", "true");

						            // Preparamos la sesion
						            Session sessionCorreo = Session.getDefaultInstance(props);

						            // Construimos el mensaje
						            MimeMessage message = new MimeMessage(sessionCorreo);
						            message.setFrom(new InternetAddress(colaborador.getPersona().getCorreo()));
						            message.addRecipient(
						            Message.RecipientType.TO,new InternetAddress(colaborador.getPersona().getCorreo()));
						           
						            message.setSubject("Cuenta Temporal para el Registro de sus tallas de uniforme BCP");
						            message.setText("Saludos. Le comunicamos que su Cuenta Temporal para el registro de sus tallas de uniforme BCP es Usuario:"+colaborador.getUsuario()+" Clave:"+colaborador.getClave()+" , realizar el registro lo antes posible. Gracias");
						            // Lo enviamos.
						            Transport t = sessionCorreo.getTransport("smtp");
						            t.connect(correoUsuario,password);
						            t.sendMessage(message, message.getAllRecipients());
						            // Cierre.
						            t.close();
						      }
							request.setAttribute("msj1", "envío de cuentas temporales satisfactorio");
						}else{
							request.setAttribute("msj2", "Error. Intentelo de nuevo");
						}
						Vector<UsuarioBean> colaboradores= usuariodao.listarUsuarios();
						if(colaboradores.size()>0){
							request.setAttribute("colaboradores", colaboradores);
						}else{
							request.setAttribute("msj", "No hay ningun colaborador registrado");
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
						request.setAttribute("msj2", "Error en el envio. Intentelo de nuevo");
					}
					request.getRequestDispatcher("/Asistente/MantenerCuentasTemporales.jsp").forward(request, response);
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
