package com.credera.parks.common.service;

import com.credera.parks.common.model.Ticket;
import com.credera.parks.common.repository.*;
import com.credera.parks.common.util.EmailUtil;
import com.credera.parks.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Value("${parks.email.enabled}")
    private boolean emailEnabled;

    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final EmployeeRepository employeeRepository;
    private final ParkRepository parkRepository;
    private final StatusRepository statusRepository;
    private final EmailUtil emailUtil;

    public TicketService(TicketRepository ticketRepository,
                         CategoryRepository categoryRepository,
                         EmployeeRepository employeeRepository,
                         ParkRepository parkRepository,
                         StatusRepository statusRepository,
                         EmailUtil emailUtil) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.employeeRepository = employeeRepository;
        this.parkRepository = parkRepository;
        this.statusRepository = statusRepository;
        this.emailUtil = emailUtil;
    }

    public Ticket updateTicket(Ticket updateTicket, Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(NotFoundException::ticketNotFound);
        updateTicket.setCategory(categoryRepository.findById(updateTicket.getCategoryId()).orElseThrow(NotFoundException::categoryNotFound));
        if (updateTicket.getEmployeeUsername() != null) {
            updateTicket.setEmployee(employeeRepository.findById(updateTicket.getEmployeeUsername()).orElseThrow(NotFoundException::employeeNotFound));
        }
        updateTicket.setPark(parkRepository.findById(updateTicket.getParkId()).orElseThrow(NotFoundException::parkNotFound));
        updateTicket.setStatus(statusRepository.findById(updateTicket.getStatusId()).orElseThrow(NotFoundException::statusNotFound));
        updateTicket.setId(id);
        updateTicket.setDateCreated(ticket.getDateCreated());
        if (emailEnabled && !ticket.getStatus().getName().equals("Completed") && updateTicket.getStatus().getName().equals("Completed")) {
            emailUtil.send(false, updateTicket);
        }
        return ticketRepository.saveAndFlush(updateTicket);
    }

    public Ticket createTicket(Ticket createdTicket){
        createdTicket.setCategory(categoryRepository.findById(createdTicket.getCategoryId()).orElseThrow(NotFoundException::categoryNotFound));
        createdTicket.setPark(parkRepository.findById(createdTicket.getParkId()).orElseThrow(NotFoundException::parkNotFound));
        createdTicket.setStatus(statusRepository.findById(createdTicket.getStatusId()).orElseThrow(NotFoundException::statusNotFound));
        if (emailEnabled) {
            emailUtil.send(true, createdTicket);
        }
        return ticketRepository.save(createdTicket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAllByOrderByIdDesc();
    }

    public List<Ticket> getAllParkTickets(Long parkId){ return ticketRepository.findByPark_idOrderByIdDesc(parkId); }

    public List<Ticket> getEmployeeTickets(String employeeUsername) {
        return ticketRepository.findByEmployee_usernameOrderByIdDesc(employeeUsername);
    }
}
