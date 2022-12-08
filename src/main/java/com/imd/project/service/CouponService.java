package com.imd.project.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imd.project.model.Coupon;

@Service
public interface CouponService {
  public Coupon save(Coupon item);

  public void delete(Coupon item);

  public Coupon getOneById(Integer id);

  public List<Coupon> getAll();
}
