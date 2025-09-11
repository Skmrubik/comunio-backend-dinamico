package com.back_comunio_dinamico.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jornadas_acumuladas")
public class JornadasAcumuladas {
	
	@Id
	private String idJornadaAcumulada;
	private Integer numeroJornada;
	private Integer idEquipo1;
	private Integer idEquipo2;
	private Integer golesEquipo1;
	private Integer golesEquipo2;
	
	
	public JornadasAcumuladas() {
	}

	public String getIdJornadaAcumulada() {
		return idJornadaAcumulada;
	}
	public void setIdJornadaAcumulada(String idJornadaAcumulada) {
		this.idJornadaAcumulada = idJornadaAcumulada;
	}
	public Integer getNumeroJornada() {
		return numeroJornada;
	}
	public void setNumeroJornada(Integer numeroJornada) {
		this.numeroJornada = numeroJornada;
	}
	public Integer getIdEquipo1() {
		return idEquipo1;
	}
	public void setIdEquipo1(Integer idEquipo1) {
		this.idEquipo1 = idEquipo1;
	}
	public Integer getIdEquipo2() {
		return idEquipo2;
	}
	public void setIdEquipo2(Integer idEquipo2) {
		this.idEquipo2 = idEquipo2;
	}
	public Integer getGolesEquipo1() {
		return golesEquipo1;
	}
	public void setGolesEquipo1(Integer golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}
	public Integer getGolesEquipo2() {
		return golesEquipo2;
	}
	public void setGolesEquipo2(Integer golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}

}
