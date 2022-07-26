package com.directcore.NexcomAcquisitionPortal.services;

import com.directcore.NexcomAcquisitionPortal.repositories.AdmiRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUserDetailsService implements UserDetailsService {

    private final AdmiRepository admiRepository;

    public DatabaseUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount == null) {
            throw new UsernameNotFoundException("User with username [" + username + "] not found in the system");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();

        for (UserToRole userToRole : userAccount.getUserToRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userToRole.getRole().getRoleName()));
            for (UserRoleToPrivilege userRoleToPrivilege : userToRole.getRole().getUserRoleToPrivileges()) {
                authorities.add(new SimpleGrantedAuthority(userRoleToPrivilege.getPrivilege().getPrivilegeName()));
            }
        }

        return new CustomUserDetails(userAccount.getUsername(), userAccount.getPassword(), userAccount.isActive(), authorities);
    }
}