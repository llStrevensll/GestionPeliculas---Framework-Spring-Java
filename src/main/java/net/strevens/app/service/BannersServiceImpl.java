package net.strevens.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import net.strevens.app.model.Banner;

@Service
public class BannersServiceImpl implements IBannersService{
	
	private List<Banner> lista = null;
	
	public BannersServiceImpl() {
		System.out.println("Creando una instancia de la clase BannersServiceImpl");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			lista = new LinkedList<>();
			
			
			Banner banner1 = new Banner();
			banner1.setId(1);
			banner1.setTitulo("Proximamente Kong La Isla Calavera");
			banner1.setArchivo("slide1.jpg");
			
			Banner banner2 = new Banner();
			banner2.setId(2);
			banner2.setTitulo("Estreno La bella y la bestia");
			banner2.setArchivo("slide2.jpg");
			
			Banner banner3 = new Banner();
			banner3.setId(3);
			banner3.setTitulo("Este mes no te pierdas Rapidos y Furiosos 8");
			banner3.setArchivo("slide3.jpg");
			
			Banner banner4 = new Banner();
			banner4.setId(4);
			banner4.setTitulo("Estreno en Agosto - Jefe en Panales");
			banner4.setArchivo("slide4.jpg");
			banner4.setEstatus("Inactivo");
			
			//Agregar los objetos Banners a la lista
			lista.add(banner1);
			lista.add(banner2);
			lista.add(banner3);
			lista.add(banner4);
			
			
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
			
		}
	}
	
	
	@Override
	public void insertar(Banner banner) {
		lista.add(banner);
	}

	@Override
	public List<Banner> buscarTodos() {
		
		return lista;
	}
	
	
	
}
