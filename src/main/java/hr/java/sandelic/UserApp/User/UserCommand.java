package hr.java.sandelic.UserApp.User;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

public class UserCommand {


    @NotBlank(message = "First name must not be empty")
    private String firstName;
    @NotBlank(message = "Last name must not be empty")
    private String lastName;
    @NotBlank(message = "Username must not be empty")
    private String username;
    @NotBlank(message = "Password must not be empty")
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of birth must be entered")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;
    @NotBlank(message = "Email must not be empty")
    @Email(message =  "Email is not valid")
    private String email;
    @NotBlank(message = "Gender must not be empty")
    private String gender;



    public UserCommand(String firstName, String lastName, String username, LocalDate dob, String password, String email, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        //this.authorities = authorities;
    }

    public UserCommand() {
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

   /* public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }*/
}
