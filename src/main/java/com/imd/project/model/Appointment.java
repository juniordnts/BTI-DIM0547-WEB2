package com.imd.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "appointments")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 100)
  private String date;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  // @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "coupon_id", nullable = true)
  private Coupon coupon;

  @ManyToOne
  @JoinColumn(name = "payment_id", nullable = true)
  private Payment payment;

  // @JsonIgnore
  @ManyToMany
  @JoinTable(name = "procedure_appointment", joinColumns = @JoinColumn(name = "appointment_id"), inverseJoinColumns = @JoinColumn(name = "procedure_id"))
  private Set<Procedure> procedures;

}
