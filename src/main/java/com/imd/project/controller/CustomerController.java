package com.imd.project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;

import com.imd.project.dto.CustomerDTO;
import com.imd.project.model.Address;
import com.imd.project.model.Appointment;
import com.imd.project.model.Customer;
import com.imd.project.service.AddressService;
import com.imd.project.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  CustomerService currentModelService;

  @Autowired
  AddressService addressModelService;

  //

  @GetMapping("/{id}")
  public Customer getById(@Valid @PathVariable Integer id) {
    Customer itemFound = currentModelService.getOneById(id);

    Customer newItem = new Customer();
    newItem.setId(itemFound.getId());
    newItem.setName(itemFound.getName());
    newItem.setCpf(itemFound.getCpf());
    newItem.setBorn_date(itemFound.getBorn_date());
    newItem.setAddress(itemFound.getAddress());
    List<Appointment> newAppointments = new ArrayList<Appointment>();
    for (Appointment appointmentIt : itemFound.getAppointments()) {
      appointmentIt.setCustomer(null);
      newAppointments.add(appointmentIt);
    }
    newItem.setAppointments(new HashSet<>(newAppointments));
    return newItem;
  }

  @GetMapping("/list")
  public List<Customer> getList() {
    List<Customer> listFound = currentModelService.getAll();
    List<Customer> newList = new ArrayList<Customer>();
    for (Customer customerIt : listFound) {
      Customer newItem = new Customer();
      newItem.setId(customerIt.getId());
      newItem.setName(customerIt.getName());
      newItem.setCpf(customerIt.getCpf());
      newItem.setBorn_date(customerIt.getBorn_date());
      newItem.setAddress(customerIt.getAddress());
      List<Appointment> newAppointments = new ArrayList<Appointment>();
      for (Appointment appointmentIt : customerIt.getAppointments()) {
        appointmentIt.setCustomer(null);
        newAppointments.add(appointmentIt);
      }
      newItem.setAppointments(new HashSet<>(newAppointments));
      newList.add(newItem);
    }

    return newList;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Customer postCreate(@Valid @RequestBody CustomerDTO item) {
    Customer newItem = new Customer();
    newItem.setName(item.getName());
    newItem.setCpf(item.getCpf());
    newItem.setBorn_date(item.getBorn_date());

    Address addressFound = addressModelService.getOneById(item.getAddress());
    if (addressFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Endereço não encontrado");

    newItem.setAddress(addressFound);

    return currentModelService.save(newItem);
  }

  @PutMapping("/{id}")
  public Customer update(@Valid @PathVariable Integer id, @RequestBody CustomerDTO item) {
    Customer itemFound = currentModelService.getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    Customer newItem = new Customer();
    newItem.setName(item.getName());
    newItem.setCpf(item.getCpf());
    newItem.setBorn_date(item.getBorn_date());

    Address addressFound = addressModelService.getOneById(item.getAddress());
    if (addressFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Endereço não encontrado");

    newItem.setAddress(addressFound);

    newItem.setId(itemFound.getId());
    currentModelService.save(newItem);

    return newItem;
  }

  @DeleteMapping("/{id}")
  public void postDelete(@Valid @PathVariable Integer id) {
    Customer itemFound = currentModelService.getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    currentModelService.delete(itemFound);
  }

}
