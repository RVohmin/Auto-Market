package ru.job4j.market.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.market.persistence.Photo;
import ru.job4j.market.persistence.PostCar;
import ru.job4j.market.persistence.User;
import static ru.job4j.market.servlet.IndexServlet.LOGGER;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class MarketHbmStore implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private MarketHbmStore() {
    }

    private static final class Lazy {
        private static final Store INST = new MarketHbmStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public User findUserById(int id) {
        return tx(
                session -> {
                    final Query query = session.createQuery(
                            "from User s where s.id = :fId");
                    query.setParameter("fId", id);
                    User user = (User) query.uniqueResult();
                    return user;
                }
        );
    }

    @Override
    public User findUserByEmail(String email) {
        return tx(session -> {
            Query query = session.createQuery("from User s where s.email = :fEmail");
            query.setParameter("fEmail", email);
            User user = (User) query.uniqueResult();
            if (user == null) {
                return null;
            }
            return user.getEmail().equals(email) ? user : null;
        });
    }

    @Override
    public void saveUser(User user) {
        tx(session -> session.save(user));
    }

    @Override
    public Integer savePhoto(Photo photo) {
        return (Integer) tx(session -> session.save(photo));
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public void savePostCar(PostCar postCar) {
        LOGGER.debug("----------- in savePostCar------------");
        tx(session -> session.save(postCar));
        LOGGER.debug("----------- after savePostCar------------\n{}", postCar);
    }

    @Override
    public Collection<PostCar> findAllPostCar() {
        LOGGER.debug("----------- in findAllPostCar ------------");
        return tx(session -> session.createQuery("from PostCar").list());
    }
    @Override
    public Collection<PostCar> findPostCarByUserId(int id) {
        LOGGER.debug("----------- in findPostCarByUserId ------------");
        return tx(session -> session.createQuery("from PostCar t where t.user.id = " + id).list());
    }

    @Override
    public void changeStatusByPostId(int id, boolean status) {
        LOGGER.debug("----------- in changeStatusByPostId ------------");
        tx(
                session -> {
                    final Query query = session.createQuery(
                            "update PostCar post set post.status = :newDone where post.id = :fId");
                    query.setParameter("fId", id);
                    query.setParameter("newDone", status);
                    query.executeUpdate();
                    return null;
                }
        );

    }
}
