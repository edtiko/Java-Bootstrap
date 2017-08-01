package com.premize.sgp.dto.pruebas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.premize.sgp.dto.BaseEntity;

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class ReporteMapaPruebaDTO
 * @description
 * @date 11/04/2014
 */
public class ReporteMapaPruebaDTO extends BaseEntity {

	private static final long serialVersionUID = 4672279102271710208L;
	private static final String formatoFecha = "yyyy/MM/dd HH:mm:ss";
	
	private Integer id;
	private String artefacto;
	private String descripcionCasoPrueba;
	private String tipoPrueba;
	private String resultadoEsperado;
	private Date fechaCreacion;
	//private String fechaCreacionStr; BaseEntity
	private String usuarioCrea;
	private String usuarioEjecutor;
	private Date fechaEjecucion;
	private String fechaEjecucionString;
	private String cumple;
	private String nombreMapaPrueba;
	
	private Integer idMapaPrueba;
	private Integer idProyecto;
	private String nombreMapa;
	private Date fechaCreacionMapa;
	
	//Hallazgos
	private Integer numeroHallazgo;
	private String tituloHallazgo;
	private String descripcionHallazgo;
	private String tipoHallazgo;
	private String severidad;
	private String prioridad;
	private String responsableRealizarAjuestes;
	private String causaGeneracion;
	private String causaAnulacion;
	private String estadoHallazgo;
	private Date fechaEstadoHallazgo;	
	/*
	 * .....
	 */
	
	public ReporteMapaPruebaDTO() {
	}
	
	

