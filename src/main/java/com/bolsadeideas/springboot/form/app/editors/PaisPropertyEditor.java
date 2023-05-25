package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.form.app.services.PaisService;

//heredamos de PropertyEditorSupport
@Component //lo hacemos componente Spring con Component que se usa para indicar que es un service
public class PaisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private PaisService service;

	//sobreescribimos el metodo
	@Override
	public void setAsText(String idString) throws IllegalArgumentException {
		try {
			Integer id = Integer.parseInt(idString);
			//setValue le enviamos un objeto de tipo Pais y usamos el metodo obtenerPorId y le pasamos el id como entero
			this.setValue(service.obtenerPorId(id));
		} catch (NumberFormatException e) {
			setValue(null);
		}

	}

}
