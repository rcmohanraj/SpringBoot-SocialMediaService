package it.mohanrc.socialmedia.persistence;

import it.mohanrc.socialmedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepoistory extends JpaRepository<Post, Integer> {


}
