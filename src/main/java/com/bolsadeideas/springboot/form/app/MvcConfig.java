package com.bolsadeideas.springboot.form.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Clase configuracion para registrar el interceptor

@Configuration //anotamos para registrar en springboot
public class MvcConfig implements WebMvcConfigurer {
	
	//inyectamos
	@Autowired
	@Qualifier("TiempoTranscurridoInterceptor") //inyectamos con el nombre usando quialifier
	private HandlerInterceptor tiempoTranscurridoInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//usamos registry.addInterceptor y le pasamos el objeto registry que recibimos por argumento
		registry.addInterceptor(tiempoTranscurridoInterceptor);
	}
	
}
