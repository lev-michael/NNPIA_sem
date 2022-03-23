package cz.upce.fei.nnpia.nnpia_sem.app.user.service;

import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) {
        return this.userRepository.getById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
