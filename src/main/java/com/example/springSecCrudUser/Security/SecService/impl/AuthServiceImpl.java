package com.example.springSecCrudUser.Security.SecService.impl;

import com.example.springSecCrudUser.Dto.RegisterDTO;
import com.example.springSecCrudUser.Entity.Rol;
import com.example.springSecCrudUser.Entity.Users;
import com.example.springSecCrudUser.Repositories.UsersRepository;
import com.example.springSecCrudUser.Security.SecService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Users findByEmail(String email) throws Exception {
        try{
            return usersRepository.findByEmail(email);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void registerUser(RegisterDTO user) throws Exception {
        Users u= new Users();
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        u.setRole(Rol.USER);
        try{
            usersRepository.saveAndFlush(u);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
