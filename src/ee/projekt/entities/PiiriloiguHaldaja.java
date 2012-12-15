package ee.projekt.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PiiriloiguHaldaja
 *
 */
@Entity

public class PiiriloiguHaldaja implements Serializable {

	@Id
	@TableGenerator(name="PLHALDAJA_SEQ",
			table="SEQUENCE",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_COUNT",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="PLHALDAJA_SEQ")
	private int id;
	private Date alates;
	private String kommentaar;
	private Date kuni;
	private int version;
	@ManyToOne
	@JoinColumn(name="PIIRILOIK_ID")
	private Piiriloik piiriloik_id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAlates() {
		return alates;
	}

	public void setAlates(Date alates) {
		this.alates = alates;
	}

	public String getKommentaar() {
		return kommentaar;
	}

	public void setKommentaar(String kommentaar) {
		this.kommentaar = kommentaar;
	}

	public Date getKuni() {
		return kuni;
	}

	public void setKuni(Date kuni) {
		this.kuni = kuni;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Piiriloik getPiiriloik_id() {
		return piiriloik_id;
	}

	public void setPiiriloik_id(Piiriloik piiriloik_id) {
		this.piiriloik_id = piiriloik_id;
	}

	public Piiripunkt getPiiripunkt_id() {
		return piiripunkt_id;
	}

	public void setPiiripunkt_id(Piiripunkt piiripunkt_id) {
		this.piiripunkt_id = piiripunkt_id;
	}

	@ManyToOne
	@JoinColumn(name="PIIRIPUNKT_ID")
	private Piiripunkt piiripunkt_id;
	private static final long serialVersionUID = 1L;

	public PiiriloiguHaldaja() {
		super();
	}
   
}
