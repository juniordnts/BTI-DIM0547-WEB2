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

import com.imd.project.model.Coupon;
import com.imd.project.service.CouponService;

@RestController
@RequestMapping("/coupon")
public class CouponController {

  @Autowired
  CouponService currentModelService;

  //

  @GetMapping("/{id}")
  public Coupon getById(@Valid @PathVariable Integer id) {
    Coupon itemFound = currentModelService
        .getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    return itemFound;
  }

  @GetMapping("/list")
  public List<Coupon> getList() {
    return currentModelService
        .getAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Coupon postCreate(@Valid @RequestBody Coupon item) {
    return currentModelService.save(item);
  }

  @PutMapping("/{id}")
  public Coupon update(@Valid @PathVariable Integer id, @RequestBody Coupon item) {
    Coupon itemFound = currentModelService.getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    item.setId(itemFound.getId());
    currentModelService.save(item);

    return item;
  }

  @DeleteMapping("/{id}")
  public void postDelete(@Valid @PathVariable Integer id) {
    Coupon itemFound = currentModelService.getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    currentModelService.delete(itemFound);
  }

}
