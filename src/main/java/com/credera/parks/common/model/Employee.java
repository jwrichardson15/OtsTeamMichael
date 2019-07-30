package com.credera.parks.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "employee")
@Getter @Setter @NoArgsConstructor
public class Employee implements Serializable {

//    @Id
//    @GeneratedValue(generator="id", strategy = GenerationType.IDENTITY)
//    private Long id;
    @Id
    private String username;
    @Column(name = "fname")
    private String fName;
    @Column(name = "lname")
    private String lName;
    @Column
    private String password;
    @Column
    private Long park_id;

    @ManyToOne(targetEntity = Park.class)
    @JoinColumn(name="park_id", updatable = false, insertable = false)
    private Park park;

}
