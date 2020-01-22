package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.strevens.app.repository.NoticiasRepository;

public class AppExist {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		//Método para verificar si una entidad existe en la base de datos (metodo existById)
		int idNoticia=2;
		System.out.println(repo.existsById(idNoticia));
				
		context.close();

	}

}
