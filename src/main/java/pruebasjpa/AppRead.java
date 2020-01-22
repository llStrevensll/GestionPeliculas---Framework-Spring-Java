package pruebasjpa;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.strevens.app.model.Noticia;
import net.strevens.app.repository.NoticiasRepository;

public class AppRead {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		//Operacion CRUD - Read[metodo findById del repositorio]
		Optional<Noticia> noticia = repo.findById(1); //evita excepcion de null en caso de no encontrar el registro
		System.out.println(noticia);
		
		context.close();
		
	}

}
