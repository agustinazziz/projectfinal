package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.capa2LogicaNegocio.ActividadBeanRemote;
import com.capa2LogicaNegocio.CasillasBeanRemote;
import com.capa2LogicaNegocio.FormulariosBeanRemote;
import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa2LogicaNegocio.Usuario;
import com.entities.Actividad;
import com.entities.CasillaNueva;
import com.entities.FormularioNuevo;
import com.entities.UsuarioEmpresa;
import com.exception.PersistenciaException;

@Named(value = "gestionActividad") // JEE8
@SessionScoped // JEE8
public class GestionActividadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ActividadBeanRemote actividadBean;
	@EJB
	GestionUsuarioService gestionUsuario;
	@EJB
	FormulariosBeanRemote formulariosBean;
	@EJB
	CasillasBeanRemote casillasBean;

	public Actividad crearActividad(Actividad actividad) throws Exception {
		return actividadBean.agregarActividad(actividad);
	}

	public Actividad buscarActividad(Actividad actividad) throws Exception {
		return actividadBean.buscarActividad(actividad);
	}

	public Actividad modificarActividad(Actividad actividad) throws Exception {
		return actividadBean.modificarActividad(actividad);
	}

	String caracteristica;
	Date fechaIni;
	Date fechaFin;
	String metodoMuestreo;
	String tipoMuestreo;
	Integer latitud;
	Integer longitud;
	UsuarioEmpresa usuarioCreador;
	FormularioNuevo formActividad;

	String usuarioActividad;
	String contraseñaActividad;

	String idForm;
	String nombreForm;
	String resumenForm;

	String contadorString;
	Integer contador = 0;
	Integer controladorContador = 0;

	List<CasillaNueva> casillaNueva = new ArrayList<CasillaNueva>();
	List<Actividad> listadoActividad = new ArrayList<Actividad>();

//	METODOS  //

	public String salvarCambios() throws Exception {
		
		formActividad = formulariosBean.buscarFormulario(Long.parseLong(idForm));
		
		usuarioCreador = gestionUsuario.buscarInicioSesion(usuarioActividad, contraseñaActividad);
		
		Actividad nuevaActividad = new Actividad(caracteristica, fechaIni, fechaFin, metodoMuestreo, tipoMuestreo, latitud, longitud, usuarioCreador, formActividad);
		
		actividadBean.agregarActividad(nuevaActividad);
		
		
		
		
		for (Integer i = 0 ; i <= contador-1 ; i++) {
			try {
				casillasBean.ModificarCasilla(casillaNueva.get(i).getIdCasilla(),
											  casillaNueva.get(i).getNombre(),
											  casillaNueva.get(i).getDescripcion(),
											  casillaNueva.get(i).getParametro(),
											  casillaNueva.get(i).getUnidadesMedida(),
											  casillaNueva.get(i).getTiposDato(),
											  formActividad);
				
			}catch(Exception e) {
				casillasBean.altaCasilla(casillaNueva.get(i).getNombre(),
										 casillaNueva.get(i).getDescripcion(),
										 casillaNueva.get(i).getParametro(),
										 casillaNueva.get(i).getUnidadesMedida(),
										 casillaNueva.get(i).getTiposDato(),
										 formActividad);
				
			
			}
			
		}
		
		caracteristica=null;
		fechaIni=null;
		fechaFin=null;
		metodoMuestreo=null;
		tipoMuestreo=null;
		latitud=null;
		longitud=null;
		usuarioCreador=null;
		formActividad=null;
		
		return "/pages/formularios.xhtml";
	}
	
	public List<CasillaNueva> buscarCasillaFormId(Long idForm){
		return actividadBean.buscarCasillaFormId(idForm);
	}
	
	public List<Actividad> buscarActividades() {
		listadoActividad= actividadBean.listarActividades();		
		return listadoActividad;
	}
  
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public void addContador() {
		contador = contador + 1;
		CasillaNueva nuevaCasilla = new CasillaNueva();
		casillaNueva.add(nuevaCasilla);
	}

	public void delContador() {
		if (contador > controladorContador) {
			contador = contador - 1;
			casillaNueva.remove(contador + 1 - 1);
		}
	}
	
	public String prepModif() {
		for (Integer i = 0; i < 10; i++) {
			CasillaNueva casillaAux = new CasillaNueva();
			casillaNueva.add(casillaAux);
		}
		return"true";
	}
	
	public String prepCasillas() {
			casillaNueva.clear();
		for (Integer i = 0; i < 10; i++) {
			CasillaNueva casillaAux = new CasillaNueva();
			casillaNueva.add(casillaAux);
		}
		return "/pages/formularios.xhtml";
	}

	public String modifContador() {
		contador = Integer.parseInt(contadorString);
		controladorContador = contador;

		List<CasillaNueva> listaAuxiliar = new ArrayList<CasillaNueva>();
		for (Integer i = 0; i < contador; i++) {
			CasillaNueva casillaAux = new CasillaNueva();
			listaAuxiliar.add(casillaAux);
			listaAuxiliar.set(i, casillaNueva.get(i));
		}

		casillaNueva.clear();
		for (Integer i = 0; i < contador; i++) {
			CasillaNueva casillaAux = new CasillaNueva();
			casillaNueva.add(casillaAux);
			casillaNueva.set(i, listaAuxiliar.get(i));

		}

		listaAuxiliar.clear();
		return "Actividades.xhtml?faces-redirect=true";
	}

