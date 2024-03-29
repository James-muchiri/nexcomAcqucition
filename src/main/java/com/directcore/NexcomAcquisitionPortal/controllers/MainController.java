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
import org.springframework.http.MediaType;
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
    public Object addbuildings(@RequestParam("pp_photo") MultipartFile photo, @RequestParam("roa_document") MultipartFile file, @ModelAttribute Building_form request, HttpSession req) {

        try {
            return adminService.addbuildings(req,request, photo, file);
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

    @GetMapping("/cluster/{id}")

    public ModelAndView getareaclute(@PathVariable Integer id, ModelAndView v, HttpSession request) {


        return (ModelAndView) adminService.cluster(id, v, request);

    }
    @RequestMapping(value = "/addcluster", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public Object addcluster(@RequestParam("areaId") Integer areaId, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("clustertype") String clustertype,@RequestParam("clusterother") String clusterother, @RequestParam("pp_photo[]") MultipartFile[] files) {

        try {
            return adminService.addcluster(areaId, name, description, clustertype, clusterother, files);
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
    public Object editcluster(@ModelAttribute Cluster request) {

        try {
            return adminService.editcluster(request);
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
    public Object addcontact(@ModelAttribute Contact_profile request) {

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
    public Object editbuillding(@RequestParam("buildingId") Integer buildingId, @ModelAttribute Building_form request, HttpSession req ) {

        try {
            return adminService.editbuildings(buildingId, request);
        } catch (Exception e) {
            return "Failed!";
        }

    }
    @RequestMapping(value = "/edit_1_edit_accessright", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public Object edit_1_edit_accessright(@RequestParam("buildingcode") String buildingcode, @ModelAttribute Building_form request, HttpSession req ) {

        try {
            return adminService.edit_1_edit_accessright(buildingcode, request);
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
    Object newportalUsers(@ModelAttribute Admi newportalUser) {

        return adminService.newportalUsers(newportalUser);
    }





    //   // get portal user by id
    @GetMapping(path = "/getPortalUserById/{id}")
    public ModelAndView getuser(@PathVariable final Integer id, HttpSession request, ModelAndView v) {

        return (ModelAndView) adminService.getuser(request, v, id);

    }

    //   // get portal user by id
    @GetMapping(path = "/getPortalUsed/{id}")
    @ResponseBody
    public Object getuserbyid(@PathVariable final Integer id) {

        return adminService.getuserbyid( id);

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
    public Object view_ba(@RequestParam("search_type") Integer search_type, @RequestParam("search") Integer search) {

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

    @RequestMapping(value = "/useraddrole", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Object useraddrole(@RequestParam("roleid") Integer roleid, @RequestParam("userid") Integer userid) {

        try {
            return adminService.useraddrole(roleid, userid);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }

    @GetMapping(path = "/fetchadmins")
    @ResponseBody
    public Object fetchadmins() {

        return adminService.fetchadmins();

    }

//    @GetMapping(path = "/test")
//    @ResponseBody
//    public Object test() {
//
//        return adminService.generate_code();
//
//    }


    @RequestMapping(value = "/change_password", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Object change_password(@RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword, @RequestParam("newpassword_confirmation") String newpassword_confirmation, HttpSession request) {

        try {
            return adminService.change_password(request, oldpassword, newpassword, newpassword_confirmation);
        } catch (Exception e) {
            return "Failed!";
        }
        // return  request;
    }
}