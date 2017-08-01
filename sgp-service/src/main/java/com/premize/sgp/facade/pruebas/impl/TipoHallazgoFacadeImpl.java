/**
 * 
 */
package com.premize.sgp.facade.pruebas.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.facade.pruebas.TipoHallazgoFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.service.ParametroService;
import com.premize.sgp.service.pruebas.TipoHallazgoService;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class PaisFacadeImpl
 * @since 21/01/2014
 */
@Service("tipoHallazgoFacade")
public class TipoHallazgoFacadeImpl extends AbstractEntityFacade<SgpTipoHallazgo, Integer> implements TipoHallazgoFacade {

	
	private static final String INGRESO_HALLAZGO_MANUAL = "INGRESO_HALLAZGO_MANUAL";
	private static final String INGRESO_HALLAZGO = "INGRESO_HALLAZGO";
	private static final String PARAMETRO_FIELD_CLAVE = "nombre";
	private static final byte INGRESO_MANUAL_HALLAZGO=1;
	@Autowired
	private TipoHallazgoService tipoHallazgoService;
	
	@Autowired
	private ParametroService parametroService;
	
	public EntityService<SgpTipoHallazgo, Integer> getEntityService() {
		return tipoHallazgoService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.pruebas.TipoHallazgoFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<TipoHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException {

		return tipoHallazgoService.getRecords(criteria);
	}

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.pruebas.TipoHallazgoFacade#guardarTipoHallazgo(com.premize.sgp.dto.pruebas.TipoHallazgoDTO)
	 */
	@Override
	public void guardarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO) throws AppBaseException {
		tipoHallazgoService.guardarTipoHallazgo(tipoHallazgoDTO);
	}
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.pruebas.TipoHallazgoFacade#editarTipoHallazgo(com.premize.sgp.dto.pruebas.TipoHallazgoDTO)
	 */
	@Override
	public void editarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO)
			throws AppBaseException {
	tipoHallazgoService.editarTipoHallazgo(tipoHallazgoDTO);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.pruebas.TipoHallazgoFacade#getTipoHallazgo(java.lang.Integer)
	 */
	@Override
	public TipoHallazgoDTO getTipoHallazgo(Integer idTipoHallazgo) throws AppBaseException {
		return tipoHallazgoService.getTipoHallazgo(idTipoHallazgo);
	}

/*
 * (non-Javadoc)
 * @see com.premize.sgp.facade.pruebas.TipoHallazgoFacade#getTipoHallazgos(byte)
 */
	@Override
	public List<TipoHallazgoDTO> getTipoHallazgos(byte tipoIngresoHallazgo) throws AppBaseException {
		SgpParametro parametro;
		String[] tipoIngresoHallazgos;
		if(tipoIngresoHallazgo==INGRESO_MANUAL_HALLAZGO){
			parametro = parametroService.findByProperty(PARAMETRO_FIELD_CLAVE, INGRESO_HALLAZGO_MANUAL);
		}else{
			parametro = parametroService.findByProperty(PARAMETRO_FIELD_CLAVE, INGRESO_HALLAZGO);
		}
		
		tipoIngresoHallazgos=parametro.getValor().split(",");
		return tipoHallazgoService.getTipoHallazgos(tipoIngresoHallazgos);
	}

	public List<TipoHallazgoDTO> tipoHallazgos() throws AppBaseException{
		return tipoHallazgoService.tipoHallazgos();
	}
}