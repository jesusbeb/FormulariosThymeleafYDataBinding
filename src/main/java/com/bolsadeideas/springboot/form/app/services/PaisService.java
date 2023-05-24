/*
 * Clase service que opera con los datos
 */

package com.bolsadeideas.springboot.form.app.services;

import java.util.List;

import com.bolsadeideas.springboot.form.app.models.domain.Pais;

public interface PaisService {
	
	//Metodo para listar paises
	public List<Pais> listar();
	
	//Metodo que retorna un pais y recibe el Integer id
	public Pais obtenerPorId(Integer id);

}
