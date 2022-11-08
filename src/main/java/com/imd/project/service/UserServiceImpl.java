package com.imd.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.imd.project.model.User;
import com.imd.project.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository modelEntityRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public User save(User modelInstance) {
    return modelEntityRepository.save(modelInstance);
  }

  @Override
  public void delete(User modelInstance) {
    modelEntityRepository.delete(modelInstance);
  }

  @Override
  public User getOneById(Integer id) {
    return modelEntityRepository.findById(id).map(modelInstance -> {
      return modelInstance;
    }).orElseGet(() -> null);
  }

  @Override
  public List<User> getAll() {
    return modelEntityRepository.findAll();
  }

  //

  public UserDetails auth(User item) {
    UserDetails userdDetails = loadUserByUsername(item.getUsername());
    boolean matchPassword = passwordEncoder.matches(item.getPassword(), userdDetails.getPassword());

    if (matchPassword)
      return userdDetails;

    throw new RuntimeException("Senha inválida");
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User userFound = modelEntityRepository.findByUsername(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("Usuário não encontrado"));

    String[] roles = userFound.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };

    return org.springframework.security.core.userdetails.User
        .builder()
        .username(userFound.getUsername())
        .password(userFound.getPassword())
        .roles(roles)
        .build();

  }

}
