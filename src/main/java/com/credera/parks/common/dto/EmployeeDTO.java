package com.credera.parks.common.dto;

import com.credera.parks.common.model.Employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
public class EmployeeDTO implements Serializable {

    private String username;
    private String fName;
    private String lName;
    private Long parkId;
    private String parkName;

    public EmployeeDTO(Employee employee) {
        this.username = employee.getUsername();
        this.fName = employee.getFName();
        this.lName = employee.getLName();
        this.parkId = employee.getPark_id();
        this.parkName = employee.getPark().getParkName();
    }
}
