package com.credera.parks.common.service;

import com.credera.parks.common.dto.TicketDTO;
import com.credera.parks.common.model.Ticket;
import com.credera.parks.common.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService {

        @Autowired
        TicketRepository ticketRepository;

        TicketDTO ticketDTO;

        public List<Ticket> getAllTickets(){
            return ticketRepository.findAll();
        }

        public List<Ticket> getAllParkTickets(Long parkId){ return ticketRepository.findByPark_id(parkId); }

    }
