package fi.laiterekisteri.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.laiterekisteri.domain.CategoryRepository;
import fi.laiterekisteri.domain.Device;
import fi.laiterekisteri.domain.DeviceRepository;
import fi.laiterekisteri.domain.LocationRepository;
import fi.laiterekisteri.domain.PersonRepository;

@Controller
public class DeviceController {
	
	@Autowired
	private PersonRepository prepo;
	@Autowired
	private DeviceRepository drepo;
	@Autowired
	private LocationRepository lrepo;
	@Autowired
	private CategoryRepository crepo;
	
	// Frontpage, show all persons, devices, locations
	@GetMapping({"/","/index"})
	public String list(Model model) {
		model.addAttribute("persons", prepo.findAll());
		model.addAttribute("devices", drepo.findAll());
		model.addAttribute("locations", lrepo.findAll());
		model.addAttribute("categories", crepo.findAll());
		return "index";
	}
	
    // Add a new device
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/adddevice"})
    public String addDevice(Model model) {	
        model.addAttribute("device", new Device());
        model.addAttribute("categories", crepo.findAll());
        model.addAttribute("persons", prepo.findAll());
        model.addAttribute("locations", lrepo.findAll());
        return "adddevice";
    }
    // Save a new added device
    @RequestMapping(value = "/savedevice", method = RequestMethod.POST)
    public String save(Device device){
        drepo.save(device);
        return "redirect:index";
    }
    
    // Delete device
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/deletedevice/{id}", method = RequestMethod.GET)
    public String deleteDevice(@PathVariable("id") Long deviceId, Model model) {
    	// must be changed to setDeletedById -> set device.deleted to 1
    	drepo.deleteById(deviceId);
        return "redirect:/";
    }     
    
    // Edit device
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/editdevice/{deviceId}"}, method = RequestMethod.GET)
    public String editDevice(@PathVariable("deviceId") Long deviceId, Model model) {	
        model.addAttribute("editDevice", drepo.findById(deviceId));
        model.addAttribute("categories", crepo.findAll());
        model.addAttribute("persons", prepo.findAll());
        model.addAttribute("locations", lrepo.findAll());
        return "editdevice";
    }
    
}