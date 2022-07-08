package com.directcore.NexcomAcquisitionPortal.controllers;


import com.directcore.NexcomAcquisitionPortal.model.Building_information;
import com.directcore.NexcomAcquisitionPortal.repositories.AdmiRepository;
import com.directcore.NexcomAcquisitionPortal.services.AdminService;
import com.directcore.NexcomAcquisitionPortal.validation.UserValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;



@Controller
@RequestMapping("/admin")
public class MainController {


    @Autowired
    private AdmiRepository admiRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AdminService adminService;


    private static final Logger logger = LoggerFactory
            .getLogger(MainController.class);
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> finale = new HashMap<String, Object>();


    @GetMapping({"/index"})
    public ModelAndView index(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.index( request, v);

    }







    @RequestMapping(value = "/addbuilding", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object doSignUp(@ModelAttribute Building_information request) {

        try {
            return adminService.addbuilding(request);
        } catch (Exception e) {
            return "Failed!";
        }
       // return  request;
    }

    @GetMapping({"/myacqusitions"})
    public ModelAndView myacqusitions(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.myacqusition( request, v);

    }

    @GetMapping({"/Teritories"})
    public ModelAndView Teritories(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.teritories( request, v);

    }

}
