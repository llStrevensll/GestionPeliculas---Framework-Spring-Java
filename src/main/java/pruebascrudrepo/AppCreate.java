package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.strevens.app.model.Noticia;
import net.strevens.app.repository.NoticiasRepository;

//Aplicacion para persistir(crear) en la tabla noticias un objeto de tipo Noticia
public class AppCreate {

	public static void main(String[] args) {
		
		Noticia noticia = new Noticia();
		noticia.setTitulo("Proximo Estreno: Juego 5");
		noticia.setDetalle("El mes de Marzo estrena");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		repo.save(noticia);
		
		context.close();

	}

}
