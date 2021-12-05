package hr.java.sandelic.UserApp.User;

import java.time.LocalDate;
import java.util.Set;

public class UserDTO {


    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    //private String password;
    private LocalDate dob;
    private String email;
    private String gender;
    private Set<String> authorities;


    public UserDTO(Long id, String firstName, String lastName, String username, LocalDate dob, String email, String gender,Set<String> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
       // this.password = password;
        this.username = username;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.authorities = authorities;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   /* public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "UserDTO {" +
                "First name = " + firstName +
                ", Last name = " + lastName +
                ", Username = " + username +
                ", Date of birth = " + dob +
                ", Email = " + email +
                ", Gender = " + gender +
                '}';
    }

}
