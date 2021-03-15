package com.ucad.sn.entities;

import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Date;

// Projection de spring est different de ma classe projection vaudrait mieux de renommer la classe
@Projection(name = "p1",types = {com.ucad.sn.entities.Projection.class})
@CrossOrigin
public interface ProjectionProj {
    // http://localhost:8085/projections/1?projection=p1 pour acceder les donnees avec cette url
    public Long getId();
    public  double getPrix();
    public Date getDateProjection();
    public  Salle getSalle();
    public  Film getFilm();
    public  Seance getSeance();
    public Collection<Ticket> getTickets();

}
