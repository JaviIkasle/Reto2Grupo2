package com.example.Reto2Grupo2.billete.service;

import java.util.List;

import com.example.Reto2Grupo2.billete.modelo.BilleteExpands;
import com.example.Reto2Grupo2.billete.modelo.BilletePostRequest;
import com.example.Reto2Grupo2.billete.modelo.BilleteServiceModel;

public interface BilleteServiceImpl {
	
	List<BilleteServiceModel>getBilletes(Integer integer);
	
	BilleteServiceModel getBilleteById(Integer id, List<BilleteExpands> expand);
	
	BilleteServiceModel create(BilletePostRequest billetePostRequest, Integer integer);
	
	BilleteServiceModel updateById(Integer id , BilletePostRequest billetePostRequest);
	
	
	
	

}
