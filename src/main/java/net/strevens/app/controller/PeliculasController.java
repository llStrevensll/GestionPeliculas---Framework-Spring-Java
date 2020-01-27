package net.strevens.app.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.strevens.app.model.Pelicula;
import net.strevens.app.service.IDetallesService;
import net.strevens.app.service.IPeliculasService;
import net.strevens.app.util.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IDetallesService serviceDetalles;
	
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
	public String crear(@ModelAttribute Pelicula pelicula, Model model) {
		//model.addAttribute("generos", servicePeliculas.buscarGeneros());
		return "peliculas/formPelicula";
	}
	
	//Guardar
	@PostMapping("/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
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
		
		/*Insertar primero detalles  (por la llave foranea)*/
		System.out.println("Antes: " + pelicula.getDetalle());
		serviceDetalles.insertar(pelicula.getDetalle());  //Encaso que ya exista el id se realizara un update
		System.out.println("Despues: " + pelicula.getDetalle());
		
		servicePeliculas.insertar(pelicula);
		//System.out.println("Elementos en la lista despues de la insercion: " + servicePeliculas.buscarTodas().size());
		
		//Adicionar para mostrar el msj en la vista
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		//return "peliculas/formPelicula";
		return "redirect:/peliculas/index";
	}
	
	
	//Editar Pelicula
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int idPelicula, Model model) {
		//Por el url obtenemos idPelicula
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		//Adicionar pelicula y generos
		model.addAttribute("pelicula", pelicula);
		//model.addAttribute("generos", servicePeliculas.buscarGeneros());
		
		return "peliculas/formPelicula";
	}
	
	//Eliminar Pelicula
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable ("id") int idPelicula, RedirectAttributes attributes) {
		
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		
		//Eliminar peliculas y despues detalles
		servicePeliculas.eliminar(idPelicula);
		serviceDetalles.eliminar(pelicula.getDetalle().getId());
		
		attributes.addFlashAttribute("mensaje", "La pelicula fue eliminada!");
		return "redirect:/peliculas/index";
	}
	
	@GetMapping(value="/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Pelicula> lista = servicePeliculas.buscarTodas(page);
		model.addAttribute("peliculas", lista);
		
		return "peliculas/listPeliculas";
	}
	
	
	//Obtener lista de generos - generos estara disponible para cada metodo del controlador
	@ModelAttribute("generos")
	public List<String> getGeneros(){
		return servicePeliculas.buscarGeneros();
	}
	
	//InitBinder - Permite crear metodos para configurar databinding
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Convertir fecha del formulario a dd-MM-yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	
}
