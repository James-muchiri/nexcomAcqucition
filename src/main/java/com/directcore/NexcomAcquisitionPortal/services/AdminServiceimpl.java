package com.directcore.NexcomAcquisitionPortal.services;


import com.directcore.NexcomAcquisitionPortal.model.*;
import com.directcore.NexcomAcquisitionPortal.repositories.*;
import com.directcore.NexcomAcquisitionPortal.repositories.Contact_infoRepository;
import com.directcore.NexcomAcquisitionPortal.validation.UpdatableBCrypt;
import com.directcore.NexcomAcquisitionPortal.validation.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminServiceimpl implements com.directcore.NexcomAcquisitionPortal.services.AdminService {

    private final Path root = Paths.get("uploads");


    @Autowired
    private AdmiRepository admiRepository;
@Autowired
private RolesRepository roleRepository;



    @Autowired
      private UserValidator userValidator;


    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private ClusterRepository clusterRepository;


    @Autowired
    private Contact_infoRepository contact_infoRepository;

    @Autowired
    private  Building_infoRepository building_infoRepository;

    @Autowired
    private  Imags_infoRepository imags_infoRepository;

    @Autowired
    private  Sales_profileRepository sales_profileRepository;

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

        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {

            Roles_admin role = roleRepository.findByName(roles.getName());
            if (role != null){

                 rdata.put("success", 0);
                rdata.put("msg", "Role already created.");
                return rdata;
            }
            Integer user_admin = (Integer) request.getAttribute("user_admin");
            Admi admin = admiRepository.findById(user_admin);
            roles.setCreated_by(admin.getName());
            roleRepository.save(roles);

            rdata.put("success", 1);
            rdata.put("msg", "successful.");
            return rdata;

        } catch (Exception e) {
            e.printStackTrace();
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }
    }

    @Override
    public Object index(HttpSession request, ModelAndView v) {

        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);




        v.setViewName("index");
        v.addObject("user", admi);

        return v;

    }

    @Override
    public Object acqusition(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);
        List <Region> regions = (List<Region>) regionRepository.findAll();

        v.setViewName("acqusition");
        v.addObject("user", admi);
        v.addObject("regions", regions);
        return v;

    }





    @Override
    public Object addbuilding(Building_information request, MultipartFile file) {




        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {

            Building_info building_info = new Building_info();


            Region region = regionRepository.findById(request.getRegion()).orElse(null);
            building_info.setRegion(region.getName());

            Zone zone = zoneRepository.findById(request.getZone()).orElse(null);
            building_info.setZone(zone.getName());

            Area area = (Area) areaRepository.findById(request.getArea());
            building_info.setArea(area.getName());

            Cluster cluster= (Cluster) clusterRepository.findById(request.getCluster());
            building_info.setCluster(cluster.getName());

            building_info.setBuilding_name(request.getBuilding_name());
            building_info.setBuilding_description(request.getBuilding_description());
            building_info.setBuilding_photos(file.getOriginalFilename());
            building_info.setPossible_sales(request.getPossible_sales());
            building_info.setBuilding_type(request.getBuilding_type());
            building_info.setUse_type(request.getUse_type());
            building_info.setStreet_name(request.getStreet_name());
            building_info.setPower(request.getPower());
            building_info.setState(request.getState());
            building_info.setRoA(request.getRoA());
            building_info.setToA(request.getToA());
            building_info.setSecurity(request.getSecurity());
            building_info.setComments(request.getComments());

            building_infoRepository.save(building_info);

            Contact_info contact_info = new Contact_info();

            contact_info.setBuildingId(building_info.getId());
            contact_info.setManagement_type(request.getManagement_type());
            contact_info.setFull_names(request.getFull_names());
            contact_info.setPhone_number(request.getPhone_number());
            contact_info.setId_number(request.getId_number());
            contact_info.setEmail(request.getEmail());
            contact_infoRepository.save(contact_info);


            // sales profile
            Sales_profile sales_profile = new Sales_profile();
            sales_profile.setBuildingId(building_info.getId());
            sales_profile.setNumberofUnits(request.getNumberofUnits());
            sales_profile.setPackagesPosible(request.getPackagesPosible());
            sales_profile.setRent(request.getRent());
            sales_profile.setExsistingProviders(request.getExsistingProviders());
            sales_profile.setInternetUsers(request.getInternetUsers());
            sales_profile.setBlocks(request.getBlocks());
            sales_profile.setFloors(request.getFloors());
            sales_profileRepository.save(sales_profile);




            // String UPLOADED_FOLDER = "C/temp/";
           // Path p = Paths.get("uploads");


//            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(p + file.getOriginalFilename());
//
//            Files.write(path, bytes);






            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

            Images_info images_info = new Images_info();
            images_info.setBuildingId(building_info.getId());
            images_info.setName(file.getOriginalFilename());
            imags_infoRepository.save(images_info);

            rdata.put("success", 1);
            rdata.put("msg", "successful.");


            return rdata;

        } catch (Exception e) {
            e.printStackTrace();
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }



    }

    @Override
    public Object myacqusition(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);



        List <Building_info> building_infos = (List<Building_info>) building_infoRepository.findAll();

        v.setViewName("myacqusitions");
        v.addObject("user", admi);
        v.addObject("buildings", building_infos);
        return v;
    }

    @Override
    public Object teritories(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);


        List<Region> regions = (List<Region>) regionRepository.findAll();


         v.setViewName("teritories");
        v.addObject("user", admi);
        v.addObject("regions", regions);

        return v;
    }

    @Override
    public Object addregion(Region request) {


        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {

            regionRepository.save(request);

            rdata.put("success", 1);
            rdata.put("msg", "successful.");


            return rdata;

        } catch (Exception e) {
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }

    }

    @Override
    public Object region(Integer id, ModelAndView v, HttpSession request) {


        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);

        Region region = regionRepository.findById(id).orElse(null);




        List <Zone> zones = (List<Zone>) zoneRepository.findAllByRegionId(region.getId());




   


        v.setViewName("region");
        v.addObject("user", admi);
        v.addObject("regions", region);
        v.addObject("zones", zones);


        return v;
    }

    @Override
    public Object addzone(Integer regionId, String name, String description) {



        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {

            Zone zone = new Zone();

            zone.setRegionId(regionId);
            zone.setName(name);
            zone.setDescription(description);
            zoneRepository.save(zone);

            rdata.put("success", 1);
            rdata.put("msg", "successful.");


            return rdata;

        } catch (Exception e) {
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }
    }

    @Override
    public Object zone(Integer id, ModelAndView v, HttpSession request) {


        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);

        Zone zone =  zoneRepository.findById(id).orElse(null);


        List <Area> areas =  areaRepository.findAllByZoneId(zone.getId());





        v.setViewName("Zones");
        v.addObject("user", admi);

        v.addObject("zones", zone);
        v.addObject("areas", areas);


        return v;
    }

    @Override
    public Object addarea(Integer zoneId, String name, String description) {


        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {

            Zone zone = (Zone) zoneRepository.findById(zoneId).orElse(null);

            Region region = regionRepository.findById(zone.getRegionId()).orElse(null);

            Area area = new Area();

            area.setName(name);
            area.setDescription(description);
            area.setRegionId(region.getId());
            area.setZoneId(zoneId);
            areaRepository.save(area);

            rdata.put("success", 1);
            rdata.put("msg", "successful.");


            return rdata;

        } catch (Exception e) {
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }

    }

    @Override
    public Object area(Integer id, ModelAndView v, HttpSession request) {


        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);

       Area area = (Area) areaRepository.findById(id);


        List <Cluster> clusters = (List<Cluster>) clusterRepository.findAllByAreaId(area.getId());





        v.setViewName("areas");
        v.addObject("user", admi);
        v.addObject("areas", area);
        v.addObject("clusters", clusters);


        return v;
    }

    @Override
    public Object addcluster(Integer areaId, String name, String description) {





        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {
            Area area = (Area) areaRepository.findById(areaId);



            Cluster cluster = new Cluster();

            cluster.setName(name);
            cluster.setDescription(description);
            cluster.setRegionId(area.getRegionId());
            cluster.setZoneId(area.getZoneId());
            cluster.setAreaId(area.getId());
            clusterRepository.save(cluster);

            rdata.put("success", 1);
            rdata.put("msg", "successful.");


            return rdata;

        } catch (Exception e) {
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }


    }

    @Override
    public Object getzonesbyid(Integer id) {

        List <Zone> zones = zoneRepository.findAllByRegionId(id);

        return zones;
    }

    @Override
    public Object getareabyid(Integer id) {

        List <Area> areas = areaRepository.findAllByZoneId(id);
        return  areas;
    }

    @Override
    public Object getclusterbyid(Integer id) {

        List <Cluster> clusters = clusterRepository.findAllByAreaId(id);
        return clusters;
    }

    @Override
    public Object editregion(Integer regionId, String name) {



        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {


            Region region = regionRepository.findById(regionId).orElse(null);

            region.setName(name);
            regionRepository.save(region);

            rdata.put("success", 1);
            rdata.put("msg", "successful.");
            rdata.put("region", region.getName());

            return rdata;

        } catch (Exception e) {
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }
    }

    @Override
    public Object editzone(Integer zoneId, String name) {




        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {


            Zone zone = zoneRepository.findById(zoneId).orElse(null);
            zone.setName(name);
            zoneRepository.save(zone);

            rdata.put("success", 1);
            rdata.put("msg", "successful.");
            rdata.put("zone", zone.getName());

            return rdata;

        } catch (Exception e) {
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }

    }

    @Override
    public Object editarea(Integer areaId, String name) {


        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {


            Area area = (Area) areaRepository.findById(areaId);

            area.setName(name);
            areaRepository.save(area);

        rdata.put("success", 1);
        rdata.put("msg", "successful.");
            rdata.put("area", area.getName());

        return rdata;

    } catch (Exception e) {
        rdata.put("success", 0);
        rdata.put("msg", "An error occured! ");
        return rdata;
    }
    }

    @Override
    public Object updatecluster(Integer clusterId) {
        Cluster cluster = (Cluster) clusterRepository.findById(clusterId);

        return  cluster;
    }

    @Override
    public Object editcluster(Integer clusterId, String name) {
        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {
       Cluster cluster = (Cluster) clusterRepository.findById(clusterId);
       cluster.setName(name);
       clusterRepository.save(cluster);


                rdata.put("success", 1);
                rdata.put("msg", "successful.");

            return rdata;

        } catch (Exception e) {
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }




    }

    @Override
    public Object myacqusitionbyid(Integer id, HttpSession request, ModelAndView v) {

        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);
     Building_info building_info = (Building_info) building_infoRepository.findById(id);

     List <Contact_info> contact_infos = contact_infoRepository.findByBuildingId(building_info.getId());

   List  <Images_info> images_info = imags_infoRepository.findByBuildingId(building_info.getId());
        v.setViewName("myacqusition");
        v.addObject("user", admi);
        v.addObject("buildings", building_info);
        v.addObject("contacts", contact_infos);
        v.addObject("images", images_info);
        return v;

    }

    @Override
    public Resource load(String filename) throws MalformedURLException {
 
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
         
      
    }
            }

    @Override
    public Object addimage(Integer buildingId, MultipartFile file) {

        HashMap<String, Object> rdata = new HashMap<String, Object>();

        try {
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

        Images_info images_info = new Images_info();
        images_info.setBuildingId(buildingId);
        images_info.setName(file.getOriginalFilename());
        imags_infoRepository.save(images_info);

        rdata.put("success", 1);
        rdata.put("msg", "successful.");


        return rdata;

    } catch (Exception e) {
        e.printStackTrace();
        rdata.put("success", 0);
        rdata.put("msg", "An error occured! ");
        return rdata;
    }


    }

    @Override
    public Object deleteimage(Integer id) {

        HashMap<String, Object> rdata = new HashMap<String, Object>();

        try {
            Images_info images_info =imags_infoRepository.findById(id);

            Files.delete(this.root.resolve(images_info.getName()));

            imags_infoRepository.delete(images_info);
            rdata.put("success", 1);
            rdata.put("msg", "successful.");


            return rdata;

        } catch (Exception e) {
            e.printStackTrace();
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }
    }

    @Override
    public Object addcontact(Contact_info request) {
        HashMap<String, Object> rdata = new HashMap<String, Object>();

        try {

            contact_infoRepository.save(request);
            rdata.put("success", 1);
            rdata.put("msg", "successful.");


            return rdata;

        } catch (Exception e) {
            e.printStackTrace();
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }
    }

    @Override
    public Object fetchcontact(Integer id) {


        Contact_info contact_info = contact_infoRepository.findById(id);
        return contact_info;
    }

    @Override
    public Object editcontact(Integer contactId, String management_type, String full_names, String phone_number, String id_number) {

        HashMap<String, Object> rdata = new HashMap<String, Object>();

        try {

            Contact_info contact_info = contact_infoRepository.findById(contactId);


            contact_info.setManagement_type(management_type);
            contact_info.setFull_names(full_names);
            contact_info.setPhone_number(phone_number);
            contact_info.setId_number(id_number);
            contact_infoRepository.save(contact_info);




            rdata.put("success", 1);
            rdata.put("msg", "successful.");


            return rdata;

        } catch (Exception e) {
            e.printStackTrace();
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }
    }

    @Override
    public Object editbuilding(Integer buildingId, String building_description, String building_name, String building_type, String possible_sales) {
        HashMap<String, Object> rdata = new HashMap<String, Object>();

        try {



            Building_info building_info = building_infoRepository.findById(buildingId);
            building_info.setBuilding_name(building_name);
            building_info.setBuilding_description(building_description);
            building_info.setPossible_sales(possible_sales);
            building_info.setBuilding_type(building_type);
            building_infoRepository.save(building_info);



            rdata.put("success", 1);
            rdata.put("msg", "successful.");


            return rdata;

        } catch (Exception e) {
            e.printStackTrace();
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }
    }

    @Override
    public Object admin_roles(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);


       List <Roles_admin> roles_admins = roleRepository.findAll();


        v.setViewName("admin_roles");
        v.addObject("user", admi);
        v.addObject("roles", roles_admins);

        return v;
    }

    @Override
    public Object fetchRole(Integer id) {
        Roles_admin rolesAdmin = roleRepository.findAllById(id);
        return rolesAdmin;
    }

    @Override
    public Object getPortalroleById(Integer id, HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);


        List <Roles_admin> roles_admins = roleRepository.findAll();
        Roles_admin role = roleRepository.findAllById(id);

        v.setViewName("edit_roles");
        v.addObject("user", admi);
        v.addObject("role", role);
         return v;
    }



    @Override
    public Object getPortalroleByid(Integer id) {




        Roles_admin role = roleRepository.findAllById(id);

        return role;
    }

    @Override
    public Object editportalRoles(Roles_admin rolesAdmin) {





        HashMap<String, Object> rdata = new HashMap<String, Object>();

        try {



            Roles_admin rolebyid = roleRepository.findAllById(rolesAdmin.getId());
            rolebyid.setName(rolesAdmin.getName());
            rolebyid.setRole(rolesAdmin.getRole());
            rolebyid.setDescription(rolesAdmin.getDescription());
            roleRepository.save(rolebyid);




            rdata.put("success", 1);
            rdata.put("msg", "successful.");


            return rdata;

        } catch (Exception e) {
            e.printStackTrace();
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }
    }

    @Override
    public Object portalUsers(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);


        List<Admi> admi3 = (List<Admi>) admiRepository.findAll();

        v.setViewName("admin_user");
        v.addObject("user", admi);
        v.addObject("users", admi3);

        return v;
    }

    @Override
    public Object getuser(HttpSession request, ModelAndView v, Integer id) {







        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);
        List <Roles_admin>  rolesAdmin = roleRepository.findAll();
        Admi user = admiRepository.findById(id);



        v.setViewName("edit_portalUsers");
        v.addObject("user", admi);
        v.addObject("roles", rolesAdmin);
        v.addObject("admin_user", user);


        return v;
    }

    @Override
    public Object addPermissions(Integer roleId, String[] data) {

        Roles_admin rolesAdmin = roleRepository.findAllById(roleId);

      String[] permissions  = rolesAdmin.getRole();

            rolesAdmin.setRole(data);
return "ddd";


    }

    @Override
    public Object role_edit(Integer roleId, String name, String is_active) {
        Roles_admin rolesAdmin = roleRepository.findAllById(roleId);

        rolesAdmin.setName(name);
        rolesAdmin.setIs_active(is_active);
        roleRepository.save(rolesAdmin);

        return "ddd";
      }



}
