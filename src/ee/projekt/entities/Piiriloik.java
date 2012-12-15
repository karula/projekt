package ee.projekt.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Piiriloik
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Piiriloik.findById", query = "SELECT p from Piiriloik p where p.id = :id")
	
})
public class Piiriloik implements Serializable {

	@Id
	@TableGenerator(name="PLOIK_SEQ",
			table="SEQUENCE",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_COUNT",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="PLOIK_SEQ")
	private int id;
	private String gpskoordinaadid;
	private String kommentaar;
	private String kood;
	@Digits(integer=100000,fraction=3)
	private double korgusmerepinnast;
	private String nimetus;
	@Digits(integer=100000,fraction=0)
	private int version;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGpskoordinaadid() {
		return gpskoordinaadid;
	}

	public void setGpskoordinaadid(String gpskoordinaadid) {
		this.gpskoordinaadid = gpskoordinaadid;
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

	public void setVersion(int version) {
		this.version = version;
	}

	@OneToMany(mappedBy="piiriloik_id")
	private Collection<PiiriloiguHaldaja> piiriloiguhaldaja;
	
	@OneToMany(mappedBy="piiriloik_id")
	private Collection<VahtkondPiiriloigul> vahtkondpiiriloiguls;
	
	public Collection<VahtkondPiiriloigul> getVahtkondpiiriloiguls() {
		return vahtkondpiiriloiguls;
	}

	public void setVahtkondpiiriloiguls(
			Collection<VahtkondPiiriloigul> vahtkondpiiriloiguls) {
		this.vahtkondpiiriloiguls = vahtkondpiiriloiguls;
	}

	private static final long serialVersionUID = 1L;

	public Piiriloik() {
		super();
	}
   
}
