package com.credera.parks.common.controller;

import com.credera.parks.common.dto.EmployeeDTO;
import com.credera.parks.common.dto.TicketDTO;
import com.credera.parks.common.model.Employee;
import com.credera.parks.common.model.Ticket;
import com.credera.parks.common.service.EmployeeService;
import com.credera.parks.common.service.TicketService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final TicketService ticketService;

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(TicketService ticketService, EmployeeService employeeService) {
        this.ticketService = ticketService;
        this.employeeService = employeeService;
    }

    @GetMapping
    @ApiOperation(value="Get all employees", nickname = "getEmployees", notes="gets the information of all the employees in the system")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        List<EmployeeDTO> employeesDTOs = employees.stream().map(EmployeeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(employeesDTOs);
    }

    @GetMapping("/me")
    @ApiOperation(value = "Get the currently logged in employee", nickname = "getSelf", notes = "gets information about the currently logged in user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<EmployeeDTO> me(Principal principal) {
        Employee employee = employeeService.getByUsername(principal.getName());
        return ResponseEntity.ok(new EmployeeDTO(employee));
    }

    @GetMapping("/tickets")
    @ApiOperation(value = "Get all tickets for the logged in user", nickname = "getEmployeeTickets", notes = "gets all the tickets for the logged in user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TicketDTO.class),
            @ApiResponse(code = 400, message = "Employee username is invalid"),
            @ApiResponse(code = 404, message = "Employee not found")
    })
    public ResponseEntity<List<TicketDTO>> getEmployeeTickets(@RequestParam(value = "username", required = true) String employeeUsername) {
        List<Ticket> employeeTickets = ticketService.getEmployeeTickets(employeeUsername);
        List<TicketDTO> employeeTicketDTOs = employeeTickets.stream().map(TicketDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(employeeTicketDTOs);
    }

}
