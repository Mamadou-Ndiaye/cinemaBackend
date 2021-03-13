package com.ucad.sn;

import com.ucad.sn.dao.ProjectionRepository;
import com.ucad.sn.entities.Film;
import com.ucad.sn.entities.Projection;
import com.ucad.sn.service.CinemaInitServiceImpl;
import com.ucad.sn.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;
import java.util.ListIterator;

@SpringBootApplication
public class CinemabackendApplication implements CommandLineRunner {

    @Autowired
    public ICinemaService iCinemaService;
    @Autowired
    ProjectionRepository projectionRepository;

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(CinemabackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Film.class);
        iCinemaService.initVilles();
        iCinemaService.initCinemas();
        iCinemaService.initSalles();
        iCinemaService.initPlaces();
        iCinemaService.initSeances();
        iCinemaService.initCategories();
        iCinemaService.initFilms();
        iCinemaService.initProjections();
        iCinemaService.initTickets();

        System.out.println("hello world");
       /* for(Projection elem: projections)
        {
            System.out.println (elem);
        }

*/

    }
}
