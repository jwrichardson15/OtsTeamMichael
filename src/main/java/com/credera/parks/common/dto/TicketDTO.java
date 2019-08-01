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

    public TicketDTO(long categoryId, String email, long parkId, String description, long statusId) {
        this.categoryId = categoryId;
        this.email = email;
        this.parkId = parkId;
        this.description = description;
        this.statusId = statusId;
    }

    public TicketDTO(long id, long categoryId, String email, long parkId, String description, long statusId) {
        this.id = id;
        this.categoryId = categoryId;
        this.email = email;
        this.parkId = parkId;
        this.description = description;
        this.statusId = statusId;
    }
}
