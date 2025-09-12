package com.back_comunio_dinamico.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.back_comunio_dinamico.entities.JornadasAcumuladas;
import com.back_comunio_dinamico.entities.Jugador;
import com.back_comunio_dinamico.entities.MensajeJugadorDTO;
import com.back_comunio_dinamico.entities.Partido;
import com.back_comunio_dinamico.entities.Puntos;
import com.back_comunio_dinamico.entities.Resultado;
import com.back_comunio_dinamico.repositories.JornadasAcumuladasRepository;
import com.back_comunio_dinamico.repositories.PuntosRepository;
import com.back_comunio_dinamico.services.KafkaProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ControllerPartido {
	private final KafkaProducerService kafkaProducerService;


	public ControllerPartido(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}

	public Resultado generarResultadoPartido(Partido partido) {
		Integer golesLocal = partido.getEquipoLocal().getGolesCasa() + partido.getEquipoVisitante().getGolesFuera() + 2;
		Integer golesVisitante = partido.getEquipoLocal().getGolesFuera() + partido.getEquipoVisitante().getGolesCasa();
		List<Integer> distribucion = new ArrayList<>();
		for (int i=0 ; i<golesLocal+golesVisitante; i++) {
			if(i< golesLocal) {
				distribucion.add(0);
			} else {
				distribucion.add(1);
			}
		}
		List<Integer> distribucionGolesPartido = Arrays.asList(0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,
				2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,5,5,5,5,6,6,7,8,9);
		Random r= new Random();
		int r1 = r.nextInt(79);
		Integer golesPartido = distribucionGolesPartido.get(r1);
		Resultado result = new Resultado(0, 0, null, null, null);
		for (int i = 0; i<golesPartido; i++) {
			int r2 = r.nextInt(golesLocal+golesVisitante);
			if (distribucion.get(r2)==0) {
				result.addGolLocal();
			} else {
				result.addGolVisitante();
			}
		}
		return result;
	}
	
	public Resultado generarPuntosPartido(PuntosRepository puntosRepository, Partido partido, Resultado result) throws JsonProcessingException {
		List<Jugador> jugadoresLocales = partido.getJugadoresLocales();
		List<Jugador> jugadoresVisitantes = partido.getJugadoresVisitantes();
		
		List<Integer> probabilidadGolesLocal = getArrayProbabilidadGoles(jugadoresLocales);
		List<Integer> goleadoresLocal = getGoleadores(result.getResultadoLocal(), probabilidadGolesLocal);		
		setPuntosJugadores(puntosRepository,jugadoresLocales, goleadoresLocal, partido.getNumJornada());
		
		List<Integer> probabilidadGolesVisitantes = getArrayProbabilidadGoles(jugadoresVisitantes);		
		List<Integer> goleadoresVisitantes = getGoleadores(result.getResultadoVisitante(), probabilidadGolesVisitantes);
		setPuntosJugadores(puntosRepository,jugadoresVisitantes, goleadoresVisitantes, partido.getNumJornada());
		
		result.setJugadoresLocales(jugadoresLocales);
		result.setJugadoresVisitantes(jugadoresVisitantes);
		result.setNumJornada(partido.getNumJornada());
		return result;
	}
	
	public int getGolesJugador(List<Integer> goles, Integer idJugador) {
		int contadorGoles = 0;
		for (int i=0; i<goles.size(); i++) {
			if (goles.get(i)==idJugador) {
				contadorGoles++;
			}
		}
		return contadorGoles;
	}
	
	public int getPuntosPorGoles(Jugador jugador, int goles) {
		int puntosPorGoles = 0;
		if (jugador.getPosicion()==2) {
			puntosPorGoles = goles*5;
		} else if (jugador.getPosicion()==3) {
			puntosPorGoles = goles*4;
		} else if (jugador.getPosicion()==4) {
			puntosPorGoles = goles*3;
		}
		return puntosPorGoles;
	}
	
	public List<Integer> getArrayProbabilidadGoles(List<Jugador> jugadores){
		List<Integer> probabilidadGoles = new ArrayList<>();
		for	(Jugador jugador:jugadores) {
			for(int i=0; i< jugador.getProbGol(); i++) {
				probabilidadGoles.add(jugador.getIdJugador());
			}
		}
		return probabilidadGoles;
	}
	
	public List<Integer> getGoleadores(Integer golesTotales, List<Integer> probabilidadGoles){
		List<Integer> goleadores = new ArrayList<>();
		for (int i= 0; i<golesTotales; i++) {
			Random r= new Random();
			int r1 = r.nextInt(probabilidadGoles.size());
			goleadores.add(probabilidadGoles.get(r1));
		}
		return goleadores;
	}
	
	public void setPuntosJugadores(PuntosRepository puntosRepository,List<Jugador> jugadores, List<Integer> goleadores,
			Integer numJornada) throws JsonProcessingException {
		for	(Jugador jugador:jugadores) {
			int goles = getGolesJugador(goleadores, jugador.getIdJugador());
			int puntosPorGoles = getPuntosPorGoles(jugador, goles);
			Random random = new Random();
			
			float media = jugador.getNivel();
			float desviacionEstandar = 1.6F;
			int puntosPartido = (int) Math.round((media+goles) + (desviacionEstandar * random.nextGaussian()));
			jugador.setPuntosJornada(puntosPartido + puntosPorGoles);
			jugador.setGoles(goles);
			Puntos puntos = new Puntos();
			puntos.setIdEquipo(jugador.getIdEquipo().getIdEquipo());
			puntos.setIdJugador(jugador.getIdJugador());
			puntos.setPuntosJornada(jugador.getPuntosJornada());
			puntos.setNumJornada(numJornada);
			puntos.setPosicion(jugador.getPosicion());
			puntos.setGoles(goles);
			puntos.setNombre(jugador.getNombre());
			MensajeJugadorDTO json = new MensajeJugadorDTO(jugador.getIdJugador(),jugador.getPuntosJornada(), jugador.getGoles());
			ObjectMapper mapper = new ObjectMapper();
			String mensaje = mapper.writeValueAsString(json);
			kafkaProducerService.sendMessage(json);
			puntosRepository.save(puntos);
		}
	}
}
