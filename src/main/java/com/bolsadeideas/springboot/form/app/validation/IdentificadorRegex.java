/*
 * Se creo como Annotation Type, en la ventana de creacion se selecciono: Add @Retention, Runtime
 * Add @Target, Field y Method
 */

package com.bolsadeideas.springboot.form.app.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//Constraint enlaza la interface IdentificadorRegex con la clase IdentificadorRegexValidador
@Constraint(validatedBy = IdentificadorRegexValidador.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface IdentificadorRegex {
	/*
	 * Estos metodos se agregaron copiandolos de cuaquier anotacion de validaciones
	 * P.e. clase Usuario.java se presiono Ctrl + clic sobre la anotacion NotEmpty y se copiaron los metodos 
	 */
	
	String message() default "Identificador inválido"; //mensaje por defecto

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
