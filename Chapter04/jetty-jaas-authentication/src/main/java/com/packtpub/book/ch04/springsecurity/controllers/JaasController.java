package com.packtpub.book.ch04.springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Single controller which we use for our sample application having all the routes
 */
@Controller
public class JaasController {

  @RequestMapping(value="/", method = RequestMethod.GET)
  public ModelAndView userPage() {
    ModelAndView modelAndView = new ModelAndView("user");
    return modelAndView;
  }

  @RequestMapping(value = "/admin/moresecured", method = RequestMethod.GET)
  public ModelAndView adminPage(HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("moresecured");
    return modelAndView;
  }

  @RequestMapping(value="/noaccess", method = RequestMethod.GET)
  public ModelAndView accessDenied() {
    ModelAndView modelAndView = new ModelAndView("noaccess");
    return modelAndView;
  }
}
