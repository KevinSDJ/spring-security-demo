package com.example.springSecCrudUser.Security.SecService.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springSecCrudUser.Entity.Users;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AuthServiceImpl authServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        try{
            Users customer = authServiceImpl.findByEmail(username);
            if(customer != null){

                return new UserDetailimpl(customer);
            }else{

                throw new UsernameNotFoundException("User not found");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new UsernameNotFoundException("Error to search user");
        }
    }
    
}
