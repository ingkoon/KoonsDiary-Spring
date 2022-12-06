package upf2022.team464.koonsdiary.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upf2022.team464.koonsdiary.user.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository{



    Optional<List<User>> findAllByAccount(String account);
    Optional<User> findByAccount(String account);

    Optional<User> findByNickname(String nickname);

    Boolean existsByAccount(String account);
    Boolean existsByEmail(String email);
}
