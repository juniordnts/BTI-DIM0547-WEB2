package com.imd.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imd.project.model.Customer;
import com.imd.project.repository.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  CustomerRepository modelEntityRepository;

  @Override
  public Customer save(Customer modelInstance) {
    return modelEntityRepository.save(modelInstance);
  }

  @Override
  public void delete(Customer modelInstance) {
    modelEntityRepository.delete(modelInstance);
  }

  @Override
  public Customer getOneById(Integer id) {
    return modelEntityRepository.findById(id).map(modelInstance -> {
      return modelInstance;
    }).orElseThrow(() -> null);
  }

  @Override
  public List<Customer> getAll() {
    return modelEntityRepository.findAll();
  }

}
