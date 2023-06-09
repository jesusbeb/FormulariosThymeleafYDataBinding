package com.bolsadeideas.springboot.form.app.models.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.bolsadeideas.springboot.form.app.validation.IdentificadorRegex;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
	//Convertiremos los datos del campo nombre a mayusculas mediante propertyEditors. Creamos un nuevo package (.editors) y dentro una clase NombreMayusculaEditor
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
	
	@NotEmpty //Valida solo objetos String al igual que NotBlank
	@Email(message = "correo con formato incorrecto :(") //@Email valida que sea un formato de correo, con message podemos mandar un mensaje de error personalizado
	private String email;
	
	@NotNull //Nos sirve para validar objetos diferentes de String. Recomendable usar objetos en lugar de primitivos
	@Min(5) //Min y max nos serviria para tipos primitivos
	@Max(5000)
	private Integer cuenta;
	
	//importar Date de java.util
	@NotNull
	//En lugar de formatear fechas con anotacion DateTimeFormat, implementamos en el initBinder un custom editor, o sea una clase que permite obtener el string
	//de la fecha que se envia en el formulario y cambiarle el formato. Esto en el FormController
	//@DateTimeFormat(pattern = "yyyy-MM-dd") //mantiene este formato de fecha internamente al dar clic en enviar. Tambien valida que no venga vacia la fecha
	@Past //Valida que sea una fecha pasada @Future debe ser fecha futura
	private Date fechaNacimiento;
	
	/*//Se comenta porque ahora se creara con una clase llamada Pais
	@NotEmpty
	private String pais;*/
	
	//@Valid valida los objetos relacionados
	@NotNull //valida completamente el objeto
	private Pais pais;
	
	/*
	//Atributo para los roles (checkbox). Lista que contiene String
	@NotEmpty //Asi se valida una la lista
	private List<String> roles;
	*/
	
	//Ahora se crea una Lista con objetos de tipo Role
	@NotEmpty
	private List<Role> roles;
	
	//atributo booleano no se valida, ya que es opcional, se habilita o no se habilita
	private Boolean habilitar;
	
	@NotEmpty
	private String genero;
	
	//atributo oculto que no se muestra en el formulario
	private String valorSecreto;
	
	
	
	
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

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getHabilitar() {
		return habilitar;
	}

	public void setHabilitar(Boolean habilitar) {
		this.habilitar = habilitar;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getValorSecreto() {
		return valorSecreto;
	}

	public void setValorSecreto(String valorSecreto) {
		this.valorSecreto = valorSecreto;
	}

	
	
	
}
