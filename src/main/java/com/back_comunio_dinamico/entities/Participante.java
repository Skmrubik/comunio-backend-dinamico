package com.back_comunio_dinamico.entities;

public class Participante {

	private Integer idParticipante;
	
	private String nickname;
	
	private Integer puntosTotales;
	
	private String password;
	
	private Integer puntosJornadaActual;

	public Integer getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Integer idParticipante) {
		this.idParticipante = idParticipante;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getPuntosTotales() {
		return puntosTotales;
	}

	public void setPuntosTotales(Integer puntosTotales) {
		this.puntosTotales = puntosTotales;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPuntosJornadaActual() {
		return puntosJornadaActual;
	}

	public void setPuntosJornadaActual(Integer puntosJornadaActual) {
		this.puntosJornadaActual = puntosJornadaActual;
	}
}
