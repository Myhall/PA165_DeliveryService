package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.dto.UserDTO;

/**
 *
 * @author Filip Volner <volner@mail.muni.cz>
 */
public interface UserService {
    /**
     * Creates new user.
     * 
     * @param user User's DTO
     * @return UserDTO created user data transfer object.
     */
    UserDTO createUser(UserDTO user);

    /**
     * Deletes user.
     * 
     * @param user object to be removed
     */
    void deleteUser(UserDTO user);

    /**
     *  Update user.
     * 
     * @param user object to be updated
     */
    UserDTO updateUser(UserDTO user);

    /**
     * Returns UserDTO object with given ID
     * 
     * @param id return UserDTO with given ID
     * @return UserDTO object
     */
    UserDTO findUser(Long id);

    /**
     * Searches and returns UserDTO entity with
     * provided username.
     * 
     * @param username find UserDTO with given username
     * @return UserDTO with given username
     */
    UserDTO findByUsername(String username);

    /**
     * Check for available username.
     * Username is unique parameter in User entity.
     * 
     * @return true if username is free for use
     */
    boolean availableUsername(String username);

    /**
     * Check whether user has administrator privileges
     * 
     * @param user to be checked for administrator privileges
     * @return true if user is administrator
     */
    boolean isAdmin(UserDTO user);

    /**
     * Make user an administrator
     * 
     * @param user to have administrator privileges added
     */
    void makeAdmin(UserDTO user);
}
