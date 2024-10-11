package com.pe.t2.controller;

import com.pe.t2.dto.ProgramadorDTO;
import com.pe.t2.services.ProgramadorService;
import com.pe.t2.utils.MensajeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pe.t2.entidad.Programador;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/programador")
public class ProgramadorController {

    @Autowired
    private ProgramadorService programadorService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/lista")
    public ResponseEntity<?> listar() throws Exception {
        List<Programador> lista=programadorService.listar();
		if(lista.size()==0) {
			return new ResponseEntity<>(
						MensajeResponse.builder().mensaje("No hay registros")
						.object(null).build(),HttpStatus.OK);
		}
		else {
			List<ProgramadorDTO> lista2=lista.stream().map(
					m->mapper.map(m, ProgramadorDTO.class)).collect(Collectors.toList());			
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Si hay registros")
					.object(lista2).build(),HttpStatus.OK);
		}
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody ProgramadorDTO bean) throws Exception {
        try {
			Programador p=null;
			p=mapper.map(bean, Programador.class);
			p=programadorService.registrar(p);
			ProgramadorDTO pro=mapper.map(p, ProgramadorDTO.class);
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje("Guardado Correctaamente")
					.object(pro).build(),HttpStatus.CREATED);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(
					MensajeResponse.builder().mensaje(e.getMessage())
					.object(null).build(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @GetMapping("/consulta/sueldo/{min}/{max}")
    public ResponseEntity<?> listarPorSueldo(
            @PathVariable("min") Double sueldoMin,
            @PathVariable("max") Double sueldoMax) throws Exception {

        List<ProgramadorDTO> lista = programadorService.listarPorSueldo(sueldoMin, sueldoMax)
                .stream()
                .map(programador -> mapper.map(programador, ProgramadorDTO.class))
                .collect(Collectors.toList());

        if (lista.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder().mensaje("No hay registros").object(null).build(), 
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    MensajeResponse.builder().mensaje("Si hay registros").object(lista).build(), 
                    HttpStatus.OK);
        }
    }
}
