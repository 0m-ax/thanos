package monster.theundefinedavengers.thanos.auth.service;

import monster.theundefinedavengers.thanos.auth.model.Role;
import monster.theundefinedavengers.thanos.auth.model.User;
import monster.theundefinedavengers.thanos.auth.model.UserDto;
import monster.theundefinedavengers.thanos.auth.repository.RoleRepository;
import monster.theundefinedavengers.thanos.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository repository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerNewUserAccount(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findById(1))));
        return repository.save(user);
    }
}