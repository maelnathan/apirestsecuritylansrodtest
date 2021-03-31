package com.cdilansrod.apirestlansrod.repositories;

import com.cdilansrod.apirestlansrod.entities.Entreprise;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{
}
