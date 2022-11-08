package com.imd.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imd.project.model.Address;
import com.imd.project.repository.AddressRepository;

@Component
public class AddressServiceImpl implements AddressService {

  @Autowired
  AddressRepository modelEntityRepository;

  @Override
  public Address save(Address modelInstance) {
    return modelEntityRepository.save(modelInstance);
  }

  @Override
  public void delete(Address modelInstance) {
    modelEntityRepository.delete(modelInstance);
  }

  @Override
  public Address getOneById(Integer id) {
    return modelEntityRepository.findById(id).map(modelInstance -> {
      return modelInstance;
    }).orElseGet(() -> null);
  }

  @Override
  public List<Address> getAll() {
    return modelEntityRepository.findAll();
  }

}
