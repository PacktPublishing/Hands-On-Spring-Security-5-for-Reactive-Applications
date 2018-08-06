package com.packtpub.book.ch07.springsecurity.springbootspringsecurityhdiv.controller;

import com.packtpub.book.ch07.springsecurity.springbootspringsecurityhdiv.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class MovieController {

  static final Logger LOG = LoggerFactory.getLogger(MovieController.class);

  @RequestMapping("/")
  String main() {
    return "main";
  }

  @RequestMapping("/links")
  String links() {
    return "links";
  }

  @RequestMapping(value = "/movie", method = RequestMethod.GET)
  String movie(final Model model) {

    model.addAttribute("movieBean", new Movie());
    return "movie";
  }

  @RequestMapping(value = "/movie", method = RequestMethod.POST)
  String submit(@Valid @ModelAttribute final Movie bean, final BindingResult bindingResult,
                final RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      return "movie";
    }
    redirectAttributes.addFlashAttribute("resultMovieBean", bean);
    LOG.info("Title: " + bean.getTitle() + " Genre: " + bean.getGenre());
    return "redirect:/movie";
  }

}
