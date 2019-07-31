package com.credera.parks.common.controller;

import com.credera.parks.common.dto.TicketDTO;
import com.credera.parks.common.model.Ticket;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final TicketService ticketService;

    @Autowired
    public EmployeeController(TicketService ticketService) {
        this.ticketService = ticketService;
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
