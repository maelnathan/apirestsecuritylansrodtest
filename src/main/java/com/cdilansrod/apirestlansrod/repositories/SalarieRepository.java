package com.cdilansrod.apirestlansrod.repositories;

import com.cdilansrod.apirestlansrod.entities.Salarie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;


@SuppressWarnings("unused")
@Repository
public interface SalarieRepository extends JpaRepository<Salarie, Long> {

    @Query("select s from Salarie s where (s.entreprise.id = :entrepriseId OR :entrepriseId is null ) and (s.contrat.id = :contratId OR :contratId is null ) and (s.dateEmbauche =:dateEmbauche or :dateEmbauche is null) and (s.salaire =:salaire or :salaire is null)")
    public Page<Salarie> getList(@Param("entrepriseId") Long  entrepriseId,
                                 @Param("contratId") Long  contratId,
                                 @Param("dateEmbauche") Instant dateEmbauche,
                                 @Param("salaire") BigDecimal salaire,
                                 Pageable pageable);


    @Query("SELECT min(s.salaire) as salairemin,max(s.salaire) as salairemax, avg(s.salaire) as salairemoys FROM Salarie s where(s.entreprise.id = :entrepriseId) and (s.contrat.id = :contratId)")
    public Page<Salarie> getListDetail(@Param("entrepriseId") Long  entrepriseId,
                                       @Param("contratId") Long  contratId,
                                       Pageable pageable);
}
