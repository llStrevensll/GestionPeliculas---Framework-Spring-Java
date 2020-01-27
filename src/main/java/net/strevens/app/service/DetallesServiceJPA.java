package net.strevens.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.strevens.app.model.Detalle;
import net.strevens.app.repository.DetallesRepository;

@Service
public class DetallesServiceJPA implements IDetallesService{
	
	@Autowired
	private DetallesRepository detallesRepo;
	
	@Override
	public void insertar(Detalle detalle) {
		//Insertar detalle en BD
		detallesRepo.save(detalle);
		
	}

	@Override
	public void eliminar(int idDetalle) {
		detallesRepo.deleteById(idDetalle);
		
	}

}
