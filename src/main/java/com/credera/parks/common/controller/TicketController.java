package com.credera.parks.common.controller;

import com.credera.parks.common.dto.TicketDTO;
import com.credera.parks.common.model.Ticket;
import com.credera.parks.common.service.TicketService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {


    @Autowired
    TicketService ticketService;

    @GetMapping("")
    @ApiOperation(value = "Get All Tickets test", nickname = "getAllTickets", notes = "returns all the tickets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity getAllTicketsByPark(@RequestParam(value = "park", required = false, defaultValue = "-1") Long parkId) {
        List<Ticket> ticketList = new ArrayList<>();

        if(parkId != -1) {
            ticketList = ticketService.getAllParkTickets(parkId);
        } else {
            ticketList = ticketService.getAllTickets();
        }

        List<TicketDTO> ticketDTOList = ticketList.stream().map(TicketDTO::new).collect(Collectors.toList());
        return new ResponseEntity(ticketDTOList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update ticket", nickname = "updateTicket", notes = "updates a ticket by it's id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TicketDTO.class),
            @ApiResponse(code = 400, message = "Ticket ID is invalid"),
            @ApiResponse(code = 404, message = "Ticket not found")
    })
    public ResponseEntity<TicketDTO> updateTicket(@Valid @RequestBody TicketDTO request, @PathVariable Long id) {
        Ticket ticket = new Ticket(request);
        Ticket returnTicket = ticketService.updateTicket(ticket, id);
        return ResponseEntity.ok(new TicketDTO(returnTicket));
    }

    @PostMapping("")
    @ApiOperation(value = "Create ticket", nickname = "createTicket", notes = "creates a new ticket")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TicketDTO.class)
    })
    public ResponseEntity<TicketDTO> createTicket(@Valid @RequestBody TicketDTO request) {
        Ticket ticket = new Ticket(request);
        Ticket returnTicket = ticketService.createTicket(ticket);
        return new ResponseEntity(new TicketDTO(returnTicket), HttpStatus.CREATED);
    }
}