//	Getters and Setters  //
	public Integer getContador() {
		return contador;
	}

	public String getIdForm() {
		return idForm;
	}

	public void setIdForm(String idForm) {
		this.idForm = idForm;
	}

	public String getUsuarioActividad() {
		return usuarioActividad;
	}

	public void setUsuarioActividad(String usuarioActividad) {
		this.usuarioActividad = usuarioActividad;
	}
	
	
	
	public List<Actividad> getListadoActividad() {
		return listadoActividad;
	}

	public void setListadoActividad(List<Actividad> listadoActividad) {
		this.listadoActividad = listadoActividad;
	}

	public String getContraseñaActividad() {
		return contraseñaActividad;
	}

	public void setContraseñaActividad(String contraseñaActividad) {
		this.contraseñaActividad = contraseñaActividad;
	}

	public String getNombreForm() {
		return nombreForm;
	}

	public void setNombreForm(String nombreForm) {
		this.nombreForm = nombreForm;
	}

	public String getResumenForm() {
		return resumenForm;
	}

	public void setResumenForm(String resumenForm) {
		this.resumenForm = resumenForm;
	}

	public String getContadorString() {
		return contadorString;
	}

	public void setContadorString(String contadorString) {
		this.contadorString = contadorString;
	}

	public List<CasillaNueva> getCasillaNueva() {
		return casillaNueva;
	}

	public void setCasillaNueva(List<CasillaNueva> casillaNueva) {
		this.casillaNueva = casillaNueva;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getMetodoMuestreo() {
		return metodoMuestreo;
	}

	public void setMetodoMuestreo(String metodoMuestreo) {
		this.metodoMuestreo = metodoMuestreo;
	}

	public String getTipoMuestreo() {
		return tipoMuestreo;
	}

	public void setTipoMuestreo(String tipoMuestreo) {
		this.tipoMuestreo = tipoMuestreo;
	}

	public Integer getLatitud() {
		return latitud;
	}

	public void setLatitud(Integer latitud) {
		this.latitud = latitud;
	}

	public Integer getLongitud() {
		return longitud;
	}

	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}

	public UsuarioEmpresa getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(UsuarioEmpresa usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public FormularioNuevo getFormActividad() {
		return formActividad;
	}

	public void setFormActividad(FormularioNuevo formActividad) {
		this.formActividad = formActividad;
	}

}
