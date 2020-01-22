package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppConexion {

	public static void main(String[] args) {
		
		//Instancia de application context - config de los beans: root-context.xml
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		
		context.close();
	}

}
