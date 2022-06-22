package com.restapi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DAOCasilla;
import com.dao.UsuariosEmpresaDAO;
import com.entities.CasillaNueva;
import com.entities.UsuarioEmpresa;

/**
 * Servlet implementation class CargarDatos
 */
@WebServlet("/CargarDatos")
public class CargarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	DAOCasilla casillasDAO;
	@EJB
	UsuariosEmpresaDAO usuariosEmpresaDAO;

	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath()+"\n");
		PrintWriter out = response.getWriter();
		
		try {
			UsuarioEmpresa e= new UsuarioEmpresa("Diego", "Silvera","52315623","diego.silvera","123456","UTEC","Estudiante","diego.silvera@estudiantes.utec.edu.uy","Común","true");
			
			UsuarioEmpresa usuarioCreado = usuariosEmpresaDAO.agregarUsuario(e);
			out.println("Se creo el usuario:"+ usuarioCreado.getId()+" Nombre: "+usuarioCreado.getNombre());
			
			e=new UsuarioEmpresa("Santiago", "Medina","22568548","santiago.medina","123456","UTEC","Técnico Informático","santiago.medina@utec.edu.uy","Experto","true");

			usuarioCreado = usuariosEmpresaDAO.agregarUsuario(e);
			out.println("Se creo el usuario:"+ usuarioCreado.getId()+" Nombre: "+usuarioCreado.getNombre());
			
			e=new UsuarioEmpresa("Agustin", "Azziz","47046538","agustin.azziz","123","UTEC","Técnico Informático","agustin.azziz@utec.edu.uy","Administrador","true");

			usuarioCreado = usuariosEmpresaDAO.agregarUsuario(e);
			out.println("Se creo el usuario:"+ usuarioCreado.getId()+" Nombre: "+usuarioCreado.getNombre());
			
			 try {
			CasillaNueva c = new CasillaNueva("Nombre","Descripción", "TEST", "TEST", "TEST");	
			casillasDAO.altaCasillaClase(c);
			out.println("Se creo la casilla:"+ c.getNombre()+" Descripción: "+c.getDescripcion());	
			 }
			 catch(Exception ex) {
					out.println("No se creo la casilla:"+ ex.getClass().getSimpleName()+"----"+ex.getMessage() + "________"+ex.getCause());

			 }
			
		}catch(Exception e) {
			out.println("No se creo el Usuario:"+ e.getClass().getSimpleName()+"-"+e.getMessage());
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
