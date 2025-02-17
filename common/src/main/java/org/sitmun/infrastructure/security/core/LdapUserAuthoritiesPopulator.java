package org.sitmun.infrastructure.security.core;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;
 
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("ldap")
@Slf4j
@RequiredArgsConstructor
@Component
public class LdapUserAuthoritiesPopulator implements LdapAuthoritiesPopulator {
 
    private final UserDetailsService userDetailsService;
 
    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
        Collection<? extends GrantedAuthority> authorities = new HashSet<>();
        try {
            authorities = userDetailsService.loadUserByUsername(username).getAuthorities();
        } catch(UsernameNotFoundException ue) {
        	throw ue;        
    	} catch (Exception e) {
            log.error("Exception occurred while trying to fetch the user authorities from the database");
        }
        return authorities;
    }
}
