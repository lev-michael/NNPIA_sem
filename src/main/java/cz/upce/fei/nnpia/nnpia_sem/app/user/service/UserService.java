package cz.upce.fei.nnpia.nnpia_sem.app.user.service;

import cz.upce.fei.nnpia.nnpia_sem.app.user.dto.UserCredentialsDto;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserCredentialsDto getUserCredentials(String username) {
        Optional<User> result = this.userRepository.findUserByUserNameEquals(username);
        if (result.isPresent()) {
            return new UserCredentialsDto(result.get().getUserName(), result.get().getPassword());
        }
        return null;
    }
}
