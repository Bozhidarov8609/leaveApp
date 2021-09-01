package com.example.leaveapp.web;

import com.example.leaveapp.model.binding.UserRegisterBindingModel;
import com.example.leaveapp.model.service.LeaveServiceModel;
import com.example.leaveapp.model.service.UserServiceModel;
import com.example.leaveapp.model.view.UserProfileViewModel;
import com.example.leaveapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username, RedirectAttributes redirectAttributes) {


        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);


        return "redirect:/users/login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("userExist", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        } else {
            UserServiceModel userServiceModel = userService.findByUsername(userRegisterBindingModel.getUsername());
            if (userServiceModel != null) {
                redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
                redirectAttributes.addFlashAttribute("userExist", true);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
                return "redirect:register";
            }


            this.userService.registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));


            return "redirect:/home";
        }
    }

    @GetMapping("/profile")
    public ModelAndView myProfile(@RequestParam("id") Long id, ModelAndView modelAndView) {

        UserServiceModel userServiceModel = userService.findById(id);
        modelAndView.addObject("myProfile", modelMapper.map(userServiceModel, UserProfileViewModel.class));
        modelAndView.setViewName("profile");
        return modelAndView;


    }

    @GetMapping("/myLeave")
    public ModelAndView myLeave(@RequestParam("id") Long id, ModelAndView modelAndView) {


        modelAndView.addObject("myAl", false);

        List<LeaveServiceModel> leaveServiceModelList = userService.findAllMyLeave(id);

        if (leaveServiceModelList != null) {

            modelAndView.addObject("myAllLeave", leaveServiceModelList);
        } else {
            modelAndView.addObject("myAl", true);
        }
        modelAndView.setViewName("my-leave");
        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView listOfAllEmployees(ModelAndView modelAndView){


        modelAndView.addObject("allEmployees",userService.showAllEmployees());
        modelAndView.setViewName("all-employees");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String disapproved(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/users/all";
    }

    @GetMapping("/changeRole/{id}")
    public String changeRole(@PathVariable("id") Long id){
        userService.changeRole(id);
        return "redirect:/users/all";
    }
}