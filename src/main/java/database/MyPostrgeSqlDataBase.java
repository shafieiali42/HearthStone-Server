package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class MyPostrgeSqlDataBase implements DataBase {


    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
//        PrintStream err = System.err;
//        try {
////            PrintStream printStream = new PrintStream(new File("./l.txt"));
////            System.setErr(printStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        System.setErr(err);
        return sessionFactory;
    }





    @Override
    public void save(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public <T> T fetch(Class<T> tClass, Serializable id) {
        Session session = sessionFactory.openSession();
        T t = session.get(tClass, id);
        session.close();
        return t;
    }

    @Override
    public void update(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public <E> List<E> fetchAll(Class<E> entity) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entity);
        Root<E> eRoot = criteriaQuery.from(entity);
        criteriaQuery.select(eRoot);
        TypedQuery<E> typedQuery = session.createQuery(criteriaQuery);
        List<E> list = typedQuery.getResultList();
        session.close();
        return list;
    }
}
