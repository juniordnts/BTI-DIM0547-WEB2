package com.imd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imd.project.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
