package com.imd.project.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "procedures")
public class Procedure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 100)
  @NotEmpty(message = "Campo 'name' obrigatorio")
  private String name;

  @Column(precision = 20, scale = 2)
  private BigDecimal price;

  //

  @JsonIgnore
  @ManyToMany(mappedBy = "procedures")
  Set<Appointment> appointments;

  //

  public Procedure() {
  }

  @Override
  public String toString() {
    return "";
  }

  //

  public Set<Appointment> getAppointments() {
    return appointments;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setAppointments(Set<Appointment> appointments) {
    this.appointments = appointments;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}
