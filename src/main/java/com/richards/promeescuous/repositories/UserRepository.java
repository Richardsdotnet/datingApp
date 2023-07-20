package com.richards.promeescuous.repositories;

import com.richards.promeescuous.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
