package fi.laiterekisteri.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.laiterekisteri.domain.Location;
import fi.laiterekisteri.domain.LocationRepository;

@Controller
public class LocationController {

	@Autowired
	private LocationRepository lrepo;
	
	// List all Location
	@GetMapping("/locations")
	public String list(Model model) {
		model.addAttribute("locations", lrepo.findAll());
		return "locations";
	}
	
    // Add a new Location
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/locations/addlocation"})
    public String addLocation(Model model) {	
        model.addAttribute("location", new Location());
        return "addlocation";
    }
    
    // Save a new added Location
    @RequestMapping(value = "/locations/savelocation", method = RequestMethod.POST)
    public String save(Location location){
        lrepo.save(location);
        return "redirect:/locations";
    }
    
    // Delete Location
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/locations/delete/{id}", method = RequestMethod.GET)
    public String deleteLocation(@PathVariable("id") Long locationId, Model model) {
    	lrepo.deleteById(locationId);
        return "redirect:/locations";
    }
    
    // Edit Location
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/locations/editlocation/{locationId}"}, method = RequestMethod.GET)
    public String editLocations(@PathVariable("locationId") Long locationId, Model model) {	
        model.addAttribute("editLocation", lrepo.findById(locationId));
        return "editlocation";
    }
    
}
