package net.strevens.app.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
}
