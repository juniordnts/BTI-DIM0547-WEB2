package com.jeanlima.mvcapp.model;

public class Estudante {

  private int id;
  private String primeiroNome;
  private String ultimoNome;
  private Curso curso;
  private String linguagem;
  private String email;

  private String[] sistemasOperacionas;

  public Estudante() {
  }

  public Estudante(String primeiroNome, String ultimoNome, Curso curso, String linguagem, String email) {
    this.primeiroNome = primeiroNome;
    this.ultimoNome = ultimoNome;
    this.curso = curso;
    this.linguagem = linguagem;
    this.email = email;
  }

  public Estudante(int id, String primeiroNome, String ultimoNome, Curso curso, String linguagem, String email) {
    this.id = id;
    this.primeiroNome = primeiroNome;
    this.ultimoNome = ultimoNome;
    this.curso = curso;
    this.linguagem = linguagem;
    this.email = email;
  }

  public String getPrimeiroNome() {
    return primeiroNome;
  }

  public void setPrimeiroNome(String primeiroNome) {
    this.primeiroNome = primeiroNome;
  }

  public String getUltimoNome() {
    return ultimoNome;
  }

  public void setUltimoNome(String ultimoNome) {
    this.ultimoNome = ultimoNome;
  }

  @Override
  public String toString() {
    return "Estudante [" + primeiroNome + " " + ultimoNome + "]";
  }

  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
  }

  public String getLinguagem() {
    return linguagem;
  }

  public void setLinguagem(String linguagem) {
    this.linguagem = linguagem;
  }

  public String[] getSistemasOperacionas() {
    return sistemasOperacionas;
  }

  public void setSistemasOperacionas(String[] sistemasOperacionas) {
    this.sistemasOperacionas = sistemasOperacionas;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
