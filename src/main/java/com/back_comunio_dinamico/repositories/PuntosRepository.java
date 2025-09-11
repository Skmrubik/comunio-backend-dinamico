package com.back_comunio_dinamico.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.back_comunio_dinamico.entities.JornadasAcumuladas;
import com.back_comunio_dinamico.entities.Puntos;

@Repository
public interface PuntosRepository extends MongoRepository<Puntos, String>{
	
	@Query(value = "{ '$and': [{ 'numJornada': ?0 }, { 'idEquipo': ?1 }] }", sort = "{ 'posicion': 1 }")
    List<Puntos> findJugadoresEquipoJornada(Integer numJornada, Integer idEquipo);
}
