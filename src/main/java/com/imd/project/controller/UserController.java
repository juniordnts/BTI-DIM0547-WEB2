package com.imd.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.imd.project.dto.CredentialsDTO;
import com.imd.project.dto.TokenDTO;
import com.imd.project.model.User;
import com.imd.project.security.JwtService;
import com.imd.project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService currentModelService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtService jwtService;

  //

  @PostMapping("/auth")
  public TokenDTO autenticar(@RequestBody CredentialsDTO credenciais) {
    try {
      User userItem = User.builder()
          .username(credenciais.getUsername())
          .password(credenciais.getPassword()).build();

      currentModelService.auth(userItem);

      String token = jwtService.generateToken(userItem);

      return new TokenDTO(userItem.getUsername(), token);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public User getById(@PathVariable Integer id) {
    User itemFound = currentModelService
        .getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    return itemFound;
  }

  @GetMapping("/list")
  public List<User> getList() {
    return currentModelService
        .getAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User postCreate(@RequestBody User item) {
    String encryptedPassword = passwordEncoder.encode(item.getPassword());
    item.setPassword(encryptedPassword);
    return currentModelService.save(item);
  }

  @PutMapping("/{id}")
  public User update(@PathVariable Integer id, @RequestBody User item) {
    User itemFound = currentModelService.getOneById(id);
    String encryptedPassword = passwordEncoder.encode(item.getPassword());
    item.setPassword(encryptedPassword);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    item.setId(itemFound.getId());
    currentModelService.save(item);

    return item;
  }

  @DeleteMapping("/{id}")
  public void postDelete(@PathVariable Integer id) {
    User itemFound = currentModelService.getOneById(id);

    if (itemFound == null)
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Não encontrado");

    currentModelService.delete(itemFound);
  }

}
