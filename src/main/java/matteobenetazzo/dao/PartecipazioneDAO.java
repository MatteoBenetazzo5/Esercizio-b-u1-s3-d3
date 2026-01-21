package matteobenetazzo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import matteobenetazzo.entities.Partecipazione;
import matteobenetazzo.exceptions.NotFoundException;

import java.util.UUID;

public class PartecipazioneDAO {
    private final EntityManager em;

    public PartecipazioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Partecipazione newPartecipazione) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newPartecipazione);

        transaction.commit();
        System.out.println("Partecipazione salvata con id: " + newPartecipazione.getId());
    }

    public Partecipazione findById(String id) {
        Partecipazione found = em.find(Partecipazione.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }
}
