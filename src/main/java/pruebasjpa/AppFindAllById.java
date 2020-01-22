package pruebasjpa;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.strevens.app.model.Noticia;
import net.strevens.app.repository.NoticiasRepository;

public class AppFindAllById {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		//Recuperar varios registros por Id [metodo findAllById del repositorio]
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(3);
		
		
		Iterable<Noticia> it = repo.findAllById(ids);
		for (Noticia n : it) {
			System.out.println(n);
		}
		context.close();

	}

}
