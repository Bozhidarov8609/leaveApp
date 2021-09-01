package com.example.leaveapp.web;

import com.example.leaveapp.model.binding.LeaveRequestBindingModel;
import com.example.leaveapp.model.entity.Leave;
import com.example.leaveapp.model.service.LeaveServiceModel;
import com.example.leaveapp.repository.LeaveRepository;
import com.example.leaveapp.service.LeaveService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/leave")
public class LeaveController {
    private final LeaveService leaveService;
    private final ModelMapper modelMapper;
    private final LeaveRepository leaveRepository;


    public LeaveController(LeaveService leaveService, ModelMapper modelMapper, LeaveRepository leaveRepository) {
        this.leaveService = leaveService;
        this.modelMapper = modelMapper;
        this.leaveRepository = leaveRepository;
    }

    @GetMapping("/request")
    public String requestForLeave(Model model){
        if (!model.containsAttribute("leaveRequestBindingModel")){
            model.addAttribute("leaveRequestBindingModel", new LeaveRequestBindingModel());
            model.addAttribute("notEnoughDays",false);
            model.addAttribute("notToBeInThePast",false);
        }
        return "request-for-leave";
    }

    @PostMapping("/request")
    public String requestConfirm(@Valid @ModelAttribute LeaveRequestBindingModel leaveRequestBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("leaveRequestBindingModel",leaveRequestBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.leaveRequestBindingModel",bindingResult);
            return "redirect:request";
        }else {
           LeaveServiceModel leaveServiceModel= leaveService.requestForLeave(modelMapper.map(leaveRequestBindingModel, LeaveServiceModel.class));

           boolean isPresentOrFuture= true;
           boolean s = LocalDate.now().isAfter(LocalDate.parse(leaveServiceModel.getStartDay()));
           boolean e = LocalDate.now().isAfter(LocalDate.parse(leaveServiceModel.getEndDay()));
           if (s||e){
               isPresentOrFuture=false;
           }


           if (leaveServiceModel==null||!isPresentOrFuture){
               redirectAttributes.addFlashAttribute("leaveRequestBindingModel",leaveRequestBindingModel);
               redirectAttributes.addFlashAttribute("notEnoughDays",true);
               redirectAttributes.addFlashAttribute("notToBeInThePast",true);
               redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.leaveRequestBindingModel",bindingResult);
               return "redirect:request";

           }
           Leave leave = modelMapper.map(leaveServiceModel, Leave.class);
           leave.setStartDay(LocalDate.parse(leaveServiceModel.getStartDay()));
           leave.setEndDay(LocalDate.parse(leaveServiceModel.getEndDay()));
           leaveRepository.saveAndFlush(leave);
           return "redirect:/home";
        }

    }
   @GetMapping("/chahgeStatus/{id}")
        public String changeStatus(@PathVariable("id") Long id){
        leaveService.changeStatus(id);
        return "redirect:/home";
   }
    @GetMapping("/delete/{id}")
    public String disapproved(@PathVariable("id") Long id){
        leaveService.disapproved(id);
        return "redirect:/home";
    }

}
