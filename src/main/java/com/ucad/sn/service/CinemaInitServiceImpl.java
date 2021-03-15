package com.ucad.sn.service;


import com.ucad.sn.dao.*;
import com.ucad.sn.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
@CrossOrigin
public class CinemaInitServiceImpl implements  ICinemaService {
    @Autowired
    public VilleRepository villeRepository;
    @Autowired
    public CinemaRepository cinemaRepository;
    @Autowired
    public SalleRepository salleRepository;
    @Autowired
    public PlaceRepository placeRepository;
    @Autowired
    public ProjectionRepository projectionRepository;
    @Autowired
    public  SeanceRepository seanceRepository;
    @Autowired
    public  TicketRepository ticketRepository;
    @Autowired
    public  FilmRepository filmRepository;
    @Autowired
    public  CategorieRepository categorieRepository;
    int a=1;

    @Override
    public void initVilles() {
        Stream.of("Dakar","Thies","Saint Louis").forEach(nameVille->{
            Ville ville = new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v->{
            Stream.of("HotelBlue","Radisson","IMAX","MegaRama").forEach(
                    nameCinema->{
                        Cinema cinema= new Cinema();
                        cinema.setName(nameCinema);
                        cinema.setNombreSalles((int) (3+Math.random()*7));
                        cinema.setVille(v);
                        cinemaRepository.save(cinema);
                    }
            );
        });

    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
           for (int i=0 ;i<cinema.getNombreSalles();i++)
           {
               Salle salle=new Salle();
               salle.setName("Salle"+(i+1));
               salle.setCinema(cinema);
               salle.setNombrePlace((int) (15+Math.random()*20));
               salleRepository.save(salle);
           }
        });

    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
           for(int i=0;i<salle.getNombrePlace();i++)
            {
                Place place=new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });

    }

    @Override
    public void initSeances() {
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
     Stream.of("12:00","15:00","17:00","19:00").forEach(s -> {
       Seance seance=new Seance();
         try {
             seance.setHeureDebut(dateFormat.parse(s));
         } catch (ParseException e) {
             e.printStackTrace();
         }
         seanceRepository.save(seance);
   });
    }

    @Override
    public void initCategories() {
    Stream.of("Histoire","Actions","Drama","Fiction").forEach(cat->{
        Categorie categorie= new Categorie();
        categorie.setName(cat);
        categorieRepository.save(categorie);
    });
    }

    @Override
    public void initFilms() {
        double[] durees= new  double[] {1,2,1.5,3,4,2,1,5,2};
        List<Categorie> categories = categorieRepository.findAll();
        Random random = new Random();
        Stream.of("12 Hommes en colere","Forrest Gump","Green Book","La Ligne verte","Le Parrain","Le Seigneur des anneaux").forEach(titre->{
               Film film=new Film();
                film.setTitre(titre);

                film.setDuree(durees[new Random().nextInt(durees.length)]);
                film.setPhoto(titre.replaceAll(" ",""));
            if(!categories.isEmpty()) {
                film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            }
            filmRepository.save(film);

         });
    }

    @Override
    public void initProjections() {
        double[] prix= new  double[]{2000,1500,3000,1000,4000};
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    filmRepository.findAll().forEach(film -> {
                        seanceRepository.findAll().forEach(seance -> {
                            Projection projection = new Projection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prix[new  Random().nextInt(prix.length)]);
                            projection.setSeance(seance);
                            projectionRepository.save(projection);
                        });
                    });
                });
            });
        });

    }

    @Override
    public void initTickets() {

        List<Place> places =placeRepository.findAll();
        projectionRepository.findAll().forEach(p->{
            if(p!=null) {
                places.forEach(place -> {
                    if(place!=null) {
                        while (a<=10)
                        {
                        Ticket ticket = new Ticket();
                        ticket.setPlace(place);
                        ticket.setReserve(false);
                        ticket.setPrix(p.getPrix());
                        ticket.setProjection(p);
                        ticketRepository.save(ticket);
                        a++;
                        }

                    }
                });
            }
        });
    }
}
