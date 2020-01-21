package net.strevens.app.service;

import java.util.List;

import net.strevens.app.model.Pelicula;

public interface IPeliculasService {
	
	void insertar (Pelicula pelicula);
	
	//Declaración - Metodo para obtener Lista de tipo pelicula
	List<Pelicula> buscarTodas();
	Pelicula buscarPorId(int idPelicula);
	List<String> buscarGeneros();

}
