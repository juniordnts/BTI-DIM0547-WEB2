package com.imd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imd.project.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
