package com.ucad.sn.entities;

import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Date;

// Projection de spring est different de ma classe projection vaudrait mieux de renommer la classe
@Projection(name = "ticketProj",types = {com.ucad.sn.entities.Ticket.class})
@CrossOrigin
public interface ProjectionTicket {
    // http://localhost:8085/projections/1?projection=ticketProj pour acceder les donnees avec cette url
    public  Long getId();
    public  Integer getCodePayement();
    public  String getNomClient();
    public  double getPrix();
    public  boolean getReserve();
    public  Place getPlace();
    public com.ucad.sn.entities.Projection getProjection();

}
