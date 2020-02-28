package com.bps.employee.service;

import com.bps.employee.model.Employee;
import com.bps.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void store(Employee employee){
        employeeRepository.store(employee);
    }
}
