package domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@NamedQueries({
	@NamedQuery(name="Reservatie.findByName", 
 	       query = "SELECT r FROM Reservatie r WHERE r.gereserveerdVoor LIKE CONCAT(:gereserveerdVoor,'%')")
    	})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
public class Reservatie implements Serializable{
	private static final long serialVersionUID = 1L;
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	@Getter @Setter private Dier dier;
	@Getter @Setter private LocalDate reservatieDatum;
	@Getter @Setter private String gereserveerdVoor;
	public Reservatie(Dier dier, String gereserveerdVoor)
	{
		this.dier = dier;
		if(!dier.isReedsGereserveerd())
		{
			this.gereserveerdVoor = gereserveerdVoor;
			reservatieDatum = LocalDate.now();
		}
		else
		{
			throw new Error("Dier met naam " + dier.getNaam() + " is reeds gereserveerd door iemand anders.");
		}
	}
}
