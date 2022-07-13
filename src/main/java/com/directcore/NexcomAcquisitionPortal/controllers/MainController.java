package com.directcore.NexcomAcquisitionPortal.controllers;


import com.directcore.NexcomAcquisitionPortal.model.Building_information;
import com.directcore.NexcomAcquisitionPortal.model.Region;
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



    @GetMapping({"/acqusitions"})
    public ModelAndView acqusitions(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.acqusition( request, v);

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


    @RequestMapping(value = "/addregion", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object addregion(@ModelAttribute Region request) {

        try {
            return adminService.addregion(request);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    @GetMapping("/region/{id}")

    public ModelAndView getEmployeesById(@PathVariable Integer id, ModelAndView v, HttpSession request) {



            return (ModelAndView) adminService.region(id, v, request);

    }

    @RequestMapping(value = "/addzone", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object addzone(@RequestParam("regionId") Integer regionId, @RequestParam("name") String name, @RequestParam("description") String description) {

        try {
            return adminService.addzone(regionId, name, description);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }


    @GetMapping("/zone/{id}")

    public ModelAndView getAreasforthiszone(@PathVariable Integer id, ModelAndView v, HttpSession request) {



        return (ModelAndView) adminService.zone(id, v, request);

    }

    @RequestMapping(value = "/addarea", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object addarea(@RequestParam("zoneId") Integer zoneId, @RequestParam("name") String name, @RequestParam("description") String description) {

        try {
            return adminService.addarea(zoneId, name, description);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }
    @GetMapping("/area/{id}")

    public ModelAndView getareacluters(@PathVariable Integer id, ModelAndView v, HttpSession request) {



        return (ModelAndView) adminService.area(id, v, request);

    }

    @RequestMapping(value = "/addcluster", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object addcluster(@RequestParam("areaId") Integer areaId, @RequestParam("name") String name, @RequestParam("description") String description) {

        try {
            return adminService.addcluster(areaId, name, description);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }



    @GetMapping("/getzonesbyid/{id}")
    @ResponseBody
    public Object getzonesbyid(@PathVariable Integer id) {


        try {
            return adminService.getzonesbyid(id);
        } catch (Exception e) {
            return "Failed!";
        }

    }

    @GetMapping("/getareasbyid/{id}")
    @ResponseBody
    public Object getareabyid(@PathVariable Integer id) {


        try {
            return adminService.getareabyid(id);
        } catch (Exception e) {
            return "Failed!";
        }

    }

    @GetMapping("/getclusterbyid/{id}")
    @ResponseBody
    public Object getclusterbyid(@PathVariable Integer id) {


        try {
            return adminService.getclusterbyid(id);
        } catch (Exception e) {
            return "Failed!";
        }

    }

    @RequestMapping(value = "/editregion", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object editregion(@RequestParam("regionIdd") Integer regionId, @RequestParam("name") String name) {

        try {
            return adminService.editregion(regionId, name);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    @RequestMapping(value = "/editzone", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object editedit(@RequestParam("zoneIdd") Integer zoneId, @RequestParam("name") String name) {

        try {
            return adminService.editzone(zoneId, name);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }


    @RequestMapping(value = "/editarea", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object editarea(@RequestParam("areaIdd") Integer areaId, @RequestParam("name") String name) {

        try {
            return adminService.editarea(areaId, name);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    @GetMapping("/updatecluster/{id}")
    @ResponseBody
    public Object updatecluster(@PathVariable Integer id) {



        try {
            return adminService.updatecluster(id);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    @RequestMapping(value = "/editcluster", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object editcluster(@RequestParam("clusterId") Integer clusterId, @RequestParam("name") String name) {

        try {
            return adminService.editcluster(clusterId, name);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    }
