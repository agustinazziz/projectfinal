package com.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.CasillaNueva;
import com.entities.FormularioNuevo;
import com.exception.PersistenciaException;


@Stateless
@LocalBean

public class DAOCasilla {

	
	@PersistenceContext
	private EntityManager em;
	
	public DAOCasilla() {
		super();
	}

	public List<CasillaNueva> listarCasillas() {
		TypedQuery<CasillaNueva> query = em.createQuery("SELECT c FROM CasillaNueva c order by c.idCasilla", CasillaNueva.class);
		List<CasillaNueva> resultados = query.getResultList();
		return resultados;
	}
	
	public void altaCasilla(String nom, String desc, String param, String tipoDato, String unuMedida, EntityManager em)
			throws Exception {

		try {

			CasillaNueva cas = new CasillaNueva();
			cas.setNombre(nom);
			cas.setDescripcion(desc);
			cas.setParametro(param);
			cas.setTiposDato(tipoDato);
			cas.setUnidadesMedida(unuMedida);
			em.persist(cas);

		} catch (Exception e) {
			throw new Exception("No se puede crear la Casilla!");
		}

	}
	

	public static void modificarCasilla(Long idCas, String nom, String des, String par,String uni, String tipDato,FormularioNuevo formNuevo,EntityManager em) {
		
		CasillaNueva casBuscada= buscarCasilla(idCas, em);
		
		casBuscada.setNombre(nom);
		casBuscada.setDescripcion(des);
		casBuscada.setParametro(par);
		casBuscada.setUnidadesMedida(uni);
		casBuscada.setTiposDato(tipDato);
		casBuscada.setFormNuevo(formNuevo);
		em.merge(casBuscada);
		em.flush();
		
	}

	public static CasillaNueva buscarCasilla(Long idCasi, EntityManager em) {
		
		TypedQuery<CasillaNueva> query= em.createQuery("SELECT f FROM CasillaNueva f WHERE f.idCasillaNueva=:idcas ", CasillaNueva.class).setParameter("idcas", idCasi);
		CasillaNueva casBuscada= query.getSingleResult();
		return casBuscada;
		
	}


public void altaCasillaClase(CasillaNueva casillaNueva) throws PersistenciaException {

	try {
	CasillaNueva cas = new CasillaNueva();
	//FormularioNuevo fn =new FormularioNuevo();
	cas.setNombre(casillaNueva.getNombre());
	cas.setDescripcion(casillaNueva.getDescripcion());
	cas.setParametro(casillaNueva.getParametro());
	cas.setTiposDato(casillaNueva.getTiposDato());
	cas.setUnidadesMedida(casillaNueva.getUnidadesMedida());
//	cas.setFormNuevo(fn);
	em.persist(cas);
	}
	catch (PersistenceException e) {
		throw new PersistenciaException("No se pudo agregar la casilla." + e.getMessage(), e);
	}

}
}
