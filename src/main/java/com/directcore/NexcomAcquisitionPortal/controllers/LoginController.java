package com.directcore.NexcomAcquisitionPortal.controllers;

import com.directcore.NexcomAcquisitionPortal.repositories.AdmiRepository;
import com.directcore.NexcomAcquisitionPortal.services.AdminService;
import com.directcore.NexcomAcquisitionPortal.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {


    @Autowired
    private AdmiRepository admiRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AdminService adminService;


    @RequestMapping(value = { "/", "/index", "/home", "/welcome", "/login" }, method = RequestMethod.GET)
    public ModelAndView index(ModelAndView v) {
        v.setViewName("login");
        return v;
    }

    @PostMapping(path = "/admin_login", consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession request, ModelAndView v) {


        return (String) adminService.login(email, password, model, request, v);

    }

    @GetMapping({"/admin_logout"})
    public String logout(Model model, HttpSession request) {
        return adminService.logout(model, request);
    }


    @GetMapping({"/forgotpassword"})
    public String forgotpassword(Model model, HttpSession request) {
        return adminService.forgotpassword(model, request);
    }
}
