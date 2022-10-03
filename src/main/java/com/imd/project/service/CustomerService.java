package com.imd.project.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imd.project.model.Customer;

@Service
public interface CustomerService {
  public Customer save(Customer curso);

  public void delete(Customer curso);

  public Customer getOneById(Integer id);

  public List<Customer> getAll();
}
