package matteobenetazzo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import matteobenetazzo.entities.Persona;
import matteobenetazzo.exceptions.NotFoundException;

import java.util.UUID;

public class PersonaDAO {
    private final EntityManager em;

    public PersonaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Persona newPersona) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newPersona);

        transaction.commit();
        System.out.println("Persona salvata con id: " + newPersona.getId());
    }

    public Persona findById(String id) {
        Persona found = em.find(Persona.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }
}
