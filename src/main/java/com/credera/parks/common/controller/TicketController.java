package com.credera.parks.common.controller;

import com.credera.parks.common.dto.TicketDTO;
import com.credera.parks.common.model.Ticket;
import com.credera.parks.common.service.TicketService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update ticket", nickname = "updateTicket", notes = "updates a ticket by it's id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TicketDTO.class),
            @ApiResponse(code = 400, message = "Ticket ID is invalid"),
            @ApiResponse(code = 404, message = "Ticket not found")
    })
    public ResponseEntity<TicketDTO> updateTicket(@Valid @RequestBody TicketDTO request, @PathVariable long id) {
        Ticket ticket = new Ticket(request);
        Ticket returnTicket = ticketService.updateTicket(ticket, id);
        return ResponseEntity.ok(new TicketDTO(returnTicket));
    }
}
