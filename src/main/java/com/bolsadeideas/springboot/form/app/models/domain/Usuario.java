package com.bolsadeideas.springboot.form.app.models.domain;

import jakarta.validation.constraints.NotEmpty;

//Clase usuario con atributos igual a los del formulario

public class Usuario {
	
	//indicamos que regla de validacion vamos a usar en cada campo
	//@NotEmpty valida que sean datos obligatorios
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private String email;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

}
