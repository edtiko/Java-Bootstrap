/**
 * 
 */
package com.premize.sgp.dto.pruebas;

import java.util.Date;
import java.util.List;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class HallazgoDTO
 * @since 24/02/2014
 */
public class HallazgoDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8723018662659832627L;
	private Integer id;
	private CasoDePruebaDTO casoPrueba;
	private SgpCasoPrueba casoPrueba2;
	private ArtefactoDTO artefacto;
	private TipoHallazgoDTO tipoHallazgo;
	private CausaGeneracionDTO causaGeneracion;
	private TipoSeveridadDTO severidad;
	private TipoPrioridadDTO prioridad;
	private ParametroDTO causaAnulacion;
	private ParametroDTO motivoReasignacion;
	private UsuarioDTO usuarioAsignado;
	private UsuarioDTO usuarioAsigna;
	private ProyectoDTO proyecto;
	private MapaPruebasDTO mapaPrueba;
	private String titulo;
	private String descripcion;
	private String usuarioCrea;
	private ParametroDTO accion;
	private List<FlujoHallazgoDTO> flujos;
	private List<AnexoHallazgoDTO> anexos;
	private FlujoHallazgoDTO flujoActual;
	private String fechaSolicitudString;
	private Date fechaSolicitud;
	
	public HallazgoDTO(CasoDePruebaDTO casoPrueba, TipoHallazgoDTO tipoHallazgo, CausaGeneracionDTO causaGeneracion,
			TipoSeveridadDTO severidad, TipoPrioridadDTO prioridad, String titulo, String descripcion) {
		this.casoPrueba = casoPrueba;
		this.tipoHallazgo = tipoHallazgo;
		this.causaGeneracion = causaGeneracion;
		this.severidad = severidad;
		this.prioridad = prioridad;
		this.descripcion = descripcion;
		this.titulo = titulo;
	}
	
	public HallazgoDTO() {
		super();
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/02/2014
	 * @return the proyecto
	 */
	public ProyectoDTO getProyecto() {
		return proyecto;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/02/2014
	 * @param proyecto
	 *            the proyecto to set
	 */
	public void setProyecto(ProyectoDTO proyecto) {
		this.proyecto = proyecto;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/02/2014
	 * @return the mapaPrueba
	 */
	public MapaPruebasDTO getMapaPrueba() {
		return mapaPrueba;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/02/2014
	 * @param mapaPrueba
	 *            the mapaPrueba to set
	 */
	public void setMapaPrueba(MapaPruebasDTO mapaPrueba) {
		this.mapaPrueba = mapaPrueba;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @return the casoPrueba
	 */
	public CasoDePruebaDTO getCasoPrueba() {
		return casoPrueba;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param casoPrueba
	 *            the casoPrueba to set
	 */
	public void setCasoPrueba(CasoDePruebaDTO casoPrueba) {
		this.casoPrueba = casoPrueba;
	}
	
	
	
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @return
	 */
	public TipoHallazgoDTO getTipoHallazgo() {
		return tipoHallazgo;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param tipoHallazgo
	 */
	public void setTipoHallazgo(TipoHallazgoDTO tipoHallazgo) {
		this.tipoHallazgo = tipoHallazgo;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @return
	 */
	public CausaGeneracionDTO getCausaGeneracion() {
		return causaGeneracion;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param causaGeneracion
	 */
	public void setCausaGeneracion(CausaGeneracionDTO causaGeneracion) {
		this.causaGeneracion = causaGeneracion;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @return
	 */
	public TipoSeveridadDTO getSeveridad() {
		return severidad;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param severidad
	 */
	public void setSeveridad(TipoSeveridadDTO severidad) {
		this.severidad = severidad;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @return
	 */
	public TipoPrioridadDTO getPrioridad() {
		return prioridad;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param prioridad
	 */
	public void setPrioridad(TipoPrioridadDTO prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @return the causaAnulacion
	 */
	public ParametroDTO getCausaAnulacion() {
		return causaAnulacion;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param causaAnulacion
	 *            the causaAnulacion to set
	 */
	public void setCausaAnulacion(ParametroDTO causaAnulacion) {
		this.causaAnulacion = causaAnulacion;
	}
	
	public HallazgoDTO(SgpHallazgo sgpHallazgo) {
		
		this.id = sgpHallazgo.getId();
		if(null != sgpHallazgo.getCasoPrueba()){
			this.casoPrueba = new CasoDePruebaDTO(sgpHallazgo.getCasoPrueba());
			this.mapaPrueba = new MapaPruebasDTO(sgpHallazgo.getCasoPrueba().getSgpMapaPrueba());
			this.artefacto = new ArtefactoDTO(sgpHallazgo.getCasoPrueba().getSgpArtefacto());
		}

		if(null != sgpHallazgo.getArtefacto()){
			this.artefacto = new ArtefactoDTO(sgpHallazgo.getArtefacto());
		}
		
		this.titulo = sgpHallazgo.getTitulo();
		this.tipoHallazgo = new TipoHallazgoDTO(sgpHallazgo.getTipoHallazgo());
		if(this.getTipoHallazgo().getNumeroTieneCausaGeneracion()==1){
			if(null !=sgpHallazgo.getCausaGeneracion()){
				this.causaGeneracion = new CausaGeneracionDTO(sgpHallazgo.getCausaGeneracion());
			}
		}
		if (sgpHallazgo.getCausaAnulacion() != null) {
			this.causaAnulacion = new ParametroDTO(sgpHallazgo.getCausaAnulacion());
		}
		this.severidad = new TipoSeveridadDTO(sgpHallazgo.getSeveridad());
		this.prioridad = new TipoPrioridadDTO(sgpHallazgo.getPrioridad());
		
		if(null!=sgpHallazgo.getCasoPrueba()){
			this.proyecto = new ProyectoDTO(sgpHallazgo.getCasoPrueba().getSgpMapaPrueba().getSgpProyecto());
		}else if(null!=sgpHallazgo.getArtefacto()){
			this.proyecto=new ProyectoDTO(sgpHallazgo.getArtefacto().getProyecto());
				
		}
		
		this.descripcion = sgpHallazgo.getDescripcion(); 
		setIndActivo(sgpHallazgo.getIndActivo());
		setNumeroEstado(sgpHallazgo.getIndActivo());
		setUsuarioCreacion(sgpHallazgo.getUsuarioCrea());
		this.usuarioCrea = sgpHallazgo.getUsuarioCrea();
		setFecCreacion(sgpHallazgo.getFecCreacion());
		
		if (sgpHallazgo.getFecEdita() != null) {
			setFechaEdita(sgpHallazgo.getFecEdita());
		}
		if (sgpHallazgo.getUsuarioEdita() != null) {
			setUsuarioEdita(sgpHallazgo.getUsuarioEdita());
		}
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @param sgpHallazgo
	 * @param flujos
	 */
	public HallazgoDTO(SgpHallazgo sgpHallazgo, List<FlujoHallazgoDTO> flujos, List<AnexoHallazgoDTO> anexos,
			SgpFlujoHallazgo flujoActual) {
		this.id = sgpHallazgo.getId();
		if(null != sgpHallazgo.getCasoPrueba()){
			this.casoPrueba = new CasoDePruebaDTO(sgpHallazgo.getCasoPrueba());
			this.mapaPrueba = new MapaPruebasDTO(sgpHallazgo.getCasoPrueba().getSgpMapaPrueba());
			this.artefacto = new ArtefactoDTO(sgpHallazgo.getCasoPrueba().getSgpArtefacto());
		}

		if(null != sgpHallazgo.getArtefacto()){
			this.artefacto = new ArtefactoDTO(sgpHallazgo.getArtefacto());
		}
		

		
		this.titulo = sgpHallazgo.getTitulo();
		this.tipoHallazgo = new TipoHallazgoDTO(sgpHallazgo.getTipoHallazgo());
		if(this.tipoHallazgo.getNumeroTieneCausaGeneracion()==1){
			
			if(null !=sgpHallazgo.getCausaGeneracion()){
				this.causaGeneracion = new CausaGeneracionDTO(sgpHallazgo.getCausaGeneracion());
			}
		}
		if (sgpHallazgo.getCausaAnulacion() != null) {
			this.causaAnulacion = new ParametroDTO(sgpHallazgo.getCausaAnulacion());
		}
		this.severidad = new TipoSeveridadDTO(sgpHallazgo.getSeveridad());
		this.prioridad = new TipoPrioridadDTO(sgpHallazgo.getPrioridad());
		
		if(null!=sgpHallazgo.getCasoPrueba()){
			this.proyecto = new ProyectoDTO(sgpHallazgo.getCasoPrueba().getSgpMapaPrueba().getSgpProyecto());
		}else if(null!=sgpHallazgo.getArtefacto()){
			this.proyecto=new ProyectoDTO(sgpHallazgo.getArtefacto().getProyecto());
				
		}
		
		

		this.descripcion = sgpHallazgo.getDescripcion();
		setIndActivo(sgpHallazgo.getIndActivo());
		setNumeroEstado(sgpHallazgo.getIndActivo());
		setUsuarioCreacion(sgpHallazgo.getUsuarioCrea());
		this.usuarioCrea = sgpHallazgo.getUsuarioCrea();
		setFecCreacion(sgpHallazgo.getFecCreacion());
		if (sgpHallazgo.getFecEdita() != null) {
			setFechaEdita(sgpHallazgo.getFecEdita());
		}
		if (sgpHallazgo.getUsuarioEdita() != null) {
			setUsuarioEdita(sgpHallazgo.getUsuarioEdita());
		}
		this.flujos = flujos;
		this.anexos = anexos;
		this.flujoActual = new FlujoHallazgoDTO(flujoActual);
	}
	
	public HallazgoDTO(SgpHallazgo sgpHallazgo, SgpFlujoHallazgo sgpFlujoHallazgo) {
		
		this.id = sgpHallazgo.getId();
		
		if(null != sgpHallazgo.getCasoPrueba()){
			this.casoPrueba = new CasoDePruebaDTO(sgpHallazgo.getCasoPrueba());
			this.mapaPrueba = new MapaPruebasDTO(sgpHallazgo.getCasoPrueba().getSgpMapaPrueba());
			this.artefacto = new ArtefactoDTO(sgpHallazgo.getCasoPrueba().getSgpArtefacto());
		}

		if(null != sgpHallazgo.getArtefacto()){
			this.artefacto = new ArtefactoDTO(sgpHallazgo.getArtefacto());
		}
		
	
		this.titulo = sgpHallazgo.getTitulo();
		this.tipoHallazgo = new TipoHallazgoDTO(sgpHallazgo.getTipoHallazgo());
		
		
		
		
		if(this.tipoHallazgo.getNumeroTieneCausaGeneracion()==1){
			if(null !=sgpHallazgo.getCausaGeneracion()){
				this.causaGeneracion = new CausaGeneracionDTO(sgpHallazgo.getCausaGeneracion());
			}
		}
		if (sgpHallazgo.getCausaAnulacion() != null) {
			this.causaAnulacion = new ParametroDTO(sgpHallazgo.getCausaAnulacion());
		}
		this.severidad = new TipoSeveridadDTO(sgpHallazgo.getSeveridad());
		this.prioridad = new TipoPrioridadDTO(sgpHallazgo.getPrioridad());
		this.descripcion = sgpHallazgo.getDescripcion();
		if (sgpFlujoHallazgo.getUsuarioAsignado() != null)
			this.usuarioAsignado = new UsuarioDTO(sgpFlujoHallazgo.getUsuarioAsignado());
		if (sgpFlujoHallazgo.getUsuarioAsigna() != null){
			this.usuarioAsigna = new UsuarioDTO(sgpFlujoHallazgo.getUsuarioAsigna());
		}
		if(null!=sgpHallazgo.getCasoPrueba()){
			this.proyecto = new ProyectoDTO(sgpHallazgo.getCasoPrueba().getSgpMapaPrueba().getSgpProyecto());
		}else if(null!=sgpHallazgo.getArtefacto()){
			this.proyecto=new ProyectoDTO(sgpHallazgo.getArtefacto().getProyecto());
				
		}

		setIndActivo(sgpHallazgo.getIndActivo());
		setNumeroEstado(sgpHallazgo.getIndActivo());
		setUsuarioCreacion(sgpHallazgo.getUsuarioCrea());
		this.usuarioCrea = sgpHallazgo.getUsuarioCrea();
		setFecCreacion(sgpHallazgo.getFecCreacion());
		if (sgpHallazgo.getFecEdita() != null) {
			setFechaEdita(sgpHallazgo.getFecEdita());
		}
		if (sgpHallazgo.getUsuarioEdita() != null) {
			setUsuarioEdita(sgpHallazgo.getUsuarioEdita());
		}
		if (sgpFlujoHallazgo.getAccion() != null)
			this.accion = new ParametroDTO(sgpFlujoHallazgo.getAccion());
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @return the artefacto
	 */
	public ArtefactoDTO getArtefacto() {
		return artefacto;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param artefacto
	 *            the artefacto to set
	 */
	public void setArtefacto(ArtefactoDTO artefacto) {
		this.artefacto = artefacto;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 5/03/2014
	 * @return the usuarioCrea
	 */
	public String getUsuarioCrea() {
		return usuarioCrea;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 5/03/2014
	 * @param usuarioCrea
	 *            the usuarioCrea to set
	 */
	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 6/03/2014
	 * @return the usuarioAsignado
	 */
	public UsuarioDTO getUsuarioAsignado() {
		return usuarioAsignado;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 6/03/2014
	 * @param usuarioAsignado
	 *            the usuarioAsignado to set
	 */
	public void setUsuarioAsignado(UsuarioDTO usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 6/03/2014
	 * @return the usuarioAsigna
	 */
	public UsuarioDTO getUsuarioAsigna() {
		return usuarioAsigna;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 6/03/2014
	 * @param usuarioAsigna
	 *            the usuarioAsigna to set
	 */
	public void setUsuarioAsigna(UsuarioDTO usuarioAsigna) {
		this.usuarioAsigna = usuarioAsigna;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 6/03/2014
	 * @return the accion
	 */
	public ParametroDTO getAccion() {
		return accion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 6/03/2014
	 * @param accion
	 *            the accion to set
	 */
	public void setAccion(ParametroDTO accion) {
		this.accion = accion;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @return the flujos
	 */
	public List<FlujoHallazgoDTO> getFlujos() {
		return flujos;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @param flujos
	 *            the flujos to set
	 */
	public void setFlujos(List<FlujoHallazgoDTO> flujos) {
		this.flujos = flujos;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @return the flujoActual
	 */
	public FlujoHallazgoDTO getFlujoActual() {
		return flujoActual;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @param flujoActual
	 *            the flujoActual to set
	 */
	public void setFlujoActual(FlujoHallazgoDTO flujoActual) {
		this.flujoActual = flujoActual;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @return the anexos
	 */
	public List<AnexoHallazgoDTO> getAnexos() {
		return anexos;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @param anexos
	 *            the anexos to set
	 */
	public void setAnexos(List<AnexoHallazgoDTO> anexos) {
		this.anexos = anexos;
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 8/04/2014
	 * @return
	 */
	public ParametroDTO getMotivoReasignacion() {
		return motivoReasignacion;
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 8/04/2014
	 * @param motivoReasignacion
	 */
	public void setMotivoReasignacion(ParametroDTO motivoReasignacion) {
		this.motivoReasignacion = motivoReasignacion;
	}

	public SgpCasoPrueba getCasoPrueba2() {
		return casoPrueba2;
	}

	public void setCasoPrueba2(SgpCasoPrueba casoPrueba2) {
		this.casoPrueba = new CasoDePruebaDTO(casoPrueba2);
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 8/07/2014
	* @return
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 8/07/2014
	* @param fechaSolicitud
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 8/07/2014
* @return
 */
	public String getFechaSolicitudString() {
		return fechaSolicitudString;
	}

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 8/07/2014
* @param fechaSolicitudString
 */
	public void setFechaSolicitudString(String fechaSolicitudString) {
		this.fechaSolicitudString = fechaSolicitudString;
	}
	
}
