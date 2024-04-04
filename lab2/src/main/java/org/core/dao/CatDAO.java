package org.core.dao;

import org.core.models.Cat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CatDAO {
    private SessionFactory sessionFactory;

    public CatDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Cat get(Long id) {
        Session session = sessionFactory.openSession();
        Cat cat = session.get(Cat.class, id);
        session.close();
        return cat;
    }

    public void save(Cat cat) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cat);
        transaction.commit();
        session.close();
    }

    public void update(Cat cat) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cat);
        transaction.commit();
        session.close();
    }

    public void delete(Cat cat) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(cat);
        transaction.commit();
        session.close();
    }
}
