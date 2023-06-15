package com.developersview.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author pranoy.chakraborty
 * @Date 14/06/2023
 */
@RestController
public class UserController {

    @Autowired
    public UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    //GET all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    //GET user by id
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id) {
        User user = userDaoService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("Id : " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    //POST new user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User updatedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //DELETE user
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userDaoService.deleteById(id);
    }

    //POST new user
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @Valid @RequestBody User user) {
        User updatedUser = userDaoService.updateUserById(id, user);
        return user;
    }
}
