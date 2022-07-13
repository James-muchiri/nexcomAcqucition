package com.directcore.NexcomAcquisitionPortal.services;


import com.directcore.NexcomAcquisitionPortal.model.*;
import com.directcore.NexcomAcquisitionPortal.repositories.*;
import com.directcore.NexcomAcquisitionPortal.repositories.Contact_infoRepository;
import com.directcore.NexcomAcquisitionPortal.validation.UpdatableBCrypt;
import com.directcore.NexcomAcquisitionPortal.validation.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    public Object addbuilding(Building_information request) {




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
            building_info.setBuilding_photos(request.getBuilding_photos());
            building_info.setPossible_sales(request.getPossible_sales());
            building_info.setBuilding_type(request.getBuilding_type());


            Contact_info contact_info = new Contact_info();

            contact_info.setBuildingId(building_info.getId());
            contact_info.setManagement_type(request.getManagement_type());
            contact_info.setFull_names(request.getFull_names());
            contact_info.setPhone_number(request.getPhone_number());
            contact_info.setId_number(request.getId_number());







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


}