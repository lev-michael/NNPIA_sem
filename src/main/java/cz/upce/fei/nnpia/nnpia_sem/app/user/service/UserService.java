package cz.upce.fei.nnpia.nnpia_sem.app.user.service;

import cz.upce.fei.nnpia.nnpia_sem.app.user.dto.UserCredentialsDto;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public UserCredentialsDto getUserCredentials(String username) {
        Optional<User> result = this.userRepository.findUserByUserNameEquals(username);
        return result.map(user -> new UserCredentialsDto(user.getUserName(), user.getPassword())).orElse(null);
    }

    public User getUserByUsername(String username) {
        Optional<User> result = this.userRepository.findUserByUserNameEquals(username);
        if (result.isPresent()) {
            User u = result.get();
            u.setPassword(null);
            return u;
        }
        return null;
    }
}
