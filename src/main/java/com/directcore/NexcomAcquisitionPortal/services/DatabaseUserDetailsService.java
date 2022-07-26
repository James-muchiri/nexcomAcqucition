//package com.directcore.NexcomAcquisitionPortal.services;
//
//import com.directcore.NexcomAcquisitionPortal.model.Admi;
//import com.directcore.NexcomAcquisitionPortal.model.Roles_admin;
//import com.directcore.NexcomAcquisitionPortal.repositories.AdmiRepository;
//import com.directcore.NexcomAcquisitionPortal.seeders.DatabaseSeeder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class DatabaseUserDetailsService implements UserDetailsService {
//
//    private final AdmiRepository admiRepository;
//
//    private static final Logger logger = LoggerFactory.getLogger(DatabaseUserDetailsService.class);
//    public DatabaseUserDetailsService(AdmiRepository admiRepository) {
//        this.admiRepository = admiRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Admi admi = admiRepository.findByEmail(username);
//        if (admi == null) {
//            throw new UsernameNotFoundException("User with username [" + username + "] not found in the system");
//        }
//
//        Collection<GrantedAuthority> authorities = new HashSet<>();
//
//        for (Roles_admin userToRole : admi.getRoles()) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + userToRole.getName()));
//            for (String userRoleToPrivilege : userToRole.getRole()) {
//                authorities.add(new SimpleGrantedAuthority(userRoleToPrivilege));
//            }
//        }
//        String password = admi.getPassword();
//      String UserName = admi.getName();
//
//        logger.info(String.valueOf(authorities));
//        return new org.springframework.security.core.userdetails.User(
//                UserName, "password", true, true, true,
//                true, authorities);
//
//    }
//}