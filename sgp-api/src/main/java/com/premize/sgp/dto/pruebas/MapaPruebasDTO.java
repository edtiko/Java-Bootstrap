/**
 * 
 */
package com.premize.sgp.dto.pruebas;

import java.math.BigInteger;
import java.util.Date;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-api
 * @class MapaPruebasDTO
 * @since 22/01/2014
 */
public class MapaPruebasDTO extends BaseEntity {
	
	private static final long serialVersionUID = -8094458736199756392L;
	
	private Integer id;
	private String nombre;
	private Integer usuario;
	private ProyectoDTO proyecto;
	private Integer idProyecto;
	private Integer totalPruebasConstruidas;
	private Integer totalPruebasEjecutadas;
	private Integer totalPruebasAnuladas;
	private Integer totalPruebasSinEjecutar;
	private Integer totalHallazgos;
	private Integer totalPruebasSatisfactorias;
	private Integer totalPruebasInsatisfactorias;
	private Double indicadorEfectividad;
	private String porcentajeAvance;
	private String porcentajeIndicador;
	private Integer sumatoriaPruebasConstruidas;
	private Integer sumatoriaPruebasEjecutadas;
	private Integer sumatoriaPruebasSatisfactorias;
	private Integer sumatoriaPruebasInsatisfactorias;
	private Integer sumatoriaPruebasSinEjecutar;
	private Integer sumatoriaPruebasAnuladas;	
	private String sumatoriaPorcentajeAvance;
	private String sumatoriaPorcentajeIndicador;	
	private Date fec_creacion;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @param nombre
	 * @param totalPruebasConstruidas
	 * @param totalPruebasEjecutadas
	 * @param porcentajeAvanceString
	 * @param totalPruebasSinEjecutar
	 * @param totalPruebasAnuladas
	 */
	public MapaPruebasDTO(String nombre, Integer totalPruebasConstruidas,
			Integer totalPruebasEjecutadas, String porcentajeAvanceString,
			Integer totalPruebasSinEjecutar, Integer totalPruebasAnuladas) {
		super();
		this.nombre = nombre;
		this.totalPruebasConstruidas = totalPruebasConstruidas;
		this.totalPruebasEjecutadas = totalPruebasEjecutadas;
		this.totalPruebasAnuladas = totalPruebasAnuladas;
		this.totalPruebasSinEjecutar = totalPruebasSinEjecutar;
		this.porcentajeAvance= porcentajeAvanceString;
	}
	
	

	/**
	 * Constructor con campos obligatorios para el reporte Avance Ejecucion Mapa de Pruebas.
	 *  
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014 
	 * @param nombre
	 * @param totalPruebasConstruidas
	 * @param totalPruebasEjecutadas
	 * @param totalPruebasAnuladas
	 * @param totalPruebasSatisfactorias
	 * @param totalPruebasInsatisfactorias 
	 */ 
	public MapaPruebasDTO(String nombre, Integer totalPruebasConstruidas,
			Integer totalPruebasEjecutadas, Integer totalPruebasSatisfactorias,
			Integer totalPruebasInsatisfactorias, Integer totalPruebasAnuladas) {
		super();
		this.nombre = nombre;
		this.totalPruebasConstruidas = totalPruebasConstruidas;
		this.totalPruebasEjecutadas = totalPruebasEjecutadas;
		this.totalPruebasSatisfactorias = totalPruebasSatisfactorias;
		this.totalPruebasInsatisfactorias = totalPruebasInsatisfactorias;
		this.totalPruebasAnuladas = totalPruebasAnuladas;
	}



	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @return the usuario
	 */
	public Integer getUsuario() {
		return usuario;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
	public MapaPruebasDTO(Integer id) {
		this.id = id;
	}
	
	public MapaPruebasDTO(String nombre, Integer usuario, Integer id) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.id = id;
	}
	
	public MapaPruebasDTO() {
		super();
		
	}
	
