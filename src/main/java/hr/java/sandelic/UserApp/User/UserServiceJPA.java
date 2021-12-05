package hr.java.sandelic.UserApp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceJPA implements  UserService{

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceJPA(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(this::mapUserToDTO);
    }

    @Override
    public Optional<UserDTO> findOneByUsername(String username) {
        return userRepository.findOneByUsername(username).map(this::mapUserToDTO);
    }

    @Override
    public UserDTO save (UserCommand command){


        List<Authority> authList = new ArrayList<>();
        authList.add(authorityRepository.findAuthorityByName("ROLE_USER"));
        UserDTO userDTO = null;
        User user = mapCommandToUser(command);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newDate = LocalDate.parse(command.getDob().toString(), formatter);
        user.setDob(newDate);
        user.setPassword(passwordEncoder.encode(command.getPassword()));
        user.setAuthorities(authList);
        User newUser = userRepository.save(user);



        if(newUser == null)
            return userDTO;
        else {
            UserDTO newVaccineDTO = mapUserToDTO(newUser);
            return newVaccineDTO;
        }
    }

    @Override
    public void deleteById (Long id){
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(User user) {

       //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      // LocalDate newDate = LocalDate.parse(user.getDob().toString(), formatter);    //getDob vraca null;

        User existingUser = userRepository.findById(user.getId()).orElse(null);


        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(existingUser.getPassword());
        existingUser.setDob(existingUser.getDob());
        existingUser.setEmail(user.getEmail());
        existingUser.setGender(user.getGender());

        User savedUser = userRepository.save(existingUser);
        UserDTO userDTO = mapUserToDTO(savedUser);

        return userDTO;
    }


    private UserDTO mapUserToDTO(final User user){

        UserDTO userDTO = new UserDTO();


        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setDob(user.getDob());
        userDTO.setEmail(user.getEmail());
        userDTO.setGender(user.getGender());
        userDTO.setAuthorities(user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));

        return userDTO;
    }

    private User mapCommandToUser(UserCommand command) {
        User user = new User();
        user.setFirstName(command.getFirstName());
        user.setLastName(command.getLastName());
        user.setUsername(command.getUsername());
        user.setPassword(command.getPassword());
        user.setDob(command.getDob());
        user.setEmail(command.getEmail());
        user.setGender(command.getGender());

        return user;
    }



}
