package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;




//anotamos con controller
@Controller
public class FormController {

	
	//Metodo handler. Muestra el formulario en pantalla para el usuario, de tipo GetMapping
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("titulo", "Formulario usuarios");
		return "form"; //retorna a la vista "form"
	}
	
	
	
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
		
		//pasamos el titulo que mostrara el resultado.html
		model.addAttribute("titulo", "Resultado form");
		//pasamos el objeto de tipo usuario a la vista
		model.addAttribute("usuario", usuario); //el primer username es el nombre del atributo con el que se pasa a la vista, el segundo username es el valor 
		
		return "resultado"; //retorna a la vista o html "resultado"
	}
	
}
