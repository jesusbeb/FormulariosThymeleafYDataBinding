package com.bolsadeideas.springboot.form.app.models.domain;

import com.bolsadeideas.springboot.form.app.validation.IdentificadorRegex;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//Clase usuario con atributos igual a los del formulario

public class Usuario {
	
	//Este dato no se mostrara en el formulario, es para manejarlo internamente
	//Se muestrara ahora en el formulario para hacer una validacion con "expresion regular"
	//con Pattern usamos el atributo regexp de expresion regular, para que el identificador cumpla las validaciones
	//[0-9]{2} significa dos caracteres en un rango de 0 a 9, luego un punto [.], 
	//luego [\\d] tambien representa cualquier digito de 0 a 9 y que sean tres{3}, luego un guion [-]
	//[A-Z]{1} representa un caracter de la A a la Z mayusculas
	//@Pattern(regexp="[0-9]{3}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}") // Comentamos porque lo validaremos con la clase UsuarioValidador
	//IdentificadorRegex es una validacion personalizada usando anotacion. Se crea una anotacion (IdentificadorRegex) en el paquete validation y tambien una clase validadora (IdentificadorRegexValidador) como clase normal. (No recomendado, muy revuelto)
	@IdentificadorRegex
	private String identificador;
	
	//estas validaciones quedan sombreadas por las
	//validaciones en el archivo messages.properties
	//@NotEmpty(message = "el nombre no puede quedarse vacio :(") //comentamos porque lo validaremos con la clase UsuarioValidador
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	//indicamos que regla de validacion vamos a usar en cada campo
	//@NotBlanck valida que no este vacio ni tenga espacios en blanco
	@NotBlank
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
