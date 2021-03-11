package com.ucad.sn.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data  @NoArgsConstructor @AllArgsConstructor @ToString
public class Place implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  int numero;
    private  double latitude,longitude,altitude;
    @ManyToOne
    private  Salle salle;
    @OneToMany(mappedBy = "place")
    private Collection<Ticket> tickets;

}
