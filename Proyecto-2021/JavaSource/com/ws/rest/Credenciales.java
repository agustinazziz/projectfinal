package com.ws.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Credenciales {
	@XmlElement String	usuario;
	@XmlElement String contrasenia;
}
