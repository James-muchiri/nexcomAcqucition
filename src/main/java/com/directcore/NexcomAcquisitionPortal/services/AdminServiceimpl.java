package com.directcore.NexcomAcquisitionPortal.services;


import com.directcore.NexcomAcquisitionPortal.model.*;
import com.directcore.NexcomAcquisitionPortal.repositories.*;
import com.directcore.NexcomAcquisitionPortal.repositories.Contact_infoRepository;
import com.directcore.NexcomAcquisitionPortal.validation.UpdatableBCrypt;
import com.directcore.NexcomAcquisitionPortal.validation.UserValidator;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminServiceimpl implements com.directcore.NexcomAcquisitionPortal.services.AdminService {

    private final Path root = Paths.get("uploads");
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceimpl.class);

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
    private Building_profileRepository building_profileRepository;

    @Autowired
    private  Imags_infoRepository imags_infoRepository;

    @Autowired
    private  Sales_profileRepository sales_profileRepository;

    @Autowired
    private  Login_logsRepository login_logsRepository;
    @Autowired
    private Access_right_profileRepository access_right_profileRepository;

    @Autowired
    private  PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${url.portal.public}")
    private String contextPat;


    @Autowired
    private JavaMailSender emailSender;

    private Pattern regexPattern;
    private Matcher regMatcher;


    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);




