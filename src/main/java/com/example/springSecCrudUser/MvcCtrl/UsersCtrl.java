package com.example.springSecCrudUser.MvcCtrl;

import com.example.springSecCrudUser.Dto.EditUserDTO;
import com.example.springSecCrudUser.Dto.NewUserDTO;
import com.example.springSecCrudUser.Entity.Rol;
import com.example.springSecCrudUser.Entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.springSecCrudUser.Services.impl.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UsersCtrl {
    
         @Autowired
         private IUsersService usersService;

         @GetMapping("/users/new")
         public String getAddUser(Model model){
             List<String> roles= new ArrayList<>();
             for(Rol value:Rol.values()){
                 roles.add(value.toString());
             }
             Users user = new Users();
             user.setRole(Rol.USER); // default
             model.addAttribute("roles",roles);
             model.addAttribute("user",user);
             return "views/AddNew";
         }
         @PostMapping("/users/save")
         public RedirectView postNewUser(@ModelAttribute NewUserDTO user, Model model){
             try{
                 usersService.addNewUser(user);
                 return new RedirectView("/");
             }catch (Exception e){
                 model.addAttribute("error","fail to save user");
                 return new RedirectView("/users/new");
             }
         }
         @GetMapping("/users/edit/{id}")
         public String getEditUser(@PathVariable(value = "id") Integer id,Model model){
             List<String>roles= new ArrayList<>();
             roles.add(Rol.USER.toString());
             roles.add(Rol.ADMIN.toString());
             try{
                 Users user= usersService.findUserById(id);
                 model.addAttribute("user",user);
                 model.addAttribute("roles",roles);
                 return "views/EditUser";
             }catch (Exception e){

                 model.addAttribute("error",e.getMessage());
                 return "views/EditUser";
             }
         }
         @PostMapping("/users/edit")
         public RedirectView editUser(@ModelAttribute EditUserDTO user,@RequestParam("id") Integer id, Model model){
             try {
                 user.setId(id);
                 usersService.editUser(user);
                 
                 return new RedirectView("/",true);
             }catch (Exception e){
                 model.addAttribute("error","Fail to edit user");
                 return new RedirectView("/admin/users/edit/"+user.getId());
             }
         }

         @GetMapping("/users/{id}")
         public RedirectView deletById(@PathVariable(value = "id") Integer id,Model model){
            try{
                usersService.deleteUserById(id);
                model.addAttribute("success","User deleted");
                return new RedirectView("/");
            }catch(Exception error){
                model.addAttribute("error","fail to delete user");
                return new RedirectView("/");
            }
         }
}