package com.premize.sgp.dto.pruebas.indicadores;

import java.io.Serializable;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.IndicadoresDTO;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class IndReprocesoConstruccionPruebasDTO
 * @description
 * @date 10/07/2014
 */
public class IndReprocesoConstruccionPruebasDTO implements Serializable {

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014
	 */ 
	private static final long serialVersionUID = -1398267306250950656L;
	
	private Integer pruebasConstruidas;
	private Integer pruebasNoAplican;
	private IndicadorDTO indicador;
	private IndicadoresDTO parametroIndicador;
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014  
	 */ 
	public IndReprocesoConstruccionPruebasDTO() {
		super();
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @param pruebasConstruidas
	 * @param pruebasNoAplican
	 * @param indicador 
	 */ 
	public IndReprocesoConstruccionPruebasDTO(Integer pruebasConstruidas,
			Integer pruebasNoAplican, IndicadorDTO indicador) {
		super();
		this.pruebasConstruidas = pruebasConstruidas;
		this.pruebasNoAplican = pruebasNoAplican;
		this.indicador = indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @return the pruebasConstruidas 
	 */
	public Integer getPruebasConstruidas() {
		return pruebasConstruidas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @param pruebasConstruidas the pruebasConstruidas to set 
	 */
	public void setPruebasConstruidas(Integer pruebasConstruidas) {
		this.pruebasConstruidas = pruebasConstruidas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @return the pruebasNoAplican 
	 */
	public Integer getPruebasNoAplican() {
		return pruebasNoAplican;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @param pruebasNoAplican the pruebasNoAplican to set 
	 */
	public void setPruebasNoAplican(Integer pruebasNoAplican) {
		this.pruebasNoAplican = pruebasNoAplican;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @return the indicador 
	 */
	public IndicadorDTO getIndicador() {
		return indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @param indicador the indicador to set 
	 */
	public void setIndicador(IndicadorDTO indicador) {
		this.indicador = indicador;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014
	 * @description 
	 * @return
	 * @throws AppBaseException
	 */
	public Double calculateIndicadorReprocesoConstruccionPruebas() throws AppBaseException {
		if(pruebasConstruidas != null && pruebasConstruidas != 0) {
			return (pruebasConstruidas.doubleValue() - pruebasNoAplican.doubleValue()) / pruebasConstruidas.doubleValue();
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
