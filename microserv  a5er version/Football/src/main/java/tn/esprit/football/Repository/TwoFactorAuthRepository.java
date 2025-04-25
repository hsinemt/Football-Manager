package tn.esprit.football.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.football.Entity.TwoFactorAuth;
import tn.esprit.football.Entity.User;

@Repository
public interface TwoFactorAuthRepository extends JpaRepository<TwoFactorAuth, Long> {
    TwoFactorAuth findByUser(User user);
}