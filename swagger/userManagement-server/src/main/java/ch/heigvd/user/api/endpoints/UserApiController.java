package ch.heigvd.user.api.endpoints;

import ch.heigvd.user.api.UserApi;
import ch.heigvd.user.api.model.InlineObject;
import ch.heigvd.user.api.model.InlineObject1;
import ch.heigvd.user.api.model.User;
import ch.heigvd.user.entities.UserEntity;
import ch.heigvd.user.repositories.UserRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-06T10:34:47.081Z")

@Controller
public class UserApiController implements UserApi {


    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Void> createUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody User user) {
        UserEntity newUserEntity = toUserEntity(user);
        userRepository.save(newUserEntity);
        String email = newUserEntity.getEmail();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(newUserEntity.getEmail()).toUri();

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<List<User>> getUsers() {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }

        return ResponseEntity.ok(users);
    }

    // to be changed / updated
    public ResponseEntity<List<User>> getUserByName() {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }

        return ResponseEntity.ok(users);
    }


    public ResponseEntity<Void> updateUser(@ApiParam(value = ""  )  @Valid @RequestBody InlineObject user) {

        if(userRepository.updatePassword(user.getPassword(),user.getEmail()) > 0){
            return ResponseEntity.ok().build();
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody InlineObject1 user){
        userRepository.deleteById(user.getEmail());
        return ResponseEntity.ok().build();
    }

    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setPassword(user.getPassword());
        entity.setUsername(user.getUsername());
        entity.setIsAdmin(user.getIsAdmin());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();

        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPassword(entity.getPassword());
        user.setUsername(entity.getUsername());
        user.setIsAdmin(entity.isIsAdmin());
        return user;
    }

}
