package fi.laiterekisteri.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "devices")
@SQLDelete(sql = "UPDATE devices SET deleted = true WHERE device_id=?")
@Where(clause = "deleted=false")
public class Device {
	
// Properties
	// deviceId is generated for each device
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long deviceId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "personId")
	private Person person;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "locationId")
	private Location location;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	private Category category;
	
	//@Max( value= 50, message = "value is not valid")
	private String product, model, serialnumber;
		
	//@Max(value = 900, message = "note is too long")
	private String notes;
	
	//boolean required
	private boolean deleted = Boolean.FALSE;
	
	// created, edited deleted dates
	private Date dateCreated, dateEdited, dateDeleted;
	
// Constructors
	public Device() {}
	
	public Device(Person person, Location location, Category category, String product, String model,
			String serialnumber, String notes, boolean deleted) {
		super();
		this.person = person;
		this.location = location;
		this.category = category;
		this.product = product;
		this.model = model;
		this.serialnumber = serialnumber;
		this.notes = notes;
		this.deleted = deleted;
	}
	
// Getters and Setters and toString
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateEdited() {
		return dateEdited;
	}
	public void setDateEdited(Date dateEdited) {
		this.dateEdited = dateEdited;
	}
	public Date getDateDelted() {
		return dateDeleted;
	}
	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}
	
	
	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", person=" + person + ", location=" + location + ", category="
				+ category + ", product=" + product + ", model=" + model + ", serialnumber=" + serialnumber + ", notes="
				+ notes + ", deleted=" + deleted + ", dateCreated=" + dateCreated + ", dateEdited=" + dateEdited
				+ ", dateDeleted=" + dateDeleted + "]";
	}	
	
}
