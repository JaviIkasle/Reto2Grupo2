package com.example.Reto2Grupo2.trabajador.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Reto2Grupo2.trabajador.modelo.Trabajador;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorExpands;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorPostRequest;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorServiceModel;
import com.example.Reto2Grupo2.trabajador.repository.TrabajadorRepository;


@Service
public class TrabajadorService implements TrabajadorServiceImpl{

	@Autowired
	private TrabajadorRepository trabajadorRepository;
	
	@Override
	public List<TrabajadorServiceModel> getTrabajadores() {
		Iterable<Trabajador> trabajadores = trabajadorRepository.findAll();
		List<TrabajadorServiceModel> response = new ArrayList<TrabajadorServiceModel>();
		for (Trabajador trabajador : trabajadores) {
			response.add(
					new TrabajadorServiceModel(
							trabajador.getId(),
							trabajador.getUsername(),
							trabajador.getPassword(),
							null,
							trabajador.getZooId()));
		}		return response;
	}

	@Override
	public TrabajadorServiceModel getTrabajadorById(Integer id, List<TrabajadorExpands> expand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrabajadorServiceModel create(TrabajadorPostRequest trabajadorPostRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrabajadorServiceModel updateById(Integer id, TrabajadorPostRequest trabajadorPostRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
