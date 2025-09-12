package com.back_comunio_dinamico.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.back_comunio_dinamico.entities.JornadasAcumuladas;

@Repository
public interface JornadasAcumuladasRepository extends MongoRepository<JornadasAcumuladas, String>{
	
	@Query("{ 'numeroJornada' : ?0 }")
    List<JornadasAcumuladas> findPartidosByNumJornada(Integer numJornada);
	
	@Query("{ '$and': [{ 'numeroJornada': ?0 }, { 'idEquipo1': ?1 }] }")
    JornadasAcumuladas findPartidoByNumJornadaAndEquipo(Integer numJornada, Integer idEquipoLocal);

}
