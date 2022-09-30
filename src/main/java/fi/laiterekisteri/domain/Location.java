package fi.laiterekisteri.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Location {

// Properties
	// locId is generated for each location
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long locationId;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private List<Device> devices;
	
	//@Max( value= 100, message = "value is not valid")
	private String office, address, department, unit, room;
	
	//@Max(value = 900, message = "note is too long")
	private String notes;
	
	//boolean required
	private int deleted;
	
// Constructors
	public Location() {}
	
	public Location(String office, String address, String department, String unit, String room, String notes, int deleted) {
		this.office = office;
		this.address = address;
		this.department = department;
		this.unit = unit;
		this.room = room;
		this.notes = notes;
		this.deleted = deleted;
		
// Getters and Setters and toString
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", office=" + office + ", address=" + address + ", department="
				+ department + ", unit=" + unit + ", room=" + room + ", notes=" + notes + ", deleted=" + deleted + "]";
	}
	
	

}
