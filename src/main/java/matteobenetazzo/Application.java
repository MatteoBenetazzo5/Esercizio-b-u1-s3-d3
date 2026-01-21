package matteobenetazzo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NOME_PU");
        EntityManager em = emf.createEntityManager();

        System.out.println("Connessione OK!");

        em.close();
        emf.close();
    }
}

