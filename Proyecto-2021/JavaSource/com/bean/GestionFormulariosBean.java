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

import com.capa2LogicaNegocio.CasillasBean;
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
	CasillasBean casillasBean;

	List <FormularioNuevo> formsEncontrados;
	
	String idForm;
	String nombreForm;
	String resumenForm;
	
	String idCasDel;
	
	String contadorString;
	Integer contadorControlador;
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
	
	public String prepModif() {
		for (Integer i = 0; i < 10; i++) {
			CasillaNueva casillaAux = new CasillaNueva();
			casillaNueva.add(casillaAux);
		}
		return"true";
	}
	
	public String modifForm() throws Exception {
		try {
			FormularioNuevo formModif = formulariosBean.buscarFormulario(Long.parseLong(idForm));
			formModif.setNombre(nombreForm);
			formModif.setResumen(resumenForm);
			formulariosBean.EditarFormulario(formModif);
			
			System.out.println(casillaNueva.get(0).getIdCasilla());
			System.out.println(casillaNueva.get(0).getNombre());
			System.out.println(casillaNueva.get(0).getDescripcion());
			System.out.println();
			
			System.out.println("Llega bien hasta acá");
			
			return "/pages/formularios.xhtml";
		}catch(PersistenciaException e) {
			e.printStackTrace();
		}
		return"";
	}
	
	public String prerpModifForm() {
		
		contador = Integer.parseInt(contadorString);
		contadorControlador = contador;
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
		return "/pages/modifForm.xhtml";
	
	}
	
	public String prepCrearForm() {
		
		this.setContador(0);
		this.casillaNueva.clear();
		this.nombreForm="";
		this.resumenForm="";
		this.idForm=null;
		this.contadorString="0";
		return"/pages/NewFormulario.xhtml";
	}
	
	public void borrarForm() throws Exception {
		
		try {
			formulariosBean.eliminarFormulario(Long.parseLong(idForm));
			addMessage("Formulario borrado correctamente", "Formulario Borrado");
			idForm=null;
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}

		
	}
	
	public void borrarCas() throws Exception {
		try {
			
			casillasBean.eliminarCasilla(Long.parseLong(idCasDel));
			addMessage("Casilla borrada correctamente", "Casilla Borrasa");
		}	catch(Exception e) {
			System.out.println("No se puede borrar casilla.");
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

	

	public Integer getContadorControlador() {
		return contadorControlador;
	}

	public void setContadorControlador(Integer contadorControlador) {
		this.contadorControlador = contadorControlador;
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

	
	
	public String getIdCasDel() {
		return idCasDel;
	}

	public void setIdCasDel(String idCasDel) {
		this.idCasDel = idCasDel;
	}

	public String getContadorString() {
		return contadorString;
	}

	public void setContadorString(String contadorString) {
		this.contadorString = contadorString;
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
		if (contador > 0) {
			contador=contador -1;
			casillaNueva.remove(contador+1-1);

		}
	}
	
	public void delContadorModif() {
		if (contador > contadorControlador) {
			contador = contador - 1;
			casillaNueva.remove(contador + 1 - 1);
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