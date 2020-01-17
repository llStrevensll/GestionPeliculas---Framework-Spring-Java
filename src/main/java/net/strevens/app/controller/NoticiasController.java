package net.strevens.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.strevens.app.model.Noticia;
import net.strevens.app.service.INoticiaService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {
	
	@Autowired
	private INoticiaService serviceNoticias;
	
	@GetMapping(value="/create")
	public String crear() {
		return "noticias/formNoticia";
	}
	
	@PostMapping(value="/save")
	public String guardar(@RequestParam("titulo") String titulo, @RequestParam("estatus") String estatus, @RequestParam("detalle") String detalle) {
		
		Noticia noticia = new Noticia();
		noticia.setTitulo(titulo);
		noticia.setEstatus(estatus);
		noticia.setDetalle(detalle);
		
		//Pendiente guardar el objeto noticia en la BD
		
		serviceNoticias.guardar(noticia);
	
		return "noticias/formNoticia";
	}
}
