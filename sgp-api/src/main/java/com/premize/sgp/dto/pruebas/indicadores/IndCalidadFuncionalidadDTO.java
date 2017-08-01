package com.premize.sgp.dto.pruebas.indicadores;

import java.io.Serializable;

import com.premize.sgp.dto.parametros.IndicadoresDTO;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class IndCalidadFuncionalidadDTO
 * @description DTO Indicador Calidad por Funcionalidad (ICF)
 * @date 9/07/2014
 */
public class IndCalidadFuncionalidadDTO implements Serializable {
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014
	 */ 
	private static final long serialVersionUID = 2279670574150464024L;
	
	private String artefactoNombre;
	private Integer artefactoId;
	private String usuario;
	private Integer pruebasEjecutadas;
	private Integer hallazgosReportados;
	private Integer hallazgosInvalidados;
	private Integer hallazgosAnulados;
	private IndicadorDTO indicador;
	private IndicadoresDTO parametroIndicador;
	
	private Integer totalPruebasEjecutadas;
	private Integer totalHallazgosReportados;
	private Integer totalHallazgosInvalidados;
	private Integer totalHallazgosAnulados;
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014  
	 */ 
	public IndCalidadFuncionalidadDTO() {
		super();
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param artefactoNombre
	 * @param artefactoId
	 * @param usuario
	 * @param pruebasEjecutadas
	 * @param hallazgosReportados
	 * @param hallazgosInvalidados
	 * @param hallazgosAnulados
	 * @param indicador 
	 */ 
	public IndCalidadFuncionalidadDTO(String artefactoNombre, Integer artefactoId,
			String usuario, Integer pruebasEjecutadas,
			Integer hallazgosReportados, Integer hallazgosInvalidados,
			Integer hallazgosAnulados, IndicadorDTO indicador) {
		super();
		this.artefactoNombre = artefactoNombre;
		this.artefactoId = artefactoId;
		this.usuario = usuario;
		this.pruebasEjecutadas = pruebasEjecutadas;
		this.hallazgosReportados = hallazgosReportados;
		this.hallazgosInvalidados = hallazgosInvalidados;
		this.hallazgosAnulados = hallazgosAnulados;
		this.indicador = indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014 
	 * @param parametroIndicador 
	 */ 
	public IndCalidadFuncionalidadDTO(IndicadoresDTO parametroIndicador) {
		super();
		this.parametroIndicador = parametroIndicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the artefactoNombre 
	 */
	public String getArtefactoNombre() {
		return artefactoNombre;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param artefactoNombre the artefactoNombre to set 
	 */
	public void setArtefactoNombre(String artefactoNombre) {
		this.artefactoNombre = artefactoNombre;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the artefactoId 
	 */
	public Integer getArtefactoId() {
		return artefactoId;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param artefactoId the artefactoId to set 
	 */
	public void setArtefactoId(Integer artefactoId) {
		this.artefactoId = artefactoId;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the usuario 
	 */
	public String getUsuario() {
		return usuario;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param usuario the usuario to set 
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the pruebasEjecutadas 
	 */
	public Integer getPruebasEjecutadas() {
		return pruebasEjecutadas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param pruebasEjecutadas the pruebasEjecutadas to set 
	 */
	public void setPruebasEjecutadas(Integer pruebasEjecutadas) {
		this.pruebasEjecutadas = pruebasEjecutadas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the hallazgosReportados 
	 */
	public Integer getHallazgosReportados() {
		if(hallazgosReportados==null){
			hallazgosReportados=0;
		}
		return hallazgosReportados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param hallazgosReportados the hallazgosReportados to set 
	 */
	public void setHallazgosReportados(Integer hallazgosReportados) {
		this.hallazgosReportados = hallazgosReportados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the hallazgosInvalidados 
	 */
	public Integer getHallazgosInvalidados() {
		if(hallazgosInvalidados==null){
			hallazgosInvalidados=0;
		}
		return hallazgosInvalidados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param hallazgosInvalidados the hallazgosInvalidados to set 
	 */
	public void setHallazgosInvalidados(Integer hallazgosInvalidados) {
		this.hallazgosInvalidados = hallazgosInvalidados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the hallazgosAnulados 
	 */
	public Integer getHallazgosAnulados() {
		if(hallazgosAnulados==null){
			hallazgosAnulados=0;
		}
		return hallazgosAnulados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param hallazgosAnulados the hallazgosAnulados to set 
	 */
	public void setHallazgosAnulados(Integer hallazgosAnulados) {
		this.hallazgosAnulados = hallazgosAnulados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the indicador 
	 */
	public IndicadorDTO getIndicador() {
		return indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param indicador the indicador to set 
	 */
	public void setIndicador(IndicadorDTO indicador) {
		this.indicador = indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the totalPruebasEjecutadas 
	 */
	public Integer getTotalPruebasEjecutadas() {
		return totalPruebasEjecutadas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param totalPruebasEjecutadas the totalPruebasEjecutadas to set 
	 */
	public void setTotalPruebasEjecutadas(Integer totalPruebasEjecutadas) {
		this.totalPruebasEjecutadas = totalPruebasEjecutadas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the totalHallazgosReportados 
	 */
	public Integer getTotalHallazgosReportados() {
		return totalHallazgosReportados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param totalHallazgosReportados the totalHallazgosReportados to set 
	 */
	public void setTotalHallazgosReportados(Integer totalHallazgosReportados) {
		this.totalHallazgosReportados = totalHallazgosReportados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the totalHallazgosInvalidados 
	 */
	public Integer getTotalHallazgosInvalidados() {
		return totalHallazgosInvalidados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param totalHallazgosInvalidados the totalHallazgosInvalidados to set 
	 */
	public void setTotalHallazgosInvalidados(Integer totalHallazgosInvalidados) {
		this.totalHallazgosInvalidados = totalHallazgosInvalidados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the totalHallazgosAnulados 
	 */
	public Integer getTotalHallazgosAnulados() {
		return totalHallazgosAnulados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param totalHallazgosAnulados the totalHallazgosAnulados to set 
	 */
	public void setTotalHallazgosAnulados(Integer totalHallazgosAnulados) {
		this.totalHallazgosAnulados = totalHallazgosAnulados;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014
	 * @description 
	 * @return
	 */
	public Double calculateIndicadorCalidadFuncionalidad() {
		
    	if(pruebasEjecutadas != null && pruebasEjecutadas != 0) {
    		hallazgosReportados=hallazgosReportados==null?0:hallazgosReportados;
    		hallazgosInvalidados=hallazgosInvalidados==null?0:hallazgosInvalidados;
    		hallazgosAnulados=hallazgosAnulados==null?0:hallazgosAnulados;
    		
    		Integer divisor = (pruebasEjecutadas - (hallazgosReportados - (hallazgosInvalidados + hallazgosAnulados)));
    		return divisor.doubleValue() / pruebasEjecutadas.doubleValue();
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
