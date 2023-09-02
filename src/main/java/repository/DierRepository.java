package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domain.Dier;
import domain.Guest;

public interface DierRepository extends CrudRepository<Dier, Long> {

	List<Dier> findByName(String name);

	List<Dier> findByRas(@Param("ras") String ras);
	List<Dier> getAllSorted();
	List<Dier> findById(@Param("dierId")int id);

   
/*
 * 
 *     @Query("SELECT d FROM Dier d WHERE d.name LIKE CONCAT(:diernaam,'%')")
    Optional<Guest> findByName(@Param("diernaam") String diernaam);
    @Query("SELECT d FROM Dier d WHERE d.name LIKE CONCAT(:diernaam,'%')")
    Optional<Guest> findByRas(@Param("diernaam") String diernaam);
 */

}
