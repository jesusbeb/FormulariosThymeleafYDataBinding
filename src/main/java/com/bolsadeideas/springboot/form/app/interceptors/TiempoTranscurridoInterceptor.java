package com.bolsadeideas.springboot.form.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.util.Random;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Implementamos la interface HandlerInterceptor
 * Sobreescribimos dos de sus metodos
 * Se calculara el tiempo transcurrido en el request
 */

//damos nombre para cuando se inyecte por si existe otra componente
@Component("TiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor{
	
	//atributo para registrar evento en el log, cuando entramos, salimos, etc...
	//private static final es una constante
	//pasamos el nombre de la clase
	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	//preHandle, antes
	//Aqui obtenemos el tiempo de inicio
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//validamos si la instancia es de un metodo del controlador
		if(handler instanceof HandlerMethod) {
			//cast
			HandlerMethod metodo = (HandlerMethod) handler;
			//se muestra en la consola
			logger.info("es un metodo del controlador: " + metodo.getMethod().getName());
		}
		
		//con el logger mostramos informacion del interceptor
		logger.info("TiempoTranscurridoInterceptor: preHandle() entrando ...");
		//asi sabemos que recursos se estan invocando (controlador, metodo handler, hoja de estilo,...)
		//solo se muestra en la consola
		logger.info("Interceptando" +handler);
		//calculamos el tiempo actual en milisegundos
		long tiempoInicio = System.currentTimeMillis();
		//asignamos el tiempoInicio en el request. ("le damos nombre", pasamos la variable)
		request.setAttribute("tiempoInicio", tiempoInicio);
		
		//simulamos una demora aleatoria
		Random random = new Random();
		Integer demora = random.nextInt(500); //sera entre cero y quinientos milisegundos
		//Thread.sleep hace una pausa, que vale lo que contiene la variable demora
		Thread.sleep(demora);
		
		return true;
	}

	//postHandle, despues
	//Aqui obtenemos el tiempo final
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//obtenemos el tiempo final
		long tiempoFin = System.currentTimeMillis();
		//obtenemos el tiempo de inicio que esta asignado al request y hacemos un parseo porque esta almacenado como objeto generico
		//lo convertimos a objeto Long y la variable en que se almacena es primitiva de tipo long
		long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		long tiempoTranscurrido = tiempoFin - tiempoInicio;
		
		//Pasamos el tiempo transcurrido a la vista usando modelAndView, pero antes validamos
		//que modelAndView sea una instancia de HandlerMethod y sea distinto de null
		if(handler instanceof HandlerMethod && modelAndView != null) {
			//agregamos el tiempo transcurrido a la vista ("nombreDelAtributo", variableAPasar)
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
		}
		//mostramos informacion
		logger.info("Tiempo Transcurrido: " + tiempoTranscurrido + " milisegundos");
		logger.info("TiempoTranscurridoInterceptor: postHandle() saliendo ...");
		
	}

}
