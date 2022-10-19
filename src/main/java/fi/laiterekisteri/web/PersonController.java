package fi.laiterekisteri.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.laiterekisteri.domain.Person;
import fi.laiterekisteri.domain.PersonRepository;
import fi.laiterekisteri.service.PersonService;

@Controller
public class PersonController {
	
	private static final Logger log = LoggerFactory.getLogger(Person.class);
	
	@Autowired
	private PersonRepository prepo;
	@Autowired
	private PersonService pservice;
    
	/*
	 * Old version
	// List all Persons
	@GetMapping("/persons")
	public String list(Model model) {
		model.addAttribute("persons", prepo.findAll());
		return "persons";
	}
	*/
	
    // List and search persons
    @GetMapping(value = {"/persons","/persons/search"})
    public String listpersons(Person person, Model model, String keyword) {
		model.addAttribute("persons", prepo.findAll());
		if(keyword!=null) {
			List<Person> personlist = pservice.getByKeyword(keyword);
			model.addAttribute("personlist", personlist );
	    } else {
	    	List<Person> personlist = pservice.getAllPersons();
	    	model.addAttribute("personlist", personlist);}
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
    // checks if username is already used
    // /persons/addpersons -> /persons/savenewperson
    @PostMapping("/persons/savenewperson")
    public String save(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult){
    	log.info("saveperson: newperson = " + person.toString());
    	if (!bindingResult.hasErrors()) {
        	String username = person.getUsername();
        	if (prepo.findByUsername(username) == null) {
            	prepo.save(person);
                return "redirect:/persons";
        	} else {
        		log.info(LocalDateTime.now() + ": username already exists");
        		bindingResult.rejectValue("username", "err.username", "Username already exists");
        		return "addperson";
        	}     	
        } else {
        	log.info(LocalDateTime.now() + ": Error: Saving person failed.");
        	return "addperson";
        }
    }
    
    // Edit Person
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/persons/editperson/{personId}")
    public String editPerson(@PathVariable("personId") Long personId, Model model) {	
        model.addAttribute("editPerson", prepo.findById(personId));
        return "editperson";
    }
    
    // Save a edited Person 
    // /persons/editperson -> /persons/saveeditedperson
    // validation works, but does not give a message of failure in the UI!
    @PostMapping("/persons/saveeditedperson")
    public String save(@Valid Person person, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
        	log.info(LocalDateTime.now() + ": error saving Person");
        	return "redirect:/persons";
        }
    	prepo.save(person);
        return "redirect:/persons";
    }
    
    // Soft Delete person
    // See SQLDelete statement in class object
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/persons/delete/{id}", method = RequestMethod.GET)
    public String deletePerson(@PathVariable("id") Long personId, Model model) {
    	// must be changed to setDeletedById -> set device.deleted to 1
    	prepo.deleteById(personId);
        return "redirect:/persons";
    }
    

}
