//Clase personalizada

package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

public class NombreMayusculaEditor extends PropertyEditorSupport{

	//Sobreescribimos metodo, Clic derecho, Source, Override, seleccionamos setAsText(String)
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		//usamos el metodo setValue(recibeTexto.ConvierteaMayuscula() trim() quita espacios en blanco
		//ahora registramos el property editors en FormController
		setValue(text.toUpperCase().trim());
	}

}
