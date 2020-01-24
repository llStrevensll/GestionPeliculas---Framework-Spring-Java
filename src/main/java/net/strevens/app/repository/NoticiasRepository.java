package net.strevens.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.strevens.app.model.Noticia;

@Repository
//public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {
public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {
	
	//Filtrar por estatus - query methods(uso de palabras reservadas)
	//select * from Noticias
	List<Noticia> findBy();
	
	//select * from Noticias where estatus = ?
	List<Noticia> findByEstatus(String estatus);
	
	//where fecha = ?
	List<Noticia> findByFecha(Date fecha);
	
	//where estatus=? and fecha=?
	List<Noticia> findByEstatusAndFecha(String estatus, Date fecha);
	
	//where estatus=? or fecha=?
	List<Noticia> findByEstatusOrFecha(String estatus, Date fecha);
	
	//where fecha between ? and ?
	List<Noticia> findByFechaBetween(Date fecha1, Date fecha2);

}
