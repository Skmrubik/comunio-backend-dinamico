package com.back_comunio_dinamico.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "puntos")
public class Puntos {
	
	@Id
	private String idPuntos;
	private Integer idJugador;
	private String nombre;
	private Integer puntosJornada;
	private Integer goles;
	private Integer numJornada;
	private Integer idEquipo;
	private Integer posicion;
	
	public String getIdPuntos() {
		return idPuntos;
	}
	public void setIdPuntos(String idPuntos) {
		this.idPuntos = idPuntos;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getIdJugador() {
		return idJugador;
	}
	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
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
	public Integer getNumJornada() {
		return numJornada;
	}
	public void setNumJornada(Integer numJornada) {
		this.numJornada = numJornada;
	}
	public Integer getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}
	public Integer getPosicion() {
		return posicion;
	}
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}
}
