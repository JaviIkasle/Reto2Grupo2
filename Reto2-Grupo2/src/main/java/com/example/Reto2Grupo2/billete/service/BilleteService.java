package com.example.Reto2Grupo2.billete.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Reto2Grupo2.billete.modelo.Billete;
import com.example.Reto2Grupo2.billete.modelo.BilleteExpands;
import com.example.Reto2Grupo2.billete.modelo.BilletePostRequest;
import com.example.Reto2Grupo2.billete.modelo.BilleteServiceModel;
import com.example.Reto2Grupo2.billete.repository.BilleteRepository;
import com.example.Reto2Grupo2.cliente.modelo.Cliente;
import com.example.Reto2Grupo2.cliente.modelo.ClienteServiceModel;
import com.example.Reto2Grupo2.cliente.repository.ClienteRepository;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;

@Service
public class BilleteService implements BilleteServiceImpl {

	@Autowired
	private BilleteRepository billeteRepository;

	@Autowired
	private ZooRepository zooRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<BilleteServiceModel> getBilletes() {
		Iterable<Billete> billetes = billeteRepository.findAll();
		List<BilleteServiceModel> response = new ArrayList<BilleteServiceModel>();
		for (Billete billete : billetes) {
			
			response.add(new BilleteServiceModel(
					billete.getId(), 
					billete.getFecha(),
					billete.getCantidad(),
					billete.getIdZoo(), 
					billete.getIdCliente(),
					null
					));
		
		}
		return response;
	}

	@Override
	public BilleteServiceModel getBilleteById(Integer id, List<BilleteExpands> expand) {
		Billete billete = billeteRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Billete no encontrado"));

		ZooServiceModel zooResponse = null;

		if (expand != null && expand.indexOf(BilleteExpands.ZOO) != -1) {
			Zoo zooBD = billete.getZoo();

			zooResponse = new ZooServiceModel(
					zooBD.getId(), 
					zooBD.getNombre(),
					zooBD.getPvpEntrada(),
					zooBD.getWeb(),
					zooBD.getInformacion(), 
					zooBD.getLatitud(), 
					zooBD.getLongitud(), 
					zooBD.getCiudad(),
					zooBD.getPais(),
					null, null);
		}

		ClienteServiceModel ClienteResponse = null;

		if (expand != null && expand.indexOf(BilleteExpands.CLIENTE) != -1) {
			Cliente clienteBD = billete.getCliente();

			ClienteResponse = new ClienteServiceModel(
					clienteBD.getId(), 
					clienteBD.getEmail(),
					clienteBD.getPassword());

		}

		BilleteServiceModel response = new BilleteServiceModel(
				billete.getId(), 
				billete.getFecha(),
				billete.getCantidad(),
				billete.getImporte(), 
				zooResponse, 
				billete.getIdZoo(),
				ClienteResponse,
				billete.getIdCliente()

		);

		return response;
	}

	@Override
	public BilleteServiceModel create(BilletePostRequest billetePostRequest) {
		Zoo zoo = zooRepository.findById(billetePostRequest.getIdZoo())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));

		Cliente cliente = clienteRepository.findById(billetePostRequest.getIdCliente())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Cliente no encontrado"));
		
		

		Billete billete = new Billete(
				null, 
				billetePostRequest.getFecha(),
				billetePostRequest.getCantidad(),
				billetePostRequest.getImporte(),
				zoo,
				cliente);

		billete = billeteRepository.save(billete);
		BilleteServiceModel billeteResponse = new BilleteServiceModel(
				billete.getId(), 
				billete.getFecha(),
				billete.getCantidad(), 
				billete.getImporte(), 
				null, 
				zoo.getId(), 
				null, 
				cliente.getId());
		
		return billeteResponse;
	}

	@Override
	public BilleteServiceModel updateById(Integer id, BilletePostRequest billetePostRequest) {
		// TODO Auto-generated method stub
		Billete billete = billeteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Billete no encontrado"));
		
		
		
		if (billetePostRequest.getFecha() != null) {
			billete.setFecha(billetePostRequest.getFecha());
		}
		if (billetePostRequest.getCantidad() != 0) {
			billete.setCantidad(billetePostRequest.getCantidad());
		}
		if (billetePostRequest.getImporte() != 0) {
			billete.setImporte(billetePostRequest.getImporte());
		}
		if (billetePostRequest.getIdZoo() != 0) {
			billete.setIdZoo(billetePostRequest.getIdZoo());
		}
		if (billetePostRequest.getIdCliente() != 0) {
			billete.setIdCliente(billetePostRequest.getIdCliente());
		}

		billete = billeteRepository.save(billete);

		
		BilleteServiceModel billeteResponse = new BilleteServiceModel(
				billete.getId(),
				billete.getFecha(),
				billete.getCantidad(),
				billete.getImporte(), 
				billete.getIdZoo(), 
				billete.getIdCliente()
				
				);

		return billeteResponse;

	}

}
