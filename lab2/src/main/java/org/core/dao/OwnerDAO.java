package org.core.dao;

import org.core.models.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class OwnerDAO {
    private SessionFactory sessionFactory;

    public OwnerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Owner get(Long id) {
        Session session = sessionFactory.openSession();
        Owner owner = session.get(Owner.class, id);
        session.close();
        return owner;
    }

    public void save(Owner owner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(owner);
        transaction.commit();
        session.close();
    }

    public void update(Owner owner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(owner);
        transaction.commit();
        session.close();
    }

    public void delete(Owner owner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(owner);
        transaction.commit();
        session.close();
    }
}
