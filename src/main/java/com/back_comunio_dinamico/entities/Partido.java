package com.back_comunio_dinamico.entities;

import java.util.List;

public class Partido {
	
	Equipo equipoLocal;
	Equipo equipoVisitante;
	List<Jugador> jugadoresLocales;
	List<Jugador> jugadoresVisitantes;
	Integer numJornada;
	
	public Partido() {
	}
	
	public Partido(Equipo equipoLocal, Equipo equipoVisitante, List<Jugador> jugadoresLocales,
			List<Jugador> jugadoresVisitantes, Integer numJornada) {
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.jugadoresLocales = jugadoresLocales;
		this.jugadoresVisitantes = jugadoresVisitantes;
		this.numJornada = numJornada;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}
	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}
	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}
	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}
	
	public List<Jugador> getJugadoresLocales() {
		return jugadoresLocales;
	}

	public void setJugadoresLocales(List<Jugador> jugadoresLocales) {
		this.jugadoresLocales = jugadoresLocales;
	}

	public List<Jugador> getJugadoresVisitantes() {
		return jugadoresVisitantes;
	}

	public void setJugadoresVisitantes(List<Jugador> jugadoresVisitantes) {
		this.jugadoresVisitantes = jugadoresVisitantes;
	}

	public Integer getNumJornada() {
		return numJornada;
	}

	public void setNumJornada(Integer numJornada) {
		this.numJornada = numJornada;
	}
	
}
