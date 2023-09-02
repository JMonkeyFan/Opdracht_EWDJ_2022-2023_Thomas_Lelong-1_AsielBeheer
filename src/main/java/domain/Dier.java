package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import repository.VerblijfplaatsRepository;

@Entity
@NamedQueries({
		@NamedQuery(name = "Dier.findByName", query = "SELECT d FROM Dier d WHERE d.naam LIKE CONCAT(:diernaam,'%')"),
		@NamedQuery(name = "Dier.findByRas", query = "SELECT d FROM Dier d WHERE d.ras LIKE CONCAT(:ras,'%')"),
		@NamedQuery(name = "Dier.getAllSorted", query = "SELECT d FROM Dier d ORDER BY d.ras, d.geboorteDatum") })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
public class Dier implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Getter
	private String naam;
	// private ImageIcon foto;
	@Setter
	@Getter
	private String ras;
	@Setter
	@Getter
	private String geslacht;
	@Setter
	@Getter
	private LocalDate geboorteDatum;
	@Setter
	@Getter
	private double medischeKosten;
	@Setter
	@Getter
	private boolean kanMetJongeKinderen;
	@Setter
	@Getter
	private String identificationCode;

	@Setter
	@Getter
	private boolean kanMetOudereKinderen;
	@Setter
	@Getter
	private boolean kanMetKatten;
	@Setter
	@Getter
	private boolean kanMetHonden;
	@Setter
	@Getter
	private boolean geschiktAlsBinnenkat;
	@Setter
	@Getter
	private boolean reedsGereserveerd;

	public Dier(String naam, String ras, String identificationCode, String geslacht, LocalDate geboorteDatum,
			double medischeKosten, boolean kanMetJongeKinderen, boolean kanMetOudereKinderen, boolean kanMetKatten,
			boolean kanMetHonden, boolean geschiktAlsBinnenkat, boolean reedsGereserveerd) {
		super();
		this.identificationCode = identificationCode;
		this.naam = naam;
		this.ras = ras;
		this.geslacht = geslacht;
		this.geboorteDatum = geboorteDatum;
		this.medischeKosten = medischeKosten;
		this.kanMetJongeKinderen = kanMetJongeKinderen;
		this.kanMetOudereKinderen = kanMetOudereKinderen;
		this.kanMetKatten = kanMetKatten;
		this.kanMetHonden = kanMetHonden;
		this.geschiktAlsBinnenkat = geschiktAlsBinnenkat;
		this.reedsGereserveerd = reedsGereserveerd;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setRas(String ras) {
		this.ras = ras;
	}

	public void setGeslacht(String geslacht) { // Todo, enum maken of controle op invoer(enkel man, vrouw)
		this.geslacht = geslacht;
	}

	public void setMedischeKosten(double medischeKosten) {
		this.medischeKosten = medischeKosten;
	}

	public void setKanMetJongeKinderen(boolean kanMetJongeKinderen) {
		this.kanMetJongeKinderen = kanMetJongeKinderen;
	}

	public void setKanMetOudereKinderen(boolean kanMetOudereKinderen) {
		this.kanMetOudereKinderen = kanMetOudereKinderen;
	}

	public void setKanMetKatten(boolean kanMetKatten) {
		this.kanMetKatten = kanMetKatten;
	}

	public void setKanMetHonden(boolean kanMetHonden) {
		this.kanMetHonden = kanMetHonden;
	}

	public void setGeschiktAlsBinnenkat(boolean geschiktAlsBinnenkat) {
		this.geschiktAlsBinnenkat = geschiktAlsBinnenkat;
	}

	public void setReedsGereserveerd(boolean reedsGereserveerd) {
		this.reedsGereserveerd = reedsGereserveerd;
	}

	public void setGeboorteDatum(LocalDate geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}

	public int getLeeftijd() {
		LocalDate date2 = LocalDate.now();
		Period period = geboorteDatum.until(date2);
		return period.getYears();
	}

	public String getNaam() {
		return naam;
	}

	public String getRas() {
		return ras;
	}

	public String getGeslacht() {
		return geslacht;
	}

	public double getMedischeKosten() {
		return medischeKosten;
	}

	public boolean isKanMetJongeKinderen() {
		return kanMetJongeKinderen;
	}

	public boolean isKanMetOudereKinderen() {
		return kanMetOudereKinderen;
	}

	public boolean isKanMetKatten() {
		return kanMetKatten;
	}

	public boolean isKanMetHonden() {
		return kanMetHonden;
	}

	public boolean isGeschiktAlsBinnenkat() {
		return geschiktAlsBinnenkat;
	}

	public boolean isReedsGereserveerd() {
		return reedsGereserveerd;
	}

}
