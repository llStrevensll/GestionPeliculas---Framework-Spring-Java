package net.strevens.app.service;

import java.util.Date;
import java.util.List;

import net.strevens.app.model.Horario;

public interface IHorariosService {
	List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
}
