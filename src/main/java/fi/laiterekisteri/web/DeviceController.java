package fi.laiterekisteri.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.laiterekisteri.domain.CategoryRepository;
import fi.laiterekisteri.domain.Device;
import fi.laiterekisteri.domain.DeviceRepository;
import fi.laiterekisteri.domain.LocationRepository;
import fi.laiterekisteri.domain.PersonRepository;
import fi.laiterekisteri.service.DeviceService;

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
	@Autowired
	private DeviceService dservice;
    
	/*// needed to find soft deleted items
	@Autowired
    private EntityManager entityManager;
    */
	
	/*
	 * Old version
	// Frontpage, show all persons, devices, locations
	@GetMapping({"/","/index"})
	public String list(Model model) {
		model.addAttribute("persons", prepo.findAll());
		model.addAttribute("devices", drepo.findAll());
		model.addAttribute("locations", lrepo.findAll());
		model.addAttribute("categories", crepo.findAll());
		return "index";
	}
	*/
	
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
    public String save(@Valid Device device, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
        	System.out.println("error saving Device " + LocalDateTime.now());
        	return "/adddevice";
        }
        drepo.save(device);
        return "redirect:/";
    }
    
    // Soft Delete device
    // See SQLDelete statement in class object
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/deletedevice/{id}", method = RequestMethod.GET)
    public String deleteDevice(@PathVariable("id") Long deviceId, Model model) {
    	drepo.deleteById(deviceId);
        return "redirect:/";
    }
    
    // Find soft deleted Device -> requires Filter() and FilterDef() in Device.java instead of Where()
    /*public Iterable<Device> findAll(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
    	Filter filter = session.enableFilter("deletedDeviceFilter");
    	filter.setParameter("isDeleted", isDeleted);
    	Iterable<Device> devices = drepo.findAll();
    	session.disableFilter("deletedDeviceFilter");
    	return devices;
    }*/

    // Edit Device
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/editdevice/{deviceId}"}, method = RequestMethod.GET)
    public String editDevice(@PathVariable("deviceId") Long deviceId, Model model) {	
        model.addAttribute("editDevice", drepo.findById(deviceId));
        model.addAttribute("categories", crepo.findAll());
        model.addAttribute("persons", prepo.findAll());
        model.addAttribute("locations", lrepo.findAll());
        return "editdevice";
    }
    
    // List and search devices
    @GetMapping(value = {"/","/search"})
    public String listdevices(Device device, Model model, String keyword) {
		model.addAttribute("persons", prepo.findAll());
		model.addAttribute("devices", drepo.findAll());
		model.addAttribute("locations", lrepo.findAll());
		model.addAttribute("categories", crepo.findAll());
		if(keyword!=null) {
			List<Device> devicelist = dservice.getByKeyword(keyword);
			model.addAttribute("devicelist", devicelist );
	    } else {
	    	List<Device> devicelist = dservice.getAllDevices();
	    	model.addAttribute("devicelist", devicelist);}
		return "index";
    }
}
