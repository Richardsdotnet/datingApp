package com.richards.promeescuous.repositories;

import com.richards.promeescuous.models.Interest;
import com.richards.promeescuous.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> readByEmail(String email);

}
