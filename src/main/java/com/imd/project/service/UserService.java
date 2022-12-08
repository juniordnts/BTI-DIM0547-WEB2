package com.imd.project.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.imd.project.model.User;

@Service
public interface UserService {
  public User save(User item);

  public void delete(User item);

  public User getOneById(Integer id);

  public List<User> getAll();

  public UserDetails auth(User item);

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
