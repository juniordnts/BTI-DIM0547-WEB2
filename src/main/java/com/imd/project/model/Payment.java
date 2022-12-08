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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 100)
  @NotEmpty(message = "Campo 'codigo' obrigatorio")
  private String code;

  // pending | approved | inprocess | inmediation | rejected | cancelled |
  // refunded | chargedback
  @Column(length = 100)
  @NotEmpty(message = "Campo 'status' obrigatorio")
  private String status;

  @Column(length = 5000, columnDefinition = "Text")
  private String qr;

  @Column(precision = 20, scale = 2)
  private double total;

  //

  @JsonIgnore
  @OneToMany(mappedBy = "payment")
  private Set<Appointment> appointments;

}
