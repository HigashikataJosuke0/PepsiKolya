package com.example.PepsiKolya.controller;

import com.example.PepsiKolya.model.User;
import com.example.PepsiKolya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/allusers")
      public String getAllUsers(ModelMap model) {
        model.addAttribute("allusers", userService.getAll());
        return "allusers";
    }

    @RequestMapping("/addnewuser")
    public String addNewUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "/addnewuser";
    }

    @RequestMapping("saveUser")
    public String saveUser(ModelMap modelMap, @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelMap.addAttribute(user);
            return "redirect:/allusers";
        }

        userService.save(user);

        return "redirect:/allusers";
    }

    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("usrId") long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/addnewuser";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("usrId") long id) {
        userService.removeById(id);
        return "redirect:/allusers";
    }
}
