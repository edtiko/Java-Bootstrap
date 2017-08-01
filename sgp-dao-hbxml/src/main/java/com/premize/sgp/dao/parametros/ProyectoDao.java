package com.premize.sgp.dao.parametros;

import com.premize.sgp.dto.ResultSet;

import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class ProyectoDao
 * @since 27/03/2014
 *
 */
public interface ProyectoDao extends Dao<SgpProyecto, Integer> {
	
	public static final Integer ACTIVO = 1;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 * @throws AppBaseException 
	 */
	List<SgpProyecto> getListProyectos() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<ProyectoDTO> getRecords(PagingCriteria criteria,Boolean mostraActivoInactivo) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idEmpresa
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	public List<ProyectoDTO> consultarProyectoPorEmpresa(Integer idEmpresa, Paginator page) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description 
	 * @param idProyecto
	 * @return
	 * @throws AppBaseException
	 */
	String consultarProyectoNombre(Integer idProyecto) throws AppBaseException;
	
}
