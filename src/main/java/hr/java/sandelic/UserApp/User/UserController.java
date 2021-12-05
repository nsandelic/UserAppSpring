package hr.java.sandelic.UserApp.User;


import hr.java.sandelic.UserApp.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<UserDTO> getAllUsers(){
        return userService.findAll();
    }


    @GetMapping( path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") final Long id){
        return userService.findById(id)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.FOUND)
                                .body(studentDTO)

                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .build()

                );
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody final UserCommand command){
        UserDTO newUserDTO = userService.save(command);
        if (newUserDTO == null)
            return new ResponseEntity<UserDTO>(HttpStatus.CONFLICT);
        else return new ResponseEntity<UserDTO>( newUserDTO,  HttpStatus.CREATED );
    }


    @DeleteMapping( path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDTO updateUser (@RequestBody User user){
        return userService.updateUser(user);
    }




    @GetMapping("/current-user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO> getCurretUser() {
        return SecurityUtils.getCurrentUserUsername()
                .map(
                        username -> userService.findOneByUsername(username)
                                .map(ResponseEntity::ok)
                                .orElseGet(
                                        ()-> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
                                )
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
                );
    }











}
