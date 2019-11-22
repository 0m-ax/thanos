package monster.theundefinedavengers.thanos.auth.repository;

import monster.theundefinedavengers.thanos.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
//    User findByEmail(String email);
}