package com.directcore.NexcomAcquisitionPortal.controllers;


import com.directcore.NexcomAcquisitionPortal.model.*;
import com.directcore.NexcomAcquisitionPortal.repositories.AdmiRepository;
import com.directcore.NexcomAcquisitionPortal.services.AdminService;
import com.directcore.NexcomAcquisitionPortal.validation.UserValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
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

        return (ModelAndView) adminService.index(request, v);

    }


    @GetMapping({"/acqusitions"})
    public ModelAndView acqusitions(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.acqusition(request, v);

    }


    @RequestMapping(value = "/addbuilding", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public Object doSignUp(@RequestParam("pp_photo") MultipartFile file, @ModelAttribute Building_information request) {

        try {
            return adminService.addbuilding(request, file);
        } catch (Exception e) {
            return "Failed!";
        }


    }

    @GetMapping({"/myacqusitions"})
    public ModelAndView myacqusitions(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.myacqusition(request, v);

    }

    @GetMapping({"/Teritories"})
    public ModelAndView Teritories(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.teritories(request, v);

    }


    @RequestMapping(value = "/addregion", method = RequestMethod.POST, consumes = {"multipart/form-data"})
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

    @RequestMapping(value = "/addzone", method = RequestMethod.POST, consumes = {"multipart/form-data"})
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

    @RequestMapping(value = "/addarea", method = RequestMethod.POST, consumes = {"multipart/form-data"})
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

    @RequestMapping(value = "/addcluster", method = RequestMethod.POST, consumes = {"multipart/form-data"})
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

    @RequestMapping(value = "/editregion", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public Object editregion(@RequestParam("regionIdd") Integer regionId, @RequestParam("name") String name) {

        try {
            return adminService.editregion(regionId, name);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    @RequestMapping(value = "/editzone", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public Object editedit(@RequestParam("zoneIdd") Integer zoneId, @RequestParam("name") String name) {

        try {
            return adminService.editzone(zoneId, name);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }


    @RequestMapping(value = "/editarea", method = RequestMethod.POST, consumes = {"multipart/form-data"})
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

    @RequestMapping(value = "/editcluster", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public Object editcluster(@RequestParam("clusterId") Integer clusterId, @RequestParam("name") String name) {

        try {
            return adminService.editcluster(clusterId, name);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    @GetMapping("/myacqusition/{id}")
    public ModelAndView myacqusition(@PathVariable Integer id, HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.myacqusitionbyid(id, request, v);

    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws MalformedURLException {
        Resource file = adminService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @RequestMapping(value = "/addimage", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public Object addimage(@RequestParam("buildingId") Integer buildingId, @RequestParam("pp_photo") MultipartFile file) {

        try {
            return adminService.addimage(buildingId, file);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }


    @GetMapping("/deleteimage/{id}")
    @ResponseBody
    public Object deleteimage(@PathVariable Integer id) {


        try {
            return adminService.deleteimage(id);
        } catch (Exception e) {
            return "Failed!";
        }

    }

    @RequestMapping(value = "/addcontact", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public Object addcontact(@ModelAttribute Contact_info request) {

        try {
            return adminService.addcontact(request);
        } catch (Exception e) {
            return "Failed!";
        }

    }

    @GetMapping("/fetchcontact/{id}")
    @ResponseBody
    public Object fetchcontact(@PathVariable Integer id) {


        try {
            return adminService.fetchcontact(id);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    @RequestMapping(value = "/editcontact", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public Object editcontact(@RequestParam("contactId") Integer contactId, @RequestParam("management_type") String management_type,
                              @RequestParam("full_names") String full_names, @RequestParam("phone_number") String phone_number,
                              @RequestParam("id_number") String id_number) {

        try {
            return adminService.editcontact(contactId, management_type, full_names, phone_number, id_number);
        } catch (Exception e) {
            return "Failed!";
        }

    }


    @RequestMapping(value = "/editbuilding", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public Object editbuillding(@RequestParam("buildingId") Integer buildingId, @RequestParam("building_name") String building_name,
                              @RequestParam("building_description") String building_description, @RequestParam("building_type") String building_type,
                              @RequestParam("possible_sales") String possible_sales) {

        try {
            return adminService.editbuilding(buildingId, building_description, building_name,building_type, possible_sales);
        } catch (Exception e) {
            return "Failed!";
        }

    }

    @GetMapping({"/admin_roles"})
    public ModelAndView admin_roles(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.admin_roles(request, v);

    }

    @GetMapping("/getPortalroleByid/{id}")
    public @ResponseBody
    Object getPortalroleById(@PathVariable Integer id) {

        return  adminService.getPortalroleByid(id);

    }

    @PostMapping("/portalRoles")
    public @ResponseBody
   Object newportalRoles(Roles_admin admin_roles , HttpSession request) {



        try {

            return adminService.newportalRoles(admin_roles, request);

        } catch (Exception e) {
            return "Failed!";
        }
    }

    @PostMapping(path = "/portalRolesedit")
    public
    @ResponseBody
    Object lokk(Roles_admin rolesAdmin) {


        try {

            return (ResponseEntity<Object>) adminService.editportalRoles(rolesAdmin);

        } catch (Exception e) {
            return "Failed!";
        }

    }



    @GetMapping({"/admin_users"})
    public ModelAndView portalUsers(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.portalUsers(request, v);

    }

    @PostMapping(value="/portalUsers")
    public @ResponseBody
    ResponseEntity<Object> newportalUsers(@ModelAttribute Admi newportalUser) {

        return (ResponseEntity<Object>) adminService.newportalUsers(newportalUser);
    }





    //   // get portal user by id
    @GetMapping(path = "/getPortalUserById/{id}")
    public ModelAndView getuser(@PathVariable final Integer id, HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.getuser(request, v, id);

    }

    //   // get portal user by id
    @GetMapping(path = "/getPortalUserByid/{id}")
    @ResponseBody
    public Object getuserbyid(@PathVariable final Integer id) {

        return (ModelAndView) adminService.getuserbyid( id);

    }
    @RequestMapping(value = "/addPermissions", method = RequestMethod.POST,  consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Object addPermissions(@RequestParam("id") Integer roleId, @RequestParam("data") String[] data) {

        try {
            return adminService.addPermissions(roleId, data);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    @RequestMapping(value = "/role_edit", method = RequestMethod.POST,  consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Object eoleedit(@RequestParam("edit_id") Integer roleId, @RequestParam("edit_name") String name, @RequestParam("is_active") String is_active) {

        try {
            return adminService.role_edit(roleId, name, is_active);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    //   // get portal user by id
    @GetMapping(path = "/viewAll")
    public ModelAndView viewAll(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.viewAll(request, v);

    }


    @RequestMapping(value = "/view_ba_search", method = RequestMethod.POST,  consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Object view_ba_search(@RequestParam("search") String search) {

        try {
            return adminService.view_ba_search(search);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    @RequestMapping(value = "/view_ba", method = RequestMethod.POST,  consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Object view_ba(@RequestParam("search_type") Integer search_type, @RequestParam("search") String search) {

        try {
            return adminService.view_ba(search, search_type);
        } catch (Exception e) {
            return "Failed!";
        }

    }

    @RequestMapping(value = "/view_teri", method = RequestMethod.POST,  consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Object view_teri(@RequestParam("search") String search) {

        try {
            return adminService.view_teri(search);
        } catch (Exception e) {
            return "Failed!";
        }

    }

    @RequestMapping(value = "/view_region_search", method = RequestMethod.POST,  consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Object view_region_searchrch(@RequestParam("search") String search) {

        try {
            return adminService.view_region_search(search);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }
    //   // get portal user by id
    @GetMapping(path = "/Teritories_search")
    public ModelAndView Teritories_search(HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.Teritories_search(request, v);

    }
}