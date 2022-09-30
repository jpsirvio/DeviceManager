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
public class Category {
	
// Properties
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryId;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="category")
	private List<Device> devices;
	
	//@Max( value= 50, message = "value is not valid")
	private String cname;
	
	//@Max(value = 900, message = "note is too long")
	private String notes;
	
	//boolean required
	private int deleted;
	
// Constructors
	public Category() {}

	public Category(String cname, String notes, int deleted) {
		this.cname = cname;
		this.notes = notes;
		this.deleted = deleted;
	}

// Getters and Setters and toString
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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
		return "Category [categoryId=" + categoryId + ", cname=" + cname + ", notes=" + notes + ", deleted="
				+ deleted + "]";
	}
	
}