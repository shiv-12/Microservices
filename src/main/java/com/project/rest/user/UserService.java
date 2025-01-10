package com.project.rest.user;

import com.project.rest.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
        users.add(new User(1, "shivam"));
        users.add(new User(2, "kirti"));
        users.add(new User(3, "raghav"));
    }


    public List<User> getUsers() {
        return users;
    }

    public User getUserByUserId(int userid) {
        Optional<User> result = users.stream().filter(user -> user.getId() == userid).findFirst();
        if (result.isEmpty())
            throw new UserNotFoundException("User ID : " + userid + ", Not Found!");
        return result.get();
    }

    public User addUser(User user) {
        int nextUserId = users.size() + 1;
        user.setId(nextUserId);
        users.add(user);
        return user;
    }
}
