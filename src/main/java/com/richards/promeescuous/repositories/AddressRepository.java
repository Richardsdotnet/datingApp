package com.richards.promeescuous.repositories;

import com.richards.promeescuous.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
