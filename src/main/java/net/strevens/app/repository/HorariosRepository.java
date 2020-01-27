package net.strevens.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.strevens.app.model.Horario;

@Repository
public interface HorariosRepository extends JpaRepository<Horario, Integer> {

}