//    @Override
//    public String newUser(Admi userForm) {
//
//
//    //    userForm.setRoles(new HashSet<>(roleRepository.findAll()));
//      //  userForm.setPassword(bcrypt.hash(userForm.getPassword()));
//        userForm.setPassword("fff");
//        admiRepository.save(userForm);
//
//        return "User Registered successful";
//    }


    @Override
    public Object login(String email, String password, Model model, HttpSession request, ModelAndView v) {

        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(email);
        if (!regMatcher.matches()) {
            model.addAttribute("error", "Enter a valid email");
            return "login";
        }
        if (admiRepository.findByEmail(email) == null) {
            model.addAttribute("error", "Email does not exist");

            return "login";
        }






        Admi admi = admiRepository.findByEmail(email);
        if(!bcrypt.verifyHash(password, admi.getPassword() ))
        {
            model.addAttribute("error", "Password is incorrect");
            Login_logs login_logs =new Login_logs();
            login_logs.setAdminid( admi.getId());
            login_logs.setResponse("Incorrect Password");
            login_logsRepository.save(login_logs);
            return "login";
        }

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

        Login_logs login_logs =new Login_logs();
        login_logs.setAdminid( admi.getId());
        login_logs.setResponse("login success");
        login_logsRepository.save(login_logs);





        return "redirect:admin/index";


    }














    @Override
    public String logout(Model model, HttpSession request) {
        request.removeAttribute("user_admin");
        return "redirect:login";
    }



    @Override
    public Object newportalUsers(Admi newportalUser) {

        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {


            Admi admi = admiRepository.findByEmail(newportalUser.getEmail());
            if (admi != null){

                Error1 error = new Error1();
                error.setCode("error");
                error.setMessage("email already in use");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            newportalUser.setPassword(bcrypt.hash(newportalUser.getPassword()));

            admiRepository.save(newportalUser);






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
            roles.setIs_active("yes");
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


        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);
        v.setViewName("index");
        v.addObject("user", admi);


        return v;

    }
    private List<String> getPrivileges(Set<Roles_admin> roles) {
        List<String> privileges = new ArrayList<>();

        for (Roles_admin role : roles) {



            for (String rolee : role.getRole()) {
                privileges.add(rolee);

            }
        }


        return privileges;
    }
    @Override
    public Object acqusition(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);
        List <Region> regions = (List<Region>) regionRepository.findAll();
        List <Cluster> clusters = (List<Cluster>) clusterRepository.findAll();
        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);
        v.setViewName("acqusition");
        v.addObject("user", admi);
        v.addObject("regions", regions);
        v.addObject("clusters", clusters);
        return v;

    }






    @Override
    public Object myacqusition(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);




        List <Building_profile> building_profiles = (List<Building_profile>) building_profileRepository.findByAdminid(admi.getId());
        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);
        v.setViewName("myacqusitions");
        v.addObject("user", admi);
        v.addObject("buildings", building_profiles);
        return v;
    }

    @Override
    public Object teritories(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);


        List<Region> regions = (List<Region>) regionRepository.findAll();
        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);

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


        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);




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

        Region regions = regionRepository.findById(zone.getRegionId()).orElse(null);
        List <Area> areas =  areaRepository.findAllByZoneId(zone.getId());

        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);



        v.setViewName("Zones");
        v.addObject("user", admi);
        v.addObject("regions", regions);
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

        Region regions = regionRepository.findById(area.getRegionId()).orElse(null);
        Zone zones =  zoneRepository.findById(area.getZoneId()).orElse(null);
        List <Cluster> clusters = (List<Cluster>) clusterRepository.findAllByAreaId(area.getId());



        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);

        v.setViewName("areas");
        v.addObject("user", admi);
        v.addObject("regions", regions);
        v.addObject("zones", zones);
        v.addObject("areas", area);
        v.addObject("clusters", clusters);


        return v;
    }

    @Override
    public Object cluster(Integer id, ModelAndView v, HttpSession request) {


        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);

        Cluster cluster = (Cluster) clusterRepository.findById(id);

        Region regions = regionRepository.findById(cluster.getRegionId()).orElse(null);
        Zone zones =  zoneRepository.findById(cluster.getZoneId()).orElse(null);
        Area areas = (Area) areaRepository.findById(cluster.getAreaId());




        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);

        v.setViewName("clusters");
        v.addObject("user", admi);
        v.addObject("regions", regions);
        v.addObject("zones", zones);
        v.addObject("areas", areas);
        v.addObject("cluster", cluster);


        return v;
    }

    @Override
    public Object addcluster(Integer areaId, String name, String description, String clustertype, String clusterother, MultipartFile[] files) {


        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {
            Area area = (Area) areaRepository.findById(areaId);

            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            List<String> fileNames = new ArrayList<>();
            for (MultipartFile file : files) {

                try {
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS").format(new Date());
                    String[] fileFrags = file.getOriginalFilename().split("\\.");
                    String extension = fileFrags[fileFrags.length - 1];
                    String picName = timeStamp + "." + extension;


                    Files.copy(file.getInputStream(), this.root.resolve(picName));
                    fileNames.add(picName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }




            Cluster cluster = new Cluster();




            String[] fileName = fileNames.toArray(new String[0]);
            cluster.setName(name);
            cluster.setFilenames(fileName);
            cluster.setDescription(description);
            cluster.setRegionId(area.getRegionId());
            cluster.setZoneId(area.getZoneId());
            cluster.setAreaId(area.getId());
            cluster.setClustertype(clustertype);
            cluster.setOtherClustertype(clusterother);

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
    public Object editcluster(Cluster request) {
        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {
            Cluster cluster = (Cluster) clusterRepository.findById(request.getId());
            cluster.setName(request.getName());
            cluster.setClustertype(request.getClustertype());
            cluster.setDescription(request.getDescription());
            if(request.getClustertype() == "Other"){
                cluster.setOtherClustertype(request.getOtherClustertype());
            }
            else{
                cluster.setOtherClustertype("");
            }
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
        Building_profile building_info = (Building_profile) building_profileRepository.findById(id);

        List <Contact_profile> contact_infos = contact_infoRepository.findByBuildingcode(building_info.getBuilding_code());
        Sales_profile  sales_profile = (Sales_profile) sales_profileRepository.findByBuildingcode(building_info.getBuilding_code());
        List  <Images_info> images_info = imags_infoRepository.findByBuildingId(building_info.getId());
        Access_right_profile access_right_profile = access_right_profileRepository.findByBuildingcode(building_info.getBuilding_code());

        Cluster cluster = (Cluster) clusterRepository.findById(building_info.getBuilding_cluster());
        Area area = (Area) areaRepository.findById(cluster.getAreaId());
        Zone zone = zoneRepository.findById(cluster.getZoneId()).orElse(null);
        Region region = regionRepository.findById(cluster.getRegion_id()).orElse(null);

        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);
        v.setViewName("myacqusition");
        v.addObject("user", admi);
        v.addObject("buildings", building_info);
        v.addObject("contacts", contact_infos);
        v.addObject("sales_profiles", sales_profile);
        v.addObject("images", images_info);
        v.addObject("cluster", cluster);
        v.addObject("zone", zone);
        v.addObject("area", area);
        v.addObject("region", region);
        v.addObject("access_right_profile", access_right_profile);
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
    //   public String generateUniqueFileName() {
//        String filename = "";
//        long millis = System.currentTimeMillis();
//        String datetime = new Date().toGMTString();
//        datetime = datetime.replace(" ", "");
//        datetime = datetime.replace(":", "");
//        String rndchars = RandomStringUtils.randomAlphanumeric(16);
//        filename = rndchars + "_" + datetime + "_" + millis;
//        return filename;
//    }
    @Override
    public Object addimage(Integer buildingId, MultipartFile file) {

        HashMap<String, Object> rdata = new HashMap<String, Object>();

        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
            String[] fileFrags = file.getOriginalFilename().split("\\.");
            String extension = fileFrags[fileFrags.length-1];
            String picName     = timeStamp + "." + extension;

            //    String picName     = "timeStamp" + ".png";
            Files.copy(file.getInputStream(), this.root.resolve(picName));

            Images_info images_info = new Images_info();
            images_info.setBuildingId(buildingId);
            images_info.setName(picName);
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
    public Object addcontact(Contact_profile request) {
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


        Contact_profile contact_info = contact_infoRepository.findById(id);
        return contact_info;
    }

    @Override
    public Object editcontact(Integer contactId, String management_type, String full_names, String phone_number, String id_number) {

        HashMap<String, Object> rdata = new HashMap<String, Object>();

        try {

            Contact_profile contact_info = contact_infoRepository.findById(contactId);


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



//            Building_info building_info = building_infoRepository.findById(buildingId);
//            building_info.setBuilding_name(building_name);
//            building_info.setBuilding_description(building_description);
//            building_info.setPossible_sales(possible_sales);
//            building_info.setBuilding_type(building_type);
//            building_infoRepository.save(building_info);



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

        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);
        v.setViewName("admin_roles");
        v.addObject("user", admi);
        v.addObject("roles", roles_admins);

        return v;
    }

//    @Override
//    public Object fetchRole(Integer id) {
//        Roles_admin rolesAdmin = roleRepository.findAllById(id);
//        return rolesAdmin;
//    }

//    @Override
//    public Object getPortalroleById(Integer id, HttpSession request, ModelAndView v) {
//        Integer user_admin = (Integer) request.getAttribute("user_admin");
//        Admi admi = admiRepository.findById(user_admin);
//
//
//        List <Roles_admin> roles_admins = roleRepository.findAll();
//        Roles_admin role = roleRepository.findAllById(id);
//        List<String> privileges = getPrivileges(admi.getRoles());
//        logger.info(String.valueOf(privileges));
//        v.addObject("authorities", privileges);
//        v.setViewName("edit_roles");
//        v.addObject("user", admi);
//        v.addObject("role", role);
//         return v;
//    }



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


        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);
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

        List <Login_logs> login_logs = login_logsRepository.findAllByadminid(id);
        List<String> aList = new ArrayList<>();
        for (Roles_admin element : user.getRoles()) {
            aList.add(element.getName());
        }
        String[] continents = {
                "Africa", "Antarctica", "Asia", "Australia",
                "Europe", "North America", "Sourth America"
        };
//String arr[] = new String[0];
//        arr = aList.toArray(arr);

        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);
        v.setViewName("edit_portalUsers");
        v.addObject("user", admi);
        v.addObject("continents", continents);
        v.addObject("roles", rolesAdmin);
        v.addObject("admin_user", user);
        v.addObject("aList", aList);
        v.addObject("login_logs", login_logs);


        return v;
    }

    @Override
    public Object addPermissions(Integer roleId, String[] data) {

        Roles_admin rolesAdmin = roleRepository.findAllById(roleId);

        String[] permissions  = rolesAdmin.getRole();

        rolesAdmin.setRole(data);
        return "permissions Saved";


    }

    @Override
    public Object role_edit(Integer roleId, String name, String is_active) {
        Roles_admin rolesAdmin = roleRepository.findAllById(roleId);

        rolesAdmin.setName(name);
        rolesAdmin.setIs_active(is_active);
        roleRepository.save(rolesAdmin);

        return "Edit role successful";
    }

    @Override
    public Object getuserbyid(Integer id) {
        Admi admi = admiRepository.findById(id);
        List <Roles_admin>  rolesAdmin = roleRepository.findAll();
        Admi user = admiRepository.findById(id);

        List<String> aList = new ArrayList<>();
        for (Roles_admin element : admi.getRoles()) {
            aList.add(element.getName());
        }
        return  aList;
    }


    @Override
    public Object viewAll(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);

        List <Admi> admis = (List<Admi>) admiRepository.findAll();
        List  <Building_profile> building_infos = (List<Building_profile>) building_profileRepository.findAll();

        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);
        v.setViewName("viewAll");
        v.addObject("user", admi);
        v.addObject("users", admis);
        v.addObject("buildings", building_infos);
        return v;
    }


    @Override
    public Object view_ba_search(String search) {

        List<Building_profile> building_infos = building_profileRepository.findByNameLike(search);

        return building_infos;
    }


    @Override
    public Object view_ba(Integer search, Integer search_type) throws ParseException {

        if(search_type == 3){
            if(search == 1) {
                LocalDateTime creationDateTime = LocalDateTime.now().minusDays(7);
//            return this.repository.findAllWithDateAfter(threeDaysAgoDate);
//            @Query("select m from Message m where date >= :threeDaysAgoDate")
//            List<Message> findAllWithDateAfter(@Param("threeDaysAgoDate") LocalDate threeDaysAgoDate);

                List<Building_profile> building_infos = building_profileRepository.findAllWithCreateDateTimeAfter(creationDateTime);

                return building_infos;
            }else {
                LocalDateTime creationDateTime = LocalDateTime.now().minusDays(30);

                List<Building_profile> building_infos = building_profileRepository.findAllWithCreateDateTimeAfter(creationDateTime);

                return building_infos;
            }
        }
        else
        {

            List<Building_profile> building_infos = building_profileRepository.findByAdminid(search);

            return building_infos;
        }



    }


    @Override
    public Object view_teri(String search) {



        HashMap<String, Object> rdata = new HashMap<String, Object>();

        try {


            if (Objects.equals(search, "regions")) {
                List<Region> regions = (List<Region>) regionRepository.findAll();
                rdata.put("success", 1);
                rdata.put("level", "regions");
                rdata.put("regions", regions);
                return rdata;

            } else if (Objects.equals(search, "zones")) {
                List<Zone> zones = (List<Zone>) zoneRepository.findAll();
                rdata.put("success", 1);
                rdata.put("level", "zones");

                rdata.put("zones", zones);
                return rdata;

            } else if (Objects.equals(search, "areas")) {
                List<Area> areas = (List<Area>) areaRepository.findAll();
                rdata.put("success", 1);
                rdata.put("level", "areas");

                rdata.put("areas", areas);
                return rdata;

            } else if (Objects.equals(search, "clusters")){
                List<Cluster> clusters = (List<Cluster>) clusterRepository.findAll();
                rdata.put("success", 1);
                rdata.put("level", "clusters");
                rdata.put("clusters", clusters);
                rdata.put("sef", search);
                return rdata;
            }else{
                rdata.put("sef", search);
                return rdata;
            }










        } catch (Exception e) {
            e.printStackTrace();
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }


    }

    @Override
    public Object view_region_search(String search) {



        HashMap<String, Object> rdata = new HashMap<String, Object>();

        try {


            List <Region> regions = regionRepository.findByNameLike(search);

            List <Zone> zones= zoneRepository.findByNameLike(search);

            List <Area> areas = areaRepository.findByNameLike(search);

            List <Cluster> clusters = clusterRepository.findByNameLike(search);





            rdata.put("success", 1);
            rdata.put("msg", "successful.");
            rdata.put("regions", regions);
            rdata.put("zones", zones);
            rdata.put("areas", areas);
            rdata.put("clusters", clusters);



            return rdata;

        } catch (Exception e) {
            e.printStackTrace();
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }
    }

    @Override
    public Object Teritories_search(HttpSession request, ModelAndView v) {
        Integer user_admin = (Integer) request.getAttribute("user_admin");
        Admi admi = admiRepository.findById(user_admin);

        List<String> privileges = getPrivileges(admi.getRoles());
        logger.info(String.valueOf(privileges));
        v.addObject("authorities", privileges);

        v.setViewName("Teritories_search");
        v.addObject("user", admi);

        return v;
    }

    @Override
    public Object useraddrole(Integer roleid, Integer userid) {
        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {

            Admi admi = admiRepository.findById(userid);
            Roles_admin rolesAdmin = roleRepository.findById(roleid);

            Set<Roles_admin> roles = admi.getRoles();

            boolean ans = roles.contains(rolesAdmin);

            if (ans) {
                roles.remove(rolesAdmin);
                admi.setRoles(roles);
                admiRepository.save(admi);

                rdata.put("msg", "role removed.");
            }
            else{
                roles.add(rolesAdmin);
                admi.setRoles(roles);
                admiRepository.save(admi);
                rdata.put("msg", "role added.");
            }


            rdata.put("success", 1);



            return rdata;

        } catch (Exception e) {
            e.printStackTrace();
            rdata.put("success", 0);
            rdata.put("msg", "An error occured! ");
            return rdata;
        }


    }


    public Object fetchadmins() {

        List <Admi> admis = (List<Admi>) admiRepository.findAll();
        return admis;
    }

    public String generate_code(){

        String code = "BCD";

        String  bcd123 = "";
        Building_profile building_profile = building_profileRepository.findTopByOrderByIdDesc();
        if(building_profile == null){
            bcd123 = "BCD001";
        }else {
            bcd123 = building_profile.getBuilding_code();
        }

//    String person[]  = bcd123.split(":");
        String person[]  = bcd123.split("(?=\\d)(?<=\\D)");
        String name = person[0];
        String number = person[1];

        int foo = Integer.parseInt(number);
        foo = foo + 1;
        String s = Integer.toString(foo);
        code = code + s;
        return code;
    }
    @Override
    public Object addbuildings(HttpSession req, Building_form request, MultipartFile photo, MultipartFile file) {


        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {



            Integer user_admin = (Integer) req.getAttribute("user_admin");
            Admi admi = admiRepository.findById(user_admin);

            String building_code = generate_code();
            Building_profile building_profile = new Building_profile();
            building_profile.setBuilding_code(building_code);
            building_profile.setAdminid(admi.getId());
            building_profile.setBuilding_cluster(request.getBuilding_cluster());
            building_profile.setBuilding_name(request.getBuilding_name());
            building_profile.setAcquisitionPurpose(request.getAcquisitionPurpose());
            building_profile.setUse_type(request.getUse_type());
            building_profile.setBuilding_type(request.getBuilding_type());
            building_profile.setBuilding_state(request.getBuilding_state());
            building_profile.setPower(request.getPower());
            building_profile.setBackup(request.getBackup());
            building_profile.setBackup_text(request.getBackup_text());
            building_profile.setNumberofUnits(request.getNumberofUnits());
            building_profile.setBlocks(request.getBlocks());
            building_profile.setFloors(request.getFloors());
            building_profile.setSecurity(request.getSecurity());
            building_profile.setStreet_name(request.getStreet_name());
            building_profile.setBuilding_description(request.getBuilding_description());
            building_profileRepository.save(building_profile);

            Contact_profile contact_profile = new Contact_profile();

            contact_profile.setBuildingcode(building_code);
            contact_profile.setManagement_type(request.getManagement_type());
            contact_profile.setFull_names(request.getFull_names());
            contact_profile.setPhone_number(request.getPhone_number());
            contact_profile.setId_number(request.getId_number());
            contact_profile.setEmail(request.getEmail());
            contact_infoRepository.save(contact_profile);




            // sales profile
            Sales_profile sales_profile = new Sales_profile();
            sales_profile.setBuildingcode(building_code);
            sales_profile.setPossible_sales(request.getPossible_sales());
            sales_profile.setExsistingProvider(request.getExsistingProvider());
            sales_profile.setWithinternet(request.getWithinternet());
            sales_profile.setHighestrent(request.getHighestrent());
            sales_profile.setMediumrent(request.getMediumrent());
            sales_profile.setLowestrent(request.getLowestrent());
            sales_profile.setLeadingprovider(request.getLeadingprovider());
            sales_profile.setIncomeclass(request.getIncomeclass());
            sales_profile.setTargetplan(request.getTargetplan());

            sales_profileRepository.save(sales_profile);

            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
            String[] fileFrags = photo.getOriginalFilename().split("\\.");
            String extension = fileFrags[fileFrags.length-1];
            String picName     = timeStamp + "." + extension;


            Files.copy(photo.getInputStream(), this.root.resolve(picName));


            Images_info images_info = new Images_info();
            images_info.setBuildingId(building_profile.getId());
            images_info.setName(picName);
            imags_infoRepository.save(images_info);

            String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
            String[] fileFrags1 = file.getOriginalFilename().split("\\.");
            String extension1 = fileFrags1[fileFrags1.length-1];
            String docName     = timeStamp1 + "." + extension1;


            Files.copy(file.getInputStream(), this.root.resolve(docName));

            Access_right_profile access_right_profile =new Access_right_profile();
            access_right_profile.setBuildingcode(building_code);
            access_right_profile.setAccessRights(request.getAccessRights());
            access_right_profile.setAccess_Status(request.getAccess_Status());
            access_right_profile.setAccessRights_text(request.getAccessRights_text());
            access_right_profile.setRoa(docName);
            access_right_profile.setRoa_status(request.getRoa_status());
            access_right_profile.setOther_terms(request.getOther_terms());
            access_right_profileRepository.save(access_right_profile);



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
    public Object editbuildings(Integer buildingId, Building_form request) {


        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {



            Building_profile building_profile =building_profileRepository.findById(buildingId);


            if(request.getBuilding_name() != null){
                building_profile.setBuilding_name(request.getBuilding_name());
            }

            if(request.getAcquisitionPurpose() != null){
                building_profile.setAcquisitionPurpose(request.getAcquisitionPurpose());
            }
            if(request.getUse_type() != null){
                building_profile.setUse_type(request.getUse_type());
            }
            if(request.getBuilding_type() != null){
                building_profile.setBuilding_type(request.getBuilding_type());
            }
            if(request.getBuilding_state() != null){
                building_profile.setBuilding_state(request.getBuilding_state());
            }
            if(request.getPower() != null){
                building_profile.setPower(request.getPower());
            }
            if(request.getBackup() != null){
                building_profile.setBackup(request.getBackup());
            }
            if(request.getBackup_text() != null){
                building_profile.setBackup_text(request.getBackup_text());
            }
            if(request.getNumberofUnits() != null){
                building_profile.setNumberofUnits(request.getNumberofUnits());
            }
            if(request.getBlocks() != null){
                building_profile.setBlocks(request.getBlocks());
            }
            if(request.getFloors() != null){
                building_profile.setFloors(request.getFloors());
            }
            if(request.getSecurity() != null){
                building_profile.setSecurity(request.getSecurity());
            }
            if(request.getStreet_name() != null){
                building_profile.setStreet_name(request.getStreet_name());
            }
            if(request.getBuilding_description() != null){
                building_profile.setBuilding_description(request.getBuilding_description());
            }

            building_profileRepository.save(building_profile);


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
    public Object edit_1_edit_accessright(String buildingcode, Building_form request) {


        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {


            Access_right_profile access_right_profile = access_right_profileRepository.findByBuildingcode(buildingcode);


//            String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
//            String[] fileFrags1 = file.getOriginalFilename().split("\\.");
//            String extension1 = fileFrags1[fileFrags1.length-1];
//            String docName     = timeStamp1 + "." + extension1;
//
//
//            Files.copy(file.getInputStream(), this.root.resolve(docName));


            if(request.getAccessRights() != null){
                access_right_profile.setAccessRights(request.getAccessRights());
            }
            if(request.getAccess_Status() != null){
                access_right_profile.setAccess_Status(request.getAccess_Status());
            }
            if(request.getAccessRights_text() != null){
                access_right_profile.setAccessRights_text(request.getAccessRights_text());
            }
            if(request.getRoa_status() != null){
                access_right_profile.setRoa_status(request.getRoa_status());
            }
            if(request.getOther_terms() != null) {
                access_right_profile.setOther_terms(request.getOther_terms());
            }
            access_right_profileRepository.save(access_right_profile);



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
    public Object forgotpassword(ModelAndView v, HttpSession request) {


        v.setViewName("forgot");


        return v;
    }
    @Bean
    public String getUserBucketPath() {
        return contextPat;
    }
    @Override
    public Object admin_forgot(String email, Model model, HttpSession request, ModelAndView v) {
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(email);
        if (!regMatcher.matches()) {
            model.addAttribute("error", "Enter a valid email");
            return "forgot";
        }
        if (admiRepository.findByEmail(email) == null) {
            model.addAttribute("error", "Email does not exist");

            return "forgot";
        }

        Admi admi = admiRepository.findByEmail(email);
        String token = UUID.randomUUID().toString();



        PasswordResetToken myToken = new PasswordResetToken(token, admi);
        passwordResetTokenRepository.save(myToken);
        String contextPat =getUserBucketPath();

        //  emailSender.send(constructResetTokenEmail(contextPat, token, admi));
        constructResetTokenEmail(contextPat, token, admi);
        return "login";
    }

//    private SimpleMailMessage constructResetTokenEmail(
//            String contextPath, String token, Admi user) throws MessagingException {
//        String url = contextPath + "/user/changePassword?token=" + token;
//        String message = "Hello" + user.getName() + "here is the link to reset your password.";
//        return constructEmail("Reset Password", message + " \r\n" + url, user);
//    }

//    private SimpleMailMessage constructEmail(String subject, String body,
//                                             Admi user) throws MessagingException {
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setSubject(subject);
//        email.setText(body);
//        email.setTo(user.getEmail());
//        email.setFrom("Nexcom Acqusition Portal");
//
//        return email;
//    }

    private void constructResetTokenEmail(
            String contextPath, String token, Admi user)  {
        String url = contextPath + "user/changePassword?token=" + token;
        String message = "Hello" + user.getName() + "here is the link to reset your password.";
        try {
            sendHtmlMessage("Reset Password", "\r\n" + url, user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendHtmlMessage(String subject, String body, Admi user) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject(subject);
        helper.setFrom("system@directcore.com");
        helper.setTo(user.getEmail());

        boolean html = true;
        helper.setText("<b>Hello " + user.getName() +"</b>,<br>Here in the link to reset your Nexcom Acqusition Portal " +
                "password " + "<a href='"  + body + "'>Reset Password</a>", html);
        // return message;
        emailSender.send(message);

        logger.info("Sending email: {} with html body: {}", html);
    }
    @Override
    public Object changePassword(HttpSession request, String token, ModelAndView v, Model model) {
        String result = validatePasswordResetToken(token);
        if(result != null) {

            model.addAttribute("token", token);
            return "invalidtoken";
        } else {
            model.addAttribute("token", token);
            return "updatepassword";
        }

    }

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);

//        return !isTokenFound(passToken) ? "invalidToken"
//                : isTokenExpired(passToken) ? "expired"
//                : null;
        return null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    @Override
    public Object admin_reset_password(String token, String confpassword, String password, Model model, HttpSession request, ModelAndView v) {

        PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
        Admi admi = passToken.getUser();

        admi.setPassword(bcrypt.hash(password));

        admiRepository.save(admi);
        return "redirect:login";
    }

    @Override
    public Object change_password(HttpSession request, String oldpassword, String newpassword, String newpassword_confirmation) {
        HashMap<String, Object> rdata = new HashMap<String, Object>();
        try {


            Integer user_admin = (Integer) request.getAttribute("user_admin");
            Admi admi = admiRepository.findById(user_admin);
            if(!bcrypt.verifyHash(oldpassword, admi.getPassword() ))
            {
                rdata.put("success", 0);
                rdata.put("msg", "An error occured! ");
                return rdata;

            }
            if(!(newpassword.equals(newpassword_confirmation))){
                rdata.put("success", 0);
                rdata.put("msg", "An error occured! ");
                return rdata;
            }

            admi.setPassword(bcrypt.hash(newpassword));

            admiRepository.save(admi);


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
}
