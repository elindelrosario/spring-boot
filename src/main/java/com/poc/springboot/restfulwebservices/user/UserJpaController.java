package com.poc.springboot.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJpaController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PostRepository postRepository;

  @GetMapping("/jpa/users")
  public List<User> retrieveAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/jpa/users/{id}")
  public Resource<User> retrieveUser(@PathVariable int id) {
    Optional<User> user = userRepository.findById(id);

    if (!user.isPresent()) {
      throw new UserNotFoundException("id-" + id);
    }

    // HATEOAS
    Resource<User> resource = new Resource<>(user.get());
    ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    resource.add(linkTo.withRel("all-users"));

    return resource;
  }

  @PostMapping("/jpa/users")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    User savedUser = userRepository.save(user);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedUser.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/jpa/users/{id}")
  public void deleteUser(@PathVariable int id) {
    userRepository.deleteById(id);
  }

  @GetMapping("/jpa/users/{id}/posts")
  public List<Post> retrieveAllUserPosts(@PathVariable int id) {
    Optional<User> user = userRepository.findById(id);

    if (!user.isPresent()) {
      throw new UserNotFoundException("id-" + id);
    }

    return user.get().getPosts();
  }

  @PostMapping("/jpa/users/{id}/posts")
  public ResponseEntity<User> createPost(@PathVariable int id, @RequestBody Post post) {
    Optional<User> user = userRepository.findById(id);

    if (!user.isPresent()) {
      throw new UserNotFoundException("id-" + id);
    }

    post.setUser(user.get());
    postRepository.save(post);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(post.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

}
