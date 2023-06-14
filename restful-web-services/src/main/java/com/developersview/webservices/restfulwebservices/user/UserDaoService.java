package com.developersview.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pranoy.chakraborty
 * @Date 14/06/2023
 */
@Component
public class UserDaoService {

    private static final List<User> userList = new ArrayList<>();
    private static int usersCount = 0;

    static {
        userList.add(new User(++usersCount, "Pranoy", LocalDate.now().minusYears(24)));
        userList.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(42)));
        userList.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(36)));
        userList.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(26)));
    }

    public List<User> findAll() {
        return userList;
    }

    public User findById(int id) {
        return userList
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User save(User user){
        user.setId(++usersCount);
        userList.add(user);
        return user;
    }
}
