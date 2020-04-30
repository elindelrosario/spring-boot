package com.poc.springboot.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

  private static List<User> users = new ArrayList<>();

  private int usersCount = 3;

  static {
    users.add(new User(1, "Elin", new Date()));
    users.add(new User(2, "Andreana", new Date()));
    users.add(new User(3, "EA", new Date()));
  }

  public List<User> findAll() {
    return users;
  }

  public User saveUser(User user) {
    if (user.getId() == null) {
      user.setId(++usersCount);
    }

    users.add(user);
    return user;
  }

  public User findOne(int id) {
    return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
  }

  public User deleteById(int id) {
    User user = findOne(id);
    users.remove(user);
    return user;
  }

}
