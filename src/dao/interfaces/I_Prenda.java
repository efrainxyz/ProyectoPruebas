package dao.interfaces;

import java.util.Vector;
import java.util.concurrent.ExecutionException;

import beans.PersonaBean;
import beans.PrendaBean;
import beans.TipoPrendaBean;

public interface I_Prenda {
	public Vector<TipoPrendaBean> listarTiposPrenda() throws Exception;
	public Vector<TipoPrendaBean> listarTiposPrendaColaborador() throws Exception;
	public boolean estadoTipoPrenda(int id, int estado) throws Exception;
	public boolean modificarTipoPrenda(TipoPrendaBean prenda) throws Exception;
	public boolean agregarPrenda (TipoPrendaBean prenda) throws  Exception;
	public boolean agregarTallaColaborador(PrendaBean prenda) throws Exception;
	public boolean modificarTallaColaborador(PrendaBean prenda) throws Exception;
	public Vector<PrendaBean> obtenerTallaColaborador(int id_persona) throws Exception;
	public Vector<PersonaBean> listarColaboradoresCompletados() throws Exception;
	public Vector<PersonaBean> listarColaboradoresFaltantes() throws Exception;
}
