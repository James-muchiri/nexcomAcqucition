package com.directcore.NexcomAcquisitionPortal.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;


@Service
public class HelperService {

    private static final Logger logger = LoggerFactory.getLogger(HelperService.class);


    public boolean isPriviledgeAllowed(String path, HttpSession session) {

        if (inAntMatches(path) || inExemptedUrls(path)) {
            return true;
        } else {// check for session
            if (session != null && session.getAttribute("subscriberId") != null) {
                return true;
            } else {

                return false;
            }
        }

    }//close fxn

    public boolean inAntMatches(String path) {

        String[] values = {"/css/", "/js/", "/resources/", "/others/",
                "/images/", "/assets/", "/plugins/", "favicon.ico" };

        ArrayList<String> resources = new ArrayList<String>();
        Collections.addAll(resources, values);

        for (String exp : resources) {
            if (path.startsWith(exp)) {
                return true;
            }
        }

        return false;

    }


    public boolean inExemptedUrls(String path) {

        String[] values = {"/login", "/get_otp", "/logout", "/",
                "/test", "/dologin", "/doSignUp", "/setPassword", "/doSetPassword", "/api/GetAllSites",
                "/getSiteDetails", "/index", "/home", "/signup", "/doSignUp", "/doActivate", "/resetPassword", "/doResetPassword", "/activate_account" };

        ArrayList<String> resources = new ArrayList<String>();
        Collections.addAll(resources, values);

        if (resources.contains(path)) {
            return true;
        }

        return false;
    }
}
