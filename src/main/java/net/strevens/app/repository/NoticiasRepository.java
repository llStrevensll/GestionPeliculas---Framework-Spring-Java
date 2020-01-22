package net.strevens.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.strevens.app.model.Noticia;

@Repository
public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {

}
