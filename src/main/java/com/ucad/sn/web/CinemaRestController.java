package com.ucad.sn.web;


import com.ucad.sn.dao.FilmRepository;
import com.ucad.sn.dao.TicketRepository;
import com.ucad.sn.entities.Film;
import com.ucad.sn.entities.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
public class CinemaRestController {
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    TicketRepository ticketRepository;

    @GetMapping(value = "/imageFilm/{id}",produces = MediaType.APPLICATION_PDF_VALUE)
    public  byte[] images(@PathVariable (name = "id")Long id) throws IOException {
         Film film =filmRepository.findById(id).get();
         String photoName=film.getPhoto();
         File file= new File(System.getProperty("user.home")+"/cinema/images/"+photoName+".jpg");
         Path path= Paths.get(file.toURI());
         return Files.readAllBytes(path);
    }

    @PostMapping("/payerTickets")
    @Transactional
    public  List<Ticket> payerTicket(@RequestBody TicketForm ticketForm)
    {
        List<Ticket> ticketList=new  ArrayList<>();
           ticketForm.getTickets().forEach(idTicket-> {
               Ticket ticket=ticketRepository.findById(idTicket).get();
               ticket.setNomClient(ticketForm.getNomClient());
               ticket.setCodePayement(ticketForm.getCodePayement());
               ticket.setReserve(true);
               ticketRepository.save(ticket);
               ticketList.add(ticket);
           });
          return  ticketList;
    }
}

@Data
class  TicketForm{
    private  String nomClient;
    private  int codePayement ;
    private  List<Long> tickets= new ArrayList<>();

}
