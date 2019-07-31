package com.credera.parks.common.service;

import com.credera.parks.common.model.Employee;
import com.credera.parks.common.repository.EmployeeRepository;
import com.credera.parks.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getByUsername(String username) {
        return employeeRepository.findByUsername(username).orElseThrow(NotFoundException::ticketNotFound);
    }

}
