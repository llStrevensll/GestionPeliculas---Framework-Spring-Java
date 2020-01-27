package net.strevens.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.strevens.app.model.Horario;
import net.strevens.app.model.Pelicula;
import net.strevens.app.service.IBannersService;
import net.strevens.app.service.IHorariosService;
import net.strevens.app.service.IPeliculasService;
import net.strevens.app.util.Utileria;

@Controller
public class HomeController {
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@Autowired
	private IHorariosService serviceHorarios;
	
	@Autowired
	private IBannersService serviceBanners;
	
	//Usado para formato de la fecha
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	//Home
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	//Buscar - Post
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String buscar(@RequestParam("fecha") String fecha, Model model) {
		
		List<String> listaFechas = Utileria.getProximosDias(4); //Lista de Fechas
		//Servicio peliculas
		List<Pelicula> peliculas =  servicePeliculas.buscarTodas();				
	
		//Adicionar atributos al modelo
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("peliculas", peliculas);
		
		model.addAttribute("banners", serviceBanners.buscarTodos());
		
		System.out.println("Buscando todas las peliculas en exhibicion para la fecha: " + fecha);
		return "home";
	}
	
	
	// / -> Llevara por defecto a Principal
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model) {
		
		
		List<String> listaFechas = Utileria.getProximosDias(4); //Lista de Fechas
		List<Pelicula> peliculas =  servicePeliculas.buscarTodas(); 				
		
		System.out.println(listaFechas);
		//Lista de tipo Pelicula -- LinkedList: Lista enlazada
		//List<Pelicula> peliculas1 =  new LinkedList<>();
		/*peliculas.add("Rapido y Furioso");
		peliculas.add("El Aro 2");
		peliculas.add("Aliens");*/
		
		//Adicionar atributos al modelo
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		model.addAttribute("peliculas", peliculas);
		
		model.addAttribute("banners", serviceBanners.buscarTodos());
		return "home";
		
	}
	
	//Mostrar Detalles de Pelicula
	//@Pathvariable - usar variable en el url (url dinamica)
	@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
	public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") Date fecha) {
	//@RequesParams recibe parametros por url
	//@RequestMapping(value= "/detail", method=RequestMethod.GET)
	//public String mostrarDetalle(Model model, @RequestParam("idMovie") int idPelicula, @RequestParam("fecha") String fecha) {
		
		List<Horario> horarios = serviceHorarios.buscarPorIdPelicula(idPelicula, fecha);
		model.addAttribute("horarios", horarios);
		model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
		
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		
		// TODO - Buscar en la base de datos los horarios.
		
		/*
		//Atributos
		String tituloPelicula = "Rapidos y furiosos";
		int duracion = 136;
		double precioEntrada = 50;
		
		//Adicionar atributos al modelo
		model.addAttribute("titulo", tituloPelicula);
		model.addAttribute("titulo", duracion);
		model.addAttribute("precio", precioEntrada);*/
		
		return "detalle";
	}
	
	//InitBinder - Permite crear metodos para configurar databinding
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Convertir fecha del formulario a dd-MM-yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	
}
