package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//anotamos con controller
@Controller
public class FormController {

	//Metodo handler. Muestra el formulario en pantalla para el usuario, de tipo GetMapping
	@GetMapping("/form")
	public String form(Model model) {
		return "form"; //retorna a la vista "form"
	}
	
	//Metodo handler. Para procesar los datos del usuario cuando completa el formulario y envia en 
	//una peticion http post, usamos PostMapping
	@PostMapping("/form")
	public String procesar(Model model) {
		return "resultado"; //retorna a la vista o html "resultado"
	}
}
