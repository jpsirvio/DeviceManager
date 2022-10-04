package fi.laiterekisteri.web;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.laiterekisteri.domain.Device;
import fi.laiterekisteri.domain.LocationRepository;
import fi.laiterekisteri.domain.Person;
import fi.laiterekisteri.domain.PersonRepository;

@Controller
public class PersonController {
	
	@Autowired
	private PersonRepository prepo;
	@Autowired
	private LocationRepository lrepo;
    @Autowired
    private EntityManager entityManager;
    
	// List all Persons
	@GetMapping("/persons")
	public String list(Model model) {
		model.addAttribute("persons", prepo.findAll());
		model.addAttribute("locations", lrepo.findAll());
		return "persons";
	}
	
    // Add a new Person
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/persons/addperson"})
    public String addPerson(Model model) {	
        model.addAttribute("person", new Person());
        return "addperson";
    }
    
    // Save a new added Person
    @RequestMapping(value = "/persons/saveperson", method = RequestMethod.POST)
    public String save(Person person){
        prepo.save(person);
        return "redirect:/persons";
    }
    
    // Delete Person
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/persons/delete/{id}", method = RequestMethod.GET)
    public String deletePerson(@PathVariable("id") Long personId, Model model) {
    	// must be changed to setDeletedById -> set device.deleted to 1
    	prepo.deleteById(personId);
        return "redirect:/persons";
    }
    
    // Edit Person
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/persons/editperson/{personId}"}, method = RequestMethod.GET)
    public String editPerson(@PathVariable("personId") Long personId, Model model) {	
        model.addAttribute("editPerson", prepo.findById(personId));
        model.addAttribute("locations", lrepo.findAll());
        return "editperson";
    }
}
