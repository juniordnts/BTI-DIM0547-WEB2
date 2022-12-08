package com.imd.project.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imd.project.model.Customer;
import com.imd.project.model.Payment;

@Service
public interface PaymentService {
  public Payment save(Payment item, Customer customer);

  public void delete(Payment item);

  public Payment getOneById(Integer id);

  public List<Payment> getAll();
}
