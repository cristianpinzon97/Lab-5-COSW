package edu.eci.cosw.samples;


import edu.eci.cosw.jpa.sample.model.Consulta;
import edu.eci.cosw.jpa.sample.model.Paciente;
import edu.eci.cosw.jpa.sample.model.PacienteId;
import edu.eci.cosw.samples.persistence.PatientsRepository;
import edu.eci.cosw.samples.services.PatientServices;
import edu.eci.cosw.samples.services.ServicesException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ActiveProfiles("application-test.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDataRestApiApplication.class)
@WebAppConfiguration
public class SpringDataRestApiApplicationTests {
    
    @Autowired
    public PatientsRepository pacienteRepository;
    
    @Autowired
    public PatientServices pacienteService;
    
    
    
	@Test
	public void contextLoads() {
	}
        
        @Test
        public void patientLoadTest(){
    
        }
        
        @Test
        public void getPatientOk(int id, String tipoid) {
            Paciente paciente = new  Paciente(new PacienteId(id, tipoid),"Cristian Prueba", new Date(2018));
            pacienteRepository.saveAndFlush(paciente);
            try {
                Assert.assertEquals(pacienteService.getPatient(id, tipoid).getNombre(), paciente.getNombre());
            } catch (ServicesException ex) {
                Logger.getLogger(SpringDataRestApiApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Test
        public void getPatientDoesntExit(int id, String tipoid) {
            try {
                pacienteService.getPatient(id, tipoid);
            } catch (ServicesException ex) {
                Assert.assertSame(ex, ServicesException.class);
                Logger.getLogger(SpringDataRestApiApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Test
        public void topPatientsDontHaveN(int N) {
            Paciente paciente = new  Paciente(new PacienteId(1030, "cc"),"Cristian Prueba", new Date(2018),new HashSet<>(Arrays.asList(new Consulta(new Date(2018), "Resumen"))));
            pacienteRepository.saveAndFlush(paciente);
            Assert.assertSame(new List(),pacienteService.equals(2));
        }

}
