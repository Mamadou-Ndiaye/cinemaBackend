package com.ucad.sn;

import com.ucad.sn.service.CinemaInitServiceImpl;
import com.ucad.sn.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemabackendApplication implements CommandLineRunner {

    @Autowired
    public ICinemaService iCinemaService;

    public static void main(String[] args) {
        SpringApplication.run(CinemabackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        iCinemaService.initVilles();
        iCinemaService.initCinemas();
        iCinemaService.initSalles();
        iCinemaService.initPlaces();
        iCinemaService.initFilms();
        iCinemaService.initCategories();
        iCinemaService.initProjections();
        iCinemaService.initSeances();
        iCinemaService.initTickets();

    }
}
