package com.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("")
public interface IUsuariosRest {
	
    @GET
    @Path("echo")
    @Produces({MediaType.TEXT_PLAIN})
    public String echo();
      
    @GET
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios();
    
    @POST 
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public String login(Credenciales cred); 
}