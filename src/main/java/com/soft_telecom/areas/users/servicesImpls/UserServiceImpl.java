package com.soft_telecom.areas.users.servicesImpls;

import com.soft_telecom.areas.plans.customerPlan.entities.CustomerPlanImpl;
import com.soft_telecom.areas.plans.mobilePlans.exceptions.LackOfMoneyException;
import com.soft_telecom.configuration.Errors;
import com.soft_telecom.entities.Role;
import com.soft_telecom.areas.users.entities.User;
import com.soft_telecom.areas.users.models.bindingModels.EditUserModel;
import com.soft_telecom.areas.users.models.bindingModels.UserRegistrationModel;
import com.soft_telecom.areas.users.models.viewModels.UserViewModel;
import com.soft_telecom.areas.users.repositories.UserRepository;
import com.soft_telecom.areas.users.services.UserService;
import com.soft_telecom.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private RoleService roleService;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegistrationModel userRegistrationModel) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = this.modelMapper.map(userRegistrationModel, User.class);
        String newPassword = bCryptPasswordEncoder.encode(userRegistrationModel.getPassword());
        user.setPassword(newPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        Role role = this.roleService.getDefaultRole();
        user.addRole(role);

        this.userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.delete(id);
    }

    @Override
    public EditUserModel getUserByUsername(String username) {
        User user = this.userRepository.findOneByUsername(username);
        EditUserModel editUserModel = this.modelMapper.map(user, EditUserModel.class);

        return editUserModel;
    }

    @Override
    public void editUser(EditUserModel editUserModel) {
        User oldUser = this.userRepository.findOne(editUserModel.getId());
        User user = this.modelMapper.map(editUserModel, User.class);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setBonusPoints(oldUser.getBonusPoints());
        user.setPlans(oldUser.getPlans());
        user.setPassword(oldUser.getPassword());
        user.setAuthorities(oldUser.getAuthorities());

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        List<User> users = this.userRepository.getAllWithRoleUser();
        List<UserViewModel> viewModels = new ArrayList<>();

        for (User user : users) {
            UserViewModel viewModel = this.modelMapper.map(user, UserViewModel.class);
            viewModels.add(viewModel);
        }

        return viewModels;
    }

    @Override
    public EditUserModel getUserById(long id) {
        User user = this.userRepository.findOne(id);
        EditUserModel editUserModel = this.modelMapper.map(user, EditUserModel.class);

        return editUserModel;
    }

    @Override
    public void getPlan(CustomerPlanImpl customerPlan, String username) throws LackOfMoneyException {
        User user = this.userRepository.findOneByUsername(username);
        double originalPlanPrice = customerPlan.getPricePerMonth();
        double discount = ((double) user.getBonusPoints()) / 100;
        double planPrice = originalPlanPrice - (originalPlanPrice * discount) ;

        if (user.getMoney() >= planPrice){
            user.addPlan(customerPlan);
            user.setBonusPoints(user.getBonusPoints() + customerPlan.getBonuses());
            user.setMoney(user.getMoney() - planPrice);

        } else {
            throw new LackOfMoneyException();
        }

        this.userRepository.save(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }
}
