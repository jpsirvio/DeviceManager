package fi.laiterekisteri.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fi.laiterekisteri.domain.Location;
import fi.laiterekisteri.domain.LocationRepository;

@RestController
public class RestLocationController {
		
	@Autowired
	private LocationRepository lrepo;
	
	// REST LOCATION
	// REST List all locations
	@GetMapping("/locations/api")
	public Iterable<Location> getLocation() {
		return lrepo.findAll();
	}
	
	// REST Find a location by id
	@GetMapping("/locations/api/{id}")
	public Optional<Location> findLocationRest(@PathVariable("id") Long locationId) {
		return lrepo.findById(locationId);
	}
	
	// REST Add a Location
	@PostMapping("locations/api")
	Location newLocation(@RequestBody Location newLocation) {
		return lrepo.save(newLocation);
	}
	
	// REST Update Location
	@PutMapping("/locations/api/{id}")
	Location editLocation(@RequestBody Location editLocation, @PathVariable Long locationId) {
		editLocation.setLocationId(locationId);
		return lrepo.save(editLocation);
	}
}
