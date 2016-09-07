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
import beans.PersonaBean;
import beans.UsuarioBean;
import dao.interfaces.I_Prenda;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class EnviarRecordatorio
 */
@WebServlet("/EnviarRecordatorio")
public class ServletEnviarRecordatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEnviarRecordatorio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				if(user.getRol().getNomTipo().equals("Administrador")){
					try {
						DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
						I_Usuario usuariodao=dao.getUsuarioDao();
						Vector<PersonaBean> colaboradoresCorreoFaltantes= usuariodao.obtenerCorreosFaltantes();
						if(colaboradoresCorreoFaltantes.size()>0){
							for(int i=0;i<colaboradoresCorreoFaltantes.size();i++){
								String correoUsuario="systemsevenperu@gmail.com";
								String password="systemseven7";
								
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
						            message.setFrom(new InternetAddress(colaboradoresCorreoFaltantes.get(i).getCorreo()));
						            message.addRecipient(
						            Message.RecipientType.TO,new InternetAddress(colaboradoresCorreoFaltantes.get(i).getCorreo()));
						           
						            message.setSubject("Registre sus tallas de uniforme BCP");
						            message.setText("Saludos, le comunicamos que hasta el momento no registra los datos de la talla de sus uniformes, realizarlo lo antes posible. Gracias");
						            // Lo enviamos.
						            Transport t = sessionCorreo.getTransport("smtp");
						            t.connect(correoUsuario,password);
						            t.sendMessage(message, message.getAllRecipients());
						            // Cierre.
						            t.close();
							}
							request.setAttribute("msj1", "Envio Exitoso");
						}else{
							request.setAttribute("msj", "No hay ningún colaborador faltante");
						}
						
						I_Prenda prendadao=dao.getPrendaDao();
						Vector<PersonaBean> colaboradoresFaltantes= prendadao.listarColaboradoresFaltantes();
						if(colaboradoresFaltantes.size()>0){
							request.setAttribute("colaboradoresFaltantes", colaboradoresFaltantes);
						}else{
							request.setAttribute("msj", "No hay ningún colaborador faltante");
						}
					} catch (Exception e) {
						request.setAttribute("msj2", "Error en el envío");
						System.out.print(e.getMessage());
					}
					request.getRequestDispatcher("/Administrador/ColaboradoresFaltantes.jsp").forward(request, response);
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
