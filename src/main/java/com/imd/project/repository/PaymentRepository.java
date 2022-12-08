package com.imd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imd.project.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
