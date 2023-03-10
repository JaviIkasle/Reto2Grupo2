package com.example.Reto2Grupo2.zoo.service;

import java.util.List;
import com.example.Reto2Grupo2.zoo.modelo.ZooPostRequest;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;

public interface ZooService {
	
	List<ZooServiceModel> getZoos(Integer userId);

	ZooServiceModel getZoosById(Integer id, Integer userId);

	ZooServiceModel create( ZooPostRequest zooPostRequest);

	ZooServiceModel updateById(Integer id, ZooPostRequest zooPostRequest);

//	Integer deleteById(Integer id);
	
}
