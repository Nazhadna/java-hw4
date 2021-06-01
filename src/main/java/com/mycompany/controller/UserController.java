package com.mycompany.controller;

import com.mycompany.model.User;
import com.mycompany.service.SendingMail;
import com.mycompany.service.UserFileWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;

@Controller
public class UserController {

    @Autowired
    SendingMail sendingEmailApplication;

    User currentUser;

    @GetMapping("/user_search")
    public String userSearch(Model model) {
        model.addAttribute("user", new User());
        return "searching/usersearch";
    }

    @PostMapping("/user_search")
    public String userSearch(Model model, @ModelAttribute("user") User user, HttpServletRequest request) {
        UserFileWorker fileWorker = new UserFileWorker("userlist.txt");
        currentUser = fileWorker.findUser(user);

        if (currentUser == null) {
            return "redirect:user_not_found";
        }
        model.addAttribute("user", currentUser);
        model.addAttribute("userAgent", request.getHeader("User-Agent"));
        HttpSession session = request.getSession(true);
        model.addAttribute("time", new Time(session.getLastAccessedTime()));

        return "searching/searchingResult";
    }

    @GetMapping(value = "/send")
    public String sendMessage(Model model) {
        model.addAttribute("message","Hello!");
        model.addAttribute("user", currentUser);
        return "mailing/sendingmail";
    }

    @PostMapping(value = "/send")
    public String sendMessage(@RequestParam(value = "message") String message) {
        try {
            sendingEmailApplication.sendEmail(currentUser.getEmail(), message);
        } catch (MailException e) {
            return "redirect:wrong_email";
        }
        return "mailing/mailsent";
    }

    @GetMapping("/user_not_found")
    public String userNotFound(){
        return "searching/wrongdata";
    }

    @GetMapping("/wrong_email")
    public String wrongFilepath(){
        return "mailing/wrongemail";
    }

}
