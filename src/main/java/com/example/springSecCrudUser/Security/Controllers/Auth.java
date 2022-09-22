package com.example.springSecCrudUser.Security.Controllers;

import com.example.springSecCrudUser.Dto.RegisterDTO;
import com.example.springSecCrudUser.Entity.Users;
import com.example.springSecCrudUser.Security.SecService.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/auth")
public class Auth {

    @Autowired
    AuthServiceImpl authService;

    @GetMapping("/register")
    public String getRegister(Model model){
        Users user= new Users();
        model.addAttribute("user", user);
        return "Auth/register";
    }

    @PostMapping("/register")
    public RedirectView postUser(@ModelAttribute RegisterDTO data){
	System.out.println(data);  
        try{
            authService.registerUser(data);
            return new RedirectView("/auth/register?success");
        }catch (Exception error){
            System.out.println(error.getMessage());
            return new RedirectView("/auth/register?error");
        }
    }
    
    @GetMapping("/logout")
    public String getlogout(){
        return "Auth/logout";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("section","login");
        return "Auth/login";
    }
}
