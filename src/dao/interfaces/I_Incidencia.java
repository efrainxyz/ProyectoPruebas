package dao.interfaces;

import java.util.Vector;

import beans.Detalle_IncidenciaBean;
import beans.IncidenciaBean;

public interface I_Incidencia {
	public String generarNroIncidencia() throws Exception;
	public boolean registrarIncidenciaOrdenCompra(IncidenciaBean incidencia, Vector<Detalle_IncidenciaBean>detalle) throws Exception;
}
