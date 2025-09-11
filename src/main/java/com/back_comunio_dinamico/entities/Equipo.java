package com.back_comunio_dinamico.entities;


public class Equipo {
	private Integer idEquipo;

	private String nombre;

	private String pathFoto;

	private Float nivel;

	private Integer golesCasa;

	private Integer golesFuera;
	
	public Equipo() {
	}

	public Equipo(Integer idEquipo, String nombre, String pathFoto, Float nivel, Integer golesCasa,
			Integer golesFuera) {
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.pathFoto = pathFoto;
		this.nivel = nivel;
		this.golesCasa = golesCasa;
		this.golesFuera = golesFuera;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public Float getNivel() {
		return nivel;
	}

	public void setNivel(Float nivel) {
		this.nivel = nivel;
	}

	public Integer getGolesCasa() {
		return golesCasa;
	}

	public void setGolesCasa(Integer golesCasa) {
		this.golesCasa = golesCasa;
	}

	public Integer getGolesFuera() {
		return golesFuera;
	}

	public void setGolesFuera(Integer golesFuera) {
		this.golesFuera = golesFuera;
	}
}
