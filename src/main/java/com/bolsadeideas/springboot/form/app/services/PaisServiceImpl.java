/*
 * Al crearla la clase en en Interfaces, clic en Ad... tecleamos Pais y agregamos PaisService
 */

package com.bolsadeideas.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Pais;


@Service //asi lo registramos en el contenedor de spring y lo podemos inyectar
public class PaisServiceImpl implements PaisService {

	//Atributo: lista de paises
	private List<Pais> lista;
	
	//Inicializamos la lista de paises
	public PaisServiceImpl() {
		this.lista = Arrays.asList(
				new Pais(1, "ES", "España"), 
				new Pais(2, "MX", "Mexico"),  
				new Pais(3, "CL", "Chile"),  
				new Pais(4, "AR", "Argentina"),  
				new Pais(5, "PE", "Peru"),  
				new Pais(6, "CO", "Colombia"),  
				new Pais(7, "VE", "Venezuela"));
	}

	@Override
	public List<Pais> listar() {
		return lista;
	}

	
	//Iteramos la lista para buscar el pais segun el id
	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado = null;
		for(Pais pais: this.lista) {
			if(id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		return resultado;
	}

}
