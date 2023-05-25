package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.form.app.services.RoleService;

//Hacemos componente de Spring para inyectar en el controlador
@Component
public class RolesEditor extends PropertyEditorSupport{
	
	//inyectamos la interface
	@Autowired
	private RoleService service;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			//comvertimos el text a integer
			Integer id = Integer.parseInt(text);
			setValue(service.obtenerPorId(id));
		} catch(NumberFormatException e) {
			setValue(null);
		}
	}

}
