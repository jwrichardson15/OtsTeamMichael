package com.credera.parks.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "park")
@Getter @Setter @NoArgsConstructor
public class Park implements Serializable {

    @Id
    @GeneratedValue(generator="id", strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "park_name")
    private String parkName;

}
