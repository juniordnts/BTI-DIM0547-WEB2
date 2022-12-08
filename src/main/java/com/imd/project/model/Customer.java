package com.imd.project.model;

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
import javax.validation.constraints.NotEmpty;

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
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 100)
  @NotEmpty(message = "Campo 'name' obrigatorio")
  private String name;

  @Column(length = 11)
  @NotEmpty(message = "Campo 'cpf' obrigatorio")
  private String cpf;

  @Column(length = 200)
  @NotEmpty(message = "Campo 'email' obrigatorio")
  private String email;

  @Column(length = 100)
  @NotEmpty(message = "Campo 'born_date' obrigatorio")
  private String born_date;

  //

  @JsonIgnore
  @OneToMany(mappedBy = "customer")
  private Set<Appointment> appointments;

  @JsonIgnore
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

}
