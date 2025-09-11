package com.back_comunio_dinamico.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.back_comunio_dinamico.controllers.ControllerPartido;
import com.back_comunio_dinamico.entities.Jugador;
import com.back_comunio_dinamico.entities.Partido;
import com.back_comunio_dinamico.entities.Resultado;
import com.back_comunio_dinamico.repositories.JornadasAcumuladasRepository;


@RestController
public class JornadasAcumuladasService {

	@Autowired
	JornadasAcumuladasRepository jornadasAcumuladasRepository;
	
	ControllerPartido controllerPartido = new ControllerPartido();
	
	@Transactional
	@PostMapping("/insertPartido")
	public ResponseEntity<Resultado> insertPartido(@RequestBody Partido partido) {
		try {
			//Atributos por defecto
			Resultado result = controllerPartido.generarResultadoPartido(partido);
			Resultado resultadoPuntos = controllerPartido.generarPuntosPartido(partido, result);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
