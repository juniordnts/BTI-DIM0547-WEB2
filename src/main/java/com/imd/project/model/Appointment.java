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

  // @JsonIgnore
  @ManyToMany
  @JoinTable(name = "procedure_appointment", joinColumns = @JoinColumn(name = "appointment_id"), inverseJoinColumns = @JoinColumn(name = "procedure_id"))
  private Set<Procedure> procedures;

  public Appointment() {
  }

  //

  public Customer getCustomer() {
    return customer;
  }

  public String getDate() {
    return date;
  }

  public Employee getEmployee() {
    return employee;
  }

  public int getId() {
    return id;
  }

  public Set<Procedure> getProcedures() {
    return procedures;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setProcedures(Set<Procedure> procedures) {
    this.procedures = procedures;
  }

}
