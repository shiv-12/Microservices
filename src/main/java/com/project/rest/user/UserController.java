package com.project.rest.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET REQUESTS
    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userid}")
    public User getUserById(@PathVariable int userid) {
        return userService.getUserByUserId(userid);
    }

    // POST REQUESTS
    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);

        // Adding Redirection URL Inside the Response (Header >> location)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() // gets current request path
                .path("/{id}")
                .buildAndExpand(savedUser.getId())  // append newly created userid
                .toUri();                           // convert it to the URI

        return ResponseEntity.created(location).build();    // .created (for response code 201)
    }


}
