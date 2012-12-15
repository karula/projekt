package ee.projekt.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Piiripunkt
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Piiripunkt.findById", query = "SELECT p from Piiripunkt p where p.id = :id")
	
})
public class Piiripunkt implements Serializable {

	@Id
	@TableGenerator(name="PPIIR_SEQ",
			table="SEQUENCE",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_COUNT",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="PPIIR_SEQ")
	private int id;
	@NotNull
	@Digits(integer=2, fraction=10)
	private double gpslaiuskraad;
	@NotNull
	@Digits(integer=2, fraction=10)
	private double gpspikkuskraad;
	
	@Valid
	@NotNull(message="Please provide your name")
	@Size(min=2, message="Please provide your name")
	private String kommentaar;
	@NotNull
	private String kood;
	@NotNull
	@Digits(integer=100000,fraction=3)
	private double korgusmerepinnast;
	private String nimetus;
	@NotNull
	@Digits(integer=1000,fraction=0)
	private int version;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getGpslaiuskraad() {
		return gpslaiuskraad;
	}

	public void setGpslaiuskraad(double gpslaiuskraad) {
		this.gpslaiuskraad = gpslaiuskraad;
	}

	public double getGpspikkuskraad() {
		return gpspikkuskraad;
	}

	public void setGpspikkuskraad(double gpspikkuskraad) {
		this.gpspikkuskraad = gpspikkuskraad;
	}

	public String getKommentaar() {
		return kommentaar;
	}

	public void setKommentaar(String kommentaar) {
		this.kommentaar = kommentaar;
	}

	public String getKood() {
		return kood;
	}

	public void setKood(String kood) {
		this.kood = kood;
	}

	public double getKorgusmerepinnast() {
		return korgusmerepinnast;
	}

	public void setKorgusmerepinnast(double korgusmerepinnast) {
		this.korgusmerepinnast = korgusmerepinnast;
	}

	public String getNimetus() {
		return nimetus;
	}

	public void setNimetus(String nimetus) {
		this.nimetus = nimetus;
	}

	public int getVersion() {
		return version;
	}

	public void setVersioon(int versioon) {
		this.version = versioon;
	}

	public Collection<Vahtkond> getVahtkond() {
		return vahtkond;
	}

	public void setVahtkond(Collection<Vahtkond> vahtkond) {
		this.vahtkond = vahtkond;
	}

	@OneToMany(mappedBy="piiripunkt")
	private Collection<Vahtkond> vahtkond;
	
	@OneToMany(mappedBy="piiripunkt_id")
	private Collection<PiiriloiguHaldaja> piiriloiguhaldaja;
	

	private static final long serialVersionUID = 1L;

	public Piiripunkt() {
		super();
	}
   
}
