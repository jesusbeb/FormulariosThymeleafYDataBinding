/*
 * Se crea esa interface personalizada con los metodos que heredara, los cuales seran implementados
 * por las clases hijo
 */

package com.bolsadeideas.springboot.form.app.services;

import java.util.List;

import com.bolsadeideas.springboot.form.app.models.domain.Role;

public interface RoleService {

	//Metodos o contrato
	public List<Role> listar();
	public Role obtenerPorId(Integer id);
	
}
