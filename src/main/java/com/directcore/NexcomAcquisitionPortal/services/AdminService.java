package com.directcore.NexcomAcquisitionPortal.services;


import com.directcore.NexcomAcquisitionPortal.model.Admi;
import com.directcore.NexcomAcquisitionPortal.model.Building_information;
import com.directcore.NexcomAcquisitionPortal.model.Region;
import com.directcore.NexcomAcquisitionPortal.model.Roles_admin;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public interface AdminService {




    String newUser(Admi userForm);



    Object login(String email, String password, Model model, HttpSession request, ModelAndView v);

    String logout(Model model, HttpSession request);

    String portalUsers(Model model);

    Object newportalUsers(Admi newportalUser);

    String portalRoles(Model model);

    Object newportalRoles(Roles_admin roles, HttpSession request);

    Object index(HttpSession request, ModelAndView v);

    Object acqusition(HttpSession request, ModelAndView v);

     Object addbuilding(Building_information request);

    Object myacqusition(HttpSession request, ModelAndView v);

    Object teritories(HttpSession request, ModelAndView v);

    Object addregion(Region request);

    Object region(Integer id, ModelAndView v, HttpSession request);

    Object addzone(Integer regionId, String name, String description);
}
