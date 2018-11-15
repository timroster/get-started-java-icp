package wasdev.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the VISIT database table.
 * 
 */
@Entity
@NamedQueries({
   @NamedQuery(name="Visit.findAll", query="SELECT v FROM Visit v")
})
public class Visit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private short visitid;

	private String firstname;

	private Timestamp visited;

	public Visit() {
	}

	public short getVisitid() {
		return this.visitid;
	}

	public void setVisitid(short visitid) {
		this.visitid = visitid;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Timestamp getVisited() {
		return this.visited;
	}

	public void setVisited(Timestamp visited) {
		this.visited = visited;
	}

}