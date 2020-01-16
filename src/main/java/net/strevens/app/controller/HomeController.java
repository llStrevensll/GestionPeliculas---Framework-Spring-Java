package net.strevens.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.strevens.app.model.Pelicula;

@Controller
public class HomeController {
	
	//Home
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	// / -> llevara por defecto a Principal
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model) {
		
		//Lista de peliculas 
		List<Pelicula> peliculas =  getLista();
		
		//Lista de tipo Pelicula -- LinkedList: Lista enlazada
		//List<Pelicula> peliculas1 =  new LinkedList<>();
		/*peliculas.add("Rapido y Furioso");
		peliculas.add("El Aro 2");
		peliculas.add("Aliens");*/
		
		//Adicionar el atributo
		model.addAttribute("peliculas", peliculas);
		
		return "home";
	}
	
	//Detalles
	@RequestMapping(value="/detail")
	public String mostrarDetalle(Model model) {//Recibe objeto de tipo model
		
		//Atributos
		String tituloPelicula = "Rapidos y furiosos";
		int duracion = 136;
		double precioEntrada = 50;
		
		//Adicionar atributos al modelo
		model.addAttribute("titulo", tituloPelicula);
		model.addAttribute("titulo", duracion);
		model.addAttribute("precio", precioEntrada);
		
		return "detalle";
	}
	
	//Metodo para generar un lista de Objetos de Modelo (Pelicula)
	private List<Pelicula> getLista(){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<Pelicula> lista = null;
		
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
			
			//Agregar los objetos Pelicula a la lista
			lista.add(pelicula1);
			lista.add(pelicula2);
			lista.add(pelicula3);
			lista.add(pelicula4);
			
			return lista;
			
		} catch(ParseException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
}
