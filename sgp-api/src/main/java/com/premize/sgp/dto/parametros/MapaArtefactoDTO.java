package com.premize.sgp.dto.parametros;

import javax.validation.Valid;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-api
 * @class MapaArtefactoDTO
 * @date 4/02/2014
 */
public class MapaArtefactoDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7660423963939257585L;
	private Integer id;
	@Valid
	private ArtefactoDTO artefacto;
	private MapaPruebasDTO mapaPrueba;
	private TipoArtefactoDTO tipoArtefacto;
	
	public MapaArtefactoDTO() {
		super();
		
	}
	
	public MapaArtefactoDTO(Integer id, String nombre) {
		super();
		this.id = id;
	}
	
	public MapaArtefactoDTO(SgpMapaArtefacto mapaArtefacto) {
		
		this.id = mapaArtefacto.getId();
		this.artefacto = new ArtefactoDTO(mapaArtefacto.getSgpArtefacto());
		this.mapaPrueba = new MapaPruebasDTO(mapaArtefacto.getSgpMapaPrueba());
		this.tipoArtefacto = new TipoArtefactoDTO(mapaArtefacto.getSgpArtefacto().getSgpTipoArtefacto());
		setIndActivo(mapaArtefacto.getIndActivo());
		setFecCreacion(mapaArtefacto.getFecCreacion());
		setUsuarioCreacion(mapaArtefacto.getUsuarioCrea());
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 20/02/2014
	 * @return the artefacto
	 */
	public ArtefactoDTO getArtefacto() {
		return artefacto;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 20/02/2014
	 * @param artefacto
	 *            the artefacto to set
	 */
	public void setArtefacto(ArtefactoDTO artefacto) {
		this.artefacto = artefacto;
	}
	
	public TipoArtefactoDTO getTipoArtefacto() {
		return tipoArtefacto;
	}
	
	public void setTipoArtefacto(TipoArtefactoDTO tipoArtefacto) {
		this.tipoArtefacto = tipoArtefacto;
	}
	
	public MapaPruebasDTO getMapaPrueba() {
		return mapaPrueba;
	}
	
	public void setMapaPrueba(MapaPruebasDTO mapaPruebas) {
		this.mapaPrueba = mapaPruebas;
	}
	
}
