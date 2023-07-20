package com.richards.promeescuous.repositories;

import com.richards.promeescuous.models.BasicData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicDataRepository extends JpaRepository<BasicData, Long> {
}
