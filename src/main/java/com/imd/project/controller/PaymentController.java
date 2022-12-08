package com.imd.project.controller;

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

import com.imd.project.model.Payment;
import com.imd.project.service.CustomerService;
import com.imd.project.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

  @Autowired
  PaymentService currentModelService;

  @Autowired
  CustomerService customerModelService;

  //

  @GetMapping("/{id}")
  public Payment getById(@Valid @PathVariable Integer id) {
    Payment itemFound = currentModelService
        .getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    return itemFound;
  }

  @GetMapping("/list")
  public List<Payment> getList() {
    return currentModelService
        .getAll();
  }

  // @PostMapping
  // @ResponseStatus(HttpStatus.CREATED)
  // public Payment postCreate(@Valid @RequestBody Payment item) {
  // return currentModelService.save(item);
  // }

  @PutMapping("/{id}")
  public Payment update(@Valid @PathVariable Integer id, @RequestBody Payment item) {
    Payment itemFound = currentModelService.getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    item.setId(itemFound.getId());
    // currentModelService.save(item);

    return item;
  }

  @DeleteMapping("/{id}")
  public void postDelete(@Valid @PathVariable Integer id) {
    Payment itemFound = currentModelService.getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    currentModelService.delete(itemFound);
  }

}
