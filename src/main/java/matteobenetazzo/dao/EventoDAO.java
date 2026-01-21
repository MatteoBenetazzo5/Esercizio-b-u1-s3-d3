package matteobenetazzo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import matteobenetazzo.entities.Evento;
import matteobenetazzo.exceptions.NotFoundException;

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
}

