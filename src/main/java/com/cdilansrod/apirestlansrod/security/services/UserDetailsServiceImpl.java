package com.cdilansrod.apirestlansrod.security.services;

import com.cdilansrod.apirestlansrod.entities.User;
import com.cdilansrod.apirestlansrod.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        User user = userRepository.findByUsername(username)
                	.orElseThrow(() -> 
                        new UsernameNotFoundException("Utilisateur non trouvé avec -> nom d'utilisateur ou email : " + username)
        );
        return UserPrinciple.build(user);
    }
}
