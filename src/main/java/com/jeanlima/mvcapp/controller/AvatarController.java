package com.jeanlima.mvcapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.jeanlima.mvcapp.model.Curso;
import com.jeanlima.mvcapp.model.Avatar;
import com.jeanlima.mvcapp.service.CursoService;
import com.jeanlima.mvcapp.service.EstudanteService;
import com.jeanlima.mvcapp.service.AvatarService;

@Controller
@RequestMapping("/avatar")
public class AvatarController {

  @Autowired
  @Qualifier("avatarServiceImpl")
  AvatarService avatarService;

  @Autowired
  @Qualifier("estudanteServiceImpl")
  EstudanteService estudanteService;

  @RequestMapping("/showForm")
  public String showFormAvatar(Model model) {
    model.addAttribute("avatar", new Avatar());
    model.addAttribute("estudantes", estudanteService.getListaEstudante());
    return "avatar/formAvatar";
  }

  @RequestMapping("/addAvatar")
  public RedirectView showFormAvatar(@ModelAttribute("avatar") Avatar avatar, Model model) {
    avatarService.salvarAvatar(avatar);
    return new RedirectView("/avatar/getListaAvatares");
  }

  @RequestMapping("/getListaAvatares")
  public String showListaAvatar(Model model) {
    List<Avatar> avatares = avatarService.getListaAvatares();
    model.addAttribute("avatares", avatares);
    return "avatar/listaAvatares";
  }

  @RequestMapping("/deleteAvatarById/{id}")
  public RedirectView deleteAvatarById(@PathVariable Integer id, Model model) {
    Avatar avatar = avatarService.getAvatarById(id);
    avatarService.deletarAvatar(avatar);
    return new RedirectView("/avatar/getListaAvatares");
  }

}
