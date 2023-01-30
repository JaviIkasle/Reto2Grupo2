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
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;
import com.example.Reto2Grupo2.user.repository.UserRepository;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;

@Service
public class BilleteServiceImpl implements BilleteService {

	@Autowired
	private BilleteRepository billeteRepository;

	@Autowired
	private ZooRepository zooRepository;


	@Autowired
	private UserRepository userRepository;

	@Override
	public List<BilleteServiceModel> getBilletes(Integer userId) {
		Iterable<Billete> billetes = billeteRepository.findAll();
		//TODO consulta compleja para generar los billetes del usuario registrado
		
		List<BilleteServiceModel> response = new ArrayList<BilleteServiceModel>();
		for (Billete billete : billetes) {
			
			response.add(new BilleteServiceModel(
					billete.getId(), 
					billete.getFecha(),
					billete.getCantidad(),
					billete.getZooId(), 
					billete.getUserId(),
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
					null,
					null);
		}

		UserServiceModel userResponse = null;

		if (expand != null && expand.indexOf(BilleteExpands.CLIENTE) != -1) {
			User userBd = billete.getUser();

			userResponse = new UserServiceModel(
					userBd.getId(), 
					userBd.getUsername(),
					userBd.getPassword());

		}

		BilleteServiceModel response = new BilleteServiceModel(
				billete.getId(), 
				billete.getFecha(),
				billete.getCantidad(),
				billete.getImporte(), 
				zooResponse, 
				billete.getZooId(),
				userResponse,
				billete.getUserId()

		);

		return response;
	}

	@Override
	public BilleteServiceModel create(BilletePostRequest billetePostRequest, Integer userId) {
		Zoo zoo = zooRepository.findById(billetePostRequest.getIdZoo())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Cliente no encontrado"));
		
		float importeTotal = billetePostRequest.getCantidad() * zoo.getPvpEntrada();

		Billete billete = new Billete(
				null, 
				billetePostRequest.getFecha(),
				billetePostRequest.getCantidad(),
				importeTotal,
				zoo,
				user);

		billete = billeteRepository.save(billete);
		
		BilleteServiceModel billeteResponse = new BilleteServiceModel(
				billete.getId(), 
				billete.getFecha(),
				billete.getCantidad(), 
				billete.getImporte(), 
				null, 
				zoo.getId(), 
				null, 
				user.getId());
		
		return billeteResponse;
	}

	@Override
	public BilleteServiceModel updateById(Integer id, BilletePostRequest billetePostRequest) {
		
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
			billete.setZooId(billetePostRequest.getIdZoo());
		}
		if (billetePostRequest.getIdCliente() != 0) {
			billete.setUserId(billetePostRequest.getIdCliente());
		}

		billete = billeteRepository.save(billete);

		
		BilleteServiceModel billeteResponse = new BilleteServiceModel(
				billete.getId(),
				billete.getFecha(),
				billete.getCantidad(),
				billete.getImporte(), 
				billete.getZooId(), 
				billete.getUserId()
				
				);

		return billeteResponse;

	}

}
