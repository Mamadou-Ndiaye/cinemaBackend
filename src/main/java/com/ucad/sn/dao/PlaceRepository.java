package com.ucad.sn.dao;

import com.ucad.sn.entities.Categorie;
import com.ucad.sn.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface PlaceRepository extends JpaRepository<Place,Long> {
}
