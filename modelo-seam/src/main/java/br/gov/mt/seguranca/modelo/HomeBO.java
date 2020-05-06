package br.gov.mt.seguranca.modelo;

import java.io.Serializable;

import org.jboss.seam.annotations.Name;

@Name("homeBO")
public class HomeBO implements Serializable {

	private static final long serialVersionUID = 4094757212004486328L;

	public String ola() {
		return "ola";
	}

}
