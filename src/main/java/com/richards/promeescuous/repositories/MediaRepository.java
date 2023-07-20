package com.richards.promeescuous.repositories;

import com.richards.promeescuous.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
