package it.mohanrc.socialmedia.persistence;

import it.mohanrc.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoistory extends JpaRepository<User, Integer> {


}
