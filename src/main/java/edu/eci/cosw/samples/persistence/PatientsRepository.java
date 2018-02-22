/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.samples.persistence;

import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2107713
 */
@Service
public interface PatientsRepository extends JpaRepository<Paciente, PacienteId>{
    
    @Query("SELECT p FROM Paciente AS p WHERE p.consultas.size>=:n")
    public  List<Paciente> getPatientsAtLeastNConsults(@Param("n") int n);   
    
}
