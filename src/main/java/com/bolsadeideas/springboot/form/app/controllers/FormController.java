package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		//pasamos los valores a la vista para mostrarlos
		model.addAttribute("titulo", "Resultado form");
		model.addAttribute("username", username); //el primer username es el nombre del atributo con el que se pasa a la vista, el segundo username es el valor
		model.addAttribute("password", password);
		model.addAttribute("email", email);
		
		return "resultado"; //retorna a la vista o html "resultado"
	}
}
