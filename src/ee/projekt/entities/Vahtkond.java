package ee.projekt.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vahtkond
 *
 */
@Entity

public class Vahtkond implements Serializable {

	@Id
	private int id;
	private String kommentaar;
	private String kood;
	private String nimetus;
	private int version;
	@ManyToOne
	@JoinColumn(name="PIIRIPUNKT_ID")
	private Piiripunkt piiripunkt;
	public Piiripunkt getPiiripunkt() {
		return piiripunkt;
	}

	public void setPiiripunkt(Piiripunkt piiripunkt) {
		this.piiripunkt = piiripunkt;
	}

	@OneToMany(mappedBy="vahtkond_id")
	private Collection<VahtkondPiiriloigul> vahtkondpiiriloigul;
	
	public Collection<VahtkondPiiriloigul> getVahtkondpiiriloigul() {
		return vahtkondpiiriloigul;
	}

	public void setVahtkondpiiriloigul(
			Collection<VahtkondPiiriloigul> vahtkondpiiriloigul) {
		this.vahtkondpiiriloigul = vahtkondpiiriloigul;
	}

	@ManyToOne
	@JoinColumn(name="VAEOSA_ID")
	private Vaeosa vaeosa;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Vaeosa getVaeosa() {
		return vaeosa;
	}

	public void setVaeosa(Vaeosa vaeosa) {
		this.vaeosa = vaeosa;
	}

	private static final long serialVersionUID = 1L;

	public Vahtkond() {
		super();
	}
   
}
