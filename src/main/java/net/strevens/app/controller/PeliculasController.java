package net.strevens.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.strevens.app.model.Pelicula;
import net.strevens.app.service.IPeliculasService;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	//Mostrar Peliculas
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		List<Pelicula> lista = servicePeliculas.buscarTodas();
		model.addAttribute("peliculas", lista);
		
		return "peliculas/listPeliculas";
	}
	
	//Crear
	@GetMapping("/create")
	public String crear() {
		return "peliculas/formPelicula";
	}
	
	//Guardar
	@PostMapping("/save")
	public String guardar(Pelicula pelicula, BindingResult result, RedirectAttributes attributes) {//Data Binding
		
		/*if (result.hasErrors()) {
			System.out.println("Ecisten errores");
			return "peliculas/formPelicula";
		}*/
		
		//Ver errores
		for (ObjectError error : result.getAllErrors()) {
			System.out.println(error.getDefaultMessage());
		}
		
		System.out.println("Recibiendo objeto Pelicula " + pelicula);
		//Insertar 
		System.out.println("Elementos en la lista antes de la insercion: " + servicePeliculas.buscarTodas().size());
		
		servicePeliculas.insertar(pelicula);
		
		System.out.println("Elementos en la lista despues de la insercion: " + servicePeliculas.buscarTodas().size());
		
		//Adicionar para mostrar el msj en la vista
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		
		//return "peliculas/formPelicula";
		return "redirect:/peliculas/index";
	}
	
	//InitBinder - Permite crear metodos para condigurar databinding
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Convertir fecha del formulario a dd-MM-yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
