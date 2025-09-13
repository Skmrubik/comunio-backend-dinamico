package com.back_comunio_dinamico.entities;

public class Jugador {
	private Integer idJugador;
	
	private String nombre;
	
	private Float puntosTotales;
	
	private Float puntosMedia;
	
	private Integer puntosJornada;
	
	private String pathFoto;
	
	private Equipo idEquipo;
	
	private Participante idParticipante;

	private Integer posicion;

	private Boolean titular;

	private Float nivel;

	private Float probGol;
	
	private Integer goles;

	public Integer getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPuntosTotales() {
		return puntosTotales;
	}

	public void setPuntosTotales(Float puntosTotales) {
		this.puntosTotales = puntosTotales;
	}

	public Float getPuntosMedia() {
		return puntosMedia;
	}

	public void setPuntosMedia(Float puntosMedia) {
		this.puntosMedia = puntosMedia;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public Equipo getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Equipo idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Participante getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Participante idParticipante) {
		this.idParticipante = idParticipante;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Boolean getTitular() {
		return titular;
	}

	public void setTitular(Boolean titular) {
		this.titular = titular;
	}

	public Float getNivel() {
		return nivel;
	}

	public void setNivel(Float nivel) {
		this.nivel = nivel;
	}

	public Float getProbGol() {
		return probGol;
	}

	public void setProbGol(Float probGol) {
		this.probGol = probGol;
	}

	public Integer getPuntosJornada() {
		return puntosJornada;
	}

	public void setPuntosJornada(Integer puntosJornada) {
		this.puntosJornada = puntosJornada;
	}

	public Integer getGoles() {
		return goles;
	}

	public void setGoles(Integer goles) {
		this.goles = goles;
	}
	
}
