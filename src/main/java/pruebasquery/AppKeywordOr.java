package pruebasquery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.strevens.app.model.Noticia;
import net.strevens.app.repository.NoticiasRepository;

public class AppKeywordOr {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);	
		
		// Ejemplo Keyword And		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<Noticia> lista=null;
		TimeZone.setDefault(TimeZone.getTimeZone("COT"));
		
		try {
			lista = repo.findByEstatusOrFecha("Inactiva", format.parse("2017-09-08"));
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
