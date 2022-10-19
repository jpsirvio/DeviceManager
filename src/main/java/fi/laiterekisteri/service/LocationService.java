package fi.laiterekisteri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.laiterekisteri.domain.Location;
import fi.laiterekisteri.domain.LocationRepository;

@Service
public class LocationService {
	 @Autowired
	 private LocationRepository lrepo;
	 
	 // Get the List of Locations
	 public List<Location> getAllLocations(){
		 List<Location> locationlist = (List<Location>)lrepo.findAll();
		 return locationlist;
	 }
	 
	 //Get Locations By keyword
	 public List<Location> getByKeyword(String keyword){
		 return lrepo.findByKeyword(keyword);
	 }
}
