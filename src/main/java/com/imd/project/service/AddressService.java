package com.imd.project.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imd.project.model.Address;

@Service
public interface AddressService {
  public Address save(Address item);

  public void delete(Address item);

  public Address getOneById(Integer id);

  public List<Address> getAll();
}
