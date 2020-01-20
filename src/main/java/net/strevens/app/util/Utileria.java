package net.strevens.app.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {
	
	/** 
	 * Metodo que regresa una Lista de Strings con las fecha siguientes, según el parametro count
	 * @param count
	 * @return
	 * */
	public static List<String> getProximosDias(int count){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		// Fecha Actual
		Date inicio = new Date();
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, count); // Siguientes n dias desde ahora
		Date finFecha = calendario.getTime();
		
		//Calendario Gregoriano
		GregorianCalendar calendarioGregoriano = new GregorianCalendar();
		calendarioGregoriano.setTime(inicio);
		
		List<String> siguientesDias = new ArrayList<String>();
		while (!calendarioGregoriano.getTime().after(finFecha)) {
			
			Date fecha = calendarioGregoriano.getTime();
			
			calendarioGregoriano.add(Calendar.DATE, 1);
			siguientesDias.add(simpleDateFormat.format(fecha));
		}
		
		return siguientesDias;
		
	}
	
	public static String guardarImagen(MultipartFile multiPart, HttpServletRequest request) {
		// Obtenemos el nombre original del archivo
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal = nombreOriginal.replace(" ", "-");//Reemplazar espacios por guiones
		
		//Agregar 8 caracteres aleatorios al inicio de la imagen
		String nombreFinal = randomAlphaNumeric(8)+nombreOriginal;
		
		// Obtenemos la ruta ABSOLUTA del directorio images
		// apache-tomcat/WebContent/cineapp1/resources/images/
		String rutaFinal = request.getServletContext().getRealPath("/resources/images/");
		
		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro
			File imageFile = new File(rutaFinal + nombreFinal);
			System.out.println(imageFile.getAbsolutePath());
			// Aqui se guarda fisicamente el archivo en el disco duro
			multiPart.transferTo(imageFile);
			return nombreFinal;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}
	
	//Generar una cadena de longitud N de caracteres aleatorios
	public static String randomAlphaNumeric(int count) {
		
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}
}
