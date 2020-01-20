package net.strevens.app.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.strevens.app.model.Pelicula;
import net.strevens.app.service.IPeliculasService;
import net.strevens.app.util.Utileria;

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
	public String guardar(Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {//Data Binding
		
		if (result.hasErrors()) {
			System.out.println("Ecisten errores");
			return "peliculas/formPelicula";
		}
		
		if (!multiPart.isEmpty()) {//si multiPart no esta vacio
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			pelicula.setImagen(nombreImagen);
		}
		
		//Ver errores
		/*for (ObjectError error : result.getAllErrors()) {
			System.out.println(error.getDefaultMessage());
		}*/
		
	
		
		servicePeliculas.insertar(pelicula);
		//System.out.println("Elementos en la lista despues de la insercion: " + servicePeliculas.buscarTodas().size());
		
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
