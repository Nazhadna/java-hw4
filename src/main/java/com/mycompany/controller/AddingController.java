package com.mycompany.controller;

import com.mycompany.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class AddingController {

    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }

    @GetMapping ("/userform")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "adding/userform";
    }

    @PostMapping("/userform")
    public String addUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "adding/userform";

        try(FileWriter writer = new FileWriter("userlist.txt", true))
        {
            writer.write(user.toString()+'\n');
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return "adding/addingResult";
    }

}
