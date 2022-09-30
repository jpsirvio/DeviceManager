package fi.laiterekisteri.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Entity
public class Device {
	
// Properties
	// deviceId is generated for each device
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long deviceId;
	
	@ManyToOne
	@JoinColumn(name = "personId")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "locationId")
	private Location location;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	//@Max( value= 50, message = "value is not valid")
	private String product, model, serialnumber;
		
	//@Max(value = 900, message = "note is too long")
	private String notes;
	
	//boolean required
	private int deleted;
	
// Constructors
	public Device() {}
	
	public Device(Person person, Location location, Category category, String product, String model,
			String serialnumber, String notes, int deleted) {
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
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public String toString() {
		/*
		 * return "Device [deviceId=" + deviceId + ", person=" + person + ", location="
		 * + location + ", category=" + category + ", product=" + product + ", model=" +
		 * model + ", serialnumber=" + serialnumber + ", notes=" + notes + ", deleted="
		 * + deleted + "]";
		 */
		return "Device [deviceId=" + deviceId + ", product=" + product + ", model=" + model + ", serialnumber=" + serialnumber + ", notes="
		+ notes + ", deleted=" + deleted + "]";
	}	
	
}
