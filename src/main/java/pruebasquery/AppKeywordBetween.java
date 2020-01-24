package pruebasquery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.strevens.app.model.Noticia;
import net.strevens.app.repository.NoticiasRepository;

public class AppKeywordBetween {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);	
		
		// Ejemplo Keyword And		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<Noticia> lista=null;
		//TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		
		try {
			lista = repo.findByFechaBetween(format.parse("2017-09-02"), format.parse("2017-09-06"));
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
