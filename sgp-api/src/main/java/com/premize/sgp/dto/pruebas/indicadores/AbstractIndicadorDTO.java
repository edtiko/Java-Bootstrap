package com.premize.sgp.dto.pruebas.indicadores;

import java.io.Serializable;

import com.premize.sgp.dto.parametros.IndicadoresDTO;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class AbstractIndicadorDTO
 * @description
 * @date 15/07/2014
 */
public abstract class AbstractIndicadorDTO<T> implements Serializable {

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014
	 */ 
	private static final long serialVersionUID = 8032808889937075720L;
	
	protected IndicadoresDTO parametroIndicador;
	protected T totales;

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014  
	 */ 
	public AbstractIndicadorDTO() {
		super();
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param parametroIndicador 
	 */ 
	public AbstractIndicadorDTO(IndicadoresDTO parametroIndicador) {
		super();
		this.parametroIndicador = parametroIndicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the parametroIndicador 
	 */
	public IndicadoresDTO getParametroIndicador() {
		return parametroIndicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param parametroIndicador the parametroIndicador to set 
	 */
	public void setParametroIndicador(IndicadoresDTO parametroIndicador) {
		this.parametroIndicador = parametroIndicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the totales 
	 */
	public T getTotales() {
		return totales;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param totales the totales to set 
	 */
	public void setTotales(T totales) {
		this.totales = totales;
	}
	
}
