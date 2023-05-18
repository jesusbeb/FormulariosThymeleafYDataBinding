/* Esta clase la creamos dentro de otro package para llevar un orden. Al crear la clase, en la ventana
 * New Java Class en la seccion Interfaces, seleccionamos Add..., tecleamos Validator y escogemos el 
 * que es de org.springframework.validation y damos clic en Ok y finalizamos
 */

package com.bolsadeideas.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;

@Component //Componente Spring para inyectar despues
public class UsuarioValidador implements Validator {

	//Metodo para soporte para indicar que clase o pojo vamos a validar
	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz); //soporte a la clase usuario......
	}

	//Metodo para validar
	@Override
	//en argumentos recibimos el objeto target
	public void validate(Object target, Errors errors) {
		//Hacemos un cast de target a tipo Usuario
		//Usuario usuario = (Usuario)target; //igual se comenta porque ya no se ocupo al usar la anotacion personalizada Identificador Regex
		
		//usamos la clase helper de utilidad de Spring: ValidationUtils y su metodo rejectIfEmptyOrWhitespace que rechaza el dato introducido si esta vacio o son espacios en blanco
		//el primer objeto es errors, que se paso por argumento, nombre del campo y/o atributo del objeto usuario
		//el tercero es un string con el mensaje error que esta registrado en el messages.properties para el campo nombre 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "requerido.usuario.nombre");
		
		//Validacion para el identificador. ! significa que sea False la condicion del if
		//usamos el metodo matches que por argumento recibe una "expresion regular"
		
		/* comentamos porque se validara con anotacion personalizada (IdentificadorRegex)
		if( ! usuario.getIdentificador().matches("[0-9]{3}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}") ) {
			//usamos error, el metodo rejectValue, pasamos como argumento el nombre del campo y el tercer argumento
			//es el mensaje de error registrado en messages.properties para el campo "identificador"
			errors.rejectValue("identificador", "pattern.usuario.identificador");
		}
		*/
	}

}
