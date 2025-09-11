package com.back_comunio_dinamico.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.back_comunio_dinamico.entities.Puntos;

@Repository
public interface PuntosRepository extends MongoRepository<Puntos, String>{

}
