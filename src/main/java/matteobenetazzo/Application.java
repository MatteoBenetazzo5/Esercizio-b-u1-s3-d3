package matteobenetazzo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import matteobenetazzo.dao.EventoDAO;
import matteobenetazzo.dao.LocationDAO;
import matteobenetazzo.dao.PartecipazioneDAO;
import matteobenetazzo.dao.PersonaDAO;
import matteobenetazzo.entities.*;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneEventiPU");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        PersonaDAO personaDAO = new PersonaDAO(em);
        LocationDAO locationDAO = new LocationDAO(em);
        EventoDAO eventoDAO = new EventoDAO(em);
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);

        // 1) LOCATION
        Location sala = new Location("Sala Grande", "Roma");
        locationDAO.save(sala);

        // 2) EVENTO (legato alla location)
        Evento concerto = new Evento("Concerto", LocalDate.now(), sala);
        eventoDAO.save(concerto);

        // 3) PERSONA
        Persona mario = new Persona("Mario", "Rossi", "mario.rossi@mail.it", LocalDate.of(1995, 5, 10), Sesso.M);
        personaDAO.save(mario);

        // 4) PARTECIPAZIONE (persona + evento + stato)
        Partecipazione p1 = new Partecipazione(mario, concerto, StatoPartecipazione.CONFERMATA);
        partecipazioneDAO.save(p1);

        em.close();
        emf.close();
    }
}



