/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.Courier;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * JPA implementation for Courier's DAO interface
 * @author Jan Vorcak
 */
public class JPACourierDAO implements CourierDAO {
    
    private EntityManager em;

    public JPACourierDAO(EntityManager em) {
        this.em = em;
    }
    
    public void createCourier(Courier courier) {    
        if(courier == null) {
            throw new NullPointerException("Courier argument can't be null");
        }
        
        em.getTransaction().begin();
        em.persist(courier);
        em.getTransaction().commit();
    }

    public void deleteCourier(Courier courier) {
        if(courier == null) {
            throw new NullPointerException("Courier argument can't be null");
        }
        
        em.getTransaction().begin();
        em.remove(courier);
        em.getTransaction().commit();
    }

    public void updateCourier(Courier courier) {
        if(courier == null) {
            throw new NullPointerException("Courier argument can't be null");
        }
        
        em.getTransaction().begin();
        em.merge(courier);
        em.getTransaction().commit();
    }

    public List<Courier> getAllCouriers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Courier> query = cb.createQuery(Courier.class);
        query.from(Courier.class);
        return em.createQuery(query).getResultList();
    }
    
}
