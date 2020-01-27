package net.strevens.app.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.strevens.app.model.Pelicula;
import net.strevens.app.repository.PeliculasRepository;

@Service
public class PeliculasServiceJPA implements IPeliculasService{
	
	@Autowired
	private PeliculasRepository peliculasRepo;

	@Override
	public void insertar(Pelicula pelicula) {
		//Guardar
		peliculasRepo.save(pelicula);
		
	}

	@Override
	public List<Pelicula> buscarTodas() {
		//Buscar peliculas
		return peliculasRepo.findAll();
	}

	@Override
	public Pelicula buscarPorId(int idPelicula) {
		// retorna tipo optional
		Optional<Pelicula> optional = peliculasRepo.findById(idPelicula);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<String> buscarGeneros() {
		//Nota: Esta lista puede ser obtenida de una BD
		List<String> generos = new LinkedList<>();
		generos.add("Accion");
		generos.add("Aventura");
		generos.add("Clasicas");
		generos.add("Comedia Romantica");
		generos.add("Drama");
		generos.add("Terror");
		generos.add("Infantil");
		generos.add("Accion y Aventura");
		generos.add("Romatica");
		generos.add("Ciencia Ficcion");
		
		return generos;
	}

	@Override
	public void eliminar(int idPelicula) {
		peliculasRepo.deleteById(idPelicula);
		
	}

	@Override
	public Page<Pelicula> buscarTodas(Pageable page) {
		
		return peliculasRepo.findAll(page);
	}

}
