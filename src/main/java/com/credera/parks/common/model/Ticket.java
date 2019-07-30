package com.credera.parks.common.model;

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
    // TODO: Add Category object after creating
    @Column(name="date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name="employee_name")
    private String employeeName;
    // TODO: Add Employee object after creating
    private String email;
    @Column(name = "park_id")
    private Long parkId;
    // TODO: Add Park object after creating

    @Column(name="employee_notes")
    private String employeeNotes;
    private String description;

}
