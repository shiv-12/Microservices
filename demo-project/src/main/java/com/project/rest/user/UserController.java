package com.project.rest.user;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private final MessageSource messageSource;
    private final UserService userService;

    @Autowired
    public UserController(MessageSource messageSource, UserService userService) {
        this.messageSource = messageSource;
        this.userService = userService;
    }

    // Welcome Message
    @GetMapping(value = "/message", headers = "version=1")
    public String getMessageForVersion1() {
        return "Hello";
    }

    @GetMapping(value = "/message", headers = "version=2")
    public String getMessageForVersion2() {
        return messageSource.getMessage("welcome.message",
                null,
                "Hello",
                LocaleContextHolder.getLocale()
        );
    }


    // GET REQUESTS
    @GetMapping()
    public MappingJacksonValue getUsers() {
        // Creating the filter (apart from dob everything should return)
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(
                "customFilter",          // filter ID
                SimpleBeanPropertyFilter.serializeAllExcept("dob")
        );

        // Setting the filter
        List<User> users = userService.getUsers();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/{userid}")
    public MappingJacksonValue getUserById(@PathVariable int userid) {

        // Creating the filter (Name should only be return)
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(
                "customFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("name")
        );

        // Setting the filter
        User user = userService.getUserByUserId(userid);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }


    // POST REQUESTS
    @PostMapping()
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{userId}")
    public String deleteUserByUserId(@PathVariable int userId) {
        userService.deleteUserByUserId(userId);
        return "User Deleted Successfully!";
    }


}
