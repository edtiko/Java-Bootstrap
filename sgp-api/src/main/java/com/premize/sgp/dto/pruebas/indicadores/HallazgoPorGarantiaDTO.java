package com.premize.sgp.dto.pruebas.indicadores;

/**
 * 
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-api
 * @class HallazgoPorGarantiaDTO
 * @date 14/07/2014
 * 
 */
public class HallazgoPorGarantiaDTO {

	private Integer hallazgosReportados;
	private Integer hallazgosAnulados;
	private Integer hallazgosInvalidados;
	private Integer totalArtefacto;
	private String formula;
	private Float valorMinimo;
	private Float valorMaximo;
	private String semaforo;
	private Float indicador;
	private String indicadorString;
	
	/**
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 14/07/2014
	 */
	public HallazgoPorGarantiaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 14/07/2014
	 * @param hallazgosReportados
	 * @param hallazgosAnulados
	 * @param hallazgosInvalidados
	 * @param totalArtefacto
	 */
	public HallazgoPorGarantiaDTO(Integer hallazgosReportados,
			Integer hallazgosAnulados, Integer hallazgosInvalidados,
			Integer totalArtefacto) {
		super();
		this.hallazgosReportados = hallazgosReportados;
		this.hallazgosAnulados = hallazgosAnulados;
		this.hallazgosInvalidados = hallazgosInvalidados;
		this.totalArtefacto = totalArtefacto;
	}

	/**
	 * @return the hallazgosReportados
	 */
	public Integer getHallazgosReportados() {
		return hallazgosReportados;
	}

	/**
	 * @param hallazgosReportados
	 *            the hallazgosReportados to set
	 */
	public void setHallazgosReportados(Integer hallazgosReportados) {
		this.hallazgosReportados = hallazgosReportados;
	}

	/**
	 * @return the hallazgosAnulados
	 */
	public Integer getHallazgosAnulados() {
		return hallazgosAnulados;
	}

	/**
	 * @param hallazgosAnulados
	 *            the hallazgosAnulados to set
	 */
	public void setHallazgosAnulados(Integer hallazgosAnulados) {
		this.hallazgosAnulados = hallazgosAnulados;
	}

	/**
	 * @return the hallazgosInvalidados
	 */
	public Integer getHallazgosInvalidados() {
		return hallazgosInvalidados;
	}

	/**
	 * @param hallazgosInvalidados
	 *            the hallazgosInvalidados to set
	 */
	public void setHallazgosInvalidados(Integer hallazgosInvalidados) {
		this.hallazgosInvalidados = hallazgosInvalidados;
	}

	/**
	 * @return the totalArtefacto
	 */
	public Integer getTotalArtefacto() {
		return totalArtefacto;
	}

	/**
	 * @param totalArtefacto
	 *            the totalArtefacto to set
	 */
	public void setTotalArtefacto(Integer totalArtefacto) {
		this.totalArtefacto = totalArtefacto;
	}

	/**
	 * @return the formula
	 */
	public String getFormula() {
		return formula;
	}

	/**
	 * @param formula
	 *            the formula to set
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	/**
	 * @return the valorMinimo
	 */
	public Float getValorMinimo() {
		return valorMinimo;
	}

	/**
	 * @param valorMinimo the valorMinimo to set
	 */
	public void setValorMinimo(Float valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	/**
	 * @return the valorMaximo
	 */
	public Float getValorMaximo() {
		return valorMaximo;
	}

	/**
	 * @param valorMaximo the valorMaximo to set
	 */
	public void setValorMaximo(Float valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	/**
	 * @return the semaforo
	 */
	public String getSemaforo() {
		return semaforo;
	}

	/**
	 * @param semaforo the semaforo to set
	 */
	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}

	/**
	 * @return the indicador
	 */
	public Float getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador the indicador to set
	 */
	public void setIndicador(Float indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return the indicadorString
	 */
	public String getIndicadorString() {
		return indicadorString;
	}

	/**
	 * @param indicadorString the indicadorString to set
	 */
	public void setIndicadorString(String indicadorString) {
		this.indicadorString = indicadorString;
	}

}
