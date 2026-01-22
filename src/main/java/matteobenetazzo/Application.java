package matteobenetazzo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import matteobenetazzo.dao.EventoDAO;
import matteobenetazzo.dao.LocationDAO;
import matteobenetazzo.entities.Concerto;
import matteobenetazzo.entities.GenereConcerto;
import matteobenetazzo.entities.Location;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneEventiPU");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        LocationDAO locationDAO = new LocationDAO(em);
        EventoDAO eventoDAO = new EventoDAO(em);

        // 1) LOCATION
        Location stadio = new Location("Stadio Olimpico", "Roma");
        locationDAO.save(stadio);

        // 2) CONCERTO streaming = true
        Concerto concertoRock = new Concerto(
                "Concerto Rock",
                LocalDate.now(),
                stadio,
                GenereConcerto.ROCK,
                true
        );
        eventoDAO.save(concertoRock);

        // 3) CONCERTO streaming = false
        Concerto concertoPop = new Concerto(
                "Concerto Pop",
                LocalDate.now().plusDays(1),
                stadio,
                GenereConcerto.POP,
                false
        );
        eventoDAO.save(concertoPop);

        // 4) TEST JPQL
        System.out.println("---- CONCERTI IN STREAMING ----");
        eventoDAO.getConcertiInStreaming(true).forEach(c -> System.out.println(c));

        System.out.println("---- CONCERTI ROCK ----");
        eventoDAO.getConcertiPerGenere(GenereConcerto.ROCK).forEach(c -> System.out.println(c));

        em.close();
        emf.close();
    }
}