	public MapaPruebasDTO(SgpMapaPrueba sgpMapaPrueba) {
		if (sgpMapaPrueba != null) {
			this.id = sgpMapaPrueba.getId();
			this.nombre = sgpMapaPrueba.getNombre();
			this.proyecto = new ProyectoDTO(sgpMapaPrueba.getSgpProyecto());
			idProyecto = proyecto.getId();
			setNumeroEstado(sgpMapaPrueba.getIndActivo());
			setIndActivo(sgpMapaPrueba.getIndActivo());
			setFecCreacion(sgpMapaPrueba.getFecCreacion());
			setFechaEdita(sgpMapaPrueba.getFecEdita());
			setUsuarioCreacion(sgpMapaPrueba.getUsuarioCrea());
			setUsuarioEdita(sgpMapaPrueba.getUsuarioEdita());
		}
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public ProyectoDTO getProyecto() {
		return proyecto;
	}
	
	public void setProyecto(ProyectoDTO proyecto) {
		this.proyecto = proyecto;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 7/03/2014
	 * @return the totalPruebasConstruidas
	 */
	public Integer getTotalPruebasConstruidas() {
		return totalPruebasConstruidas;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 7/03/2014
	 * @param totalPruebasConstruidas
	 *            the totalPruebasConstruidas to set
	 */
	public void setTotalPruebasConstruidas(Integer totalPruebasConstruidas) {
		this.totalPruebasConstruidas = totalPruebasConstruidas;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 7/03/2014
	 * @return the totalPruebasEjecutadas
	 */
	public Integer getTotalPruebasEjecutadas() {
		return totalPruebasEjecutadas;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 7/03/2014
	 * @param totalPruebasEjecutadas
	 *            the totalPruebasEjecutadas to set
	 */
	public void setTotalPruebasEjecutadas(Integer totalPruebasEjecutadas) {
		this.totalPruebasEjecutadas = totalPruebasEjecutadas;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/04/2014
	 * @description 
	 * @return
	 */
	public Integer getTotalHallazgos() {
		return totalHallazgos;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/04/2014
	 * @description 
	 * @param totalHallazgos
	 */
	public void setTotalHallazgos(Integer totalHallazgos) {
		this.totalHallazgos = totalHallazgos;
	}

	public Integer getTotalPruebasAnuladas() {
		return totalPruebasAnuladas;
	}

	public void setTotalPruebasAnuladas(Integer totalPruebasAnuladas) {
		this.totalPruebasAnuladas = totalPruebasAnuladas;
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 22/04/2014
	 * @return
	 */
	public String getPorcentajeAvance() {
		return porcentajeAvance;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 22/04/2014
	 * @param porcentajeAvance
	 */
	public void setPorcentajeAvance(String porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 22/04/2014
	 * @return
	 */
	public Integer getSumatoriaPruebasConstruidas() {
		return sumatoriaPruebasConstruidas;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 22/04/2014
	 * @param porcentajeAvance
	 */
	public void setSumatoriaPruebasConstruidas(Integer sumatoriaPruebasConstruidas) {
		this.sumatoriaPruebasConstruidas = sumatoriaPruebasConstruidas;
	}

	/**
	 *
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 28/04/2014
	 * @return
	 */
	public String getPorcentajeIndicador() {
		return porcentajeIndicador;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 28/04/2014
	 * @param porcentajeAvance
	 */
	public void setPorcentajeIndicador(String porcentajeIndicador) {
		this.porcentajeIndicador = porcentajeIndicador;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 22/04/2014
	 * @return
	 */
	public Integer getTotalPruebasSinEjecutar() {
		return totalPruebasSinEjecutar;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 22/04/2014
	 * @param totalPruebasSinEjecutar
	 */
	public void setTotalPruebasSinEjecutar(Integer totalPruebasSinEjecutar) {
		this.totalPruebasSinEjecutar = totalPruebasSinEjecutar;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @return the totalPruebasSatisfactorias 
	 */
	public Integer getTotalPruebasSatisfactorias() {
		return totalPruebasSatisfactorias;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @param totalPruebasSatisfactorias the totalPruebasSatisfactorias to set 
	 */
	public void setTotalPruebasSatisfactorias(Integer totalPruebasSatisfactorias) {
		this.totalPruebasSatisfactorias = totalPruebasSatisfactorias;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @return the totalPruebasInsatisfactorias 
	 */
	public Integer getTotalPruebasInsatisfactorias() {
		return totalPruebasInsatisfactorias;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @param totalPruebasInsatisfactorias the totalPruebasInsatisfactorias to set 
	 */
	public void setTotalPruebasInsatisfactorias(Integer totalPruebasInsatisfactorias) {
		this.totalPruebasInsatisfactorias = totalPruebasInsatisfactorias;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @return the indicadorEfectividad 
	 */
	public Double getIndicadorEfectividad() {
		return indicadorEfectividad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 23/04/2014 
	 * @param indicadorEfectividad the indicadorEfectividad to set 
	 */
	public void setIndicadorEfectividad(Double indicadorEfectividad) {
		this.indicadorEfectividad = indicadorEfectividad;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description Si el parametro porcentaje = true entonces devuelve el calculo como una representacion
	 *              porcentual(%) de lo contrario devuelve la probabilidad.
	 * @param porcentaje
	 * @return
	 */
	public Double getCalculatedPorcentajeAvance(boolean porcentaje) {
		if(null != totalPruebasConstruidas && totalPruebasAnuladas != null) {
			Integer divisor = totalPruebasConstruidas - totalPruebasAnuladas;
			if(divisor != 0) {
				Double val = totalPruebasEjecutadas.doubleValue() / divisor;
				Double resultado = val.doubleValue();
				return porcentaje ? resultado * 100 : resultado;
			}
		}
		return 0d;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description 
	 * @return
	 */
	public Integer getCalculatedPruebasSinEjecutar() {
		
		Integer res = null;
		if(totalPruebasConstruidas != null && totalPruebasEjecutadas != null && totalPruebasAnuladas != null)
		res =  totalPruebasConstruidas - (totalPruebasEjecutadas + totalPruebasAnuladas);
		
		return res; 
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description Si el parametro porcentaje = true entonces devuelve el calculo como una representacion
	 *              porcentual(%) de lo contrario devuelve la probabilidad.
	 * @param porcentaje
	 * @return
	 */
	public Double getCalculatedIndicadorCalidadICF(boolean porcentaje) {
		if(null != totalPruebasEjecutadas && totalPruebasEjecutadas != 0) {
			Double d = totalPruebasInsatisfactorias.doubleValue() / totalPruebasEjecutadas.doubleValue();
			Double resultado = 1 - d;
			return porcentaje ? resultado * 100 : resultado;
		}
		return 0d;
	}

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @return
 */
	public Integer getSumatoriaPruebasEjecutadas() {
		return sumatoriaPruebasEjecutadas;
	}

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @param sumatoriaPruebasEjecutadas
 */
	public void setSumatoriaPruebasEjecutadas(
			Integer sumatoriaPruebasEjecutadas) {
		this.sumatoriaPruebasEjecutadas = sumatoriaPruebasEjecutadas;
	}

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @return
 */
	public Integer getSumatoriaPruebasSatisfactorias() {
		return sumatoriaPruebasSatisfactorias;
	}

	/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @param sumatoriaPruebasSatisfactorias
 */
	public void setSumatoriaPruebasSatisfactorias(
			Integer sumatoriaPruebasSatisfactorias) {
		this.sumatoriaPruebasSatisfactorias = sumatoriaPruebasSatisfactorias;
	}

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @return
 */
	public Integer getSumatoriaPruebasInsatisfactorias() {
		return sumatoriaPruebasInsatisfactorias;
	}

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @param sumatoriaPruebasInsatisfactorias
 */
	public void setSumatoriaPruebasInsatisfactorias(
			Integer sumatoriaPruebasInsatisfactorias) {
		this.sumatoriaPruebasInsatisfactorias = sumatoriaPruebasInsatisfactorias;
	}

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @return
 */
	public Integer getSumatoriaPruebasSinEjecutar() {
		return sumatoriaPruebasSinEjecutar;
	}

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @param sumatoriaPruebasSinEjecutar
 */
	public void setSumatoriaPruebasSinEjecutar(
			Integer sumatoriaPruebasSinEjecutar) {
		this.sumatoriaPruebasSinEjecutar = sumatoriaPruebasSinEjecutar;
	}

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @return
 */
	public Integer getSumatoriaPruebasAnuladas() {
		return sumatoriaPruebasAnuladas;
	}

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @param sumatoriaPruebasAnuladas
 */
	public void setSumatoriaPruebasAnuladas(Integer sumatoriaPruebasAnuladas) {
		this.sumatoriaPruebasAnuladas = sumatoriaPruebasAnuladas;
	}


/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @return
 */
	public String getSumatoriaPorcentajeAvance() {
	return sumatoriaPorcentajeAvance;
}


/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @param sumatoriaPorcentajeAvance
 */
	public void setSumatoriaPorcentajeAvance(String sumatoriaPorcentajeAvance) {
	this.sumatoriaPorcentajeAvance = sumatoriaPorcentajeAvance;
}


/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @return
 */
	public String getSumatoriaPorcentajeIndicador() {
	return sumatoriaPorcentajeIndicador;
}


/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 30/04/2014
 * @param sumatoriaPorcentajeIndicador
 */
	public void setSumatoriaPorcentajeIndicador(
		String sumatoriaPorcentajeIndicador) {
	this.sumatoriaPorcentajeIndicador = sumatoriaPorcentajeIndicador;
}
	
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 1/07/2014 
	 * @return the idProyecto 
	 */
	public Integer getIdProyecto() {
		return idProyecto;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 1/07/2014 
	 * @param idProyecto the idProyecto to set 
	 */
	public void setIdproyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 1/07/2014
	 * @description 
	 * @param totalpruebasconstruidas
	 */
	public void setTotalpruebasconstruidas(BigInteger totalpruebasconstruidas) {
		totalPruebasConstruidas = totalpruebasconstruidas.intValue();
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 1/07/2014
	 * @description 
	 * @param totalpruebasejecutadas
	 */
	public void setTotalpruebasejecutadas(BigInteger totalpruebasejecutadas) {
		totalPruebasEjecutadas = totalpruebasejecutadas.intValue();
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 1/07/2014
	 * @description 
	 * @param totalpruebasanuladas
	 */
	public void setTotalpruebasanuladas(BigInteger totalpruebasanuladas) {
		totalPruebasAnuladas = totalpruebasanuladas.intValue();
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 1/07/2014
	 * @description 
	 * @param totalpruebassatisfactorias
	 */
	public void setTotalpruebassatisfactorias(BigInteger totalpruebassatisfactorias) {
		totalPruebasSatisfactorias = totalpruebassatisfactorias.intValue();
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 1/07/2014
	 * @description 
	 * @param totalpruebasinsatisfactorias
	 */
	public void setTotalpruebasinsatisfactorias(BigInteger totalpruebasinsatisfactorias) {
		totalPruebasInsatisfactorias = totalpruebasinsatisfactorias.intValue();
	}



	public Date getFec_creacion() {
		return fec_creacion;
	}



	public void setFec_creacion(Date fec_creacion) {
		this.fec_creacion = fec_creacion;
		super.setFecCreacion(fec_creacion);
	}




	
}
