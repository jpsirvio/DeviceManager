package fi.laiterekisteri.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "locations")
@SQLDelete(sql = "UPDATE locations SET deleted = true WHERE location_id=?")
@Where(clause = "deleted=false")
public class Location {

// Properties
	// locId is generated for each location
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "location_id")
	private Long locationId;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private List<Device> devices;
	
	@Size( max= 100, message = "value is not valid")
	private String office, address, department, unit, room;
	
	@Size( max = 900, message = "note is too long")
	private String notes;
	
	//boolean required
	private boolean deleted;
	
// Constructors
	public Location() {}
	
	public Location(String office, String address, String department, String unit, String room, String notes, boolean deleted) {
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
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
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
