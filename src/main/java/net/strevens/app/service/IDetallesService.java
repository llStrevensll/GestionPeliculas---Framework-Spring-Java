package net.strevens.app.service;

import net.strevens.app.model.Detalle;

public interface IDetallesService {
	void insertar(Detalle detalle);
	void eliminar(int idDetalle);

}
