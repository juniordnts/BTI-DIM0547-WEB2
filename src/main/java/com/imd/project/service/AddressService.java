package com.imd.project.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imd.project.model.Address;

@Service
public interface AddressService {
  public Address save(Address curso);

  public void delete(Address curso);

  public Address getOneById(Integer id);

  public List<Address> getAll();
}
