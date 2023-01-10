package com.example.Reto2Grupo2.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Reto2Grupo2.user.modelo.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	Optional<User> findByUsername(String username);
	
}
