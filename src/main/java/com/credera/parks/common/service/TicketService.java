package com.credera.parks.common.service;

import com.credera.parks.common.model.Ticket;
import com.credera.parks.common.repository.CategoryRepository;
import com.credera.parks.common.repository.EmployeeRepository;
import com.credera.parks.common.repository.ParkRepository;
import com.credera.parks.common.repository.TicketRepository;
import com.credera.parks.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final EmployeeRepository employeeRepository;
    private final ParkRepository parkRepository;

    public TicketService(TicketRepository ticketRepository,
                         CategoryRepository categoryRepository,
                         EmployeeRepository employeeRepository,
                         ParkRepository parkRepository) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.employeeRepository = employeeRepository;
        this.parkRepository = parkRepository;
    }

    public Ticket updateTicket(Ticket updateTicket, Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(NotFoundException::ticketNotFound);
        updateTicket.setCategory(categoryRepository.findById(updateTicket.getCategoryId()).orElseThrow(NotFoundException::categoryNotFound));
        if (updateTicket.getEmployeeUsername() != null) {
            updateTicket.setEmployee(employeeRepository.findById(updateTicket.getEmployeeUsername()).orElseThrow(NotFoundException::employeeNotFound));
        }
        updateTicket.setPark(parkRepository.findById(updateTicket.getParkId()).orElseThrow(NotFoundException::parkNotFound));
        updateTicket.setId(id);
        updateTicket.setDateCreated(ticket.getDateCreated());
        return ticketRepository.saveAndFlush(updateTicket);
    }

    public Ticket createTicket(Ticket createdTicket){
        createdTicket.setCategory(categoryRepository.findById(createdTicket.getCategoryId()).orElseThrow(NotFoundException::categoryNotFound));
        createdTicket.setPark(parkRepository.findById(createdTicket.getParkId()).orElseThrow(NotFoundException::parkNotFound));
        return ticketRepository.save(createdTicket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllParkTickets(Long parkId){ return ticketRepository.findByPark_id(parkId); }

}
