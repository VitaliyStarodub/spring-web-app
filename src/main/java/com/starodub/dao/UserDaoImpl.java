package com.starodub.dao;

import com.starodub.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    /*@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO USERS (EMAIL, PASSWORD, TOKEN, FIRST_NAME, LAST_NAME)" +
                " VALUES (?, ?, ?, ?, ?)",
                user.getEmail(),
                user.getPassword(),
                user.getToken(),
                user.getFirstName(),
                user.getLastName());
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT ID, EMAIL, PASSWORD, TOKEN, FIRST_NAME, LAST_NAME" +
                " FROM USERS WHERE EMAIL = ?", new Object[] {email}, new BeanPropertyRowMapper<>(User.class)));
    }*/

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        User user = (User) sessionFactory.getCurrentSession()
                .createQuery("from User u join fetch u.roles where email =:email")
                .setParameter("email", email)
                .uniqueResult();

        return Optional.ofNullable(user);
    }
}
