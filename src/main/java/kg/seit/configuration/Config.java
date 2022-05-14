package kg.seit.configuration;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author seiitbeknarynbaev
 */
public class Config {

    public static EntityManagerFactory createEntityManagerFactory() {


        return Persistence.createEntityManagerFactory("Shop");
    }

}
