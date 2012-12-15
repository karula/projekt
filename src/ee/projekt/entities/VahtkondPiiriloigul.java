package ee.projekt.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: VahtkondPiiriloigul
 *
 */
@Entity

public class VahtkondPiiriloigul implements Serializable {

	@Id
	@TableGenerator(name="VKPLOIGUL_SEQ",
			table="SEQUENCE",
			pkColumnName="SEQ_NAME",
			valueColumnName="SEQ_COUNT",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="VKPLOIGUL_SEQ")
	private int id;
	private Date alates;
	private Date kuni;
	private int version;
	@ManyToOne
	@JoinColumn(name="VAHTKOND_ID")
	private Vahtkond vahtkond_id;
	public Vahtkond getVahtkond_id() {
		return vahtkond_id;
	}

	public void setVahtkond_id(Vahtkond vahtkond_id) {
		this.vahtkond_id = vahtkond_id;
	}

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
	
	private static final long serialVersionUID = 1L;

	public VahtkondPiiriloigul() {
		super();
	}
   
}
