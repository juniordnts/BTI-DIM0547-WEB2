package com.jeanlima.mvcapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 100)
  private String name;

  @Column(length = 11)
  private String cpf;

  @Column(length = 100)
  private String born_date;

  //

  @OneToMany(mappedBy = "customer")
  private Set<Appointment> appointments;

  //

  public Customer() {
  }

  @Override
  public String toString() {
    return "";
  }

  //

  public Set<Appointment> getAppointments() {
    return appointments;
  }

  public String getBorn_date() {
    return born_date;
  }

  public String getCpf() {
    return cpf;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setAppointments(Set<Appointment> appointments) {
    this.appointments = appointments;
  }

  public void setBorn_date(String born_date) {
    this.born_date = born_date;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

}
