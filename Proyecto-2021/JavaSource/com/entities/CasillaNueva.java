package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CASILLAS database table.
 * 
 */
@Entity
@Table(name="CASILLASNUEVAS")
@NamedQuery(name="CasillaNueva.findAll", query="SELECT c FROM CasillaNueva c")
public class CasillaNueva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CASILLAS_IDCASILLANUEVA_GENERATOR", sequenceName="SEQ_ID_CASILLANUEVA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASILLAS_IDCASILLANUEVA_GENERATOR")
	@Column(name="ID_CASILLA", unique=true, nullable=false, precision=38)
	private long idCasillaNueva;

	@Column(nullable=false, length=150)
	private String descripcion;
	
	@Column(nullable=false, length=150)
	private String nombre;

	@Column(nullable=false, length=100)
	private String parametro;

	@Column(nullable=false, length=40)
	private String tiposDato;

	@Column(nullable=false, length=40)
	private String unidadesMedida;
	
	@ManyToOne
	@JoinColumn (name= "FK_FORMULARIONUEVO", nullable= true, updatable= true)
	private FormularioNuevo formNuevo;


	public CasillaNueva() {
	}
	
	

	public CasillaNueva(String nombre, String descripcion, String parametro, String tiposDato,
			String unidadesMedida) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.parametro = parametro;
		this.tiposDato = tiposDato;
		this.unidadesMedida = unidadesMedida;
	}



	public long getIdCasilla() {
		return this.idCasillaNueva;
	}

	public void setIdCasilla(long idCasilla) {
		this.idCasillaNueva = idCasilla;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getParametro() {
		return this.parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getTiposDato() {
		return this.tiposDato;
	}

	public void setTiposDato(String tiposDato) {
		this.tiposDato = tiposDato;
	}

	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	public String getUnidadesMedida() {
		return this.unidadesMedida;
	}

	public void setUnidadesMedida(String unidadesMedida) {
		this.unidadesMedida = unidadesMedida;

	}

	public FormularioNuevo getFormNuevo() {
		return formNuevo;
	}



	public void setFormNuevo(FormularioNuevo formNuevo) {
		this.formNuevo = formNuevo;
	}



	public String toString() {
		return "Casilla idCasilla=" + idCasillaNueva + ", descripcion=" + descripcion + ", parametro=" + parametro
				+ ", tiposDato=" + tiposDato + ", unidadesMedida=" + unidadesMedida;
	}

	
}