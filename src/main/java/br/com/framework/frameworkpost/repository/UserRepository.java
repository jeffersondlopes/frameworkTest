package br.com.framework.frameworkpost.repository;

import br.com.framework.frameworkpost.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String Email);

    Optional<User> findByEmail(String email);

}
