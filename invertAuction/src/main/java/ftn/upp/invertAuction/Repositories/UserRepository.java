package ftn.upp.invertAuction.repositories;

import ftn.upp.invertAuction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
