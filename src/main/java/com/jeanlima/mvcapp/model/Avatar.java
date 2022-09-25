package com.jeanlima.mvcapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avatares")
public class Avatar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 100)
  private String nomeFantasia;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "estudante_id", referencedColumnName = "id")
  private Estudante estudante;

  public Avatar() {
  }

  public Avatar(String nomeFantasia) {
    this.nomeFantasia = nomeFantasia;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNomeFantasia() {
    return nomeFantasia;
  }

  public void setNomeFantasia(String nomeFantasia) {
    this.nomeFantasia = nomeFantasia;
  }

  public Estudante getEstudante() {
    return estudante;
  }

  public void setEstudante(Estudante estudante) {
    this.estudante = estudante;
  }

  @Override
  public String toString() {
    return "Avatar de " + this.getNomeFantasia();
  }

}
