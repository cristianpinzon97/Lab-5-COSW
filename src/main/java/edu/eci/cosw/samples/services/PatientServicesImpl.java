/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.samples.services;

import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import edu.eci.cosw.samples.persistence.PatientsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2107713
 */
@Service
public class PatientServicesImpl implements PatientServices {

    @Autowired
    public PatientsRepository patientsRepository;

    @Override
    public Paciente getPatient(int id, String tipoid) throws ServicesException {
        return patientsRepository.getOne(new PacienteId(id, tipoid));
    }

    @Override
    public List<Paciente> topPatients(int n) throws ServicesException {
        return patientsRepository.getPatientsAtLeastNConsults(n);
    }
    
}
