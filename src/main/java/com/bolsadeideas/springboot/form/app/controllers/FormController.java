package com.bolsadeideas.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.editors.NombreMayusculaEditor;
import com.bolsadeideas.springboot.form.app.editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.form.app.editors.RolesEditor;
import com.bolsadeideas.springboot.form.app.models.domain.Pais;
import com.bolsadeideas.springboot.form.app.models.domain.Role;
import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.services.PaisService;
import com.bolsadeideas.springboot.form.app.services.RoleService;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

import jakarta.validation.Valid;




//anotamos con controller
@Controller
@SessionAttributes("usuario") //(1) le damos el nombre del objeto que se pasa a la vista y se guarda en una sesion http. Todos los datos que contenga independiente si estan o no en el formulario, se mantienen
public class FormController {

	//atributo del tipo UsuarioValidador
	@Autowired //inyectamos
	private UsuarioValidador validador;
	
	//Atributo de la interface PaisService
	@Autowired
	private PaisService paisService;
	
	//inyectamos el PaisPropertyEditor
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	//inyectamos el roleService
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RolesEditor roleEditor;
	
	
	//(2)
	//metodo que llamamos initBinder y recibe el WebDataBinder
	//binder.setValidator solo pone las validaciones que tenemos en la clase validador porque set reemplaza el validador por defecto de las anotaciones
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador); //pasamos el validador para registrarlo. addValidators agrega un nuevo validador sin sustituir el validador por defecto
		
		//Custom Editor
		//una instancia de SimpleDateFormat y agregamos el formato ("yyyy/MM/dd")
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); //define si el analizador anterior que analizara la fecha es estricto o tolerante al interpretar el patron. false es estricto
		//registerCustomEditor(tipoDato.class, nuevoObjetoCustomEditor(dateFormat, permiteVacios?)
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		
		//Registramos CustomEditor para convertir a mayusculas
		// .(tipodeDatoAConvertir, "nombreCampo", new Clase personalizada()
		//binder.registerCustomEditor(String.class, new NombreMayusculaEditor()) si no especificamos el campo en los argumentos, convierte a todos los String
		binder.registerCustomEditor(String.class, "nombre",  new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido",  new NombreMayusculaEditor());
		
		//Registramos el PaisPropertyEditor
		binder.registerCustomEditor(Pais.class, "pais",  paisEditor);
		
		binder.registerCustomEditor(Role.class, "roles",  roleEditor);
	}
	
	//Metodo con una lista de String que contiene los dos generos que se mostraran en el radio buton
	@ModelAttribute("genero")
	public List<String> genero(){
		return Arrays.asList("Hombre", "Mujer");
	}
	
	
	/* Se comenta porque ahora se hara con clase Pais
	//Poblamos con datos la lista select con un ArrayList
	//Metodo que retorna un Array hacia la vista con el nombre "paises"
	@ModelAttribute("paises")
	public List<String> paises(){
		return Arrays.asList("España", "Mexico", "Chile", "Argentina", "Peru", "Colombia", "Venezuela");
	}*/
	
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisService.listar();
	}
	
	//Metodo para llenar los checkbox que vienen del RoleServiceImpl
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles(){
		return this.roleService.listar();
	}
	
	//Metodo para checkbox implementado con ArrayList
	@ModelAttribute("listaRolesString") //en la vista se guarda con este nombre lo que se retorna
	public List<String> listaRolesString(){
		//creamos una lista con las opciones que mostraran los checkbox
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles; //se retorna roles
	}
	
	//Metodo para checkbox implementado con Map
	@ModelAttribute("listaRolesMap")
	//Map<tipoNombre, tipoValor>
	public Map<String, String> listaRolesMap(){
		//Objeto Map llamado roles. Map es la interface y HashMap es la implementacion
		Map<String, String> roles = new HashMap<String, String>();
		//usamos metodo put(key, value) y pasamos un codigo o identificador y el valor
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_MODERATOR", "Moderador");
		return roles;
	}
	
	
	/*//Se comenta porque ahora se usara PaisServiceImpl
	//Metodo que crea lista de tipo Pais 
	@ModelAttribute("listaPaises") 
	public List<Pais> listaPaises(){
		return Arrays.asList(
				//creamos instancia por cada uno, reciben 3 argumentos
				//con esta lista de paises poblaremos el formulario
				new Pais(1, "ES", "España"), 
				new Pais(2, "MX", "Mexico"),  
				new Pais(3, "CL", "Chile"),  
				new Pais(4, "AR", "Argentina"),  
				new Pais(5, "PE", "Peru"),  
				new Pais(6, "CO", "Colombia"),  
				new Pais(7, "VE", "Venezuela")); 
	}
	*/
	
	//paisesMap es como lo pasamos a la vista
	@ModelAttribute("paisesMap")
	//Map<tipoNombre, tipoValor>
	public Map<String, String> paisesMap(){
		//Objeto Map llamado paises. Map es la interface y HashMap es la implementacion
		Map<String, String> paises = new HashMap<String, String>();
		//usamos metodo put(key, value) y pasamos un codigo o identificador y el valor
		paises.put("ES", "España");
		paises.put("MX", "Mexico");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Peru");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");
		return paises;
	}
	
	
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
		//atributo booleano
		usuario.setHabilitar(true); //por defecto queda habilitado
		usuario.setValorSecreto("Algun valor secreto..."); //asignamos un valor al atributo valor secreto
		
		//Pasamos por defecto estos valores de Pais y Role al formulario
		usuario.setPais(new Pais(2, "MX", "México")); //estamos asignando el objeto completo al formulario. Por lo que hay que sobreescribir el metodo toString de la clase Pais
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER"), new Role(3, "Moderador", "ROLE_MODERATOR"))); //implemetamos el metodo equals en la clase Role
		
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
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) { //, SessionStatus status) { El sesion status se quita porque ahora se maneja en el metodo Status
		//Para validar usamos la instancia inyectada y llamamos a su metodo validate
		//Pasamos el objeto target o sea el usuario y errors que seria el BindingResult
		//validador.validate(usuario, result); //lo comentamos para que se valide en automatico con la anotacion @Valid. Pero tenemos que implementar y registrar el validador en el initBinder (2)
		//pasamos el titulo que mostrara el resultado.html
		//model.addAttribute("titulo", "Resultado form"); //se comenta porque se pasa este titulo al metodo ver
		
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
			model.addAttribute("titulo", "Resultado form"); //lo pasamos aqui ya que solo se mostrara cuando ocurra un error para validar
			return "form";  
		}
		
		

		//pasamos el objeto de tipo usuario a la vista
		//el objeto usaurio ahora lo quitamos porque ya no lo pasamos a la vista
		//model.addAttribute("usuario", usuario); //el primer username es el nombre del atributo con el que se pasa a la vista, el segundo username es el valor
		//(1)completa el proceso y se elimina el objeto usuario de la sesion
		//status.setComplete(); //se comenta porque igual se pasa al metodo ver
		//return "resultado"; //retorna a la vista o html "resultado"
		return "redirect:/ver"; //redirije a ver, por lo tanto hace nuevo request
	}
	
	
	/* 
	 * Cuando se actualiza en el resultado despues de haber enviado el formulario, se vuelve a enviar la informacion. Esto esta mal.
	 * Se redirige a este nuevo link "ver" el resultado del formulario, asi cuando se da en actualizar se genera una nueva peticion
	 * Metodo del controlador donde se maneja el resultado
	 */
	@GetMapping("/ver")
	//inyectamos el usuario y lo obtenemos del SessionAttribute. Como el usuario esta en el SessionAttribute, tambien esta en la vista, por lo tanto no es necesario pasarlo con el Model
	public String ver(@SessionAttribute(name="usuario", required = false) Usuario usuario, Model model, SessionStatus status) {
		
		if(usuario == null) {
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Resultado form");
		
		status.setComplete(); 
		return "resultado";
	}
	
}
