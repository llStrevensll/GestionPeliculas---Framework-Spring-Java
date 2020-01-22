package pruebascrudrepo;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.strevens.app.model.Noticia;
import net.strevens.app.repository.NoticiasRepository;

public class AppUpdate {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		//Operacion CRUD - Update [metodo save del repositprio]
		
		//1. Primero se busca la entidad que se va a modificar (findById)
		Optional<Noticia> optional = repo.findById(1);
		
		//2. Modificamos la entidad y la guardamos
		if (optional.isPresent()) {
			Noticia noticia = optional.get(); //Objeto que envuelve -> <Noticia>
			noticia.setEstatus("Inactiva");
			//Actualizar
			repo.save(noticia);  //Si spring detecta que el id es diferente a cero hace un update,--- en caso contrario seria insert
		}
		
		context.close();

	}

}
