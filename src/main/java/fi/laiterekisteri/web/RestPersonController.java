package fi.laiterekisteri.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fi.laiterekisteri.domain.Person;
import fi.laiterekisteri.domain.PersonRepository;

@RestController
public class RestPersonController {
	
	@Autowired
	private PersonRepository prepo;
	
	// REST PERSON
	// REST List all persons
	@GetMapping("/persons/api")
	public Iterable<Person> getPersons() {
		return prepo.findAll();
	}
	
	// REST Find a person by id
	@GetMapping("/persons/api/{id}")
	public Optional<Person> findPersonRest(@PathVariable("id") Long personId) {
		return prepo.findById(personId);
	}
	
	// REST Add a person
	@PostMapping("persons/api")
	Person newPerson(@RequestBody Person newPerson) {
		return prepo.save(newPerson);
	}
	
	// REST Update person
	@PutMapping("/persons/api/{id}")
	Person editPerson(@RequestBody Person editPerson, @PathVariable Long personId) {
		editPerson.setPersonId(personId);
		return prepo.save(editPerson);
	}
}
