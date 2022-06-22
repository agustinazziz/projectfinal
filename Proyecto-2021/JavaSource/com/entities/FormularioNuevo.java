package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: FormularioNuevo
 *
 */
@Entity
public class FormularioNuevo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="FORMULARIOSNUEVOS_IDFORMULARIONUEVO_GENERATOR", sequenceName="SEQ_ID_FORMULARIONUEVO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FORMULARIOSNUEVOS_IDFORMULARIONUEVO_GENERATOR")
	@Column(name="ID_FORMULARIONUEVO", unique=true, nullable=false, precision=38)
	private Long id;
	
	@Column(length = 40)
	private String nombre;
	
	@Column(length = 40)
	private String resumen;
	
	@Column(length=1)
	private Long status;
	
    @OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL, mappedBy="formNuevo")
    private List<CasillaNueva> casillaNueva;
    
    @OneToMany( cascade= CascadeType.ALL, mappedBy="formActividad")
    private Set<Actividad> actividadNueva;
    
	
	public FormularioNuevo() {
		super();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	public List<CasillaNueva> getCasillaNueva(){
		return casillaNueva;
	}
	
	public void setCasillaNueva(List<CasillaNueva> casillaNueva) {
		
		this.casillaNueva = casillaNueva;
	}

	
	
	public Set<Actividad> getActividadNueva() {
		return actividadNueva;
	}


	public void setActividadNueva(Set<Actividad> actividadNueva) {
		this.actividadNueva = actividadNueva;
	}


	@Override
	public String toString() {
		String returnNew = "Formulario id=" + id + ", nombre=" + nombre + ", resumen=" + resumen + ", status=" + status ;
		for (CasillaNueva casilla : casillaNueva) {
		returnNew.concat(", "+ casilla.getNombre() + ": " + casilla.getDescripcion());
		}
		
		return returnNew;
	}
   
	
	
	
	
	
}
