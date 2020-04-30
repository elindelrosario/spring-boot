package com.poc.springboot.restfulwebservices.user;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "User details")
@Entity
public class User {

  @Id
  @GeneratedValue
  private Integer id;

  @ApiModelProperty(notes = "name should be at least 2 characters")
  @Size(min = 2, message = "name should be at least 2 characters")
  @NotBlank(message = "name is required")
  private String name;

  @ApiModelProperty(notes = "birthdate should be in the past")
  @Past(message = "birthdate should be in the past")
  private Date birthdate;

  @OneToMany(mappedBy = "user") // 'user' is field name in Post entity
  private List<Post> posts;

  protected User() {}

  public User(Integer id, String name, Date birthdate) {
    super();
    this.id = id;
    this.name = name;
    this.birthdate = birthdate;
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

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  @Override
  public String toString() {
    return String.format("User [id=%s, name=%s, birthdate=%s]", id, name, birthdate);
  }

}
