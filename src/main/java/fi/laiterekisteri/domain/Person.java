package fi.laiterekisteri.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Person {

// Properties
	// personId is generated for each location
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long personId;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
	private List<Device> devices;

	//@Size(min = 5, max = 10, message = "username is not valid")
	private String username;
	
	//@Size(min = 1, max = 100, message = "value is not valid")
	private String email, firstName, lastName;
	
	//@Max(value = 900, message = "note is too long")
	private String notes;
	
	//@Size(min = 8, max = 100, message = "password must be min 8 and max 100 characters")
	private String passwordHash;
	
	private String role; 
	
	//boolean required
	private int admin, deleted;
	
// Constructors
	public Person() {}
	
	public Person(String userName, String email, String firstName, String lastName, String notes, String passwordHash, String role, int admin, int deleted) {
		this.username = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.notes = notes;
		this.passwordHash = passwordHash;
		this.role = role;
		this.admin = admin;
		this.deleted = deleted;
	}

// Setters and Getters and toString
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	public String getUsername() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "Person [personId=" + personId + ", username=" + username + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", notes=" + notes + ", role=" + role + ", admin=" + admin + ", deleted=" + deleted + "]";
	}
	
	
}
