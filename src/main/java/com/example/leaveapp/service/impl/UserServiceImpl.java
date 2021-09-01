package com.example.leaveapp.service.impl;

import com.example.leaveapp.model.entity.Leave;
import com.example.leaveapp.model.entity.Role;
import com.example.leaveapp.model.entity.User;
import com.example.leaveapp.model.entity.enums.RoleCategoryName;
import com.example.leaveapp.model.service.LeaveServiceModel;
import com.example.leaveapp.model.service.UserServiceModel;
import com.example.leaveapp.repository.LeaveRepository;
import com.example.leaveapp.repository.RoleRepository;
import com.example.leaveapp.repository.UserRepository;
import com.example.leaveapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;
    private final DBUserService dbUserService;
    private final LeaveRepository leaveRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, ModelMapper modelMapper, DBUserService dbUserService, LeaveRepository leaveRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.modelMapper = modelMapper;
        this.dbUserService = dbUserService;
        this.leaveRepository = leaveRepository;
    }

    @PostConstruct
    public void init() {

        if (userRepository.count() == 0) {

            if (roleRepository.count() == 0) {
                Role admin = new Role(RoleCategoryName.ADMIN);
                Role manager = new Role(RoleCategoryName.MANAGER);
                Role employee = new Role(RoleCategoryName.EMPLOYEE);

                this.roleRepository.saveAndFlush(admin);
                this.roleRepository.saveAndFlush(manager);
                this.roleRepository.saveAndFlush(employee);

            }

            User admin = new User();
            admin.setDateOfAppointment(LocalDate.now())
                    .setFirstName("Bozhidar")
                    .setLastName("Bozhidarov")
                    .setUsername("bozhidarov86")
                    .setEmail("bozhidarov_86@mail.bg")
                    .setPassword(encoder.encode("123456"))
                    .setDateOfAppointment(LocalDate.now())
                    .setRole(roleRepository.findById(1))
                    .setJobPosition("Admin")
                    .setTotalDaysLeave(20);
            User manger = new User();
            manger.setDateOfAppointment(LocalDate.now())
                    .setFirstName("Elica")
                    .setLastName("Dimova")
                    .setUsername("Elica")
                    .setEmail("elica@mail.bg")
                    .setPassword(encoder.encode("123456"))
                    .setDateOfAppointment(LocalDate.now())
                    .setRole(roleRepository.findById(2))
                    .setJobPosition("Manager")
                    .setTotalDaysLeave(20);
            User employee1 = new User();
            employee1.setDateOfAppointment(LocalDate.now())
                    .setFirstName("Samuil")
                    .setLastName("Bozhidarov")
                    .setUsername("Samuil")
                    .setEmail("samuil@mail.bg")
                    .setPassword(encoder.encode("123456"))
                    .setDateOfAppointment(LocalDate.now())
                    .setRole(roleRepository.findById(3))
                    .setJobPosition("Seller")
                    .setTotalDaysLeave(20);
            User employee2 = new User();
            employee2.setDateOfAppointment(LocalDate.now())
                    .setFirstName("Alexander")
                    .setLastName("Bozhidarov")
                    .setUsername("Alexander")
                    .setEmail("alexander@mail.bg")
                    .setPassword(encoder.encode("123456"))
                    .setDateOfAppointment(LocalDate.now())
                    .setRole(roleRepository.findById(3))
                    .setJobPosition("accounter")
                    .setTotalDaysLeave(20);

            userRepository.saveAndFlush(admin);
            userRepository.saveAndFlush(manger);
            userRepository.saveAndFlush(employee1);
            userRepository.saveAndFlush(employee2);


            Leave firstNotAprLeave = new Leave();
            firstNotAprLeave
                    .setUser(admin)
                    .setApproved(false)
                    .setStartDay(LocalDate.of(2021,9,5))
                    .setEndDay(LocalDate.of(2021,9,10))
                    .setDays(4);
            Leave secondNotAprLeave = new Leave();
            secondNotAprLeave
                    .setUser(manger)
                    .setApproved(false)
                    .setStartDay(LocalDate.of(2021,9,5))
                    .setEndDay(LocalDate.of(2021,9,10))
                    .setDays(4);
            Leave firstAprLeave = new Leave();
            firstAprLeave
                    .setUser(employee1)
                    .setApproved(true)
                    .setStartDay(LocalDate.of(2021,9,5))
                    .setEndDay(LocalDate.of(2021,9,10))
                    .setDays(4);
            Leave secondAprLeave = new Leave();
            secondAprLeave
                    .setUser(employee2)
                    .setApproved(true)
                    .setStartDay(LocalDate.of(2021,9,5))
                    .setEndDay(LocalDate.of(2021,9,10))
                    .setDays(4);


            leaveRepository.saveAndFlush(firstNotAprLeave);
            leaveRepository.saveAndFlush(secondNotAprLeave);
            leaveRepository.saveAndFlush(firstAprLeave);
            leaveRepository.saveAndFlush(secondAprLeave);
        }
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        User user = modelMapper.map(userServiceModel, User.class);

        user.setRole(roleRepository.findById(3))
                .setTotalDaysLeave(20)
                .setDateOfAppointment(LocalDate.parse(userServiceModel.getDateOfAppointment()))
                .setPassword(encoder.encode(user.getPassword()));


        user = userRepository.saveAndFlush(user);

        UserDetails principal = dbUserService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return modelMapper.map(user, UserServiceModel.class);
    }


    @Override
    public UserServiceModel findByUsername(String username) {

        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {

            return modelMapper.map(user, UserServiceModel.class);
        } else
            return null;
    }

    @Override
    public UserServiceModel findById(Long id) {

        User user = userRepository.findById(id).orElse(null);

        return modelMapper.map(user,UserServiceModel.class);
    }

    @Override
    public List<LeaveServiceModel> findAllMyLeave(Long id) {

        User user = userRepository.findById(id).orElse(null);
        List<Leave> leaveList = user.getLeave();
        List<LeaveServiceModel> leaveServiceModelList = new LinkedList<>();

        for (int i = 0; i <leaveList.size() ; i++) {
            leaveServiceModelList.add(modelMapper.map(leaveList.get(i),LeaveServiceModel.class));
        }


        return leaveServiceModelList;
    }

    @Override
    public List<UserServiceModel> showAllEmployees() {

        List<User> allUser = userRepository.findAll();
        List<UserServiceModel> allUserService = new LinkedList<>();

        allUser.forEach(u->allUserService.add(modelMapper.map(u,UserServiceModel.class)));



        return allUserService;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if (user!=null){
            userRepository.delete(user);
        }

    }

    @Override
    public void changeRole(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(roleRepository.findById(2));
        user=userRepository.saveAndFlush(user);
    }
}
