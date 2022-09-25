package com.jeanlima.mvcapp.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jeanlima.mvcapp.model.Avatar;
import com.jeanlima.mvcapp.repository.AvatarRepository;

@Component
public class AvatarServiceImpl implements AvatarService {

  @Autowired
  AvatarRepository avatarRepository;

  @Override
  public Avatar salvarAvatar(Avatar avatar) {
    return avatarRepository.save(avatar);
  }

  @Override
  public void deletarAvatar(Avatar avatar) {
    avatarRepository.delete(avatar);
  }

  @Override
  public Avatar getAvatarById(Integer id) {
    return avatarRepository.findById(id).map(avatar -> {
      return avatar;
    }).orElseThrow(() -> null);
  }

  @Override
  public List<Avatar> getListaAvatares() {
    return avatarRepository.findAll();
  }

}
