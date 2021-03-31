package com.cdilansrod.apirestlansrod.repositories;

import com.cdilansrod.apirestlansrod.entities.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

}
