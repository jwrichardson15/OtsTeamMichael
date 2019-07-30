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
    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name="category_id", updatable = false, insertable = false)
    private Category category;

    @Column(name="date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="username")
    private String employeeUsername;
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name="username", updatable = false, insertable = false)
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

}
