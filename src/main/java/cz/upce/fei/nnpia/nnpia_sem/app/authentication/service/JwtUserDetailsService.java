package cz.upce.fei.nnpia.nnpia_sem.app.authentication.service;

import cz.upce.fei.nnpia.nnpia_sem.app.user.dto.UserCredentialsDto;
import cz.upce.fei.nnpia.nnpia_sem.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserCredentialsDto user = userService.getUserCredentials(username);
    if (user != null) {
      return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
   /* if ("javainuse".equals(username)) {
      return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
    }

    if ("javainuseWithRole".equals(username)) {
      return new User("javainuseWithRole", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }*/

    throw new UsernameNotFoundException("User not found with username: " + username);
  }
}
