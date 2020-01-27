package net.strevens.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.strevens.app.model.Pelicula;

//@Service
public class PeliculasServiceImpl implements IPeliculasService{
	
	private List<Pelicula> lista = null;
	
	public PeliculasServiceImpl() {
		System.out.println("Creando una instancia de la clase PeliculasServiceImpl");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		
		try {
			// LinkedList: Lista enlazada
			lista = new LinkedList<>();
			
			Pelicula pelicula1 = new Pelicula();
			pelicula1.setId(1);
			pelicula1.setTitulo("Joker");
			pelicula1.setDuracion(120);
			pelicula1.setClasificacion("C");
			pelicula1.setGenero("Drama/Suspenso");
			pelicula1.setFechaEstreno(formatter.parse("02-10-2019"));
			pelicula1.setImagen("joker.jpg");
			
			Pelicula pelicula2 = new Pelicula();
			pelicula2.setId(2);
			pelicula2.setTitulo("Jumanji: siguiente nivel");
			pelicula2.setDuracion(123);
			pelicula2.setClasificacion("B");
			pelicula2.setGenero("Fantasía/Acción");
			pelicula2.setFechaEstreno(formatter.parse("04-12-2019"));
			pelicula2.setImagen("jumanjisiguientenivel.jpg");//Nombre del archivo de imagen
			
			
			Pelicula pelicula3 = new Pelicula();
			pelicula3.setId(3);
			pelicula3.setTitulo("Ip Man 4");
			pelicula3.setDuracion(105);
			pelicula3.setClasificacion("B");
			pelicula3.setGenero("Drama/Acción");
			pelicula3.setFechaEstreno(formatter.parse("25-12-2019"));
			pelicula3.setImagen("ipman4.jpg");
			
			Pelicula pelicula4 = new Pelicula();
			pelicula4.setId(4);
			pelicula4.setTitulo("Star Wars: The rise of Skywalker");
			pelicula4.setDuracion(142);
			pelicula4.setClasificacion("B");
			pelicula4.setGenero("Fantasia/Ciencia Ficcion");
			pelicula4.setFechaEstreno(formatter.parse("16-12-2019"));
			pelicula4.setImagen("starwars rise skywalker.jpg");
			pelicula4.setEstatus("Inactiva");
			
			Pelicula pelicula5 = new Pelicula();
			pelicula5.setId(5);
			pelicula5.setTitulo("Gundala");
			pelicula5.setDuracion(119);
			pelicula5.setClasificacion("B");
			pelicula5.setGenero("Accion/Drama");
			pelicula5.setFechaEstreno(formatter.parse("29-08-2019"));
			pelicula5.setImagen("gundala.jpg");
			pelicula5.setEstatus("Activa");
			
			//Agregar los objetos Pelicula a la lista
			lista.add(pelicula1);
			lista.add(pelicula2);
			lista.add(pelicula3);
			lista.add(pelicula4);
			lista.add(pelicula5);
			
			
			
		} catch(ParseException e) {
			System.out.println("Error: " + e.getMessage());
			
		}
	}
	
	
	@Override
	public List<Pelicula> buscarTodas() {
		return lista;
	}


	@Override
	public Pelicula buscarPorId(int idPelicula) {
		for(Pelicula p : lista) {
			if (p.getId() == idPelicula) {
				return p;
			}
		}
		return null;
	}


	@Override
	public void insertar(Pelicula pelicula) {
		
		lista.add(pelicula);
		
	}
	
	/*
	 * Metodo que regresa una lista de los Generos de las peliculas
	 */

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
		// TODO Auto-generated method stub
		
	}


	@Override
	public Page<Pelicula> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
