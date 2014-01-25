/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.User;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation for Courier's DAO interface
 * @author Jan Vorcak
 */
@Repository
public class JPACourierDAO implements CourierDAO {
    
    
    @PersistenceContext
    private EntityManager em;

    public JPACourierDAO() {}
    
    public JPACourierDAO(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void createCourier(Courier courier) {   
        if(courier == null) {
            throw new NullPointerException("Courier argument can't be null");
        }
        if(courier.getId() != null) {
            throw new IllegalStateException("Trying to create courier with id assigned");
        }
        
        em.persist(courier);
    }

    @Override
    public void deleteCourier(Courier courier) {
        if(courier == null) {
            throw new NullPointerException("Courier argument can't be null");
        }
        
        if(courier.getId() == null) {
            throw new IllegalStateException("Trying to delete courier with no id assigned");
        }
        
        Courier toRemove = em.find(Courier.class, courier.getId());
        
        em.remove(toRemove);
    }

    @Override
    public Courier updateCourier(Courier courier) {
        if(courier == null) {
            throw new NullPointerException("Courier argument can't be null");
        }
        
        return em.merge(courier);
    }

    @Override
    public List<Courier> getAllCouriers(boolean include_deleted) {
        String q = "";
        if(!include_deleted) {
            q = " WHERE c.active = true";
        }
        Query query = em.createQuery("SELECT c FROM Courier c" + q);
        return query.getResultList();
    }
    
    @Override
    public List<Courier> getAllCouriers() {
        return getAllCouriers(true);
    }   

    @Override
    public Courier findCourier(Long id) {
        if (id == null) {
            throw new NullPointerException("ID is null.");
        }
        return em.find(Courier.class, id);
    }

    @Override
    public User getUser(Courier courier) {
        Query query;
        query = em.createQuery("SELECT u FROM User u WHERE u.id = :id ");
        query.setParameter("id", courier.getUser().getId());

        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public Courier findByUsername(String username) {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.user.username = :username");
        query.setParameter("username", username);

        Courier courier = (Courier) query.getSingleResult();
        return courier;
    }

}
