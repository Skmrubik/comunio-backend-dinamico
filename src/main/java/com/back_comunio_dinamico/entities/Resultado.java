package com.back_comunio_dinamico.entities;

public class Resultado {
	Integer resultadoLocal;
	Integer resultadoVisitante;
	
	public Resultado(Integer resultadoLocal, Integer resultadoVisitante) {
		this.resultadoLocal = resultadoLocal;
		this.resultadoVisitante = resultadoVisitante;
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
}
