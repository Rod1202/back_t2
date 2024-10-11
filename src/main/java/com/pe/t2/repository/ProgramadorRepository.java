package com.pe.t2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.pe.t2.entidad.Programador;

public interface ProgramadorRepository extends JpaRepository<Programador, Long> {

    @Query("SELECT p FROM Programador p WHERE p.sueldo >= :sueldoMin AND p.sueldo <= :sueldoMax")
    public List<Programador> listarPorSueldo(@Param("sueldoMin") Double sueldoMin, @Param("sueldoMax") Double sueldoMax);
}
