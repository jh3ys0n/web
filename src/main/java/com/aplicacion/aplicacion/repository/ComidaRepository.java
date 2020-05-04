package com.aplicacion.aplicacion.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.aplicacion.entity.Comida;

@Repository
public interface ComidaRepository extends CrudRepository<Comida,Long>{
    
	
}
