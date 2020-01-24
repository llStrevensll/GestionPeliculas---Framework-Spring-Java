package pruebasjparepo;

import java.util.List;
import java.util.TimeZone;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.strevens.app.model.Noticia;
import net.strevens.app.repository.NoticiasRepository;

public class AppFindAll {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		TimeZone.setDefault(TimeZone.getTimeZone("COT"));
		List<Noticia> lista = repo.findAll();
		
		for (Noticia n : lista) {
			System.out.println(n);
		}
		
		
		
		context.close();

	}

}
