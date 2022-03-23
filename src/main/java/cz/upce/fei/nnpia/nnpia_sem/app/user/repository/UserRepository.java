package cz.upce.fei.nnpia.nnpia_sem.app.user.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
