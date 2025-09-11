package com.back_comunio_dinamico.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.back_comunio_dinamico.entities.Partido;
import com.back_comunio_dinamico.entities.Resultado;
import com.back_comunio_dinamico.repositories.JornadasAcumuladasRepository;


@RestController
public class JornadasAcumuladasService {

	@Autowired
	JornadasAcumuladasRepository jornadasAcumuladasRepository;
	
	@Transactional
	@PostMapping("/insertPartido")
	public ResponseEntity<Resultado> insertPartido(@RequestBody Partido partido) {
		try {
			//Atributos por defecto
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
			Resultado result = new Resultado(0, 0);
			for (int i = 0; i<golesPartido; i++) {
				int r2 = r.nextInt(golesLocal+golesVisitante);
				if (distribucion.get(r2)==0) {
					result.addGolLocal();
				} else {
					result.addGolVisitante();
				}
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
