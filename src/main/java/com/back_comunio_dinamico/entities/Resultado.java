package com.back_comunio_dinamico.entities;

import java.util.List;

public class Resultado {
	Integer resultadoLocal;
	Integer resultadoVisitante;
	List<Jugador> jugadoresLocales;
	List<Jugador> jugadoresVisitantes;
	Integer numJornada;

	public Resultado(Integer resultadoLocal, Integer resultadoVisitante, List<Jugador> jugadoresLocales,
			List<Jugador> jugadoresVisitantes, Integer numJornada) {
		this.resultadoLocal = resultadoLocal;
		this.resultadoVisitante = resultadoVisitante;
		this.jugadoresLocales = jugadoresLocales;
		this.jugadoresVisitantes = jugadoresVisitantes;
		this.numJornada = numJornada;
	}

	public Resultado() {
	}

	public Integer getResultadoLocal() {
		return resultadoLocal;
	}
	
	public void setResultadoLocal(Integer resultadoLocal) {
		this.resultadoLocal = resultadoLocal;
	}
	
	public Integer getResultadoVisitante() {
		return resultadoVisitante;
	}
	
	public void setResultadoVisitante(Integer resultadoVisitante) {
		this.resultadoVisitante = resultadoVisitante;
	}
	
	public void addGolLocal() {
		this.resultadoLocal++;
	}

	public void addGolVisitante() {
		this.resultadoVisitante++;
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
