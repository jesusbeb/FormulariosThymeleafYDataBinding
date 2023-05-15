package com.bolsadeideas.springboot.form.app.models.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//Clase usuario con atributos igual a los del formulario

public class Usuario {
	
	//Este dato no se mostrara en el formulario, es para manejarlo internamente
	private String identificador;
	
	@NotEmpty(message = "el nombre no puede quedarse vacio :(")
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	//indicamos que regla de validacion vamos a usar en cada campo
	//@NotEmpty valida que sean datos obligatorios
	@NotEmpty
	@Size(min=3, max=8) //tamaño del username, solo para string
	private String username;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	@Email(message = "correo con formato incorrecto :(") //@Email valida que sea un formato de correo, con message podemos mandar un mensaje de error personalizado
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	
	
}
