package com.premize.sgp.dto.pruebas.indicadores;

import java.io.Serializable;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.IndicadoresDTO;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class IndMejorasIdentificadasDTO
 * @description
 * @date 14/07/2014
 */
public class IndMejorasIdentificadasDTO implements Serializable {

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014
	 */ 
	private static final long serialVersionUID = -8848513497276230269L;
	
	private String usuarioResponsable;
	private Integer idUsuario;
	private Integer mejoras;
	private Integer mejorasAnuladas;
	private Integer mejorasInvalidadas;
	private Integer mejorasAceptadas;
	private IndicadorDTO indicador;
	private IndicadoresDTO parametroIndicador;
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014  
	 */ 
	public IndMejorasIdentificadasDTO() {
		super();
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @param usuarioResponsable
	 * @param idUsuario
	 * @param mejoras
	 * @param mejorasAnuladas
	 * @param mejorasInvalidadas
	 * @param mejorasAceptadas
	 * @param indicador 
	 */ 
	public IndMejorasIdentificadasDTO(String usuarioResponsable,
			Integer idUsuario, Integer mejoras, Integer mejorasAnuladas,
			Integer mejorasInvalidadas, Integer mejorasAceptadas,
			IndicadorDTO indicador) {
		super();
		this.usuarioResponsable = usuarioResponsable;
		this.idUsuario = idUsuario;
		this.mejoras = mejoras;
		this.mejorasAnuladas = mejorasAnuladas;
		this.mejorasInvalidadas = mejorasInvalidadas;
		this.mejorasAceptadas = mejorasAceptadas;
		this.indicador = indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param parametroIndicador 
	 */ 
	public IndMejorasIdentificadasDTO(IndicadoresDTO parametroIndicador) {
		super();
		this.parametroIndicador = parametroIndicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @return the usuarioResponsable 
	 */
	public String getUsuarioResponsable() {
		return usuarioResponsable;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @param usuarioResponsable the usuarioResponsable to set 
	 */
	public void setUsuarioResponsable(String usuarioResponsable) {
		this.usuarioResponsable = usuarioResponsable;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @return the idUsuario 
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @param idUsuario the idUsuario to set 
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @return the mejoras 
	 */
	public Integer getMejoras() {
		return mejoras;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @param mejoras the mejoras to set 
	 */
	public void setMejoras(Integer mejoras) {
		this.mejoras = mejoras;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @return the mejorasAnuladas 
	 */
	public Integer getMejorasAnuladas() {
		return mejorasAnuladas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @param mejorasAnuladas the mejorasAnuladas to set 
	 */
	public void setMejorasAnuladas(Integer mejorasAnuladas) {
		this.mejorasAnuladas = mejorasAnuladas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @return the mejorasInvalidadas 
	 */
	public Integer getMejorasInvalidadas() {
		return mejorasInvalidadas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @param mejorasInvalidadas the mejorasInvalidadas to set 
	 */
	public void setMejorasInvalidadas(Integer mejorasInvalidadas) {
		this.mejorasInvalidadas = mejorasInvalidadas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @return the mejorasAceptadas 
	 */
	public Integer getMejorasAceptadas() {
		return mejorasAceptadas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @param mejorasAceptadas the mejorasAceptadas to set 
	 */
	public void setMejorasAceptadas(Integer mejorasAceptadas) {
		this.mejorasAceptadas = mejorasAceptadas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @return the indicador 
	 */
	public IndicadorDTO getIndicador() {
		return indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @param indicador the indicador to set 
	 */
	public void setIndicador(IndicadorDTO indicador) {
		this.indicador = indicador;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014
	 * @description 
	 * @return
	 * @throws AppBaseException
	 */
	public Double calculateIndicadorMejorasIndentificadas() throws AppBaseException {
		if(mejoras != null) {
//			Integer divisor = (mejoras - (mejorasAnuladas + mejorasInvalidadas));
//			return divisor.doubleValue() / mejoras.doubleValue();
			return mejoras.doubleValue();
		}
		return 0d;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @return the parametroIndicador 
	 */
	public IndicadoresDTO getParametroIndicador() {
		return parametroIndicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014 
	 * @param parametroIndicador the parametroIndicador to set 
	 */
	public void setParametroIndicador(IndicadoresDTO parametroIndicador) {
		this.parametroIndicador = parametroIndicador;
	}
	

}
