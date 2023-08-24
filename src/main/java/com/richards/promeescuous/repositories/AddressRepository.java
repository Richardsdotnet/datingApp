package com.richards.promeescuous.repositories;

import com.richards.promeescuous.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

        List<Address> readAddressByCountryAndState(String country, String state);
    }

