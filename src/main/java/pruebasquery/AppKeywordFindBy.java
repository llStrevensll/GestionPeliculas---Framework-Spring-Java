package pruebasquery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.strevens.app.model.Noticia;
import net.strevens.app.repository.NoticiasRepository;

public class AppKeywordFindBy {
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);	
		
		// Ejemplo Keyword findBy
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		/*Date fecha1 = new Date(Calendar.getInstance().getTimeInMillis());
		System.out.println(fecha1);
		String fechaTexto = formatter.format(fecha1);
		System.out.println("fechaTexto: "+ fechaTexto);*/
		
		List<Noticia> lista=null;

		//TimeZone.setDefault(TimeZone.getTimeZone("UTF"));
		try {
			System.out.println("Entree");
			Date dataa = formatter.parse("2017-09-02");
			System.out.println("Dataaa: " + dataa.getClass() );
			System.out.println("Dataaa: " + dataa );
		    //TimeZone.setDefault(TimeZone.getTimeZone("COT"));
			lista = repo.findByFecha(dataa);
			
			System.out.println(lista);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		for (Noticia n: lista) {
			System.out.println(n);
		}
				
		context.close();

	}
}
