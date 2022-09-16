package com.example.springSecCrudUser.Services;
import com.example.springSecCrudUser.Dto.EditUserDTO;
import com.example.springSecCrudUser.Dto.NewUserDTO;
import com.example.springSecCrudUser.Entity.Users;
import java.util.List;

public interface UsersService {
    List<Users> getAllUsers() throws Exception;
    void deleteUserById(Integer id) throws Exception;
    Users updateUserByEmail(Users user,String email)throws Exception;
    Users findUserById(Integer id) throws Exception;
    Users editUser(EditUserDTO user) throws Exception;
    Users addNewUser(NewUserDTO user) throws Exception;
}