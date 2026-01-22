package matteobenetazzo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import matteobenetazzo.entities.Concerto;
import matteobenetazzo.entities.Evento;
import matteobenetazzo.entities.GenereConcerto;
import matteobenetazzo.entities.PartitaDiCalcio;
import matteobenetazzo.exceptions.NotFoundException;

import java.util.List;

public class EventoDAO {
    private final EntityManager em;

    public EventoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Evento newEvento) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newEvento);

        transaction.commit();
        System.out.println("Evento salvato con id: " + newEvento.getId());
    }

    public Evento findById(long id) {
        Evento found = em.find(Evento.class, id);
        if (found == null) throw new NotFoundException(String.valueOf(id));
        return found;
    }

    // ===== ESERCIZIO 1 =====

    // Concerti in streaming
    public List<Concerto> getConcertiInStreaming(boolean streaming) {
        TypedQuery<Concerto> query = em.createQuery(
                "SELECT c FROM Concerto c WHERE c.inStreaming = :streaming",
                Concerto.class
        );
        query.setParameter("streaming", streaming);
        return query.getResultList();
    }

    // Concerti per genere
    public List<Concerto> getConcertiPerGenere(GenereConcerto genere) {
        TypedQuery<Concerto> query = em.createQuery(
                "SELECT c FROM Concerto c WHERE c.genere = :genere",
                Concerto.class
        );
        query.setParameter("genere", genere);
        return query.getResultList();
    }

    // ===== ESERCIZIO 2 (NamedQuery) =====

    // Partite vinte in casa
    public List<PartitaDiCalcio> getPartiteVinteInCasa() {
        TypedQuery<PartitaDiCalcio> query =
                em.createNamedQuery("getPartiteVinteInCasa", PartitaDiCalcio.class);
        return query.getResultList();
    }

    // Partite vinte in trasferta
    public List<PartitaDiCalcio> getPartiteVinteInTrasferta() {
        TypedQuery<PartitaDiCalcio> query =
                em.createNamedQuery("getPartiteVinteInTrasferta", PartitaDiCalcio.class);
        return query.getResultList();
    }
}



