package by.it.academy.repositories.hiber.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Realization od the giving {@link SessionFactory}.
 *
 * @author Maxim Zhevnov
 */
public class HibernateUtils {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    /**
     * Creates a new {@link SessionFactory}.
     *
     * @return {@link SessionFactory}
     */
    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    /**
     * Returns {@link SessionFactory}.
     *
     * @return {@link SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
