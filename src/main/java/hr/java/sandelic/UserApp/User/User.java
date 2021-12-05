package hr.java.sandelic.UserApp.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( name = "first_name")
    private String firstName;
    @Column( name = "last_name")
    private String lastName;
    @Column( name = "username")
    private String username;
    @Column( name = "password")
    private String password;
    @Column( name = "dob")
    private LocalDate dob;
    @Column( name = "email")
    private String email;
    @Column( name = "gender")
    private String gender;
    @ManyToMany(targetEntity =  Authority.class)
    @JoinTable(
            name ="user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = {@JoinColumn (name = "authority_id")}
    )
    private List<Authority> authorities;


    public User(Long id, String firstName, String lastName, String username, String password, LocalDate dob, String email, String gender, List<Authority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.authorities = authorities;
    }

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
