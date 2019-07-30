package com.credera.parks.common.controller;

import com.credera.parks.common.dto.TicketDTO;
import com.credera.parks.common.model.Ticket;
import com.credera.parks.common.service.TicketService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
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
}

