package com.example.leaveapp.web;

import com.example.leaveapp.service.LeaveService;
import com.example.leaveapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {
    private final UserService userService;
    private final LeaveService leaveService;

    public HomeController(UserService userService, LeaveService leaveService) {
        this.userService = userService;
        this.leaveService = leaveService;
    }

    @GetMapping("/home")
    public ModelAndView index(ModelAndView modelAndView, Principal principal){

        modelAndView.addObject("user", userService.findByUsername(principal.getName()));
        modelAndView.addObject("notApprovedLeave",leaveService.allNotApprovedLeave());
        modelAndView.addObject("approvedLeave",leaveService.allApprovedLeave());
        modelAndView.setViewName("home");

return modelAndView;
    }
}
