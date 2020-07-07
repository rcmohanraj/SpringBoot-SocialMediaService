package it.mohanrc.socialmedia.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@ApiModel(description = "User Info Type")
@Entity
public class User extends RepresentationModel<User> {

    @Id
    @SequenceGenerator(name="user_seq_gen", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(generator = "user_seq_gen")
    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    @ApiModelProperty(notes = "Name should have at least 2 characters")
    private String name;

    @Past(message = "DOB can not be future")
    @ApiModelProperty(notes = "DOB should have past date")
    private LocalDate dob;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(Integer id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
