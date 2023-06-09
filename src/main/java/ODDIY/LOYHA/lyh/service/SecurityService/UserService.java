package ODDIY.LOYHA.lyh.service.SecurityService;

import ODDIY.LOYHA.lyh.entity.Security.User;
import ODDIY.LOYHA.lyh.repository.SecurityRepository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public Boolean cheeckUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

}
