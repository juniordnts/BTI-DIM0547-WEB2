package com.jeanlima.mvcapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jeanlima.mvcapp.model.Avatar;

@Service
public interface AvatarService {
  public Avatar salvarAvatar(Avatar curso);

  public void deletarAvatar(Avatar curso);

  public Avatar getAvatarById(Integer id);

  public List<Avatar> getListaAvatares();
}
