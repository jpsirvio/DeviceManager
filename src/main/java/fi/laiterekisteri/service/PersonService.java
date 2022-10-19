package fi.laiterekisteri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.laiterekisteri.domain.Person;
import fi.laiterekisteri.domain.PersonRepository;

@Service
public class PersonService {
	 @Autowired
	 private PersonRepository prepo;
	 
	 // Get the List of Persons
	 public List<Person> getAllPersons(){
		 List<Person> personlist = (List<Person>)prepo.findAll();
		 return personlist;
	 }
	 
	 //Get Persons By keyword
	 public List<Person> getByKeyword(String keyword){
		 return prepo.findByKeyword(keyword);
	 }
}
