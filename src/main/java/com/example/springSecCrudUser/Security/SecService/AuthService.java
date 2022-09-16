package com.example.springSecCrudUser.Security.SecService;

import com.example.springSecCrudUser.Dto.RegisterDTO;
import com.example.springSecCrudUser.Entity.Users;

public interface AuthService {

    Users findByEmail(String email) throws Exception;
    void registerUser(RegisterDTO user) throws Exception;
}
