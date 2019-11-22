package monster.theundefinedavengers.thanos.auth.service;

import monster.theundefinedavengers.thanos.auth.model.User;
import monster.theundefinedavengers.thanos.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String usernname)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(usernname);
        System.out.println(user.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException(
                    "No user found with username: "+ usernname);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return  new org.springframework.security.core.userdetails.User
                (user.getUsername(),
                        user.getPassword(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                        user.getRoles());
    }

}