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
		//.addPathPatterns signfica agregar patron de rutas, en este caso se agrega a lo que venga despues de form/
		//por lo tanto ahora no se mostrara el tiempo transcurrido en /ver que contiene a resultado.html
		registry.addInterceptor(tiempoTranscurridoInterceptor).addPathPatterns("/form/**");
	}
	
}
