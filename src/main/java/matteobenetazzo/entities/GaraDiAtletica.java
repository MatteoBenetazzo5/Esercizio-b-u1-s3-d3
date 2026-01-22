package matteobenetazzo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("GaraDiAtletica")
public class GaraDiAtletica extends Evento {

    public GaraDiAtletica() {
    }

    public GaraDiAtletica(String titolo, LocalDate dataEvento, Location location) {
        super(titolo, dataEvento, location);
    }

    @Override
    public String toString() {
        return "GaraDiAtletica{} " + super.toString();
    }
}

