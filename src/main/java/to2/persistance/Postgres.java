package to2.persistance;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Postgres {
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure("persistance/hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }
}