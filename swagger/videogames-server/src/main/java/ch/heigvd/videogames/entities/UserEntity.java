package ch.heigvd.videogames.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class UserEntity implements Serializable {

    @Id
    private String email;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
}
