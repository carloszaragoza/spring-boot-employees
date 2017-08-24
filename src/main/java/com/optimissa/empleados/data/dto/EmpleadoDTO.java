package com.optimissa.empleados.data.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class EmpleadoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String apellidoPaterno;
	
	@NotNull
	private String apellidoMaterno;
	
	@NotNull
	private String rfc;
	
	@NotNull
	private String curp;
	
	public EmpleadoDTO() {
		
	}
		
	public EmpleadoDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, String curp) {
		super();
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.rfc = rfc;
		this.curp = curp;
	}



	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	
}
