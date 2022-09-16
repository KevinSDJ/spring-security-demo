package com.example.springSecCrudUser.MvcCtrl;

import com.example.springSecCrudUser.Entity.Users;
import com.example.springSecCrudUser.Services.impl.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class Home {
    @Autowired
    IUsersService usersService;

    @GetMapping("/")
    public String getIndex(Model model) throws Exception {

        List<Users> users= usersService.getAllUsers();
        model.addAttribute("msg","hsadasdsa");
        model.addAttribute("users",users);

        return "Home";
    }
}
