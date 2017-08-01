package com.premize.sgp.dto.pruebas.indicadores;

import java.util.List;

import com.premize.sgp.dto.parametros.IndicadoresDTO;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class IndTiemposRespuestaGarantiaDTO
 * @description
 * @date 15/07/2014
 */
public class IndTiemposRespuestaGarantiaDTO extends AbstractIndicadorDTO<TiemposRespuestaGarantiaDTO> {

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014
	 */ 
	private static final long serialVersionUID = -1078303917517499191L;
	
	private List<TiemposRespuestaGarantiaDTO> listaIndicador;

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014  
	 */ 
	public IndTiemposRespuestaGarantiaDTO() {
		super();
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param listaIndicador 
	 */ 
	public IndTiemposRespuestaGarantiaDTO(
			List<TiemposRespuestaGarantiaDTO> listaIndicador) {
		super();
		this.listaIndicador = listaIndicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param listaIndicador 
	 */ 
	public IndTiemposRespuestaGarantiaDTO(IndicadoresDTO parametroIndicador,
			List<TiemposRespuestaGarantiaDTO> listaIndicador, TiemposRespuestaGarantiaDTO totales) {
		super();
		this.parametroIndicador = parametroIndicador;
		this.listaIndicador = listaIndicador;
		this.totales = totales;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the listaIndicador 
	 */
	public List<TiemposRespuestaGarantiaDTO> getListaIndicador() {
		return listaIndicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param listaIndicador the listaIndicador to set 
	 */
	public void setListaIndicador(List<TiemposRespuestaGarantiaDTO> listaIndicador) {
		this.listaIndicador = listaIndicador;
	}
	

}
