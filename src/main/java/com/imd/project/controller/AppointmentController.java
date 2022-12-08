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

import com.imd.project.dto.AppointmentDTO;
import com.imd.project.model.Appointment;
import com.imd.project.model.Coupon;
import com.imd.project.model.Customer;
import com.imd.project.model.Employee;
import com.imd.project.model.Payment;
import com.imd.project.model.Procedure;
import com.imd.project.service.AppointmentService;
import com.imd.project.service.CouponService;
import com.imd.project.service.CustomerService;
import com.imd.project.service.EmployeeService;
import com.imd.project.service.PaymentService;
import com.imd.project.service.ProcedureService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

  @Autowired
  AppointmentService currentModelService;

  @Autowired
  CustomerService customerModelService;

  @Autowired
  EmployeeService employeeModelService;

  @Autowired
  ProcedureService procedureModelService;

  @Autowired
  CouponService couponModelService;

  @Autowired
  PaymentService paymentModelService;

  //

  @GetMapping("/{id}")
  public Appointment getById(@Valid @PathVariable Integer id) {
    Appointment itemFound = currentModelService
        .getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    return itemFound;
  }

  @GetMapping("/list")
  public List<Appointment> getList() {
    return currentModelService
        .getAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Appointment postCreate(@Valid @RequestBody AppointmentDTO item) {
    Appointment newItem = new Appointment();
    newItem.setDate(item.getDate());

    Customer customerFound = customerModelService.getOneById(item.getCustomer());
    if (customerFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Customer não encontrado");

    Employee employeeFound = employeeModelService.getOneById(item.getEmployee());
    if (employeeFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Employee não encontrado");

    newItem.setCustomer(customerFound);
    newItem.setEmployee(employeeFound);

    List<Procedure> newProcedureList = new ArrayList<Procedure>();
    double productTotal = 0.00;

    for (Integer procedureId : item.getProcedures()) {
      Procedure procedureFound = procedureModelService.getOneById(procedureId);
      if (procedureFound == null)
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Procedure não encontrado");

      newProcedureList.add(procedureFound);
      productTotal += procedureFound.getPrice();
    }

    if (item.getCoupon() != null) {
      Coupon couponFound = couponModelService.getOneById(item.getCoupon());
      if (couponFound == null)
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Cupom não encontrado");

      newItem.setCoupon(couponFound);
      productTotal = (double) productTotal - Math.round(productTotal * (couponFound.getDiscount() / 100));
    }

    Payment newPayment = new Payment();

    newPayment.setCode("Manual");
    newPayment.setStatus("pending");
    newPayment.setTotal(productTotal);

    paymentModelService.save(newPayment, customerFound);

    newItem.setProcedures(new HashSet<>(newProcedureList));

    return currentModelService.save(newItem);
  }

  @PutMapping("/{id}")
  public Appointment update(@Valid @PathVariable Integer id, @RequestBody Appointment item) {
    Appointment itemFound = currentModelService.getOneById(id);

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
    Appointment itemFound = currentModelService.getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    currentModelService.delete(itemFound);
  }

}
