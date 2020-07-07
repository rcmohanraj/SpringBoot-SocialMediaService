package it.mohanrc.socialmedia.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import it.mohanrc.socialmedia.dao.UserDaoService;
import it.mohanrc.socialmedia.exception.UserNotFoundException;
import it.mohanrc.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id) {
        User user = userDaoService.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id-"+id);
        }
        /*HATEOAS implementation to send the links back.
        * for sending links we needs to extend RepresentationModel for
        * our User Pojo, so that we can do add the links
        * WebMvcLinkBuilder static methods are added as static imports
        * */
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        Link link = linkTo.withRel("all-users");
        user.add(link);
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedUser.getId())
                            .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable Integer id) {
        User user = userDaoService.deleteById(id);
        if(user == null) {
            throw new UserNotFoundException("id-"+id);
        }
        return user;
    }
}
