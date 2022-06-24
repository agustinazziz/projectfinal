package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import com.capa2LogicaNegocio.CasillasBeanRemote;
import com.capa2LogicaNegocio.FormulariosBean;
import com.entities.CasillaNueva;
import com.entities.FormularioNuevo;
import com.exception.PersistenciaException;

@Named(value="gestionFormularios")		//JEE8
@SessionScoped				        //JEE8
public class GestionFormulariosBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	FormulariosBean formulariosBean;
	@EJB
	CasillasBeanRemote casillasBean;

	List <FormularioNuevo> formsEncontrados;
	
	String idForm;
	String nombreForm;
	String resumenForm;
	List<CasillaNueva> casillaNueva = new ArrayList<CasillaNueva>();
	
	List<String> casillas;
	
	
	public String seleccionarFormularios () throws PersistenciaException {
		formsEncontrados = formulariosBean.listarFormularios();
		
		
		return " ";
	}
	
	public void agregarCasillaNueva(CasillaNueva casillaAgr) {
		
		this.casillaNueva.add(casillaAgr);
		
	}
	
	
	public String salvarCambios() throws PersistenceException {
		
			
		try {
			
			FormularioNuevo nuevoForm= formulariosBean.insertarFormularioNuevo(nombreForm, resumenForm, casillaNueva );
			
			for (CasillaNueva casillaModif : casillaNueva) {
				casillaModif.setFormNuevo(nuevoForm);
				casillasBean.ModificarCasilla(casillaModif.getIdCasilla()
											, casillaModif.getNombre() 
											, casillaModif.getDescripcion()
											, casillaModif.getParametro()
											, casillaModif.getUnidadesMedida()
											, casillaModif.getTiposDato()
											, casillaModif.getFormNuevo());
				
			}
			this.setContador(0);
			this.casillaNueva.clear();
			this.nombreForm="";
			this.resumenForm="";
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
        addMessage("Acción realizada correctamente.", "Su formulario ha sido creado");
		
		return "";
	}
	
	public void modifForm() throws Exception {
		try {
			FormularioNuevo formModif = formulariosBean.buscarFormulario(Long.parseLong(idForm));
			formModif.setNombre(nombreForm);
			formModif.setResumen(resumenForm);
			formulariosBean.EditarFormulario(formModif);
			
			System.out.println("Llega bien hasta acá");
		}catch(PersistenciaException e) {
			e.printStackTrace();
		}
		
	}
	
	public void borrarForm() throws Exception {
		
		try {
			formulariosBean.eliminarFormulario(Long.parseLong(idForm));
			addMessage("Formulario borrado correctamente", "Formulario Borrado");
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		
	}
	
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	

	public List<FormularioNuevo> getFormsEncontrados() {
		return formsEncontrados;
	}


	public void setFormsEncontrados(List<FormularioNuevo> formsEncontrados) {
		this.formsEncontrados = formsEncontrados;
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


	public String getIdForm() {
		return idForm;
	}

	public void setIdForm(String idForm) {
		this.idForm = idForm;
	}

	public void setResumenForm(String resumenForm) {
		this.resumenForm = resumenForm;
	}
	
	public List<CasillaNueva> getCasillaNueva() {
		return casillaNueva;
	}

	public void setCasillaNueva(List<CasillaNueva> casillaNueva) {
		this.casillaNueva = casillaNueva;
	}
	
	
	//Test de iteración primefaces
	
	


	private Integer contador = 0 ;

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}
	
	public void addContador() {
		contador = contador + 1;
		CasillaNueva nuevaCasilla = new CasillaNueva();
		casillaNueva.add(nuevaCasilla);
	}
	
	public void delContador() {
		if (contador >=1) {
			contador=contador -1;
			casillaNueva.remove(contador+1-1);

		}
	}

	
	
	public List<String> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<String> casillas) {
		this.casillas = casillas;
	}

	
	
	
	// Test de iteración primefaces
	
	
}