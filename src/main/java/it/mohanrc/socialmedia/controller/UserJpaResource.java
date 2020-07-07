package it.mohanrc.socialmedia.controller;

import it.mohanrc.socialmedia.dao.UserDaoDBService;
import it.mohanrc.socialmedia.dao.UserDaoService;
import it.mohanrc.socialmedia.exception.UserNotFoundException;
import it.mohanrc.socialmedia.model.Post;
import it.mohanrc.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserJpaResource {

    @Autowired
    private UserDaoDBService daoDBService;

    @GetMapping("jpa/users")
    public List<User> retrieveAllUsers() {
        return daoDBService.findAll();
    }

    @GetMapping("jpa/users/{id}")
    public User retrieveUser(@PathVariable Integer id) {
        return daoDBService.findOne(id);
    }

    @PostMapping("jpa/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user) {
        User savedUser = daoDBService.save(user);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedUser.getId())
                            .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("jpa/users/{id}")
    public User deleteUser(@PathVariable Integer id) {
        return daoDBService.deleteById(id);
    }

    @GetMapping("jpa/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable Integer id) {
        return daoDBService.findUserPosts(id);
    }

    @PostMapping("jpa/users/{id}/posts")
    public ResponseEntity createPost(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Post savePost = daoDBService.createPost(id, post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savePost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
