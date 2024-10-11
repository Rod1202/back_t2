package com.pe.t2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.t2.entidad.Programador;
import com.pe.t2.repository.ProgramadorRepository;

@Service
public class ProgramadorService {
    @Autowired
    private ProgramadorRepository repo;
    public Programador registrar (Programador bean) throws Exception {
        return repo.save(bean);
    }
    public List<Programador> listar () throws Exception {
        return repo.findAll();
    }

    public List<Programador> listarPorSueldo (Double sueldoMin,Double sueldoMax) throws Exception {
        return repo.listarPorSueldo(sueldoMin,sueldoMax);
}
}