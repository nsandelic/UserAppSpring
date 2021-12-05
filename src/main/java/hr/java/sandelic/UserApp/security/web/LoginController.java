package hr.java.sandelic.UserApp.security.web;


import hr.java.sandelic.UserApp.User.UserCommand;
import hr.java.sandelic.UserApp.User.UserDTO;
import hr.java.sandelic.UserApp.User.UserService;
import hr.java.sandelic.UserApp.security.jwt.JwtFilter;
import hr.java.sandelic.UserApp.security.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserService userService;

    public LoginController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate(@Valid @RequestBody LoginController.LoginDTO login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getUsername(),
                login.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody final UserCommand command){
        UserDTO newUserDTO = userService.save(command);
        if (newUserDTO == null)
            return new ResponseEntity<UserDTO>(HttpStatus.CONFLICT);
        else return new ResponseEntity<UserDTO>( newUserDTO,  HttpStatus.CREATED );
    }



    /**
     * Return jwt token in body because Angular has problems with parsing plain string response entity
     */
    static class JWTToken {
        private String token;

        public JWTToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
    
    static class LoginDTO {
        
        @NotNull
        private String username;

        @NotNull
        private String password;

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
    }
}
