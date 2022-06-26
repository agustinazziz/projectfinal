package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.capa2LogicaNegocio.Usuario;


/**
 * The persistent class for the CASILLAS database table.
 * 
 */
@Entity
@Table(name="ACTIVIDADES")
@NamedQuery(name="Actividad.findAll", query="SELECT a FROM Actividad a")
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACTIVIDADES_IDCACTIVIDAD_GENERATOR", sequenceName="SEQ_ID_ACTIVIDAD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACTIVIDAD_IDACTIVIDAD_GENERATOR")
	@Column(name="ID_ACTIVIDAD", unique=true, nullable=false, precision=38)
	private long idActividad;

	@Column(nullable=true, length=150)
	private String caracteristica;
	
	@Column(nullable=true, length=150)
	private Date fechaIni;

	@Column(nullable=true, length=100)
	private Date fechaFin;

	@Column(nullable=true, length=40)
	private String metodoMuestreo;

	@Column(nullable=true, length=40)
	private String tipoMuestreo;
	
	@Column(nullable=true, length=40)
	private Integer latitud;
	
	@Column(nullable=true, length=40)
	private Integer longitud;
	
	@ManyToOne
	@JoinColumn (name= "FK_USUARIOCREADOR", nullable= true, updatable= true)
	private UsuarioEmpresa usuarioCreador;
	
	
	
	@ManyToOne
	@JoinColumn (name= "FK_FORMULARIONUEVO_ACTIVIDAD", nullable= true, updatable= true)
	private FormularioNuevo formActividad;

	
	public Actividad() {
		super();
	}
	
	
	
	
	
	public Actividad(String caracteristica, Date fechaIni, Date fechaFin, String metodoMuestreo, String tipoMuestreo,
			Integer latitud, Integer longitud, UsuarioEmpresa usuarioCreador, FormularioNuevo formActividad) {
		super();
		this.caracteristica = caracteristica;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.metodoMuestreo = metodoMuestreo;
		this.tipoMuestreo = tipoMuestreo;
		this.latitud = latitud;
		this.longitud = longitud;
		this.usuarioCreador = usuarioCreador;
		this.formActividad = formActividad;
	}





	public Actividad(String caracteristica, Date fechaIni, Date fechaFin, String metodoMuestreo, String tipoMuestreo,
			UsuarioEmpresa usuarioCreador, FormularioNuevo formActividad) {
		super();
		this.caracteristica = caracteristica;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.metodoMuestreo = metodoMuestreo;
		this.tipoMuestreo = tipoMuestreo;
		this.usuarioCreador = usuarioCreador;
		this.formActividad = formActividad;
	}


	public long getIdActividad() {
		return idActividad;
	}
	

	public void setIdActividad(long idActividad) {
		this.idActividad = idActividad;
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

	
	
	
	
	
}