package com.imd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imd.project.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}
