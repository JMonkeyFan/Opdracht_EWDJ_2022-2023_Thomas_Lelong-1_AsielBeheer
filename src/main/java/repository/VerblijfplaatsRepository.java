package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import domain.Dier;
import domain.Verblijfplaats;

public interface VerblijfplaatsRepository extends CrudRepository<Verblijfplaats, Long> {

	List<Verblijfplaats> findByDier(Dier dier);

    List<Verblijfplaats> findByName(String name);
    
   
/*
 * 
 *     @Query("SELECT d FROM Dier d WHERE d.name LIKE CONCAT(:diernaam,'%')")
    Optional<Guest> findByName(@Param("diernaam") String diernaam);
    @Query("SELECT d FROM Dier d WHERE d.name LIKE CONCAT(:diernaam,'%')")
    Optional<Guest> findByRas(@Param("diernaam") String diernaam);
 */

}
