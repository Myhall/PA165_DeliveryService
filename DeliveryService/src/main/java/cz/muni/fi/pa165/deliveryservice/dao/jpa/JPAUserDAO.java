package cz.muni.fi.pa165.deliveryservice.dao.jpa;

import cz.muni.fi.pa165.deliveryservice.User;
import cz.muni.fi.pa165.deliveryservice.dao.UserDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public class JPAUserDAO implements UserDAO {

    @Autowired
    private ShaPasswordEncoder passwordEncoder;
    @PersistenceContext
    private EntityManager em;

    public JPAUserDAO() {
    }

    public JPAUserDAO(EntityManager em) {
        if (em == null) {
            throw new NullPointerException("Entity manager is null.");
        }
        this.em = em;
    }

    @Override
    public void create(User user) {
        if (user == null) {
            throw new NullPointerException("User item is null.");
        }
        String password = passwordEncoder.encodePassword(user.getPassword(), user.getUsername());
        user.setPassword(password);
        em.persist(user);
    }

    @Override
    public void remove(User user) {
        if (user == null) {
            throw new NullPointerException("User is null.");
        }

        if (user.getId() == null) {
            throw new IllegalStateException("User ID is null.");
        }

        User toRemove = em.find(User.class, user.getId());
        if (toRemove != null) {
            em.remove(user);
        }
    }

    @Override
    public User update(User user) {
        if (user == null) {
            throw new NullPointerException("User is null.");
        }

        String password = passwordEncoder.encodePassword(user.getPassword(), user.getUsername());
        user.setPassword(password);
        return em.merge(user);
    }

    @Override
    public User findUser(Long id) {
        if (id == null) {
            throw new NullPointerException("ID is null.");
        }

        User result;
        result = em.find(User.class, id);

        return result;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) {
            throw new NullPointerException("username is null");
        }
        if(username.trim().isEmpty()) {
            throw new IllegalArgumentException("username is empty");
        }
        Query query = em.createQuery("SELECT u FROM User u WHERE u.username like :username");
        query.setParameter("username", username);
        User found = (User) query.getSingleResult();
        
        return found;
    }

    @Override
    public List<User> findAll() {
        Query query = em.createQuery("SELECT u FROM User u");
        List<User> results = query.getResultList();

        return results;
    }

    @Override
    public boolean availableUsername(String username) {
        if (username == null) {
            throw new NullPointerException("username is null");
        }

        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException("username is empty");
        }

        Query query = em.createQuery("SELECT u FROM User u WHERE u.username like :username");
        query.setParameter("username", username);

        return query.getResultList().isEmpty();
    }

    @Override
    public boolean isAdmin(User user) {
        if (user == null) {
            throw new NullPointerException("User is null.");
        }
        
        Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :id");
        query.setParameter("id", user.getId());

        User foundUser = (User) query.getSingleResult();
        return foundUser.getRoleAdmin();
    }

    @Override
    public void makeAdmin(User user) {
        if (user == null) {
            throw new NullPointerException("User is null.");
        }
                
        Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :id");
        query.setParameter("id", user.getId());

        User foundUser = (User) query.getSingleResult();
        foundUser.setRoleAdmin(true);
    }
}
