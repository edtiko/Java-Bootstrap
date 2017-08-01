package com.premize.sgp.dto.pruebas.indicadores;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class TiemposRespuestaGarantiaDTO
 * @description
 * @date 15/07/2014
 */
public class TiemposRespuestaGarantiaDTO implements Serializable {

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014
	 */ 
	private static final long serialVersionUID = 7331995893183903917L;
	
	private Integer idHallazgo;
	private String clasificacion;
	private Integer idTipoSeveridad;
	private String prioridad;
	private Double tiempoPrioridad;
	private Date fechaCreacion;
	private Date fechaSolicitud;
	private Date fechaRespuesta;
	private IndicadorDTO indicador;
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014  
	 */ 
	public TiemposRespuestaGarantiaDTO() {
		super();
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param idHallazgo
	 * @param clasificacion
	 * @param prioridad
	 * @param fechaCreacion
	 * @param fechaSolicitud
	 * @param fechaRespuesta
	 * @param indicador 
	 */ 
	public TiemposRespuestaGarantiaDTO(Integer idHallazgo,
			String clasificacion, String prioridad, Date fechaCreacion, Date fechaSolicitud,
			Date fechaRespuesta, IndicadorDTO indicador) {
		super();
		this.idHallazgo = idHallazgo;
		this.clasificacion = clasificacion;
		this.prioridad = prioridad;
		this.fechaCreacion = fechaCreacion;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaRespuesta = fechaRespuesta;
		this.indicador = indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the idHallazgo 
	 */
	public Integer getIdHallazgo() {
		return idHallazgo;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param idHallazgo the idHallazgo to set 
	 */
	public void setIdHallazgo(Integer idHallazgo) {
		this.idHallazgo = idHallazgo;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the clasificacion 
	 */
	public String getClasificacion() {
		return clasificacion;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param clasificacion the clasificacion to set 
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the prioridad 
	 */
	public String getPrioridad() {
		return prioridad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param prioridad the prioridad to set 
	 */
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the idTipoSeveridad 
	 */
	public Integer getIdTipoSeveridad() {
		return idTipoSeveridad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param idTipoSeveridad the idTipoSeveridad to set 
	 */
	public void setIdTipoSeveridad(Integer idTipoSeveridad) {
		this.idTipoSeveridad = idTipoSeveridad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the fechaSolicitud 
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param fechaSolicitud the fechaSolicitud to set 
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the fechaRespuesta 
	 */
	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param fechaRespuesta the fechaRespuesta to set 
	 */
	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the indicador 
	 */
	public IndicadorDTO getIndicador() {
		return indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param indicador the indicador to set 
	 */
	public void setIndicador(IndicadorDTO indicador) {
		this.indicador = indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014 
	 * @return the tiempoPrioridad 
	 */
	public Double getTiempoPrioridad() {
		return tiempoPrioridad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014 
	 * @param tiempoPrioridad the tiempoPrioridad to set 
	 */
	public void setTiempoPrioridad(Double tiempoPrioridad) {
		this.tiempoPrioridad = tiempoPrioridad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014 
	 * @return the fechaCreacion 
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014 
	 * @param fechaCreacion the fechaCreacion to set 
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
