package ee.projekt.entities;

import java.io.Serializable;
import java.util.Collection;


import javax.persistence.*;




/**
 * Entity implementation class for Entity: Vaeosa
 *
 */
@Entity

public class Vaeosa implements Serializable {

	@Id
	private int id;
	private String kommentaar;
	private String kood;
	private String nimetus;
	private int version;
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

	@OneToMany(mappedBy="vaeosa")
	private Collection<Vahtkond> vahtkond;
	
	

	public Collection<Vahtkond> getVahtkond() {
		return vahtkond;
	}

	public void setVahtkond(Collection<Vahtkond> vahtkond) {
		this.vahtkond = vahtkond;
	}

	private static final long serialVersionUID = 1L;

	public Vaeosa() {
		super();
	}
   
}
