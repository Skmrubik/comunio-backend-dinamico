package com.back_comunio_dinamico.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.back_comunio_dinamico.entities.Jugador;
import com.back_comunio_dinamico.entities.Partido;
import com.back_comunio_dinamico.entities.Resultado;

public class ControllerPartido {

	
	public ControllerPartido() {
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
	
	public Resultado generarPuntosPartido(Partido partido, Resultado result) {
		List<Jugador> jugadoresLocales = partido.getJugadoresLocales();
		List<Jugador> jugadoresVisitantes = partido.getJugadoresVisitantes();
		
		List<Integer> probabilidadGolesLocal = new ArrayList<>();
		for	(Jugador jugador:jugadoresLocales) {
			for(int i=0; i< jugador.getProbGol(); i++) {
				probabilidadGolesLocal.add(jugador.getIdJugador());
			}
		}
		List<Integer> goleadoresLocal = new ArrayList<>();
		for (int i= 0; i<result.getResultadoLocal(); i++) {
			Random r= new Random();
			int r1 = r.nextInt(probabilidadGolesLocal.size());
			goleadoresLocal.add(probabilidadGolesLocal.get(r1));
		}
		for	(Jugador jugador:jugadoresLocales) {
			int goles = getGolesJugador(goleadoresLocal, jugador.getIdJugador());
			int puntosPorGoles = getPuntosPorGoles(jugador, goles);
			Random random = new Random();
			
			float media = jugador.getNivel();
			float desviacionEstandar = 1.6F;
			int puntosPartido = (int) Math.round((media+goles) + (desviacionEstandar * random.nextGaussian()));
			jugador.setPuntosJornada(puntosPartido + puntosPorGoles);
			jugador.setGoles(goles);
		}
		
		List<Integer> probabilidadGolesVisitantes = new ArrayList<>();
		for	(Jugador jugador:jugadoresVisitantes) {
			for(int i=0; i< jugador.getProbGol(); i++) {
				probabilidadGolesVisitantes.add(jugador.getIdJugador());
			}
		}
		
		List<Integer> goleadoresVisitantes = new ArrayList<>();
		for (int i= 0; i<result.getResultadoVisitante(); i++) {
			Random r= new Random();
			int r1 = r.nextInt(probabilidadGolesVisitantes.size());
			goleadoresVisitantes.add(probabilidadGolesVisitantes.get(r1));
		}
		for	(Jugador jugador:jugadoresVisitantes) {
			int goles = getGolesJugador(goleadoresVisitantes, jugador.getIdJugador());
			int puntosPorGoles = getPuntosPorGoles(jugador, goles);
			Random random = new Random();
			
			float media = jugador.getNivel();
			float desviacionEstandar = 1.6F;
			int puntosPartido = (int) Math.round(media+(goles) + (desviacionEstandar * random.nextGaussian()));
			jugador.setPuntosJornada(puntosPartido + puntosPorGoles);
			jugador.setGoles(goles);
		}
		
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
}
