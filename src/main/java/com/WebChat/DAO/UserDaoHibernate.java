package com.WebChat.DAO;

import com.WebChat.Entity.User;
import com.WebChat.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
class UserDaoHibernate implements UserDao {

    static final Logger logger = Logger.getLogger(MessageDaoHibernate.class);

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User getUserByName(String loginname) {
        Session session = HibernateUtil.getOrOpenSession();
        User usr = null;

        try {
        session.beginTransaction();
        Query query = session.createQuery("from User u where u.name=:loginname",User.class);
        query.setParameter("loginname",loginname);
        usr = (User)query.getSingleResult();
        }catch(NoResultException e) {
            logger.error(e);
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        return usr;
    }

    @Override
    public void saveUser(User user) {
        Session session = HibernateUtil.getOrOpenSession();
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

        }catch (Exception e) {
            logger.error(e);
            session.getTransaction().rollback();
        }
    }
}
