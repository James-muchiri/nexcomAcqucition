package com.directcore.NexcomAcquisitionPortal.services;


import com.directcore.NexcomAcquisitionPortal.model.Admi;
import com.directcore.NexcomAcquisitionPortal.model.Building_information;
import com.directcore.NexcomAcquisitionPortal.model.Error1;
import com.directcore.NexcomAcquisitionPortal.model.Roles_admin;
import com.directcore.NexcomAcquisitionPortal.repositories.AdmiRepository;
import com.directcore.NexcomAcquisitionPortal.repositories.Building_informationRepository;
import com.directcore.NexcomAcquisitionPortal.repositories.RolesRepository;
import com.directcore.NexcomAcquisitionPortal.validation.UpdatableBCrypt;
import com.directcore.NexcomAcquisitionPortal.validation.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminServiceimpl implements com.directcore.NexcomAcquisitionPortal.services.AdminService {
    @Autowired
    private AdmiRepository admiRepository;
@Autowired
private RolesRepository roleRepository;

    @Autowired
    private Building_informationRepository building_informationRepository;
    @Autowired
      private UserValidator userValidator;
    private Pattern regexPattern;
    private Matcher regMatcher;

    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);


    @Override
    public String newUser(Admi userForm) {


    //    userForm.setRoles(new HashSet<>(roleRepository.findAll()));
     //   userForm.setPassword(bcrypt.hash(userForm.getPassword()));
        admiRepository.save(userForm);

        return "User Registered successful";
    }

    @Override
    public Object login(String email, String password, Model model, HttpSession request, ModelAndView v) {

        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(email);
        if (!regMatcher.matches()) {
            model.addAttribute("error", "Enter a valid email");
            return "admin/AdminLogin";
        }
        if (admiRepository.findByEmail(email) == null) {
            model.addAttribute("error", "Email does not exist");
            return "admin/AdminLogin";
        }






        Admi admi = admiRepository.findByEmail(email);
     //   if(!bcrypt.verifyHash(password, admi.getPassword() ))
      //  {
      //      model.addAttribute("error", "Password is incorrect");
      //      return "AdminLogin";
      //  }
      //  Admi user_admin = new Admi();
        Integer user_admin = (Integer) request.getAttribute("user_admin");
       if(user_admin == null)
       {
       user_admin =   admi.getId();
           request.setAttribute("user_admin", user_admin);

       }else {
           request.removeAttribute("user_admin");
           user_admin =    admi.getId();
         request.setAttribute("user_admin", user_admin);
       }



        return "redirect:admin/index";


    }

    @Override
    public String logout(Model model, HttpSession request) {
        request.removeAttribute("user_admin");
        return "redirect:login";
    }

    @Override
    public String portalUsers(Model model) {
        List<Admi> admi = (List<Admi>) admiRepository.findAll();

         model.addAttribute("users", admi);

        return "admin/portalUsers";
    }

    @Override
    public Object newportalUsers(Admi newportalUser) {


        Admi admi = admiRepository.findByEmail(newportalUser.getEmail());
        if (admi != null){

            Error1 error = new Error1();
            error.setCode("error");
            error.setMessage("email already in use");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        admiRepository.save(newportalUser);
        String success = "user created";
        return new ResponseEntity<Object>(success, HttpStatus.OK);
    }

    @Override
    public String portalRoles(Model model) {
       List<Roles_admin> roles =  roleRepository.findAll();

      model.addAttribute("roles", roles);

        return "admin/roles";
    }



    @Override
    public Object newportalRoles(Roles_admin roles, HttpSession request) {

       Roles_admin role = roleRepository.findByName(roles.getName());
        if (role != null){

            Error1 error = new Error1();
            error.setCode("error");
            error.setMessage("Role already created");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admin = admiRepository.findById(user_admin);
roles.setCreated_by(admin.getName());
        roleRepository.save(roles);
        String success = "Role created";
        return new ResponseEntity<Object>(success, HttpStatus.OK);
    }

    @Override
    public Object index(HttpSession request, ModelAndView v) {

        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("user", admi);
        return modelAndView;

    }

    @Override
    public Object acqusition(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);


        v.setViewName("acqusition");
        v.addObject("user", admi);
        return v;

    }



    @Override
    public Object addbuilding(Building_information request) {


            building_informationRepository.save(request);
            return "User Registered successful";



    }

    @Override
    public Object myacqusition(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);

        List<Building_information> building_information = (List<Building_information>) building_informationRepository.findAll();



        v.setViewName("myacqusitions");
        v.addObject("user", admi);
        v.addObject("buildings", building_information);
        return v;
    }


}