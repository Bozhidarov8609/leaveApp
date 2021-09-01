package com.example.leaveapp.service.impl;

import com.example.leaveapp.model.entity.Leave;
import com.example.leaveapp.model.entity.User;
import com.example.leaveapp.model.service.LeaveServiceModel;
import com.example.leaveapp.repository.LeaveRepository;
import com.example.leaveapp.repository.UserRepository;
import com.example.leaveapp.service.LeaveService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public LeaveServiceImpl(LeaveRepository leaveRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.leaveRepository = leaveRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LeaveServiceModel requestForLeave(LeaveServiceModel leaveServiceModel) {

        Leave leave = modelMapper.map(leaveServiceModel, Leave.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = modelMapper.map(userRepository.findByUsername(username).orElse(null), User.class);
        leave.setUser(user);

        LocalDate end = LocalDate.parse(leaveServiceModel.getEndDay());
        LocalDate start = LocalDate.parse(leaveServiceModel.getStartDay());

Optional<List<LocalDate>> holidays = allHolidays();
        int daysOfLeave= countBusinessDays( start,
         end,holidays);







        if (daysOfLeave<user.getTotalDaysLeave()){
            leave.setDays(daysOfLeave);
            leave.setApproved(false);
            leave.setStartDay(start);
            leave.setEndDay(end);
            user.setTotalDaysLeave(user.getTotalDaysLeave()-daysOfLeave);
            user=userRepository.saveAndFlush(user);

            return modelMapper.map(leave,LeaveServiceModel.class);
        }else {
            return null;
        }
    }



    @Override
    public List<LeaveServiceModel> allApprovedLeave() {
        List<Leave> leaveList = leaveRepository.findAllApprovedLeave();
        List<LeaveServiceModel> leaveServiceModelList = new LinkedList<>();

        for (int i = 0; i <leaveList.size() ; i++) {
            leaveServiceModelList.add(modelMapper.map(leaveList.get(i),LeaveServiceModel.class));
        }
        return leaveServiceModelList;
    }

    @Override
    public List<LeaveServiceModel> allNotApprovedLeave() {
        List<Leave> leaveList = leaveRepository.findAllNotApprovedLeave();
        List<LeaveServiceModel> leaveServiceModelList = new LinkedList<>();

        for (int i = 0; i <leaveList.size() ; i++) {
            leaveServiceModelList.add(modelMapper.map(leaveList.get(i),LeaveServiceModel.class));
        }
        return leaveServiceModelList;
    }

    @Override
    public void changeStatus(Long id) {
        Leave leave = leaveRepository.findById(id).orElse(null);
        leave.setApproved(true);
        leave=leaveRepository.saveAndFlush(leave);

    }

    @Override
    public void disapproved(Long id) {
        Leave leave = this.leaveRepository.findById(id).orElse(null);
        User user = leave.getUser();
        user.setTotalDaysLeave(user.getTotalDaysLeave()+leave.getDays());
        user=userRepository.saveAndFlush(user);
        leave.setUser(null);

        leaveRepository.save(leave);
        this.leaveRepository.delete(leave);
    }




    private static int countBusinessDays(final LocalDate startDate,
                                         final LocalDate endDate,
                                         final Optional<List<LocalDate>> holidays) {


        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        Predicate<LocalDate> isHoliday = date -> holidays.isPresent()
                && holidays.get().contains(date);


        List<LocalDate> businessDays = startDate.datesUntil(endDate)
                .filter(isWeekend.or(isHoliday).negate())
                .collect(Collectors.toList());


        return businessDays.size();
    }
    private Optional<List<LocalDate>> allHolidays() {

        List<LocalDate> holidays = new LinkedList<>();

        holidays.add(LocalDate.of(2021,1,1));
        holidays.add(LocalDate.of(2021,3,3));
        holidays.add(LocalDate.of(2021,4,30));
        holidays.add(LocalDate.of(2021,5,1));
        holidays.add(LocalDate.of(2021,5,2));
        holidays.add(LocalDate.of(2021,5,3));
        holidays.add(LocalDate.of(2021,5,4));
        holidays.add(LocalDate.of(2021,5,6));
        holidays.add(LocalDate.of(2021,5,24));
        holidays.add(LocalDate.of(2021,9,6));
        holidays.add(LocalDate.of(2021,9,22));
        holidays.add(LocalDate.of(2021,12,24));
        holidays.add(LocalDate.of(2021,12,25));
        holidays.add(LocalDate.of(2021,12,26));
        holidays.add(LocalDate.of(2021,12,27));
        holidays.add(LocalDate.of(2021,12,28));

        return Optional.of(holidays);
    }
}
