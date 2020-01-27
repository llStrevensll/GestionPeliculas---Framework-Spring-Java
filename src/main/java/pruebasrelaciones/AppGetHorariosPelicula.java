package pruebasrelaciones;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.strevens.app.model.Pelicula;
import net.strevens.app.repository.PeliculasRepository;

public class AppGetHorariosPelicula {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
		PeliculasRepository repo = context.getBean("peliculasRepository", PeliculasRepository.class);
		
		//findById-> Devuelve objeto de tipo Optional 
		Optional<Pelicula> optional = repo.findById(7);
		
		System.out.println(optional.get().getHorarios().size());
		
		context.close();

	}

}
