package com.project.rest.user;

import com.project.rest.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
        users.add(new User(1, "shivam", LocalDate.of(1997, 12, 8)));
        users.add(new User(2, "kirti", LocalDate.of(1996, 6, 17)));
        users.add(new User(3, "raghav", LocalDate.of(2007, 2, 27)));
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

    @Transactional
    public User addUser(User user) {
        int nextUserId = users.size() + 1;
        user.setId(nextUserId);
        users.add(user);
        return user;
    }

    @Transactional
    public void deleteUserByUserId(int userId) {
        // Check user is present inside the database or not
        Optional<User> result = users.stream().filter(user -> user.getId() == userId).findFirst();

        // if not, throw UserNotFoundException
        if (result.isEmpty())
            throw new UserNotFoundException("userid : " + userId + " not found!");

        // otherwise delete the existing user
        User theUser = result.get();
        users.remove(theUser);
    }
}
