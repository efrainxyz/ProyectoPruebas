package dao.interfaces;

import java.util.Vector;

import beans.SedeBean;
import beans.SedeXTipoPrendaBean;

public interface I_SedeReparto {
	public boolean registrarSedeStock() throws Exception;
	public Vector<SedeXTipoPrendaBean> listarStockPrendas() throws Exception;
	public Vector<SedeXTipoPrendaBean> listarTotalPrendasXSede() throws Exception;
	public Vector<SedeBean> listarSedesaEntregar()throws Exception;
	public boolean actualizarEstadoEntrega(int idSede) throws Exception;
	public Vector<SedeBean> listarSedes()throws Exception;
}
