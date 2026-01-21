package matteobenetazzo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import matteobenetazzo.entities.Location;
import matteobenetazzo.exceptions.NotFoundException;

public class LocationDAO {
    private final EntityManager em;

    public LocationDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Location newLocation) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newLocation);

        transaction.commit();
        System.out.println("Location salvata con id: " + newLocation.getId());
    }

    public Location findById(long id) {
        Location found = em.find(Location.class, id);
        if (found == null) throw new NotFoundException(String.valueOf(id));
        return found;
    }
}

