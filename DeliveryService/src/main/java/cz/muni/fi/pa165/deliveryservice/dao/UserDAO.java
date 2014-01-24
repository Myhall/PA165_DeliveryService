package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.User;
import java.util.List;

/**
 * DAO interface for User entity.
 * 
 * @author Filip Volner <volner@mail.muni.cz>
 */
public interface UserDAO {
    /**
     * Store new delivery item to database. ID is generated automatically.
     *
     * @param user
     */
    void create(User user);

    /**
     * Remove user from database.
     *
     * @param user - User type parameter to be removed
     */
    void remove(User user);

    /**
     * Update user in database.
     *
     * @param user - User type parameter to be updated
     */
    User update(User user);

    /**
     * Method finds order in database using user id.
     *
     * @param id - unique identification Long number
     * @return user
     */
    User findUser(Long id);

    /**
     *Find user with given username
     *
     * @param username find user with given username
     * @return user with given username
     */
    User findByUsername(String username);
    
    /**
     * Find all users in database.
     *
     * @return Collection of all users
     */
    List<User> findAll();

    /**
     * Check for username occurrence in database.
     *
     * @param username - User's login name
     * @return true if user with given username is in db, false otherwise
     */
    boolean availableUsername(String username);

    /**
     * Check if given user has administrator privileges.
     *
     * @param user - Checked user.
     * @return true if user has administrator privileges, false otherwise.
     */
    boolean isAdmin(User user);

    /**
     * Gives user administrator privileges.
     *
     * @param user - User which is to receive administrator privileges.
     */
    void makeAdmin(User user);
}
