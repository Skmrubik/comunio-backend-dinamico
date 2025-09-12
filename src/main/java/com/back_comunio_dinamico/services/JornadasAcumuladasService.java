package com.back_comunio_dinamico.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Collections;
import java.util.HashMap;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.back_comunio_dinamico.controllers.ControllerPartido;
import com.back_comunio_dinamico.entities.JornadasAcumuladas;
import com.back_comunio_dinamico.entities.Jugador;
import com.back_comunio_dinamico.entities.Partido;
import com.back_comunio_dinamico.entities.Puntos;
import com.back_comunio_dinamico.entities.Resultado;
import com.back_comunio_dinamico.repositories.JornadasAcumuladasRepository;
import com.back_comunio_dinamico.repositories.PuntosRepository;


@Service
@RestController
public class JornadasAcumuladasService {

	@Autowired
	JornadasAcumuladasRepository jornadasAcumuladasRepository;
	
	@Autowired
	PuntosRepository puntosRepository;
    
	ControllerPartido controllerPartido;
	
	KafkaProducerService kafkaProducerService;
	
	public JornadasAcumuladasService(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
		this.controllerPartido = new ControllerPartido(kafkaProducerService);
	}
	
	@Transactional
	@PostMapping("/insertPartido")
	public ResponseEntity<Resultado> insertPartido(@RequestBody Partido partido) {
		try {
			//Atributos por defecto
			Resultado result = controllerPartido.generarResultadoPartido(partido);
			JornadasAcumuladas jornadaAcumulada = new JornadasAcumuladas();
			jornadaAcumulada.setIdEquipo1(partido.getEquipoLocal().getIdEquipo());
			jornadaAcumulada.setIdEquipo2(partido.getEquipoVisitante().getIdEquipo());
			jornadaAcumulada.setGolesEquipo1(result.getResultadoLocal());
			jornadaAcumulada.setGolesEquipo2(result.getResultadoVisitante());
			jornadaAcumulada.setNumeroJornada(partido.getNumJornada());
			jornadasAcumuladasRepository.save(jornadaAcumulada);
			Resultado resultadoPuntos = controllerPartido.generarPuntosPartido(puntosRepository, partido, result);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getPartidosJornada")
	private ResponseEntity<List<JornadasAcumuladas>> getJornada(@RequestParam String numJornada){
		try {
			Integer num = Integer.parseInt(numJornada);
			List<JornadasAcumuladas> partidos = jornadasAcumuladasRepository.findPartidosByNumJornada(num);
			return new ResponseEntity<>(partidos, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getPartidoJornada")
	private ResponseEntity<JornadasAcumuladas> getPartidoJornada(@RequestParam String numJornada,
			@RequestParam String idEquipoLocal){
		try {
			Integer num = Integer.parseInt(numJornada);
			Integer idE = Integer.parseInt(idEquipoLocal);
			JornadasAcumuladas partido = jornadasAcumuladasRepository.findPartidoByNumJornadaAndEquipo(num, idE);
			return new ResponseEntity<>(partido, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getJugadoresPartidoJornada")
	private ResponseEntity<List<Puntos>> getJugadoresPartidoJornada(@RequestParam String numJornada,
			@RequestParam String equipo){
		try {
			Integer num = Integer.parseInt(numJornada);
			Integer eq = Integer.parseInt(equipo);
			List<Puntos> puntos = puntosRepository.findJugadoresEquipoJornada(num, eq);
			return new ResponseEntity<>(puntos, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
