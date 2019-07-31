package com.credera.parks.common.dto;

import com.credera.parks.common.model.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
public class TicketDTO implements Serializable {

    private Long id;
    private Long categoryId;
    private String categoryName;
    private LocalDateTime dateCreated;
    private String employeeUsername;
    private String email;
    private Long parkId;
    private String parkName;
    private String employeeNotes;
    private String description;
    private Long statusId;
    private String status;

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.categoryId = ticket.getCategoryId();
        this.categoryName = ticket.getCategory().getName();
        this.dateCreated = ticket.getDateCreated();
        this.employeeUsername = ticket.getEmployeeUsername();
        this.email = ticket.getEmail();
        this.parkId = ticket.getParkId();
        this.parkName = ticket.getPark().getParkName();
        this.employeeNotes = ticket.getEmployeeNotes();
        this.description = ticket.getDescription();
        this.statusId = ticket.getStatusId();
        this.status = ticket.getStatus().getName();
    }

    public TicketDTO(int categoryId, String email, int parkId, String description) {
        long lCategoryId = categoryId;
        long lParkId = parkId;
        this.categoryId = lCategoryId;
        this.email = email;
        this.parkId = lParkId;
        this.description = description;
    }

    public TicketDTO(int id, int categoryId, String email, int parkId, String description) {
        long lId = id;
        long lCategoryId = categoryId;
        long lParkId = parkId;

        this.id = lId;
        this.categoryId = lCategoryId;
        this.email = email;
        this.parkId = lParkId;
        this.description = description;
    }



}
