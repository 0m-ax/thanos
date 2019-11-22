package monster.theundefinedavengers.thanos.auth.repository;


import monster.theundefinedavengers.thanos.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findById(long id);
}