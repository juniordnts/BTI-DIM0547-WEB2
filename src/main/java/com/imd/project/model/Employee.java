package com.imd.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 100)
  @NotEmpty(message = "Campo 'name' obrigatorio")
  private String name;

  @Column(length = 100)
  @NotEmpty(message = "Campo 'role' obrigatorio")
  private String role;

  //

  @JsonIgnore
  @OneToMany(mappedBy = "employee")
  private Set<Appointment> appointments;

  //

  public Employee() {
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

  public String getRole() {
    return role;
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

  public void setRole(String role) {
    this.role = role;
  }

}
