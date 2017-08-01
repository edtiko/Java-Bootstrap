package com.premize.sgp.dto.pruebas.indicadores;

import com.premize.pmz.api.exception.AppBaseException;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class IndEfectividadHallazgosReportadosDTO
 * @description
 * @date 10/07/2014
 */
public class IndEfectividadHallazgosReportadosDTO extends AbstractIndicadorDTO<IndEfectividadHallazgosReportadosDTO> {

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014
	 */ 
	private static final long serialVersionUID = 8187029682489177277L;
	
	private Integer hallazgosReportados;
	private Integer hallazgosAnulados;
	private Integer hallazgosInvalidados;
	private IndicadorDTO indicador;
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014  
	 */ 
	public IndEfectividadHallazgosReportadosDTO() {
		super();
	}
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @param hallazgosReportados
	 * @param hallazgosAnulados
	 * @param hallazgosInvalidados
	 * @param indicador 
	 */ 
	public IndEfectividadHallazgosReportadosDTO(Integer hallazgosReportados,
			Integer hallazgosAnulados, Integer hallazgosInvalidados,
			IndicadorDTO indicador) {
		super();
		this.hallazgosReportados = hallazgosReportados;
		this.hallazgosAnulados = hallazgosAnulados;
		this.hallazgosInvalidados = hallazgosInvalidados;
		this.indicador = indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @return the hallazgosReportados 
	 */
	public Integer getHallazgosReportados() {
		return hallazgosReportados;
	}
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @param hallazgosReportados the hallazgosReportados to set 
	 */
	public void setHallazgosReportados(Integer hallazgosReportados) {
		this.hallazgosReportados = hallazgosReportados;
	}
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @return the hallazgosAnulados 
	 */
	public Integer getHallazgosAnulados() {
		return hallazgosAnulados;
	}
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @param hallazgosAnulados the hallazgosAnulados to set 
	 */
	public void setHallazgosAnulados(Integer hallazgosAnulados) {
		this.hallazgosAnulados = hallazgosAnulados;
	}
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @return the hallazgosInvalidados 
	 */
	public Integer getHallazgosInvalidados() {
		return hallazgosInvalidados;
	}
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @param hallazgosInvalidados the hallazgosInvalidados to set 
	 */
	public void setHallazgosInvalidados(Integer hallazgosInvalidados) {
		this.hallazgosInvalidados = hallazgosInvalidados;
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
	public Double calculateIndicadorEfectividadHallazgosReportados() throws AppBaseException {
		if(hallazgosReportados != null && hallazgosReportados != 0) {
			Integer divisor = hallazgosReportados - (hallazgosAnulados + hallazgosInvalidados);
			return divisor.doubleValue() / hallazgosReportados.doubleValue();
		}
		return 0d;
	}
	
	
	
}
