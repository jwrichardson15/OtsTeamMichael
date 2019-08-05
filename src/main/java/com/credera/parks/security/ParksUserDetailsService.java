package com.credera.parks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.credera.parks.common.model.Employee;
import com.credera.parks.common.service.EmployeeService;

import java.util.ArrayList;

@Service
public class ParksUserDetailsService implements UserDetailsService {

    private final EmployeeService employeeService;

    @Autowired
    public ParksUserDetailsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeService.getByUsername(username);
        if (employee != null) {
            return new User(employee.getUsername(), employee.getPassword(),
                            new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
