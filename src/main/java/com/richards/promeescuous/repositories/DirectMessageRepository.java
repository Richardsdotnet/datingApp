package com.richards.promeescuous.repositories;

import com.richards.promeescuous.models.DirectMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long> {
}
