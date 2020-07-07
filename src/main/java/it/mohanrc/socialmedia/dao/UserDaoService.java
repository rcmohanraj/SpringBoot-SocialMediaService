package it.mohanrc.socialmedia.dao;

import it.mohanrc.socialmedia.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Bob", LocalDate.of(1979, Month.DECEMBER, 1)));
        users.add(new User(2, "Brad", LocalDate.of(1982, Month.MAY, 2)));
        users.add(new User(3, "Simon", LocalDate.of(2007, Month.DECEMBER, 3)));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User savedUser) {
        if(savedUser.getId() == null) {
            savedUser.setId(users.size()+1);
        }
        users.add(savedUser);
        return savedUser;
    }

    public User findOne(Integer id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(Integer id) {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User savedUser = iterator.next();
            if(savedUser.getId() == id) {
                iterator.remove();
                return savedUser;
            }
        }
        return null;
    }
}
