package com.credera.parks.common.model;

import com.credera.parks.common.dto.TicketDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@Getter @Setter @NoArgsConstructor
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(generator="id", strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;
    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name="category_id", updatable = false, insertable = false)
    private Category category;

    @Column(name="date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="employee_username")
    private String employeeUsername;
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name="employee_username", updatable = false, insertable = false)
    private Employee employee;

    private String email;

    @Column(name = "park_id")
    private Long parkId;
    @ManyToOne(targetEntity = Park.class)
    @JoinColumn(name="park_id", updatable = false, insertable = false)
    private Park park;

    @Column(name="employee_notes")
    private String employeeNotes;
    @Column
    private String description;

    public Ticket(TicketDTO ticketDTO) {
        this.id = ticketDTO.getId();
        this.categoryId = ticketDTO.getCategoryId();
        this.dateCreated = ticketDTO.getDateCreated();
        this.employeeUsername = ticketDTO.getEmployeeUsername();
        this.parkId = ticketDTO.getParkId();
        this.email = ticketDTO.getEmail();
        this.employeeNotes = ticketDTO.getEmployeeNotes();
        this.description = ticketDTO.getDescription();
    }

}
