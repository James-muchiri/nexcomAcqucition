package com.directcore.NexcomAcquisitionPortal.services;


import com.directcore.NexcomAcquisitionPortal.model.*;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.text.ParseException;

public interface AdminService {







    Object login(String email, String password, Model model, HttpSession request, ModelAndView v);

    String logout(Model model, HttpSession request);

    Object newportalUsers(Admi newportalUser);

    String portalRoles(Model model);

    Object newportalRoles(Roles_admin roles, HttpSession request);

    Object index(HttpSession request, ModelAndView v);

    Object acqusition(HttpSession request, ModelAndView v);



    Object myacqusition(HttpSession request, ModelAndView v);

    Object teritories(HttpSession request, ModelAndView v);

    Object addregion(Region request);

    Object region(Integer id, ModelAndView v, HttpSession request);

    Object addzone(Integer regionId, String name, String description);

    Object zone(Integer id, ModelAndView v, HttpSession request);

    Object addarea(Integer zoneId, String name, String description);

    Object area(Integer id, ModelAndView v, HttpSession request);



    Object getzonesbyid(Integer id);

    Object getareabyid(Integer id);

    Object getclusterbyid(Integer id);

    Object editregion(Integer regionId, String name);

    Object editzone(Integer zoneId, String name);

    Object editarea(Integer areaId, String name);

    Object updatecluster(Integer clusterId);

    Object editcluster(Integer clusterId, String name);

    Object myacqusitionbyid(Integer id, HttpSession request, ModelAndView v);

    Resource load(String filename) throws MalformedURLException;

    Object addimage(Integer buildingId, MultipartFile file);

    Object deleteimage(Integer id);

    Object addcontact(Contact_profile request);

    Object fetchcontact(Integer id);


    Object editcontact(Integer contactId, String management_type, String full_names, String phone_number, String id_number);

    Object editbuilding(Integer buildingId, String building_description, String building_name, String building_type, String possible_sales);

    Object admin_roles(HttpSession request, ModelAndView v);



    Object editportalRoles(Roles_admin rolesAdmin);

    Object portalUsers(HttpSession request, ModelAndView v);

    Object getuser(HttpSession request, ModelAndView v, Integer id);

    Object getPortalroleByid(Integer id);

    Object addPermissions(Integer roleId, String[] data);

    Object role_edit(Integer roleId, String name, String is_active);

    Object getuserbyid(Integer id);

    Object viewAll(HttpSession request, ModelAndView v);

    Object view_ba_search(String search);

    Object view_ba(Integer search, Integer search_type) throws ParseException;

    Object view_teri(String search);

    Object view_region_search(String search);

    Object Teritories_search(HttpSession request, ModelAndView v);

    Object useraddrole(Integer roleid, Integer userid);

    Object fetchadmins();

    Object addbuildings(HttpSession req, Building_form request, MultipartFile photo, MultipartFile file);


    Object editbuildings(Integer buildingId, Building_form request);

    Object edit_1_edit_accessright(String buildingcode, Building_form request);

    Object addcluster(Integer areaId, String name, String description, String clustertype, String clusterother, MultipartFile[] files);

    Object cluster(Integer id, ModelAndView v, HttpSession request);

    Object editcluster(Cluster request);

    Object forgotpassword(ModelAndView v, HttpSession request);

    Object admin_forgot(String email, Model model, HttpSession request, ModelAndView v);

    Object changePassword(HttpSession request, String token, ModelAndView v, Model model);

    Object admin_reset_password(String token, String confpassword, String password, Model model, HttpSession request, ModelAndView v);
}
