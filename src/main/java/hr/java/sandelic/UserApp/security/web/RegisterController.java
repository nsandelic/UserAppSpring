package hr.java.sandelic.UserApp.security.web;


import hr.java.sandelic.UserApp.User.UserCommand;
import hr.java.sandelic.UserApp.User.UserDTO;
import hr.java.sandelic.UserApp.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody final UserCommand command){
        UserDTO newUserDTO = userService.save(command);
        if (newUserDTO == null)
            return new ResponseEntity<UserDTO>(HttpStatus.CONFLICT);
        else return new ResponseEntity<UserDTO>( newUserDTO,  HttpStatus.CREATED );
    }


}
