package fi.laiterekisteri.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.laiterekisteri.domain.Category;
import fi.laiterekisteri.domain.CategoryRepository;
import fi.laiterekisteri.domain.Device;
import fi.laiterekisteri.domain.DeviceRepository;
import fi.laiterekisteri.domain.Location;
import fi.laiterekisteri.domain.LocationRepository;
import fi.laiterekisteri.domain.Person;
import fi.laiterekisteri.domain.PersonRepository;

//deprecated -> move and delete

//@Controller
public class DeprecatedLaiterekisteriController {

	@Autowired
	private PersonRepository prepo;
	@Autowired
	private DeviceRepository drepo;
	@Autowired
	private LocationRepository lrepo;
	@Autowired
	private CategoryRepository crepo;
	
    // Add a new location
    @RequestMapping(value= {"/addlocation"})
    public String addLocation(Model model) {	
        model.addAttribute("location", new Location());
        return "addlocation";
    }
    // Save a new added location
    @RequestMapping(value = "/savelocation", method = RequestMethod.POST)
    public String save(Location location){
        lrepo.save(location);
        return "redirect:/";
    }
    // Add a new category
    @RequestMapping(value= {"/addcategory"})
    public String addCategory(Model model) {	
        model.addAttribute("category", new Category());
        return "addcategory";
   }
    // Save a new added category
    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String save(Category category){
        crepo.save(category);
        return "redirect:/";
    }

    

	
    // TBA:
	// add save device, location, person, category
	// delete device
    // delete location, person, category
	// edit device
    // edit location person category
	// search
    // sign in / spring security
	
}
