package upf2022.team464.koonsdiary.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upf2022.team464.koonsdiary.user.domain.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository{
}
