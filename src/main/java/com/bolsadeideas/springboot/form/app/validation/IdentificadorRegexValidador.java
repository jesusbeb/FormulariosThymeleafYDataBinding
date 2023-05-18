package com.bolsadeideas.springboot.form.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//Implementamos una interface con implements ConstraintValidator<A que anotacion se va aplicar, tipo de dato del campo a validar>
public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String>{

	//Implementamos metodo de la interface
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//value es el atributo que se va a validar, corresponde al identificador
		if( value.matches("[0-9]{3}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}") ) {
			return true; //retorna true si se valida bien
		}
		return false; //false si se valida mal
	}
}
