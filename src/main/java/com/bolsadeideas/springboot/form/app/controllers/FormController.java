package com.bolsadeideas.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

import jakarta.validation.Valid;




//anotamos con controller
@Controller
@SessionAttributes("usuario") //(1) le damos el nombre del objeto que se pasa a la vista y se guarda en una sesion http. Todos los datos que contenga independiente si estan o no en el formulario, se mantienen
public class FormController {

	//atributo del tipo UsuarioValidador
	@Autowired //inyectamos
	private UsuarioValidador validador;
	
	//Metodo handler. Muestra el formulario en pantalla a el usuario, de tipo GetMapping
	//Aqui se muestra el formulario por primera vez. Si despues cuando se ingresa informacion a los campos y no se envia la informacion
	//y solo se da enter en la barra de direcciones con localhost:8080/form, ocurrira un error, esto porque se esta queriendo acceder a los
	//atributos de un objeto usuario, asi que creamos el objeto usuario y lo pasamos a la vista para evitar ese error
	@GetMapping("/form")
	public String form(Model model) {
		//agregamos un objeto usuario por defecto
		Usuario usuario = new Usuario();
		//agregamos valores a nombre y apellido, ya que se enviaran como datos por defecto
		usuario.setNombre("James");
		usuario.setApellido("Smith");
		//este dato no se muestra en el formulario. Representa a una consulta interna a una BD. Sin embargo se pierde y se muestra nulo en el resultado
		//ya que solo se envia lo que se puebla en el form. Asi que hay que solucionarlo (1)
		usuario.setIdentificador("123.456.789-K");
		model.addAttribute("titulo", "Formulario usuarios");
		//pasamos el objeto usuario a la vista
		model.addAttribute("usuario", usuario);
		return "form"; //retorna a la vista "form"
	}
	
	
/* Mejoraremos este codigo, por eso se comenta	
	//Metodo handler. Para procesar los datos del usuario cuando completa el formulario y envia en 
	//una peticion http post, usamos PostMapping
	@PostMapping("/form")
	//Obtenemos los parametros "name" del form.html con la notacion @RequestParam
	//name="username" indicamos el nombre del metodo con el name o el value, como se llama igual al nombre del argumento, se podria omitir
	public String procesar(Model model, 
			@RequestParam (name="username") String username,
			@RequestParam String password,
			@RequestParam String email) {
		
		//Instanciamos un objeto de la clase usuario
		//Esta es una clase pojo o identity. Se podria inyectar, pero no tiene mucho sentido ya que representa los datos de nuestra app
		//no es una clase de servicio o logica de negocios o de configuracion
		Usuario usuario = new Usuario();
		//asignamos los parametros obtenidos del form.html a las propiedades del objeto instanciado
		usuario.setUsername(username);
		usuario.setEmail(email);
		usuario.setPassword(password);
*/
	
	
	//El objeto usuario de tipo Usuario lo pasamos como primer argumento para que el form mande en automatico
	//sus atributos al objeto
	//Con @Valid validamos la clase usuario cuando se reciben los datos del formulario
	//Pasamos como argumento un objeto de tipo BindingResult (interface) y representa el resultado de la validacion
	//contiene los resultados de la validacion en caso de que haya errores y se inyecta de forma automatica mientras la notacion Valid este
	//y el BindingResult debe estar siempre despues del objeto que valida en este caso el objeto usuario
	@PostMapping("/form")
	//(1) SessionStatus nos sirve para limpiar el dato que enviamos internamente despues de que ya se envio
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		//Para validar usamos la instancia inyectada y llamamos a su metodo validate
		//Pasamos el objeto target o sea el usuario y errors que seria el BindingResult
		validador.validate(usuario, result);
		//pasamos el titulo que mostrara el resultado.html
		model.addAttribute("titulo", "Resultado form");
		
/* Se comenta porque se mejorara el codigo. Se automatizara
		//validamos si hay errores.
		//Pasamos los errores a la vista con Map del tipo String
		if(result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
 			//Usamos result para obtener los mensajes de error y vamos poblando el mapa de la linea anterior por cada campo
			// con el metodo getFieldErrors que es una lista, iteramos con forEach que recibe una expresion lambda que es una funcion flecha y obtenemos los errores de la lista getFieldErrors
			result.getFieldErrors().forEach(err ->{
				//errores.put recibe dos parametros, el primero es una llave del tipo String que se obtiene de err con el metodo getField(). Asi obtenemos el campo o input que dio el error
				//el segundo parametro es un value, el mensaje de error que se mostrara.
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			//Pasamos a la vista lo anterior, o sea mostramos los errores al usuario
			model.addAttribute("error", errores);
			//retornamos a la vista para que vuelva a introducir datos
			return "form";
		}
*/
		if(result.hasErrors()) {
			
			return "form";  
		}
		
		

		//pasamos el objeto de tipo usuario a la vista
		model.addAttribute("usuario", usuario); //el primer username es el nombre del atributo con el que se pasa a la vista, el segundo username es el valor
		//(1)completa el proceso y se elimina el objeto usuario de la sesion
		status.setComplete();
		return "resultado"; //retorna a la vista o html "resultado"
	}
	
}
