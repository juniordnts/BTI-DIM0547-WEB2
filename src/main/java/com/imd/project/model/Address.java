package com.imd.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "addresses")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 100)
  @NotEmpty(message = "Campo 'city' obrigatorio")
  private String city;

  @Column(length = 100)
  @NotEmpty(message = "Campo 'street' obrigatorio")
  private String street;

  @Column(length = 100)
  @NotEmpty(message = "Campo 'number' obrigatorio")
  private String number;

  @OneToOne(mappedBy = "address")
  private Customer customer;

  //

  public Address() {
  }

  @Override
  public String toString() {
    return "";
  }

  //

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public String getNumber() {
    return number;
  }

  public String getStreet() {
    return street;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public void setStreet(String street) {
    this.street = street;
  }

}
