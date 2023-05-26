package com.bolsadeideas.springboot.form.app.models.domain;

public class Role {

	private Integer id;
	private String nombre;
	private String role;

	public Role() {
	}

	public Role(Integer id, String nombre, String role) {
		this.id = id;
		this.nombre = nombre;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object obj) {
		
		//Si esta misma instancia del objeto  es igual a la instancia del objeto se retorna true o sea activa el checkbox
		if(this == obj ) {
			return true;
		}
		
		//validamos si el tipo de obj sea instancia de Role y si no lo es se retorna false
		if(!(obj instanceof Role)) {
			return false;
		}			
		
		//convertimos el obj que es un objeto generico a un objeto tipo Role
		Role role = (Role) obj;
		//preguntamos si el id es distinto a nulo y si existe comparamos this.id con el id del role
		return this.id != null && this.id.equals(role.getId());
	}
	
	

}
