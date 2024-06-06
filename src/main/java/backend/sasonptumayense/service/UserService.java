package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.User;
import backend.sasonptumayense.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        return (user != null) ? user : null;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return (user != null) ? user : null;
    }

    public User getUserByIdentificationNumber(String identificationNumber) {
        User user = userRepository.findByIdentificationNumber(identificationNumber).orElse(null);
        return (user != null) ? user : null;
    }
}
