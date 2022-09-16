package com.example.springSecCrudUser.Services.impl;

import com.example.springSecCrudUser.Dto.EditUserDTO;
import com.example.springSecCrudUser.Dto.NewUserDTO;
import com.example.springSecCrudUser.Entity.Rol;
import com.example.springSecCrudUser.Entity.Users;
import com.example.springSecCrudUser.Repositories.UsersRepository;
import com.example.springSecCrudUser.Services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class IUsersService implements UsersService {
    
    Logger logger= LoggerFactory.getLogger(IUsersService.class);

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Users> getAllUsers() throws Exception {
        List<Users> users= new ArrayList<>();
        try {
            users= usersRepository.findAll();
            return users;
        }catch (Exception error){
            logger.error(error.getMessage());
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public void deleteUserById(Integer id) throws Exception {
        Optional<Users> userexist= usersRepository.findById(id);
        if(userexist.isPresent()){
            try{
                usersRepository.delete(userexist.get());
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }else{
            throw new Exception("Operation not valid ,user not exist");
        }
    }

    @Override
    public Users updateUserByEmail(Users user,String email) throws Exception {
        Users findbyemail= usersRepository.findByEmail(email);
        if(findbyemail != null) {
            try{
                user.setId(findbyemail.getId());
                usersRepository.save(findbyemail);
                return user;
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }else{
            throw new Exception("You can update a user has not exist");
        }
    }

    @Override
    public Users findUserById(Integer id) throws Exception {
        try{
            Optional<Users> userfind= usersRepository.findById(id);
            if(userfind.isPresent()){
                return userfind.get();
            }else{
                throw new Exception("Not found");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Users editUser(EditUserDTO user) throws Exception {
        Users userfind= usersRepository.findById(user.getId()).get();
        Users nuser= new Users();
        nuser.setId(user.getId());
        nuser.setUsername(user.getUsername());
        nuser.setEmail(user.getEmail());
        nuser.setPassword(userfind.getPassword());
        nuser.setRole(Rol.valueOf(user.getRole()));
        
        try{
            Users useredit= usersRepository.save(nuser);
            return useredit;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Users addNewUser(NewUserDTO user) throws Exception {
        Users nuser= new Users();
        nuser.setUsername(user.getUsername());
        nuser.setEmail(user.getEmail());
        nuser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        nuser.setRole(Rol.valueOf(user.getRole()));
        try {
            return usersRepository.save(nuser);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
