package com.bolsadeideas.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	//Creamos la lista
	private List<Role> roles;
	
	//inicializamos la lista en el constructor
	public RoleServiceImpl() {
		this.roles = new ArrayList<>();
		this.roles.add(new Role (1, "Administrador", "ROLE_ADMIN"));
		this.roles.add(new Role (2, "Usuario", "ROLE_USER"));
		this.roles.add(new Role (3, "Moderador", "ROLE_MODERATOR"));
	}

	
	@Override
	public List<Role> listar(){
		return roles;
	}
	
	//Busca en la lista y retorna el objeto buscado
	@Override
	public Role obtenerPorId(Integer id) {
		Role resultado = null;
		for(Role role: roles) {
			if(id == role.getId()) {
				resultado = role;
				break;
			}
		}
		return resultado;
	}
}
