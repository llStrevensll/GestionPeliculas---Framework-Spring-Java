package net.strevens.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.strevens.app.model.Horario;

@Repository
public interface HorariosRepository extends JpaRepository<Horario, Integer> {
	
	public List<Horario> findByPelicula_IdAndFechaOrderByHora(int idPelicula, Date fecha);
}
