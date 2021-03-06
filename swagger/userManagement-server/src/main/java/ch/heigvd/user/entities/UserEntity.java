package ch.heigvd.user.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * UserEntity
 */
@Entity
public class UserEntity implements Serializable {
  @Id
  private String email;

  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private Boolean isAdmin;


  /**
   * Get email
   * @return email
  **/

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean isIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

}

