package hr.java.sandelic.UserApp.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();
    Optional<UserDTO> findById (Long id);
    Optional<UserDTO> findOneByUsername(String username);
    UserDTO save (UserCommand command);
    void deleteById(Long id);
    UserDTO updateUser (User user);

}
