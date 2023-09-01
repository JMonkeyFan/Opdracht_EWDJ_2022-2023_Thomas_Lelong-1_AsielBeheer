package domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	@NamedQuery(name="Verblijfplaats.findByName", 
 	       query = "SELECT v FROM Verblijfplaats v WHERE v.dier.naam LIKE CONCAT(:dierNaam,'%')")
    	})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id") 
@ToString(exclude = "id")
public class Verblijfplaats implements Serializable{
	private static final long serialVersionUID = 1L;
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 @Getter @Setter private int hokCode1;
	 @Getter @Setter private int hokCode2;
	 @Getter @Setter private String hokNaam;
	 @ManyToOne
	 @Getter @Setter private Dier dier;
	 public Verblijfplaats(int hokCode1, int hokCode2, String hokNaam)
	 {
		 this.hokCode1 = hokCode1;
		 this.hokCode2 = hokCode2;
		 this.hokNaam = hokNaam;
	 }
	 public Verblijfplaats(int hokCode1, int hokCode2, String hokNaam, Dier dier)
	 { 
		 this.hokCode1 = hokCode1;
		 this.hokCode2 = hokCode2;
		 this.hokNaam = hokNaam;
		 this.dier = dier;
	 }
	 
	 public boolean isBezet() 
	 {
		 return dier != null;
	 }

	 public void reserveer(Dier dier)
	 {
		 if(!isBezet())
		 {
			setDier(dier);
		 }
		 else
		 {
			 throw new Error("Hok " + hokNaam + " Reeds Bezet");
		 }
	 }
}
