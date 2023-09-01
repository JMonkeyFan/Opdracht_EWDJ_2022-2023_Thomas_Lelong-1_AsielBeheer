package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import domain.Dier;
import domain.Reservatie;

public interface ReservatieRepository extends CrudRepository<Reservatie, Long> {

	List<Reservatie> findByGereserveerdVoor(String name);
	List<Reservatie> findByDier(Dier dier);
	

    
   
/*
 * 
 *     @Query("SELECT d FROM Dier d WHERE d.name LIKE CONCAT(:diernaam,'%')")
    Optional<Guest> findByName(@Param("diernaam") String diernaam);
    @Query("SELECT d FROM Dier d WHERE d.name LIKE CONCAT(:diernaam,'%')")
    Optional<Guest> findByRas(@Param("diernaam") String diernaam);
 */

}
