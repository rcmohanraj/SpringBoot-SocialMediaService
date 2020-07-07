package it.mohanrc.socialmedia.dao;

import it.mohanrc.socialmedia.exception.UserNotFoundException;
import it.mohanrc.socialmedia.model.Post;
import it.mohanrc.socialmedia.model.User;
import it.mohanrc.socialmedia.persistence.PostRepoistory;
import it.mohanrc.socialmedia.persistence.UserRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoDBService {

    @Autowired
    private UserRepoistory userRepoistory;

    @Autowired
    private PostRepoistory postRepoistory;

    public List<User> findAll() {
        return userRepoistory.findAll();
    }

    public User save(User savedUser) {
        userRepoistory.save(savedUser);
        return savedUser;
    }

    public User findOne(Integer id) {
        Optional<User> user = userRepoistory.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User "+id+" Not Found");
        }
    }

    public User deleteById(Integer id) {
        Optional<User> user = userRepoistory.findById(id);
        if(user.isPresent()) {
            userRepoistory.deleteById(id);
            return user.get();
        } else {
            throw new UserNotFoundException("User "+id+" Not Found");
        }
    }

    public List<Post> findUserPosts(Integer id) {
        Optional<User> user = userRepoistory.findById(id);
        if(user.isPresent()) {
            return user.get().getPosts();
        } else {
            throw new UserNotFoundException("User "+id+" Not Found");
        }
    }

    public Post createPost(Integer id, Post post) {
        Optional<User> user = userRepoistory.findById(id);
        if(user.isPresent()) {
            post.setUser(user.get());
            return postRepoistory.save(post);
        } else {
            throw new UserNotFoundException("User "+id+" Not Found");
        }
    }

}
