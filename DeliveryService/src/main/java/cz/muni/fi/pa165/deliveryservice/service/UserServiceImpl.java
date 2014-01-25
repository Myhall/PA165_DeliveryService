/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.User;
import cz.muni.fi.pa165.deliveryservice.dao.UserDAO;
import cz.muni.fi.pa165.deliveryservice.dto.UserDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bufo
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDto) {
        if (userDto == null) {
            throw new NullPointerException("user is null");
        }
        User user = mapper.map(userDto, User.class);
        userDao.create(user);
        return mapper.map(user, UserDTO.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
            + "(hasRole('ROLE_USER') and principal.username == #userDto.username)")
    @Transactional
    @Override
    public void deleteUser(UserDTO userDto) {
        if (userDto == null) {
            throw new NullPointerException("user is null");
        }
        if (userDto.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }

        User user = mapper.map(userDto, User.class);
        userDao.update(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or " + "(hasRole('ROLE_USER') and principal.username == #userDto.username)")
    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDto) {
        if (userDto == null) {
            throw new NullPointerException("user is null");
        }
        User user = mapper.map(userDto, User.class);
        return mapper.map(userDao.update(user), UserDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO findUser(Long id) {
        if (id == null) {
            throw new NullPointerException("id");
        }
        User user = userDao.findUser(id);
        if (user == null) {
            return null;
        }

        return mapper.map(user, UserDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO findByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username is null or empty");
        }
        User user = userDao.findByUsername(username);

        return mapper.map(user, UserDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean availableUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username is null");
        }
        if (username.equals("") || username.equals(" ")) {
            throw new IllegalArgumentException("username is empty");
        }

        return userDao.availableUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isAdmin(UserDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        User user2 = mapper.map(user, User.class);

        return userDao.isAdmin(user2);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void makeAdmin(UserDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        User user2 = mapper.map(user, User.class);
        userDao.makeAdmin(user2);
    }
}