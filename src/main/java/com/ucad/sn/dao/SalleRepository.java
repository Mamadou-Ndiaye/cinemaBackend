package com.ucad.sn.dao;

import com.ucad.sn.entities.Categorie;
import com.ucad.sn.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface SalleRepository extends JpaRepository<Salle,Long> {
}
