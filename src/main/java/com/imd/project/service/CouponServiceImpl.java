package com.imd.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imd.project.model.Coupon;
import com.imd.project.repository.CouponRepository;

@Component
public class CouponServiceImpl implements CouponService {

  @Autowired
  CouponRepository modelEntityRepository;

  @Override
  public Coupon save(Coupon modelInstance) {
    return modelEntityRepository.save(modelInstance);
  }

  @Override
  public void delete(Coupon modelInstance) {
    modelEntityRepository.delete(modelInstance);
  }

  @Override
  public Coupon getOneById(Integer id) {
    return modelEntityRepository.findById(id).map(modelInstance -> {
      return modelInstance;
    }).orElseThrow(() -> null);
  }

  @Override
  public List<Coupon> getAll() {
    return modelEntityRepository.findAll();
  }

}
