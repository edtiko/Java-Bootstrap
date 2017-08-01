package com.premize.sgp.dto.pruebas;

import java.io.Serializable;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class TotalesHallazgoDTO
 * @since 14/04/2014
 *
 */
public class TotalHallazgoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -868678645322417470L;
	

	private String tipoHallazgo;
	private String tipoSeveridad;
	private String  usuarioAsignado;
	private Integer totalHallazgos;
	

	
	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}
	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

	/**
	 * @return the totalHallazgos
	 */
	public Integer getTotalHallazgos() {
		return totalHallazgos;
	}
	/**
	 * @param totalHallazgos the totalHallazgos to set
	 */
	public void setTotalHallazgos(Integer totalHallazgos) {
		this.totalHallazgos = totalHallazgos;
	}
	/**
	 * @return the tipoHallazgo
	 */
	public String getTipoHallazgo() {
		return tipoHallazgo;
	}
	/**
	 * @param tipoHallazgo the tipoHallazgo to set
	 */
	public void setTipoHallazgo(String tipoHallazgo) {
		this.tipoHallazgo = tipoHallazgo;
	}
	/**
	 * @return the tipoSeveridad
	 */
	public String getTipoSeveridad() {
		return tipoSeveridad;
	}
	/**
	 * @param tipoSeveridad the tipoSeveridad to set
	 */
	public void setTipoSeveridad(String tipoSeveridad) {
		this.tipoSeveridad = tipoSeveridad;
	}
	
}
