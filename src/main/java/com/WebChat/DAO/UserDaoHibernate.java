package com.WebChat.DAO;

import com.WebChat.Entity.User;
import com.WebChat.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class UserDaoHibernate implements UserDao {

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
            System.err.println(e);
        }
        session.getTransaction().commit();
        return usr;
    }

    @Override
    public void saveUser() {
    }
}