	public ReporteMapaPruebaDTO(Integer id, String artefacto,
			String descripcionCasoPrueba, String tipoPrueba,
			String resultadoEsperado, Date fechaCreacion, String usuarioCrea,
			String usuarioEjecutor, Date fechaEjecucion,
			String fechaEjecucionString, String cumple, String nombreMapaPrueba) {
		super();
		this.id = id;
		this.artefacto = artefacto;
		this.descripcionCasoPrueba = descripcionCasoPrueba;
		this.tipoPrueba = tipoPrueba;
		this.resultadoEsperado = resultadoEsperado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEjecutor = usuarioEjecutor;
		this.fechaEjecucion = fechaEjecucion;
		this.fechaEjecucionString = fechaEjecucionString;
		this.cumple = cumple;
		this.nombreMapaPrueba = nombreMapaPrueba;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014
	 * @param idMapaPrueba
	 * @param idProyecto
	 * @param id
	 * @param artefacto
	 * @param descripcionCasoPrueba
	 * @param tipoPrueba
	 * @param resultadoEsperado
	 * @param fechaCreacion
	 * @param usuarioCrea
	 * @param usuarioEjecutor
	 * @param fechaEjecucion
	 * @param fechaEjecucionString
	 * @param cumple
	 * @param nombreMapaPrueba
	 * @param numeroHallazgo
	 * @param tituloHallazgo
	 * @param descripcionHallazgo
	 * @param tipoHallazgo
	 * @param severidad
	 * @param prioridad
	 * @param responsableRealizarAjuestes
	 * @param causaGeneracion
	 * @param causaAnulacion
	 * @param estadoHallazgo
	 * @param fechaEstadoHallazgo
	 */
	public ReporteMapaPruebaDTO(Integer idMapaPrueba, String nombreMapa, Integer idProyecto,
			Integer id, String artefacto, String descripcionCasoPrueba, 
            String tipoPrueba, String resultadoEsperado, Date fechaCreacion, String usuarioCrea, 
            String usuarioEjecutor, Date fechaEjecucion, String fechaEjecucionString, String cumple, 
            String nombreMapaPrueba, Integer numeroHallazgo, String tituloHallazgo, 
            String descripcionHallazgo, String tipoHallazgo, String severidad, String prioridad, 
            String responsableRealizarAjuestes, String causaGeneracion, String causaAnulacion, 
            String estadoHallazgo, Date fechaEstadoHallazgo) {
		this.idMapaPrueba = idMapaPrueba;
		this.nombreMapa = nombreMapa;
		this.idProyecto = idProyecto;
        this.id = id;
        this.artefacto = artefacto;
        this.descripcionCasoPrueba = descripcionCasoPrueba;
        this.tipoPrueba = tipoPrueba;
        this.resultadoEsperado = resultadoEsperado;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCrea = usuarioCrea;
        this.usuarioEjecutor = usuarioEjecutor;
        this.fechaEjecucion = fechaEjecucion;
        this.fechaEjecucionString = fechaEjecucionString;
        this.cumple = cumple;
        this.nombreMapaPrueba = nombreMapaPrueba;
        this.numeroHallazgo = numeroHallazgo;
        this.tituloHallazgo = tituloHallazgo;
        this.descripcionHallazgo = descripcionHallazgo;
        this.tipoHallazgo = tipoHallazgo;
        this.severidad = severidad;
        this.prioridad = prioridad;
        this.responsableRealizarAjuestes = responsableRealizarAjuestes;
        this.causaGeneracion = causaGeneracion;
        this.causaAnulacion = causaAnulacion;
        this.estadoHallazgo = estadoHallazgo;
        this.fechaEstadoHallazgo = fechaEstadoHallazgo;
    }



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014 
	 * @return the id 
	 */
	public Integer getId() {
		return id;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014 
	 * @param id the id to set 
	 */
	public void setId(Integer id) {
		this.id = id;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @return the artefacto 
	 */
	public String getArtefacto() {
		return artefacto;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @param artefacto the artefacto to set 
	 */
	public void setArtefacto(String artefacto) {
		this.artefacto = artefacto;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @return the descripcionCasoPrueba 
	 */
	public String getDescripcionCasoPrueba() {
		return descripcionCasoPrueba;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @param descripcionCasoPrueba the descripcionCasoPrueba to set 
	 */
	public void setDescripcionCasoPrueba(String descripcionCasoPrueba) {
		this.descripcionCasoPrueba = descripcionCasoPrueba;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @return the tipoPrueba 
	 */
	public String getTipoPrueba() {
		return tipoPrueba;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @param tipoPrueba the tipoPrueba to set 
	 */
	public void setTipoPrueba(String tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @return the resultadoEsperado 
	 */
	public String getResultadoEsperado() {
		return resultadoEsperado;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @param resultadoEsperado the resultadoEsperado to set 
	 */
	public void setResultadoEsperado(String resultadoEsperado) {
		this.resultadoEsperado = resultadoEsperado;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @return the usuarioEjecutor 
	 */
	public String getUsuarioEjecutor() {
		return usuarioEjecutor;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @param usuarioEjecutor the usuarioEjecutor to set 
	 */
	public void setUsuarioEjecutor(String usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}
	
	

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014 
	 * @return the fechaEjecucion 
	 */
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014 
	 * @param fechaEjecucion the fechaEjecucion to set 
	 */
	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
		if(fechaEjecucion != null) {
			DateFormat df = new SimpleDateFormat(formatoFecha);
			setFechaEjecucionString(df.format(fechaEjecucion));
		}
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014 
	 * @return the fechaEjecucionString 
	 */
	public String getFechaEjecucionString() {
		return fechaEjecucionString;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014 
	 * @param fechaEjecucionString the fechaEjecucionString to set 
	 */
	public void setFechaEjecucionString(String fechaEjecucionString) {
		this.fechaEjecucionString = fechaEjecucionString;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @return the cumple 
	 */
	public String getCumple() {
		return cumple;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014 
	 * @param cumple the cumple to set 
	 */
	public void setCumple(String cumple) {
		this.cumple = cumple;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014 
	 * @return the fechaCreacion 
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014 
	 * @param fechaCreacion the fechaCreacion to set 
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014 
	 * @return the usuarioCrea 
	 */
	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014 
	 * @param usuarioCrea the usuarioCrea to set 
	 */
	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/04/2014 
	 * @return the nombreMapaPrueba 
	 */
	public String getNombreMapaPrueba() {
		return nombreMapaPrueba;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/04/2014 
	 * @param nombreMapaPrueba the nombreMapaPrueba to set 
	 */
	public void setNombreMapaPrueba(String nombreMapaPrueba) {
		this.nombreMapaPrueba = nombreMapaPrueba;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the numeroHallazgo 
	 */
	public Integer getNumeroHallazgo() {
		return numeroHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param numeroHallazgo the numeroHallazgo to set 
	 */
	public void setNumeroHallazgo(Integer numeroHallazgo) {
		this.numeroHallazgo = numeroHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the tituloHallazgo 
	 */
	public String getTituloHallazgo() {
		return tituloHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param tituloHallazgo the tituloHallazgo to set 
	 */
	public void setTituloHallazgo(String tituloHallazgo) {
		this.tituloHallazgo = tituloHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the descripcionHallazgo 
	 */
	public String getDescripcionHallazgo() {
		return descripcionHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param descripcionHallazgo the descripcionHallazgo to set 
	 */
	public void setDescripcionHallazgo(String descripcionHallazgo) {
		this.descripcionHallazgo = descripcionHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the tipoHallazgo 
	 */
	public String getTipoHallazgo() {
		return tipoHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param tipoHallazgo the tipoHallazgo to set 
	 */
	public void setTipoHallazgo(String tipoHallazgo) {
		this.tipoHallazgo = tipoHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the severidad 
	 */
	public String getSeveridad() {
		return severidad;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param severidad the severidad to set 
	 */
	public void setSeveridad(String severidad) {
		this.severidad = severidad;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the prioridad 
	 */
	public String getPrioridad() {
		return prioridad;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param prioridad the prioridad to set 
	 */
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the responsableRealizarAjuestes 
	 */
	public String getResponsableRealizarAjuestes() {
		return responsableRealizarAjuestes;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param responsableRealizarAjuestes the responsableRealizarAjuestes to set 
	 */
	public void setResponsableRealizarAjuestes(String responsableRealizarAjuestes) {
		this.responsableRealizarAjuestes = responsableRealizarAjuestes;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the causaGeneracion 
	 */
	public String getCausaGeneracion() {
		return causaGeneracion;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param causaGeneracion the causaGeneracion to set 
	 */
	public void setCausaGeneracion(String causaGeneracion) {
		this.causaGeneracion = causaGeneracion;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the causaAnulacion 
	 */
	public String getCausaAnulacion() {
		return causaAnulacion;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param causaAnulacion the causaAnulacion to set 
	 */
	public void setCausaAnulacion(String causaAnulacion) {
		this.causaAnulacion = causaAnulacion;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the estadoHallazgo 
	 */
	public String getEstadoHallazgo() {
		return estadoHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param estadoHallazgo the estadoHallazgo to set 
	 */
	public void setEstadoHallazgo(String estadoHallazgo) {
		this.estadoHallazgo = estadoHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @return the fechaEstadoHallazgo 
	 */
	public Date getFechaEstadoHallazgo() {
		return fechaEstadoHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014 
	 * @param fechaEstadoHallazgo the fechaEstadoHallazgo to set 
	 */
	public void setFechaEstadoHallazgo(Date fechaEstadoHallazgo) {
		this.fechaEstadoHallazgo = fechaEstadoHallazgo;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014 
	 * @return the idMapaPrueba 
	 */
	public Integer getIdMapaPrueba() {
		return idMapaPrueba;
	}



	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014 
	 * @param idMapaPrueba the idMapaPrueba to set 
	 */
	public void setIdMapaPrueba(Integer idMapaPrueba) {
		this.idMapaPrueba = idMapaPrueba;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014 
	 * @return the idProyecto 
	 */
	public Integer getIdProyecto() {
		return idProyecto;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014 
	 * @param idProyecto the idProyecto to set 
	 */
	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}


	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014 
	 * @return the nombreMapa 
	 */
	public String getNombreMapa() {
		return nombreMapa;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014 
	 * @param nombreMapa the nombreMapa to set 
	 */
	public void setNombreMapa(String nombreMapa) {
		this.nombreMapa = nombreMapa;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014 
	 * @return the fechaCreacionMapa 
	 */
	public Date getFechaCreacionMapa() {
		return fechaCreacionMapa;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014 
	 * @param fechaCreacionMapa the fechaCreacionMapa to set 
	 */
	public void setFechaCreacionMapa(Date fechaCreacionMapa) {
		this.fechaCreacionMapa = fechaCreacionMapa;
	}
	

}
